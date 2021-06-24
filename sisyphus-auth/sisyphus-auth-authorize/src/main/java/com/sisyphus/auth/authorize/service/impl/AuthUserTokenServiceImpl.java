package com.sisyphus.auth.authorize.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.common.support.util.RedisKeyUtil;
import com.sisyphus.common.support.util.RequestUtil;
import com.sisyphus.auth.authorize.mapper.AuthUserTokenMapper;
import com.sisyphus.auth.authorize.model.domain.AuthUserToken;
import com.sisyphus.auth.authorize.model.dto.AuthUserDTO;
import com.sisyphus.common.base.dto.AuthUserTokenDTO;
import com.sisyphus.auth.authorize.model.enums.UserTokenStatusEnum;
import com.sisyphus.auth.authorize.service.AuthUserService;
import com.sisyphus.auth.authorize.service.AuthUserTokenService;
import com.sisyphus.auth.core.properties.OAuth2ClientProperties;
import com.sisyphus.auth.core.properties.SecurityProperties;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 14:18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthUserTokenServiceImpl extends ServiceImpl<AuthUserTokenMapper, AuthUserToken> implements AuthUserTokenService {

    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthUserTokenMapper authUserTokenMapper;
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    @Resource
    private SecurityProperties securityProperties;
    @Value("${sisyphus.auth.refresh-token-url}")
    private String refreshTokenUrl;

    @Override
    public AuthUserTokenDTO getByAccessToken(String accessToken) {
        AuthUserTokenDTO userTokenDto = (AuthUserTokenDTO) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(accessToken));
        if (userTokenDto == null) {
            AuthUserToken uacUserToken = new AuthUserToken();
            uacUserToken.setAccessToken(accessToken);
            uacUserToken = authUserTokenMapper.selectOne(new QueryWrapper<AuthUserToken>().
                    eq("access_token", accessToken));
            userTokenDto = new ModelMapper().map(uacUserToken, AuthUserTokenDTO.class);
        }
        return userTokenDto;
    }

    @Override
    public void updateUacUserToken(AuthUserTokenDTO userTokenDto, LoginAuthDTO loginAuthDTO) {
        AuthUserToken authUserToken = new ModelMapper().map(userTokenDto, AuthUserToken.class);
        authUserToken.setUpdateInfo(loginAuthDTO);
        authUserTokenMapper.updateById(authUserToken);
        OAuth2ClientProperties[] clients = securityProperties.getOauth2().getClients();
        int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
        updateRedisUserToken(authUserToken.getAccessToken(), accessTokenValidateSeconds, userTokenDto);
    }

    @Override
    public void saveUserToken(String accessToken, String refreshToken, LoginAuthDTO loginAuthDto, HttpServletRequest request) {
        // 获取登录时间
        Long userId = loginAuthDto.getUserId();
        AuthUserDTO authUser = authUserService.findUserInfoByUserId(userId);
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtil.getRemoteAddr(request);
        // 根据IP获取位置信息
//        final String remoteLocation = opcRpcService.getLocationById(remoteAddr);
        final String remoteLocation = "";

        // 存入mysql数据库
        AuthUserToken uacUserToken = new AuthUserToken();
        OAuth2ClientProperties[] clients = securityProperties.getOauth2().getClients();
        int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
        int refreshTokenValiditySeconds = clients[0].getRefreshTokenValiditySeconds();
        uacUserToken.setOs(os);
        uacUserToken.setBrowser(browser);
        uacUserToken.setAccessToken(accessToken);
        uacUserToken.setAccessTokenValidity(accessTokenValidateSeconds);
        uacUserToken.setLoginIp(remoteAddr);
        uacUserToken.setLoginLocation(remoteLocation);
        uacUserToken.setLoginTime(authUser.getLastLoginTime());
        uacUserToken.setLoginName(loginAuthDto.getLoginName());
        uacUserToken.setRefreshToken(refreshToken);
        uacUserToken.setRefreshTokenValidity(refreshTokenValiditySeconds);
        uacUserToken.setStatus(UserTokenStatusEnum.ON_LINE.getStatus());
        uacUserToken.setUserId(userId);
        uacUserToken.setUserName(loginAuthDto.getUserName());
        uacUserToken.setUpdateInfo(loginAuthDto);
        uacUserToken.setGroupId(loginAuthDto.getGroupId());
        uacUserToken.setGroupName(loginAuthDto.getGroupName());
        authUserTokenMapper.insert(uacUserToken);
        AuthUserTokenDTO userTokenDto = new ModelMapper().map(uacUserToken, AuthUserTokenDTO.class);
        // 存入redis数据库
        updateRedisUserToken(accessToken, accessTokenValidateSeconds, userTokenDto);
    }

    @Override
    public String refreshToken(String accessToken, String refreshToken, HttpServletRequest request) throws HttpProcessException {
        String token;
        Map<String, Object> map = new HashMap<>(2);
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        //插件式配置请求参数（网址、请求参数、编码、client）
        Header[] headers = HttpHeader.custom().contentType(HttpHeader.Headers.APP_FORM_URLENCODED).authorization(request.getHeader(HttpHeaders.AUTHORIZATION)).build();
        HttpConfig config = HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);
        token = HttpClientUtil.post(config);
        JSONObject jsonObj = JSON.parseObject(token);
        String accessTokenNew = (String) jsonObj.get("access_token");
        String refreshTokenNew = (String) jsonObj.get("refresh_token");
        String loginName = (String) jsonObj.get("loginName");
        // 更新本次token数据
        AuthUserTokenDTO tokenDto = this.getByAccessToken(accessToken);
        tokenDto.setStatus(UserTokenStatusEnum.ON_REFRESH.getStatus());
        AuthUserDTO authUser = authUserService.findByLoginName(loginName);

        LoginAuthDTO loginAuthDTO =
                new LoginAuthDTO(authUser.getId(), authUser.getLoginName(),
                        authUser.getUserName(), authUser.getGroupId(),
                        authUser.getGroupName(), "email", "avatar", "phone", 1, 1L);
        this.updateUacUserToken(tokenDto, loginAuthDTO);
        // 创建刷新token
        this.saveUserToken(accessTokenNew, refreshTokenNew, loginAuthDTO, request);
        return token;
    }

    private void updateRedisUserToken(String accessToken, int accessTokenValidateSeconds, AuthUserTokenDTO authUserTokenDTO) {
        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), authUserTokenDTO, accessTokenValidateSeconds, TimeUnit.SECONDS);
    }

}

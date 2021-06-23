package com.sisyphus.auth.authorize.service.impl;

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
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private SecurityProperties securityProperties;

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
        uacUserToken.setUserName(loginAuthDto.getNickName());
        uacUserToken.setUpdateInfo(loginAuthDto);
        uacUserToken.setGroupId(loginAuthDto.getGroupId());
        uacUserToken.setGroupName(loginAuthDto.getGroupName());
//        uacUserToken.setId(generateId());
        authUserTokenMapper.insert(uacUserToken);
        AuthUserTokenDTO userTokenDto = new ModelMapper().map(uacUserToken, AuthUserTokenDTO.class);
        // 存入redis数据库
        updateRedisUserToken(accessToken, accessTokenValidateSeconds, userTokenDto);
    }

    private void updateRedisUserToken(String accessToken, int accessTokenValidateSeconds, AuthUserTokenDTO authUserTokenDTO) {
        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), authUserTokenDTO, accessTokenValidateSeconds, TimeUnit.SECONDS);
    }

}

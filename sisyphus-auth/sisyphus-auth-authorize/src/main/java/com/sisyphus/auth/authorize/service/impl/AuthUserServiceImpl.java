package com.sisyphus.auth.authorize.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sisyphus.auth.authorize.model.dto.AuthRoleDTO;
import com.sisyphus.auth.authorize.model.vo.AuthMenuVO;
import com.sisyphus.auth.authorize.service.*;
import com.sisyphus.common.base.enums.ErrorCodeEnum;
import com.sisyphus.common.base.exception.BizException;
import com.sisyphus.common.support.enums.LogTypeEnum;
import com.sisyphus.common.support.util.ObjectUtil;
import com.sisyphus.common.support.util.RequestUtil;
import com.sisyphus.auth.authorize.mapper.AuthUserMapper;
import com.sisyphus.auth.authorize.model.SecurityUser;
import com.sisyphus.auth.authorize.model.domain.AuthAction;
import com.sisyphus.auth.authorize.model.domain.AuthUser;
import com.sisyphus.auth.authorize.model.dto.AuthUserDTO;
import com.sisyphus.auth.authorize.model.dto.LoginRespDTO;
import com.sisyphus.auth.authorize.uitls.SecurityUtils;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.provider.uac.api.model.dto.UacLogDTO;
import com.sisyphus.provider.uac.api.service.UacLogDubboApi;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

    @Resource
    private AuthMenuService authMenuService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthUserTokenService authUserTokenService;
    @Resource
    private AuthActionService authActionService;
    @Resource
    private AuthUserMapper authUserMapper;
    @Resource
    private TaskExecutor taskExecutor;
    @Reference(check = false)
    private UacLogDubboApi uacLogDubboApi;

    @Override
    public AuthUserDTO findByPhone(String phone) {
        return authUserMapper.findByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AuthUserDTO findByLoginName(String loginName) {
        return authUserMapper.findByLoginName(loginName);
    }

    @Override
    public AuthUserDTO findUserInfoByUserId(Long userId) {
        return authUserMapper.findUserInfoByUserId(userId);
    }

    @Override
    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        List<AuthAction> ownAuthList = authActionService.getOwnActionListByUserId(userId);
        List<GrantedAuthority> authList = Lists.newArrayList();
        for (AuthAction action : ownAuthList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(action.getActionCode());
            authList.add(grantedAuthority);
        }
        return authList;
    }

    @Override
    public LoginRespDTO loginResp(Long tenantId) {
        String loginName = SecurityUtils.getCurrentLoginName();
        if (StringUtils.isEmpty(loginName)) {
            log.error("操作超时, 请重新登录 loginName={}", loginName);
            Preconditions.checkArgument(StringUtils.isNotEmpty(loginName), "操作超时, 请重新登录");
        }
        AuthUserDTO authUserDTO = this.findByLoginName(loginName);
        if (ObjectUtil.isEmpty(authUserDTO)) {
            log.info("找不到用户信息 loginName={}", loginName);
            throw new BizException(ErrorCodeEnum.UAC10011002, loginName);
        }
        LoginAuthDTO loginAuthDTO = new ModelMapper().map(authUserDTO, LoginAuthDTO.class);

        List<AuthMenuVO> authMenus = null;
        List<AuthRoleDTO> authRoles = null;

        authRoles = authRoleService.findByUserId(authUserDTO.getId());
        if (ObjectUtil.isEmpty(authUserDTO)) {
            log.info("找不到用户角色 userId={}", authUserDTO.getId());
        }else{
            authMenus = authMenuService.findMenuTreeByRoles(loginAuthDTO.getUserId(), authRoles);
        }
        LoginRespDTO loginRespDTO = new LoginRespDTO(loginAuthDTO, authMenus, authRoles);
        return loginRespDTO;
    }

    @Override
    public void handlerLoginData(OAuth2AccessToken token, SecurityUser principal, HttpServletRequest request) {

        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtil.getRemoteAddr(request);
        // 根据IP获取位置信息
//        final String remoteLocation = opcRpcService.getLocationById(remoteAddr);
        final String remoteLocation = "";
        final String requestURI = request.getRequestURI();

        AuthUser uacUser = new AuthUser();
        Long userId = principal.getUserId();
        uacUser.setLastLoginIp(remoteAddr);
        uacUser.setId(userId);
        uacUser.setLastLoginTime(new Date());
        uacUser.setLastLoginLocation(remoteLocation);

        LoginAuthDTO loginAuthDto =
                new LoginAuthDTO(userId, principal.getLoginName(),
                        principal.getUsername(), principal.getGroupId(),
                        principal.getGroupName(), "email", "avatar", "phone", 1, principal.getTenantId());
        // 记录token日志
        String accessToken = token.getValue();
        String refreshToken = token.getRefreshToken().getValue();
        authUserTokenService.saveUserToken(accessToken, refreshToken, loginAuthDto, request);
        // 记录最后登录信息
        taskExecutor.execute(() -> this.updateUser(uacUser));

        // 记录操作日志
        UacLogDTO log = new UacLogDTO();
        log.setGroupId(principal.getGroupId());
        log.setGroupName(principal.getGroupName());
        log.setIp(remoteAddr);
        log.setLocation(remoteLocation);
        log.setOs(os);
        log.setBrowser(browser);
        log.setRequestUrl(requestURI);
        log.setLogType(LogTypeEnum.LOGIN_LOG.getType());
        log.setLogName(LogTypeEnum.LOGIN_LOG.getName());
        taskExecutor.execute(() -> uacLogDubboApi.saveUacLog(log, loginAuthDto));
    }

    @Override
    public int updateUser(AuthUser authUser) {
        log.info("更新用户信息 uacUser={}", authUser);
        int updateResult = authUserMapper.updateById(authUser);
        if (updateResult < 1) {
            log.info("用户【 {} 】修改用户信息失败", authUser.getId());
        } else {
            log.info("用户【 {} 】修改用户信息成功", authUser.getId());
        }
        return updateResult;
    }
}

package com.sisyphus.auth.authorize.service;

import com.sisyphus.auth.authorize.model.SecurityUser;
import com.sisyphus.auth.authorize.model.domain.AuthUser;
import com.sisyphus.auth.authorize.model.dto.AuthUserDTO;
import com.sisyphus.auth.authorize.model.dto.LoginRespDTO;
import com.sisyphus.common.support.service.IService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:22
 */
public interface AuthUserService extends IService<AuthUser> {

    AuthUserDTO findByPhone(String phone);

    AuthUserDTO findByLoginName(String loginName);

    AuthUserDTO findUserInfoByUserId(Long userId);

    Collection<GrantedAuthority> loadUserAuthorities(Long userId);

    LoginRespDTO loginResp(Long applicationId);

    int updateUser(AuthUser authUser);

    /**
     * 处理登录数据
     * @param accessToken
     * @param principal
     * @param request
     */
    void handlerLoginData(OAuth2AccessToken accessToken, SecurityUser principal, HttpServletRequest request);
}

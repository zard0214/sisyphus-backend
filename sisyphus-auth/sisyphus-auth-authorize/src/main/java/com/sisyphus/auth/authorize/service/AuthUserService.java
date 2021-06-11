package com.sisyphus.auth.authorize.service;

import com.sisyphus.auth.authorize.model.domain.AuthUser;
import com.sisyphus.auth.authorize.model.dto.AuthUserDTO;
import com.sisyphus.auth.authorize.model.dto.LoginRespDTO;
import coms.sisphus.common.support.service.IService;
import org.springframework.security.core.GrantedAuthority;

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
}

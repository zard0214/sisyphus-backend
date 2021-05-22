package com.sisyphus.auth.service;

import com.sisyphus.auth.model.domain.AuthUser;
import com.sisyphus.auth.model.dto.AuthUserDTO;
import coms.sisphus.common.support.service.IService;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:22
 */
public interface AuthUserService extends IService<AuthUser> {

    AuthUserDTO findByLoginName(String username);

    AuthUserDTO findUserInfoByUserId(Long userId);

    Collection<GrantedAuthority> loadUserAuthorities(Long id);
}

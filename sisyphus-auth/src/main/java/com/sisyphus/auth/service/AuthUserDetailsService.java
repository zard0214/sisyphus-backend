package com.sisyphus.auth.service;

import com.sisyphus.auth.model.SecurityUser;
import com.sisyphus.auth.model.dto.AuthUserDTO;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:20
 */
@Component
public class AuthUserDetailsService implements UserDetailsService {

    @Resource
    private AuthUserService authUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> grantedAuthorities;
        AuthUserDTO user = authUserService.findByLoginName(username);
        if (null == user) {
            throw new BadCredentialsException("用户名不存在或者密码错误");
        }
        user = authUserService.findUserInfoByUserId(user.getId());
        grantedAuthorities = authUserService.loadUserAuthorities(user.getId());
        return new SecurityUser(grantedAuthorities, user.getId(), user.getUserName(), user.getLoginName(),
                user.getLoginPwd(), user.getStatus(), user.getTenantId(), user.getGroupId(), user.getGroupName(),
                user.getAuthRoleList(), user.getAuthActionList());
    }
}

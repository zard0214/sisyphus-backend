package com.sisyphus.auth.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 09:10
 */
public class AuthAuthenticationProcessingFilter extends BasicAuthenticationFilter {

    public AuthAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
}

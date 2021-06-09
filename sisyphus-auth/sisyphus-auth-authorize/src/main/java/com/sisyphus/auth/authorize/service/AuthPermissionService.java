package com.sisyphus.auth.authorize.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 09/06/2021 10:10
 */
public interface AuthPermissionService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

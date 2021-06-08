package com.sisyphus.auth.app.controller;


import com.sisyphus.auth.app.social.AppSignUpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 17:49
 */
@RestController
public class AppSecurityController {

    @Resource
    private ProviderSignInUtils providerSignInUtils;

    @Resource
    private AppSignUpUtils appSignUpUtils;

    @GetMapping(value = "/social/signUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ConnectionData signUp(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        ConnectionData connectionData = connection.createData();
        appSignUpUtils.saveConnection(new ServletWebRequest(request), connectionData);
        return connectionData;
    }
}


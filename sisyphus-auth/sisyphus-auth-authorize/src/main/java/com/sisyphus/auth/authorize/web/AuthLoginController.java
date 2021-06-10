package com.sisyphus.auth.authorize.web;

import com.sisyphus.auth.authorize.service.AuthUserService;
import com.sisyphus.auth.authorize.service.AuthUserTokenService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 14:16
 */
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthLoginController {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserTokenService authUserTokenService;
}

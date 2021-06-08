package com.sisyphus.auth.authorize.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 05:38
 */
public class CredentialExcetion extends AuthenticationException {

    public CredentialExcetion(String msg, Throwable t) {
        super(msg, t);
    }
}

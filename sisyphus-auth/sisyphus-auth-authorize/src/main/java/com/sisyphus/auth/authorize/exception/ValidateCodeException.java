package com.sisyphus.auth.authorize.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 05:37
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

package com.sisyphus.auth.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:40
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

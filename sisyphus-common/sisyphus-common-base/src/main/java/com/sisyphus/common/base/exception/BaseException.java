package com.sisyphus.common.base.exception;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 02:07
 */
public class BaseException extends RuntimeException{


    public BaseException() {
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

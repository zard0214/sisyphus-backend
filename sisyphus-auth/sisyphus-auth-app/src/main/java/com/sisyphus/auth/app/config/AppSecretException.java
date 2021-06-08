package com.sisyphus.auth.app.config;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 17:49
 */
public class AppSecretException extends RuntimeException {

    public AppSecretException(String msg){
        super(msg);
    }
}

package com.sisyphus.auth.authorize.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhecheng.zhao
 * @date Created in 07/06/2021 18:15
 */
@Configuration
public class SecurityConfig {

    /*
        BCryptPasswordEncoder 通过加盐的方式
     */
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }

}

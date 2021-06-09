package com.sisyphus.auth.authorize.config;

import com.sisyphus.auth.core.authentication.AbstractChannelSecurityConfig;
import com.sisyphus.auth.core.authentication.FormAuthenticationConfig;
import com.sisyphus.auth.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.sisyphus.auth.core.authorize.AuthorizeConfigManager;
import com.sisyphus.auth.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 22:14
 */
@Configuration
public class AuthorizeSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private AccessDeniedHandler pcAuthenticationDeniedHandler;

    @Autowired
    protected AuthenticationSuccessHandler pcAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler pcAuthenticationFailureHandler;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Resource
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Resource
    private SpringSocialConfigurer pcSocialSecurityConfig;

    @Resource
    private AuthorizeConfigManager authorizeConfigManager;

    @Resource
    private FormAuthenticationConfig formAuthenticationConfig;

    @Resource
    private DataSource dataSource;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }

    /**
     * 记住我功能的token存取器配置
     *
     * @return the persistent token repository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true); // 第一次启动创建
        return tokenRepository;
    }

    /**
     * Configure.
     *
     * @param http the http
     *
     * @throws Exception the exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfig.configure(http);
        http.headers().frameOptions().disable();
        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(pcSocialSecurityConfig)
                .and()
//                .apply(openIdAuthenticationSecurityConfig)
//                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling().accessDeniedHandler(pcAuthenticationDeniedHandler)
                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }

}

package com.sisyphus.auth.app.config;

import com.sisyphus.auth.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.sisyphus.auth.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.sisyphus.auth.core.authorize.AuthorizeConfigManager;
import com.sisyphus.auth.core.properties.SecurityConstants;
import com.sisyphus.auth.core.properties.SecurityProperties;
import com.sisyphus.auth.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 17:55
 */
@Configuration
@EnableResourceServer
public class IResourcesServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfigs;

    @Resource
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Resource
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Resource
    private AuthenticationSuccessHandler iAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler iAuthenticationFailureHandler;

    @Resource
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Resource
    private AuthorizeConfigManager authorizeConfigManager;

    // 有三个configure的方法，这里使用http参数的
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 最简单的修改默认配置的方法
        // 在v5+中，该配置（表单登录）应该是默认配置了
        // basic登录（也就是弹框登录的）应该是v5-的版本默认

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(iAuthenticationSuccessHandler)
                .failureHandler(iAuthenticationFailureHandler)
        ;
        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfigs)
                .and()
                .apply(imoocSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf()
                .disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }
}


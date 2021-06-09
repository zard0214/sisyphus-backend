package com.sisyphus.auth.authorize.config;

import com.sisyphus.auth.core.authentication.AbstractChannelSecurityConfig;
import com.sisyphus.auth.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.sisyphus.auth.core.authorize.AuthorizeConfigManager;
import com.sisyphus.auth.core.properties.SecurityConstants;
import com.sisyphus.auth.core.properties.SecurityProperties;
import com.sisyphus.auth.core.properties.SessionProperties;
import com.sisyphus.auth.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 09/06/2021 23:31
 */
@Configuration
public class PcAuthenticationConfig extends AbstractChannelSecurityConfig {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private DataSource dataSource;

    @Resource
    private PersistentTokenRepository persistentTokenRepository;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfigs;

    @Resource
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Resource
    private SpringSocialConfigurer socialSecurityConfig;

    @Resource
    private InvalidSessionStrategy invalidSessionStrategy;

    @Resource
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;

    @Resource
    private AuthorizeConfigManager authorizeConfigManager;

    @Resource
    private AuthenticationSuccessHandler pcAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler pcAuthenticationFailureHandler;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(pcAuthenticationSuccessHandler)
                .failureHandler(pcAuthenticationFailureHandler)
        ;
        SessionProperties session = securityProperties.getBrowser().getSession();
        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfigs)
                .and()
                .apply(socialSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(session.getMaximumSessions()) //限制同一个用户只能有一个session登录
                .maxSessionsPreventsLogin(session.isMaxSessionsPreventsLogin())  // 当session达到最大后，阻止后登录的行为
                .expiredSessionStrategy(sessionInformationExpiredStrategy)  // 失效后的策略。定制型更高，失效前的请求还能拿到
                .and()
                .and()
                .logout()
//                .logoutUrl("/singout")  // 退出请求路径
                // 与logoutSuccessUrl互斥，有handler则logoutSuccessUrl失效
                // 通过处理器增加配置了页面则跳转到页面，没有则输出json
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf()
                .disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }
}

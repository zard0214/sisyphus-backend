//package com.sisyphus.auth.browser.config;
//
//import com.sisyphus.auth.core.authentication.AbstractChannelSecurityConfig;
//import com.sisyphus.auth.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
//import com.sisyphus.auth.core.authorize.AuthorizeConfigManager;
//import com.sisyphus.auth.core.properties.SecurityProperties;
//import com.sisyphus.auth.core.properties.SessionProperties;
//import com.sisyphus.auth.core.validate.code.ValidateCodeSecurityConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.security.web.session.InvalidSessionStrategy;
//import org.springframework.security.web.session.SessionInformationExpiredStrategy;
//import org.springframework.social.security.SpringSocialConfigurer;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
///**
// * @author zhecheng.zhao
// * @date Created in 08/06/2021 19:01
// */
////@Configuration
//public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
//
//    @Resource
//    private SecurityProperties securityProperties;
//
//    // 数据源是需要在使用处配置数据源的信息
//    @Resource
//    private DataSource dataSource;
//
//    @Resource
//    private PersistentTokenRepository persistentTokenRepository;
//
//    // 之前已经写好的 MyUserDetailsService
//    @Resource
//    private UserDetailsService userDetailsService;
//
//    // 由下面的  .apply(smsCodeAuthenticationSecurityConfigs)方法添加这个配置
//    @Resource
//    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfigs;
//
//    @Resource
//    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
//
//    @Resource
//    private SpringSocialConfigurer socialSecurityConfig;
//
//    @Resource
//    private InvalidSessionStrategy invalidSessionStrategy;
//
//    @Resource
//    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
//
//    @Resource
//    private LogoutSuccessHandler logoutSuccessHandler;
//
//    @Resource
//    private AuthorizeConfigManager authorizeConfigManager;
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        // org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer.tokenRepository
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        // 该对象里面有定义创建表的语句
//        // 可以设置让该类来创建表
//        // 但是该功能只用使用一次，如果数据库已经存在表则会报错
////        jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
//    }
//
//    // 有三个configure的方法，这里使用http参数的
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 最简单的修改默认配置的方法
//        // 在v5+中，该配置（表单登录）应该是默认配置了
//        // basic登录（也就是弹框登录的）应该是v5-的版本默认
//        applyPasswordAuthenticationConfig(http);
//        SessionProperties session = securityProperties.getBrowser().getSession();
//        http
//                .apply(validateCodeSecurityConfig)
//                .and()
//                .apply(smsCodeAuthenticationSecurityConfigs)
//                .and()
//                .apply(socialSecurityConfig)
//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository)
//                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//                .userDetailsService(userDetailsService)
//                .and()
//                .sessionManagement()
//                .invalidSessionStrategy(invalidSessionStrategy)
//                .maximumSessions(session.getMaximumSessions()) //限制同一个用户只能有一个session登录
//                .maxSessionsPreventsLogin(session.isMaxSessionsPreventsLogin())  // 当session达到最大后，阻止后登录的行为
//                .expiredSessionStrategy(sessionInformationExpiredStrategy)  // 失效后的策略。定制型更高，失效前的请求还能拿到
//                .and()
//                .and()
//                .logout()
////                .logoutUrl("/singout")  // 退出请求路径
//                // 与logoutSuccessUrl互斥，有handler则logoutSuccessUrl失效
//                // 通过处理器增加配置了页面则跳转到页面，没有则输出json
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .deleteCookies("JSESSIONID")
//                .and()
//                .csrf()
//                .disable();
//        authorizeConfigManager.config(http.authorizeRequests());
//    }
//}
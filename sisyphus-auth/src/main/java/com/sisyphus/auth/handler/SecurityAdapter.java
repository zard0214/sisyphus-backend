//package com.sisyphus.auth.handler;
//
//import com.sisyphus.auth.filter.AuthAuthenticationProcessingFilter;
//import com.sisyphus.auth.filter.AuthLoginAuthenticationProcessingFilter;
//import com.sisyphus.auth.model.constant.SecurityConstants;
//import com.sisyphus.auth.service.AuthUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.annotation.Resource;
//
///**
// *
// *  修改登录方式为post json
// *
// * @author zhecheng.zhao
// * @date Created in 22/05/2021 08:02
// */
//@Configuration
//public class SecurityAdapter extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    private AuthUserDetailsService authUserDetailsService;
//
//    @Resource
//    private UserAuthenticationSuccessHandler successHandler;
//
//    @Resource
//    private UserAuthenticationFailureHandler failureHandler;
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private UserAccessDeniedHandler accessDeniedHandler;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(authUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception  {
//        AuthLoginAuthenticationProcessingFilter loginProcessingFilter =
//                new AuthLoginAuthenticationProcessingFilter(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_JSON);
//        loginProcessingFilter.setAuthenticationManager(authenticationManager);
//        loginProcessingFilter.setAuthenticationSuccessHandler(successHandler);
//        loginProcessingFilter.setAuthenticationFailureHandler(failureHandler);
//
//        AuthAuthenticationProcessingFilter authProcessingFilter =
//                new AuthAuthenticationProcessingFilter(authenticationManager);
//        http
//            //添加jtw鉴权过滤器
//            .addFilterAt(authProcessingFilter, UsernamePasswordAuthenticationFilter.class)
//            //添加登录过滤器
//            .addFilterAfter(loginProcessingFilter, UsernamePasswordAuthenticationFilter.class)
//            .cors()
//            .and()
//            //关闭csrf,所以请求可以访问
//            .csrf().disable()
//            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//            .and()
//            //关闭session
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//            .anyRequest().authenticated();
//
//    }
//
//}

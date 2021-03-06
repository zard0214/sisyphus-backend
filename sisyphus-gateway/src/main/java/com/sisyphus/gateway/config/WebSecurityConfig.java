package com.sisyphus.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author zhecheng.zhao
 * @date Created in 11/06/2021 14:05
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    /** 不校验url */
    private static String[] DEFAULT_PERMIT_URL = {"/*", "/pay/alipayCallback",
            "/druid/**", "/auth/**",  "/uac/**",  "/actuator/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs"};

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() ->
                        swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))).
                 accessDeniedHandler((swe, e) -> Mono.fromRunnable(() ->
                         swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))).and()
                .authorizeExchange()
                .pathMatchers(this.DEFAULT_PERMIT_URL).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated().and()
                .csrf().disable()
                .build();
    }

}
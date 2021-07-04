package com.sisyphus.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;


/**
 * @author zhecheng.zhao
 * @date Created in 04/07/2021 22:16
 */
@Slf4j
@Component
@Order(0)
public class RenewFilter implements WebFilter {

    @Resource
    private JwtTokenStore jwtTokenStore;
    private static final int EXPIRES_IN = 60 * 20;

    @Override
    public Mono<Void> filter(ServerWebExchange swe, WebFilterChain wfc) {
        log.info("RenewFilter - token续租...");
        ServerHttpRequest request = swe.getRequest();
        String token = StringUtils.substringAfter(request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION), "Bearer ");
        if (org.apache.commons.lang.StringUtils.isEmpty(token)) {
            return wfc.filter(swe);
        }
        OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(token);
        int expiresIn = oAuth2AccessToken.getExpiresIn();

        if (expiresIn < EXPIRES_IN) {
            ServerHttpResponse servletResponse = swe.getResponse();
            servletResponse.getHeaders().set("Renew-Header", "true");
        }
        return wfc.filter(swe);
    }

}

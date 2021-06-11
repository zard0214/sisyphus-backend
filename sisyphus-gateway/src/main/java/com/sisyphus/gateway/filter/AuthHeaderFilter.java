package com.sisyphus.gateway.filter;

import com.sisyphus.common.base.enums.ErrorCodeEnum;
import com.sisyphus.common.base.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



/**
 * @author zhecheng.zhao
 * @date Created in 11/06/2021 11:52
 */
@Slf4j
@Component
public class AuthHeaderFilter implements GatewayFilter {

    private static final String BEARER_TOKEN_TYPE = "bearer ";
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH = "/auth";
    private static final String LOGOUT_URI = "/oauth/token";
    private static final String ALIPAY_CALL_URI = "/pay/alipayCallback";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestURI = request.getURI().toString();
        log.info("requestURI - {}", requestURI);
        if (OPTIONS.equalsIgnoreCase(request.getMethod().toString()) ||
                !requestURI.contains(AUTH_PATH) || !requestURI.contains(LOGOUT_URI) || !requestURI.contains(ALIPAY_CALL_URI)) {
            return null;
        }
        String authHeader = request.getHeaders().get("Authorization").toString();
        if (StringUtils.isEmpty(authHeader)) {
            throw new BizException(ErrorCodeEnum.UAC10011040, new Object[0]);
        }
        if (StringUtils.isEmpty(authHeader)) {
            throw new RuntimeException();
        }
        return null;
    }

}

package com.sisyphus.gateway.filter;

import com.sisyphus.common.support.interceptor.CoreHeaderInterceptor;
import com.sisyphus.common.support.util.RequestUtil;
import com.sisyphus.common.base.exception.GatewayException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 01:36
 */
@Slf4j
@Order(0)
@Component
public class AuthHeaderFilter implements WebFilter {

    private static final String BEARER_TOKEN_TYPE = "Bearer ";
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH = "/auth";
    private static final String AUTH_LOGOUT_URL = "/auth/user/logout";
    private static final String LOGOUT_URI = "/oauth/token";
    private static final String ACTUATOR_URI = "/actuator";
    private static final String ALIPAY_CALL_URI = "/pay/alipayCallback";

    @Override
    public Mono<Void> filter(ServerWebExchange swe, WebFilterChain wfc) {
        ServerHttpRequest request = swe.getRequest();
        String requestURI = request.getURI().toString();
        log.info("requestURI - {}", requestURI);
        if ((OPTIONS.equalsIgnoreCase(request.getMethod().toString()))
                || (requestURI.contains(AUTH_PATH) && !requestURI.contains(AUTH_LOGOUT_URL))
                || (requestURI.contains(LOGOUT_URI))
                || (requestURI.contains(ACTUATOR_URI))
                || (requestURI.contains(ALIPAY_CALL_URI))) {
            return wfc.filter(swe);
        }
        String authHeader = RequestUtil.getAuthHeader(request);
        if (StringUtils.isEmpty(authHeader)) {
            throw new GatewayException("刷新页面重试");
        }
        if (authHeader.startsWith(BEARER_TOKEN_TYPE)) {
            log.info("authHeader={} ", authHeader);
            wfc.filter(
                    swe.mutate().request(
                            swe.getRequest().mutate()
                                    .header(CoreHeaderInterceptor.HEADER_LABEL,authHeader)
                                    .build())
                            .build());
        }
        return wfc.filter(swe);
    }

}

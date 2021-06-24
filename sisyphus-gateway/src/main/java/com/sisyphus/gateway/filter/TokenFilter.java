package com.sisyphus.gateway.filter;

import com.sisyphus.common.base.constant.GlobalConstant;
import com.sisyphus.common.base.dto.AuthUserTokenDTO;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.base.exception.GatewayException;
import com.sisyphus.common.support.util.RedisKeyUtil;
import com.sisyphus.common.support.util.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 01:14
 */
@Slf4j
@Component
@Order(1)
public class TokenFilter implements WebFilter {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String MATE_TENANT_ID = "TenantId";
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH1 = "/auth";
    private static final String AUTH_LOGOUT_PATH1 = "/user/logout";
    private static final String AUTH_LOGINFO_PATH1 = "/user/loginInfo";
    private static final String AUTH_PATH2 = "/oauth";
    private static final String AUTH_PATH3 = "/error";
    private static final String AUTH_PATH4 = "/api";

    @Override
    public Mono<Void> filter(ServerWebExchange swe, WebFilterChain wfc) {
        ServerHttpRequest request = swe.getRequest();
        String uri = request.getURI().toString();
        log.info("<== preHandle - 权限拦截器.  url={}", uri);
        if (uri.contains(AUTH_PATH1)
                && !uri.contains(AUTH_LOGOUT_PATH1)
                && !uri.contains(AUTH_LOGINFO_PATH1)  || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3) || uri.contains(AUTH_PATH4)) {
            log.info("<== preHandle - 配置URL不走认证.  url={}", uri);
            return wfc.filter(swe);
        }
        log.info("<== preHandle - 调试模式不走认证.  OPTIONS={}", request.getMethod().toString().toUpperCase());
        if ((OPTIONS.equalsIgnoreCase(request.getMethod().toString()))){
            log.info("<== preHandle - 调试模式不走认证.  url={}", uri);
            return wfc.filter(swe);
        }
        String token = StringUtils.substringAfter(request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION), "Bearer ");
        log.info("<== preHandle - 权限拦截器.  token={}", token);
        AuthUserTokenDTO authUserTokenDTO = (AuthUserTokenDTO) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token));
        LoginAuthDTO loginUser = new ModelMapper().map(authUserTokenDTO, LoginAuthDTO.class);
        if (loginUser == null) {
            log.error("获取用户信息失败, 不允许操作");
            throw new GatewayException("获取用户信息失败, 不允许操作");
        }
        if(request.getHeaders().getFirst(MATE_TENANT_ID) != null){
            Long tenantId = Long.valueOf(request.getHeaders().getFirst(MATE_TENANT_ID));
            log.info("<== preHandle - 权限拦截器.  tenantId={}", tenantId);
            loginUser.setTenantId(tenantId);
            ThreadLocalMap.put(GlobalConstant.Sys.TENANT_ID, tenantId);
        }
        log.info("<== preHandle - 权限拦截器.  loginUser={}", loginUser);
        ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, loginUser);
        log.info("<== preHandle - 权限拦截器.  url={}, loginUser={}", uri, loginUser);
        return wfc.filter(swe);
    }
}

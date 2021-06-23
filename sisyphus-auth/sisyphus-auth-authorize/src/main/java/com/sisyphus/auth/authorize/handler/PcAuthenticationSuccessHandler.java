package com.sisyphus.auth.authorize.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisyphus.auth.authorize.model.SecurityUser;
import com.sisyphus.auth.authorize.service.AuthUserService;
import com.sisyphus.auth.core.SecurityResult;
import com.sisyphus.auth.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 08:37
 */
@Slf4j
@Component("pcAuthenticationSuccessHandler")
public class PcAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Resource
    private AuthUserService authUserService;

    /**
     * @param request
     * @param response
     * @param authentication 封装了所有的认证信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        /**
         * @see BasicAuthenticationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
         *  */
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            // 不被认可的客户端异常
            throw new UnapprovedClientAuthenticationException("没有Authorization请求头");
        }

        // 解析请Authorization 获取client信息
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        // 判定提交的是否与查询的匹配

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, clientId, clientDetails.getScope(),
                "refresh_token");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        authUserService.handlerLoginData(accessToken, principal, request);

        log.info("用户【 {} 】记录登录日志", principal.getUsername());

        response.setContentType("application/json;charset=UTF-8");
        SecurityResult result = SecurityResult.ok(accessToken);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }
        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}

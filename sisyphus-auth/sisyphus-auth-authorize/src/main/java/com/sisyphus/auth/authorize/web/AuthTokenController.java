package com.sisyphus.auth.authorize.web;

import com.google.common.base.Preconditions;
import com.sisyphus.auth.authorize.service.AuthUserTokenService;
import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import com.sisyphus.common.support.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 20:05
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Web - AuthTokenController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthTokenController {

    private static final String BEARER_TOKEN_TYPE = "Bearer ";

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthUserTokenService authUserTokenService;

    /**
     * 刷新token.
     *
     * @param request      the request
     * @param refreshToken the refresh token
     * @param accessToken  the access token
     *
     * @return the wrapper
     */
    @GetMapping(value = "/auth/user/refreshToken")
    @ApiOperation(httpMethod = "POST", value = "刷新token")
    public ResponseDTO<String> refreshToken(HttpServletRequest request,
                                            @RequestParam(value = "refreshToken") String refreshToken,
                                            @RequestParam(value = "accessToken") String accessToken) {
        String token;
        try {
            Preconditions.checkArgument(StringUtils.isNotEmpty(accessToken), "accessToken is null");
            Preconditions.checkArgument(StringUtils.isNotEmpty(refreshToken), "refreshToken is null");
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client信息");
            }
            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
            }
            token = authUserTokenService.refreshToken(accessToken, refreshToken, request);
        } catch (Exception e) {
            log.error("refreshToken={}", e.getMessage(), e);
            return Response.failed();
        }
        return Response.success(token);
    }


}

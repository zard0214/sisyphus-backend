package com.sisyphus.auth.authorize.web;

import com.sisyphus.common.support.base.BaseController;
import com.sisyphus.common.base.dto.AuthUserTokenDTO;
import com.sisyphus.auth.authorize.model.dto.LoginRespDTO;
import com.sisyphus.auth.authorize.model.enums.UserTokenStatusEnum;
import com.sisyphus.auth.authorize.service.AuthUserService;
import com.sisyphus.auth.authorize.service.AuthUserTokenService;
import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 14:16
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Web - AuthLoginController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthLoginController extends BaseController {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserTokenService authUserTokenService;

    /**
     * 登录后获取用户信息
     *
     * @param tenantId the access token
     *
     * @return the wrapper
     */
    @PostMapping(value = "/user/loginInfo/{tenantId}")
    @ApiOperation(httpMethod = "POST", value = "登录成功获取用户菜单")
    public ResponseDTO<LoginRespDTO> loginResp(@PathVariable Long tenantId) {
        LoginRespDTO result = authUserService.loginResp(tenantId);
        return Response.success(result);
    }

    /**
     * 登出.
     *
     * @param accessToken the access token
     *
     * @return the wrapper
     */
    @PostMapping(value = "/user/logout")
    @ApiOperation(httpMethod = "POST", value = "登出")
    public ResponseDTO loginAfter(String accessToken) {
        if (!StringUtils.isEmpty(accessToken)) {
            AuthUserTokenDTO userTokenDto = authUserTokenService.getByAccessToken(accessToken);
            userTokenDto.setStatus(UserTokenStatusEnum.OFF_LINE.getStatus());
            authUserTokenService.updateUacUserToken(userTokenDto, getLoginAuthDTO());
        }
        return Response.success();
    }
}

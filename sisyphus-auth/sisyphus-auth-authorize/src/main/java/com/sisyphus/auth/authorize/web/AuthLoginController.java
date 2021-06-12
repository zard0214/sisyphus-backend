package com.sisyphus.auth.authorize.web;

import com.sisyphus.auth.authorize.model.dto.LoginRespDTO;
import com.sisyphus.auth.authorize.service.AuthUserService;
import com.sisyphus.auth.authorize.service.AuthUserTokenService;
import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
public class AuthLoginController {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserTokenService authUserTokenService;

    @PostMapping(value = "/user/loginInfo/{applicationId}")
    @ApiOperation(httpMethod = "POST", value = "登录成功获取用户菜单")
    public ResponseDTO<LoginRespDTO> loginResp(@PathVariable Long applicationId) {
        LoginRespDTO result = authUserService.loginResp(applicationId);
        return Response.success(result);
    }
}

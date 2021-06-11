package com.sisyphus.auth.authorize.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisyphus.auth.core.SecurityResult;
import com.sisyphus.auth.core.validate.code.ValidateCodeException;
import com.sisyphus.common.base.enums.ErrorCodeEnum;
import com.sisyphus.common.base.wapper.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 08:30
 */
@Slf4j
@Component("pcAuthenticationFailureHandler")
public class PcAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.info("登录失败 exception {}", exception);
        String message = exception.getMessage();
        // 记录失败次数 和原因 ip等信息 5次登录失败,锁定用户, 不允许在此登录
        if(!(exception instanceof ValidateCodeException)){
            message = ErrorCodeEnum.UAC10011016.msg();
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        SecurityResult result = SecurityResult.error(message);
        response.getWriter().write(objectMapper.writeValueAsString(result));

    }
}
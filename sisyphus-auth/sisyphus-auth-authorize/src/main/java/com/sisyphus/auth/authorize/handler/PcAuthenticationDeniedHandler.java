package com.sisyphus.auth.authorize.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisyphus.auth.core.SecurityResult;
import com.sisyphus.auth.core.validate.code.ValidateCodeException;
import com.sisyphus.common.base.enums.ErrorCodeEnum;
import com.sisyphus.common.base.wapper.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 09:48
 */
@Slf4j
@Component("pcAuthenticationDeniedHandler")
public class PcAuthenticationDeniedHandler implements AccessDeniedHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
            throws IOException {
        log.info("登录拒绝 exception {}", exception);
        String message = exception.getMessage();
        // 记录失败次数 和原因 ip等信息 5次登录失败,锁定用户, 不允许在此登录
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        SecurityResult result = SecurityResult.error(message);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}

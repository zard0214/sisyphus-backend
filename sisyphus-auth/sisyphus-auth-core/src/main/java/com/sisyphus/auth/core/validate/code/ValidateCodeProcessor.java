package com.sisyphus.auth.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:39
 */
public interface ValidateCodeProcessor {
    /**
     * 创建校验码：创建、存入session、发送
     * org.springframework.web.context.request.ServletWebRequest 工具类可以存放request和response
     * @param request
     */
    void create(ServletWebRequest request) throws Exception;

    void validate(ServletWebRequest request);
}
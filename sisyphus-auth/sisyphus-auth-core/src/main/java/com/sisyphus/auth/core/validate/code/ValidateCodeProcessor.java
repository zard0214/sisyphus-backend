package com.sisyphus.auth.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:39
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request the request
     *
     * @throws Exception the exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码(验证后删除)
     *
     * @param servletWebRequest the servlet web request
     */
    void validate(ServletWebRequest servletWebRequest);

    /**
     * 校验验证码(验证后不删除)
     *
     * @param servletWebRequest the servlet web request
     */
    void check(ServletWebRequest servletWebRequest);
}
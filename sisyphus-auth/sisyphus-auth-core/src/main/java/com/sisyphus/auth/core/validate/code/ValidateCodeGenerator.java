package com.sisyphus.auth.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:39
 */
public interface ValidateCodeGenerator {

    /**
     * 创建验证码
     * @param request
     * @return
     * @throws Exception
     */
    ValidateCode generate(HttpServletRequest request) throws Exception;
}
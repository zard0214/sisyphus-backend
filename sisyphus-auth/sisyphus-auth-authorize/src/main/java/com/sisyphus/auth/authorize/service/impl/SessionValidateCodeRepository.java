package com.sisyphus.auth.authorize.service.impl;

import com.sisyphus.auth.core.validate.code.ValidateCode;
import com.sisyphus.auth.core.validate.code.ValidateCodeRepository;
import com.sisyphus.auth.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 09/06/2021 09:23
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /** 操作session的工具类 */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /** 验证码放入session的时候前缀 */
    public final static String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(validateCodeType), code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String sessionKey = getSessionKey(validateCodeType);
        // 拿到创建 create() 存储到session的code验证码对象
        return (ValidateCode) sessionStrategy.getAttribute(request, sessionKey);
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(validateCodeType));
    }

    private String getSessionKey(ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }
}


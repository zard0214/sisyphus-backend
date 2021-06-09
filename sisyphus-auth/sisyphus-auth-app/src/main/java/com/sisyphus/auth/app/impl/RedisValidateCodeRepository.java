package com.sisyphus.auth.app.impl;


import com.sisyphus.auth.app.config.AppConstants;
import com.sisyphus.auth.core.validate.code.ValidateCode;
import com.sisyphus.auth.core.validate.code.ValidateCodeException;
import com.sisyphus.auth.core.validate.code.ValidateCodeRepository;
import com.sisyphus.auth.core.validate.code.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 17:47
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /** 验证码放入redis规则模式：CODE_{TYPE}_{DEVICEId} */
    private final static String CODE_KEY_PATTERN = "CODE_%s_%s";

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request, validateCodeType), code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String key = buildKey(request, validateCodeType);
        return (ValidateCode) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String key = buildKey(request, validateCodeType);
        redisTemplate.delete(key);
    }

    /**
     * 构建验证码放入redis时的key; 在保存的时候也使用该key
     * @param validateCodeType
     * @return
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceId = request.getHeader(AppConstants.DEFAULT_HEADER_DEVICE_ID);
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return String.format(CODE_KEY_PATTERN, validateCodeType, deviceId);
    }
}

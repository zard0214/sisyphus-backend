package com.sisyphus.auth.core.validate.code;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:39
 */
@Component
public class ValidateCodeProcessorHolder {

    @Resource
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String beanName = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(beanName);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器 " + beanName + " 不存在");
        }
        return processor;
    }
}

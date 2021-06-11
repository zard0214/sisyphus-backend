package com.sisyphus.auth.core.validate.code;

import com.sisyphus.auth.core.properties.SecurityProperties;
import com.sisyphus.auth.core.validate.code.image.ImageValidateCodeGenerator;
import com.sisyphus.auth.core.validate.code.sms.SmsCodeSimulation;
import com.sisyphus.auth.core.validate.code.sms.SmsCodeSender;
import com.sisyphus.auth.core.validate.code.sms.SmsValidateCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:40
 */
@Configuration
public class ValidateCodeConfig {

    @Resource
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageCodeGenerate = new ImageValidateCodeGenerator(securityProperties.getCode().getImage());
        return imageCodeGenerate;
    }

    @Bean("smsValidateCodeGenerator")
    @ConditionalOnMissingBean(name = "smsValidateCodeGenerator")
    // 注意方法名称：如果没有指定bean则按方法名称作为beanName返回
    public ValidateCodeGenerator smsCodeGenerate() {
        SmsValidateCodeGenerator smsValidateCodeGenerator = new SmsValidateCodeGenerator(securityProperties.getCode().getSms());
        return smsValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSimulation.class)
    public SmsCodeSender defaultSmsCodeSender() {
        return new SmsCodeSimulation();
    }
}


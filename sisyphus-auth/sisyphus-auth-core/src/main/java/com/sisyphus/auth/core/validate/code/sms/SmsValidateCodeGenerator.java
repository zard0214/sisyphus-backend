package com.sisyphus.auth.core.validate.code.sms;

import com.sisyphus.auth.core.properties.SmsCodeProperties;
import com.sisyphus.auth.core.validate.code.ValidateCode;
import com.sisyphus.auth.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:41
 */
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    private SmsCodeProperties smsCodeProperties;

    public SmsValidateCodeGenerator(SmsCodeProperties smsCodeProperties) {
        this.smsCodeProperties = smsCodeProperties;
    }

    @Override
    public ValidateCode generate(HttpServletRequest request) {
        int count = smsCodeProperties.getLength();
        int expireIn = smsCodeProperties.getExpireIn();
        String smsCode = RandomStringUtils.randomNumeric(count);
        return new ValidateCode(smsCode, expireIn);
    }

    public SmsCodeProperties getSmsCodeProperties() {
        return smsCodeProperties;
    }

    public void setSmsCodeProperties(SmsCodeProperties smsCodeProperties) {
        this.smsCodeProperties = smsCodeProperties;
    }
}

package com.sisyphus.auth.core.properties;

import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:36
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}

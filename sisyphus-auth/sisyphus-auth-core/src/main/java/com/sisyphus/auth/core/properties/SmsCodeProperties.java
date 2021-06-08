package com.sisyphus.auth.core.properties;

import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:36
 */
@Data
public class SmsCodeProperties {

    private int length = 6;  // 验证码长度
    private int expireIn = 60;  // 过期时间
    private String url;  // 要验证的接口url路径，逗号隔开
}

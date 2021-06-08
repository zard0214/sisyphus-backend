package com.sisyphus.auth.core.validate.code.sms;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:41
 */
public class SmsCodeSimulation implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("短信模拟发送：" + mobile + " -> " + code);
    }
}
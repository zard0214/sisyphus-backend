package com.sisyphus.auth.authorize.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 *
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 11:37
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * 用户名
     */
    USERNAME("username", 1),

    /**
     * 手机号码
     */
    MOBILE("mobile", 2),

    /**
     * 第三方
     */
    SOCIAL("social", 3);

    /**
     * 名称
     */
    final String name;
    /**
     * 类型
     */
    final int type;
}
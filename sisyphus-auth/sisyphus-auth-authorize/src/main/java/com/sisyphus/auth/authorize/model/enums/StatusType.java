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
public enum StatusType {

    /**
     *
     */
    ENABLE("enable", 1),

    /**
     *
     */
    DISABLE("disable", 2);

    /**
     * 名称
     */
    final String code;
    /**
     * 类型
     */
    final int type;
}
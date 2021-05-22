package com.sisyphus.auth.model.enums;

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
public enum GenderType {

    /**
     * 男性
     */
    MALE("male", 1),

    /**
     * 女性
     */
    FEMALE("female", 2);

    /**
     * 名称
     */
    final String name;
    /**
     * 类型
     */
    final int type;
}
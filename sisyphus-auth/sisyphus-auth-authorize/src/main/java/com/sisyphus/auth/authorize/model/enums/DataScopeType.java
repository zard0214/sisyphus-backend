package com.sisyphus.auth.authorize.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 22:55
 */
@Getter
@AllArgsConstructor
public enum DataScopeType {

    /**
     * 全部数据权限
     */
    ALL("all", 1),

    /**
     * 自定数据权限
     */
    SELF("self", 2),

    /**
     * 本部门数据权限
     */
    GROUP("group", 3),

    /**
     * 本部门及以下数据权限
     */
    GROUP_CHILD("group_child", 3);


    /**
     * 名称
     */
    final String name;
    /**
     * 类型
     */
    final int type;

}

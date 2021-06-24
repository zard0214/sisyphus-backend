package com.sisyphus.auth.authorize.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 00:18
 */
@Data
public class AuthMenuVO {

    private Long id;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单码
     */
    private String menuCode;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 状态
     */
    private String visible;

    /**
     * 状态
     */
    private String status;

    /**
     * 图标
     */
    private String icon;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 序号
     */
    private Integer number;

    /**
     * 父菜单
     */
    private AuthMenuVO parentMenu;

    /**
     * 子菜单
     */
    private List<AuthMenuVO> subMenu;

    /**
     * 是否有子菜单
     */
    private boolean hasMenu = false;
}

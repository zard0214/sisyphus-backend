package com.sisyphus.demo.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.sisyphus.demo.mybatisplus.dto.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 00:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_auth_menu")
@Alias(value = "authMenu")
public class AuthMenu extends BaseDO {

    /**
     * 版本号
     */
    @Version
    @TableField(value = "version")
    private Integer version;

    /**
     * 菜单名
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 菜单码
     */
    @TableField(value = "menu_code")
    private String menuCode;

    /**
     * 菜单url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 菜单类型
     */
    @EnumValue
    @TableField(value = "menu_type")
    private String menuType;

    /**
     * 状态
     */
    @EnumValue
    @TableField(value = "menu_type")
    private String visible;

    /**
     * 状态
     */
    @EnumValue
    @TableField(value = "status")
    private String status;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 父级id
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 层级
     */
    @EnumValue
    @TableField(value = "level")
    private Integer level;

    /**
     * 是否使叶子节点
     */
    @EnumValue
    @TableField(value = "leaf")
    private Integer leaf;

    /**
     * 序号
     */
    @TableField(value = "number")
    private Integer number;

    /**
     *  运营工作台ID
     */
    @TableField(value = "application_id")
    private Long applicationId;
}

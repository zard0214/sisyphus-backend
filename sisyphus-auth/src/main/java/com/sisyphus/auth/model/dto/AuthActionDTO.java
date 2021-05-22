package com.sisyphus.auth.model.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sisyphus.common.base.dto.BaseDO;
import com.sisyphus.common.base.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 07:38
 */
@Data
public class AuthActionDTO extends BaseDTO {

    /**
     * 权限名
     */
    @TableField(value = "action_name")
    private String actionName;

    /**
     * 权限码
     */
    @TableField(value = "action_code")
    private String actionCode;

    /**
     * 权限类型
     */
    @TableField(value = "method")
    private String method;

    /**
     * 菜单url
     */
    @EnumValue
    @TableField(value = "url")
    private String url;

    /**
     * 菜单
     */
    @EnumValue
    @TableField(value = "menu_id")
    private String menuId;

    /**
     * 状态
     */
    @EnumValue
    @TableField(value = "status")
    private String status;

}

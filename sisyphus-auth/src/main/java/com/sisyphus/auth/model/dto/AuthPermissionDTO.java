package com.sisyphus.auth.model.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sisyphus.common.base.dto.BaseDO;
import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 07:38
 */
@Data
public class AuthPermissionDTO extends BaseDO {

    /**
     * 权限名
     */
    @TableField(value = "permission_name")
    private String permissionName;

    /**
     * 权限码
     */
    @TableField(value = "permission_code")
    private String permissionCode;

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

}

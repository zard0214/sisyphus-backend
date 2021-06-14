package com.sisyphus.demo.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sisyphus.demo.mybatisplus.dto.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 07:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_auth_action")
@Alias(value = "authAction")
public class AuthAction extends BaseDO {

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
    @EnumValue
    @TableField(value = "method")
    private String method;

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

    /**
     * 描述
     */
    @TableField(value = "remark")
    private String remark;


}

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
 * @date Created in 22/05/2021 07:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_auth_role")
@Alias(value = "authRole")
public class AuthRole extends BaseDO {

    /**
     * 版本号
     */
    @Version
    @TableField(value = "version")
    private Integer version;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色码
     */
    @TableField(value = "role_code")
    private String roleCode;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField(value = "data_scope")
    private String dataScope;

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

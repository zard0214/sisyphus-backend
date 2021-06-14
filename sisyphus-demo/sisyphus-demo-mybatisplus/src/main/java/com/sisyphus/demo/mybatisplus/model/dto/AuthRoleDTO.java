package com.sisyphus.demo.mybatisplus.model.dto;

import com.sisyphus.demo.mybatisplus.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 10:35
 */
@Data
public class AuthRoleDTO extends BaseDTO {

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色码
     */
    private String roleCode;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;

    /**
     * 状态
     */
    private String status;

    /**
     * 描述
     */
    private String remark;
}

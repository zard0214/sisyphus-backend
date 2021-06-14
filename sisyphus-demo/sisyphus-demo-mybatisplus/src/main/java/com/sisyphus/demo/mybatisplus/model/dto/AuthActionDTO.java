package com.sisyphus.demo.mybatisplus.model.dto;

import com.sisyphus.demo.mybatisplus.dto.BaseDTO;
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
    private String actionName;

    /**
     * 权限码
     */
    private String actionCode;

    /**
     * 权限类型
     */
    private String method;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单
     */
    private String menuId;

    /**
     * 状态
     */
    private String status;

}

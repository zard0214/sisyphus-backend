package com.sisyphus.auth.authorize.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sisyphus.common.base.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 00:10
 */
@Data
public class AuthMenuDTO extends BaseDTO {

    /**
     * 版本号
     */
    private Integer version;

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
     * 父级id
     */
    private Long pid;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 是否使叶子节点
     */
    private Integer leaf;

    /**
     * 序号
     */
    private Integer number;

    /**
     * 备注
     */
    private String remark;

    /**
     *  运营工作台ID
     */
    private Long applicationId;
}

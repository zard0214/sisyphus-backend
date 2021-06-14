package com.sisyphus.demo.mybatisplus.model.dto;

import com.sisyphus.demo.mybatisplus.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 21:48
 */
@Data
public class AuthUserTokenDTO extends BaseDTO {

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 登录位置
     */
    private String loginLocation;

    /**
     * 登录位置
     */
    private Date loginTime;

    /**
     * 访问token
     */
    private String accessToken;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * token类型
     */
    private String tokenType;

    /**
     * 访问token的生效时间(秒)
     */
    private String accessTokenValidity;

    /**
     * 刷新token的生效时间(秒)
     */
    private String refreshTokenValidity;

    /**
     * 状态 0 在线 10已刷新 20 离线
     */
    private Integer status;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 组织id
     */
    private Long groupId;

    /**
     * 组织名称
     */
    private Long groupName;
}

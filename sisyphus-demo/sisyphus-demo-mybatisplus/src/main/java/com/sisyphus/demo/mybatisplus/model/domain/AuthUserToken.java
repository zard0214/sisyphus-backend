package com.sisyphus.demo.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.sisyphus.demo.mybatisplus.dto.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.Date;


/**
 * @author zhecheng.zhao
 * @date Created in 21/05/2021 06:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_auth_user_token")
@Alias(value = "authUserToken")
public class AuthUserToken extends BaseDO {

    /**
     * 版本号
     */
    @Version
    @TableField(value = "version")
    private Integer version;

    /**
     * 父id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 登录名
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 昵称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * 浏览器
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 登录ip
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 登录位置
     */
    @TableField(value = "login_location")
    private String loginLocation;

    /**
     * 登录位置
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 访问token
     */
    @TableField(value = "access_token")
    private String accessToken;

    /**
     * 刷新token
     */
    @TableField(value = "refresh_token")
    private String refreshToken;

    /**
     * token类型
     */
    @EnumValue
    @TableField(value = "token_type")
    private String tokenType;

    /**
     * 访问token的生效时间(秒)
     */
    @TableField(value = "access_token_validity")
    private String accessTokenValidity;

    /**
     * 刷新token的生效时间(秒)
     */
    @TableField(value = "refresh_token_validity")
    private String refreshTokenValidity;

    /**
     * 状态 0 在线 10已刷新 20 离线
     */
    @EnumValue
    @TableField(value = "status")
    private Integer status;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 组织id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 组织名称
     */
    @TableField(value = "group_name")
    private Long groupName;
}


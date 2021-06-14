package com.sisyphus.demo.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sisyphus.demo.mybatisplus.dto.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 06:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_auth_user")
@Alias(value = "authUser")
public class AuthUser extends BaseDO {

    /**
     * 版本号
     */
    @Version
    @TableField(value = "version")
    private Integer version;

    /**
     * 性别
     */
    @EnumValue
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 登录名
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    @TableField(value = "login_pwd")
    private String loginPwd;

    /**
     * 工号
     */
    @TableField(value = "user_code")
    private String userCode;

    /**
     * 昵称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 状态
     */
    @EnumValue
    @TableField(value = "status")
    private String status;

    /**
     * 状态
     */
    @EnumValue
    @TableField(value = "user_source")
    private String userSource;

    /**
     * 用户类型
     */
    @EnumValue
    @TableField(value = "type")
    private String type;

    /**
     * 最后登录IP地址
     */
    @TableField(value = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录IP位置
     */
    @TableField(value = "last_login_location")
    private String lastLoginLocation;

    /**
     * 描述
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    /**
     * 密码错误次数
     */
    @TableField(value = "pwd_error_count")
    private Integer pwdErrorCount;

    /**
     * 密码错误时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "pwd_error_time")
    private Date pwdErrorTime;

    /**
     * 用户所属的组织ID
     */
    private Long groupId;

    private String groupName;
}

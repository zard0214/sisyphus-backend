package com.sisyphus.provider.uac.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sisyphus.common.base.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhecheng.zhao
 * @date Created in 24/06/2021 22:31
 */
@Data
public class UacUserDTO extends BaseDTO {

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 工号
     */
    private String userCode;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private String status;

    /**
     * 状态
     */
    private String userSource;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 最后登录IP地址
     */
    private String lastLoginIp;

    /**
     * 最后登录IP位置
     */
    private String lastLoginLocation;

    /**
     * 描述
     */
    private String remark;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 密码错误次数
     */
    private Integer pwdErrorCount;

    /**
     * 密码错误时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pwdErrorTime;

    /**
     * 用户所属的组织ID
     */
    @ApiModelProperty(value = "用户所属的组织ID")
    private Long groupId;

    @ApiModelProperty(value = "用户所属的组织名称")
    private String groupName;

}

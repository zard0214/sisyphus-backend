package com.sisyphus.provider.uac.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sisyphus.common.base.dto.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 15:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_uac_log")
@Alias(value = "uacLog")
public class UacLog extends BaseDO {

    /**
     * 群组id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 群组名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 登录类型
     */
    @EnumValue
    @TableField(value = "log_type")
    private String logType;

    /**
     * 日志类型名称
     */
    @TableField(value = "log_name")
    private String logName;

    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    private Long permissionId;

    /**
     * 权限编码
     */
    @TableField(value = "permission_code")
    private String permissionCode;

    /**
     * 权限名称
     */
    @TableField(value = "permission_name")
    private String permissionName;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 登录位置
     */
    @TableField(value = "location")
    private String location;

    /**
     * 物理地址
     */
    @TableField(value = "mac")
    private String mac;

    /**
     * 详细描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 请求参数
     */
    @TableField(value = "request_data")
    private String requestData;

    /**
     * 请求地址
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 响应结果
     */
    @TableField(value = "response_data")
    private String responseData;

    /**
     * 类名
     */
    @TableField(value = "class_name")
    private String className;

    /**
     * 方法名
     */
    @TableField(value = "method_name")
    private String methodName;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 耗时,秒
     */
    @TableField(value = "excute_time")
    private Integer excuteTime;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private String tenantId;
}

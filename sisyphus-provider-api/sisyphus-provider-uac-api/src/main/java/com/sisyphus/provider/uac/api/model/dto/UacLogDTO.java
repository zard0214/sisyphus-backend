package com.sisyphus.provider.uac.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sisyphus.common.base.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 15:09
 */
@Data
public class UacLogDTO extends BaseDTO {

    /**
     * 群组id
     */
    private Long groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 登录类型
     */
    private String logType;

    /**
     * 日志类型名称
     */
    private String logName;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 登录位置
     */
    private String location;

    /**
     * 物理地址
     */
    private String mac;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 请求参数
     */
    private String requestData;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 响应结果
     */
    private String responseData;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 耗时,秒
     */
    private Integer excuteTime;

    /**
     * 租户id
     */
    private String tenantId;
}

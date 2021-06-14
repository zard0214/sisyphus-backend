package com.sisyphus.demo.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sisyphus.demo.mybatisplus.dto.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 00:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_auth_group")
@Alias(value = "authGroup")
public class AuthGroup extends BaseDO {

    /**
     * 父级id
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 群组名
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 群组码
     */
    @TableField(value = "group_code")
    private String groupCode;

    /**
     * 联络人id
     */
    @TableField(value = "contact_id")
    private String contactId;

    /**
     * 联络人
     */
    @TableField(value = "contact_name")
    private String contactName;

    /**
     *  运营工作台ID
     */
    @TableField(value = "application_id")
    private Long applicationId;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 状态
     */
    @EnumValue
    @TableField(value = "status")
    private String status;

}

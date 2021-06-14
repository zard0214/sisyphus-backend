package com.sisyphus.demo.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.sisyphus.demo.mybatisplus.dto.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 01:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "s_auth_position")
@Alias(value = "authPosition")
public class AuthPosition extends BaseDO {

    /**
     * 版本号
     */
    @Version
    @TableField(value = "version")
    private Integer version;

    /**
     *  运营工作台ID
     */
    @TableField(value = "application_id")
    private Long applicationId;

    /**
     * 群组名
     */
    @TableField(value = "position_name")
    private String positionName;

    /**
     * 群组码
     */
    @TableField(value = "position_code")
    private String positionCode;
}

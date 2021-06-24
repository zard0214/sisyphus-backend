package com.sisyphus.common.base.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 00:00
 */
@Data
public class BaseDO implements Serializable {

    @TableId(value = "id",
            type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",
            delval = "1")
    private boolean isDeleted;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 创建人ID
     */
    @TableField(value = "creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_created", fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 最近操作人
     */
    @TableField(value = "last_operator")
    private String lastOperator;

    /**
     * 最后操作人ID
     */
    @TableField(value = "last_operator_id")
    private Long lastOperatorId;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "gmt_modified", fill = FieldFill.UPDATE)
    private Date gmtModified;

    /**
     * Is new boolean.
     *
     * @return the boolean
     */
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @Transient
    @JsonIgnore
    public void setUpdateInfo(LoginAuthDTO user) {
        if (isNew()) {
            this.creatorId = (this.lastOperatorId = user.getUserId());
            this.creator = user.getLoginName();
            this.gmtCreated = (this.gmtModified = new Date());
        }
        this.lastOperatorId = user.getUserId();
        this.lastOperator = user.getLoginName() == null ? user.getLoginName() : user.getUserName();
        this.gmtModified = new Date();
    }
}

package com.sisyphus.provider.uac.model.query;

import com.sisyphus.common.base.dto.BaseQuery;
import lombok.Data;

/**
 * @author zhecheng.zhao
 * @date Created in 24/06/2021 22:54
 */
@Data
public class UacUserQuery extends BaseQuery {

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 登录名
     */
    private String loginName;

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
}

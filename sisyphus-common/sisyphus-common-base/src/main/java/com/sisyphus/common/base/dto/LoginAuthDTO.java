package com.sisyphus.common.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 00:30
 */
@AllArgsConstructor
@Data
public class LoginAuthDTO implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "组织ID")
    private Long groupId;
    @ApiModelProperty(value = "组织名称")
    private String groupName;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "性别")
    private Integer gender;

    public LoginAuthDTO() {
    }

    public LoginAuthDTO(Long userId, String loginName, String nickName) {
        this.userId = userId;
        this.loginName = loginName;
        this.nickName = nickName;
    }


}

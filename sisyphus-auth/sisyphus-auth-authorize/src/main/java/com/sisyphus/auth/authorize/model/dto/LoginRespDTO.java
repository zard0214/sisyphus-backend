package com.sisyphus.auth.authorize.model.dto;

import com.sisyphus.auth.authorize.model.vo.AuthMenuVO;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 00:07
 */
@AllArgsConstructor
@Data
public class LoginRespDTO implements Serializable {

    @ApiModelProperty(value = "loginAuthDTO")
    private LoginAuthDTO loginAuthDTO;

    @ApiModelProperty(value = "authMenus")
    private List<AuthMenuVO> authMenus;

    @ApiModelProperty(value = "authRoles")
    private List<AuthRoleDTO> authRoles;
}

package com.sisyphus.provider.uac.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import com.sisyphus.provider.uac.model.domain.UacUser;
import com.sisyphus.provider.uac.model.query.UacUserQuery;
import com.sisyphus.provider.uac.service.UacUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 24/06/2021 23:19
 */
@Slf4j
@RestController
@RequestMapping(value = "/uac", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Web - UacUserController", produces = MediaType.APPLICATION_JSON_VALUE)
public class UacUserController {

    @Resource
    private UacUserService uacUserService;

    /**
     *   用户分页列表
     * @param uacUserQuery
     * @return
     */
    @PostMapping(value = "/user/fetchUserListWithPage")
    @ApiOperation(httpMethod = "POST", value = "用户分页列表")
    public ResponseDTO<Page<UacUser>> fetchUserListWithPage(@ApiParam(name = "uacUserQuery", value = "用户查询条件")
                                                                   @RequestBody UacUserQuery uacUserQuery) {
        Page<UacUser> uacUserDTOPage = uacUserService.fetchUserListWithPage(uacUserQuery);
        return Response.success(uacUserDTOPage);
    }
}

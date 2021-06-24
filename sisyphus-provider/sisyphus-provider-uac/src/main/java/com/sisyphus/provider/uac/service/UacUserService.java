package com.sisyphus.provider.uac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sisyphus.common.support.service.IService;
import com.sisyphus.provider.uac.model.dto.UacUserDTO;
import com.sisyphus.provider.uac.model.query.UacUserQuery;

/**
 * @author zhecheng.zhao
 * @date Created in 24/06/2021 22:32
 */
public interface UacUserService extends IService<UacUserDTO> {

    /**
     * 查询用户列表
     * @param uacUserQuery
     * @return
     */
    Page<UacUserDTO> fetchUserListWithPage(UacUserQuery uacUserQuery);
}

package com.sisyphus.provider.uac.api.service;

import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.base.wapper.ResponseDTO;
import com.sisyphus.provider.uac.api.model.dto.UacLogDTO;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 15:05
 */
public interface UacLogDubboApi {

    /**
     * save exception and send message
     * @param uacLog
     * @return
     */
    ResponseDTO saveUacLog(UacLogDTO uacLog, LoginAuthDTO loginAuthDto);
}

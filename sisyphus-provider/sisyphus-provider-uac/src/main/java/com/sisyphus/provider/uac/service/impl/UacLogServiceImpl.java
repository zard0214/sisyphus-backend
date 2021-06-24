package com.sisyphus.provider.uac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import com.sisyphus.provider.uac.api.model.dto.UacLogDTO;
import com.sisyphus.provider.uac.api.service.UacLogDubboApi;
import com.sisyphus.provider.uac.mapper.UacLogMapper;
import com.sisyphus.provider.uac.model.domain.UacLog;
import com.sisyphus.provider.uac.service.UacLogService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 16:53
 */

@Slf4j
@Service("uacLogService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = UacLogDubboApi.class)
@Transactional(rollbackFor = Exception.class)
public class UacLogServiceImpl extends ServiceImpl<UacLogMapper, UacLog>
        implements UacLogService, UacLogDubboApi {

    @Resource
    private UacLogMapper uacLogMapper;

    @Override
    public ResponseDTO saveUacLog(UacLogDTO uacLogDTP, LoginAuthDTO loginAuthDto) {
        UacLog uacLog = new ModelMapper().map(uacLogDTP, UacLog.class);
        uacLog.setCreator(loginAuthDto.getUserName());
        uacLog.setCreatorId(loginAuthDto.getUserId());
        uacLogMapper.insert(uacLog);
        return Response.success();
    }
}

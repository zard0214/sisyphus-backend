package com.sisyphus.provider.uac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.base.wapper.Response;
import com.sisyphus.common.base.wapper.ResponseDTO;
import com.sisyphus.provider.uac.api.model.dto.UacLogDTO;
import com.sisyphus.provider.uac.api.service.UacLogDubboApi;
import com.sisyphus.provider.uac.mapper.UacLogMapper;
import com.sisyphus.provider.uac.mapper.UacUserMapper;
import com.sisyphus.provider.uac.model.domain.UacLog;
import com.sisyphus.provider.uac.model.domain.UacUser;
import com.sisyphus.provider.uac.model.dto.UacUserDTO;
import com.sisyphus.provider.uac.model.query.UacUserQuery;
import com.sisyphus.provider.uac.service.UacUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhecheng.zhao
 * @date Created in 24/06/2021 22:34
 */
@Slf4j
@Service("uacUserService")
@Transactional(rollbackFor = Exception.class)
public class UacUserServiceImpl extends ServiceImpl<UacUserMapper, UacUserDTO>
        implements UacUserService {

    @Resource
    private UacUserMapper uacUserMapper;

    @Override
    public Page<UacUserDTO> fetchUserListWithPage(UacUserQuery UacUserQuery) {
        QueryWrapper<UacUserDTO> ew = new QueryWrapper<>();
        ew.eq("login_name", UacUserQuery.getLoginName());
        return uacUserMapper.selectPage(new Page<>(UacUserQuery.getPageNum(),
                        UacUserQuery.getPageSize()),
                ew);
    }
}

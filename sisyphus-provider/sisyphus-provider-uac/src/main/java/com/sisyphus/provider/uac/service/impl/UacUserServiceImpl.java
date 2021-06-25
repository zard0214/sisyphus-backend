package com.sisyphus.provider.uac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.provider.uac.mapper.UacUserMapper;
import com.sisyphus.provider.uac.model.domain.UacUser;
import com.sisyphus.provider.uac.model.query.UacUserQuery;
import com.sisyphus.provider.uac.service.UacUserService;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 24/06/2021 22:34
 */
@Slf4j
@Service("uacUserService")
@Transactional(rollbackFor = Exception.class)
public class UacUserServiceImpl
        extends ServiceImpl<UacUserMapper, UacUser> implements UacUserService {

    @Resource
    private UacUserMapper uacUserMapper;

    @Override
    public Page<UacUser> fetchUserListWithPage(UacUserQuery UacUserQuery) {
        QueryWrapper<UacUser> ew = new QueryWrapper<>();
        if(!StrUtil.isBlank(UacUserQuery.getLoginName())){
            ew.eq("login_name", UacUserQuery.getLoginName());
        }
        if(!StrUtil.isBlank(UacUserQuery.getPhone())){
            ew.eq("phone", UacUserQuery.getPhone());
        }
        if(!StrUtil.isBlank(UacUserQuery.getEmail())){
            ew.eq("email", UacUserQuery.getEmail());
        }
        return uacUserMapper.selectPage(new Page<>(UacUserQuery.getPageNum(),
                        UacUserQuery.getPageSize()),
                ew);
    }

}

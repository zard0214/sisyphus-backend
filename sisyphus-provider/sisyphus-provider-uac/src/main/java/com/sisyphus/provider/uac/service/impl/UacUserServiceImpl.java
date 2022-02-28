package com.sisyphus.provider.uac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sisyphus.provider.uac.mapper.UacUserMapper;
import com.sisyphus.provider.uac.model.domain.UacUser;
import com.sisyphus.provider.uac.model.dto.UacUserDTO;
import com.sisyphus.provider.uac.model.query.UacUserQuery;
import com.sisyphus.provider.uac.service.UacUserService;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public Page<UacUserDTO> fetchUserListWithPage(UacUserQuery UacUserQuery) {
        List<UacUserDTO> uacUserList = Lists.newArrayList();

        QueryWrapper<UacUser> ew = new QueryWrapper<>();
        if(!StrUtil.isBlank(UacUserQuery.getLoginName())){
            ew.like("login_name", UacUserQuery.getLoginName());
        }
        if(!StrUtil.isBlank(UacUserQuery.getPhone())){
            ew.like("phone", UacUserQuery.getPhone());
        }
        if(!StrUtil.isBlank(UacUserQuery.getEmail())){
            ew.like("email", UacUserQuery.getEmail());
        }
        if(!StrUtil.isBlank(UacUserQuery.getStatus())){
            ew.eq("status", UacUserQuery.getStatus());
        }
        Page<UacUser> uacUserPage = uacUserMapper.selectPage(new Page<>(UacUserQuery.getPageNum(),
                        UacUserQuery.getPageSize()),
                ew);
        Page<UacUserDTO> result = new ModelMapper().map(uacUserPage, Page.class);
        long current = uacUserPage.getCurrent();
        if(current > 0){
            List<UacUser> records = uacUserPage.getRecords();
            records.stream().forEach(uacUser -> {
                UacUserDTO uacUserDTO = new UacUserDTO();
//                List<AuthRoleDTO> authRoles = authRoleService.findByUserId(authUserDTO.getId());
//                uacUserDTO.set
                uacUserList.add(uacUserDTO);
            });
        }
        return result;
    }

}

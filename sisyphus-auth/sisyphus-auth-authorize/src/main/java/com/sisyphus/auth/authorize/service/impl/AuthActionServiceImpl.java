package com.sisyphus.auth.authorize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.auth.authorize.mapper.AuthActionMapper;
import com.sisyphus.auth.authorize.model.domain.AuthAction;
import com.sisyphus.auth.authorize.service.AuthActionService;
import com.sisyphus.common.base.constant.GlobalConstant;
import com.sisyphus.common.base.enums.ErrorCodeEnum;
import com.sisyphus.common.base.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 18:44
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthActionServiceImpl extends ServiceImpl<AuthActionMapper, AuthAction> implements AuthActionService {

    @Resource
    private AuthActionMapper authActionMapper;

    @Override
    public List<AuthAction> getOwnActionListByUserId(Long userId) {
        if (userId == null) {
            throw new BizException(ErrorCodeEnum.UAC10011001);
        }
        List<AuthAction> authActionList;
        if (Objects.equals(userId, GlobalConstant.Sys.SUPER_MANAGER_USER_ID)) {
            // 获取全部权限信息
            authActionList = authActionMapper.selectList(null);
        } else {
            authActionList = authActionMapper.getOwnUacActionListByUserId(userId);
        }
        return authActionList;
    }
}

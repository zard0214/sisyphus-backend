package com.sisyphus.auth.authorize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisyphus.auth.authorize.mapper.AuthUserTokenMapper;
import com.sisyphus.auth.authorize.model.domain.AuthUserToken;
import com.sisyphus.auth.authorize.service.AuthUserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 14:18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthUserTokenServiceImpl extends ServiceImpl<AuthUserTokenMapper, AuthUserToken> implements AuthUserTokenService {
}

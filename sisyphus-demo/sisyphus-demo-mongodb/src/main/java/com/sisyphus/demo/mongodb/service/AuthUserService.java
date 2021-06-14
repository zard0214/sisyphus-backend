package com.sisyphus.demo.mongodb.service;

import com.sisyphus.demo.mongodb.mapper.AuthUserInfoRepository;
import com.sisyphus.demo.mongodb.mapper.AuthUserRepository;
import com.sisyphus.demo.mongodb.model.AuthUser;
import com.sisyphus.demo.mongodb.model.AuthUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 11:33
 */
@Service
public class AuthUserService {

    @Resource
    private AuthUserRepository authUserRepository;

    @Resource
    private AuthUserInfoRepository authUserInfoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        AuthUser authUser = new AuthUser(1, "admin1", "admin");
        authUserRepository.save(authUser);
        int a = 1 / 0;
        AuthUserInfo authUserInfo = new AuthUserInfo(1,  "admin1");
        authUserInfoRepository.save(authUserInfo);
    }

}


package com.sisyphus.auth.authorize.service;

import com.sisyphus.auth.authorize.model.domain.AuthAction;

import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 18:44
 */
public interface AuthActionService {

    List<AuthAction> getOwnActionListByUserId(Long userId);
}

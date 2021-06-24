package com.sisyphus.auth.authorize.service;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.sisyphus.auth.authorize.model.domain.AuthUserToken;
import com.sisyphus.common.support.service.IService;
import com.sisyphus.common.base.dto.AuthUserTokenDTO;
import com.sisyphus.common.base.dto.LoginAuthDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhecheng.zhao
 * @date Created in 10/06/2021 14:18
 */
public interface AuthUserTokenService extends IService<AuthUserToken> {

    /**
     * 根据accesstoken 获取用户登录信息
     * @param accessToken
     * @return
     */
    AuthUserTokenDTO getByAccessToken(String accessToken);

    /**
     * 更新缓存中的用户token
     * @param userTokenDto
     * @param loginAuthDTO
     */
    void updateUacUserToken(AuthUserTokenDTO userTokenDto, LoginAuthDTO loginAuthDTO);

    /**
     * 保存登录信息
     * @param accessToken
     * @param refreshToken
     * @param loginAuthDto
     * @param request
     */
    void saveUserToken(String accessToken, String refreshToken, LoginAuthDTO loginAuthDto, HttpServletRequest request);

    /**
     * 刷新token
     * @param accessToken
     * @param refreshToken
     * @param request
     * @return
     */
    String refreshToken(String accessToken, String refreshToken, HttpServletRequest request) throws HttpProcessException;
}

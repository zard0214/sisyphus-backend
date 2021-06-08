package com.sisyphus.auth.core.qq.connet;

import com.sisyphus.auth.core.qq.api.QQ;
import com.sisyphus.auth.core.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:44
 */
public class QQApiAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        // 测试服务是否可用
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null); // 主页地址，像微博一般有主页地址
        // 服务提供商返回的该user的openid
        // 一般来说这个openid是和你的开发账户也就是appid绑定的
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        // 暂时不知道有什么用处
        return UserProfile.EMPTY;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        // 应该是退出的状态操作。
    }
}
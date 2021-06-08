package com.sisyphus.auth.app.social;

import com.sisyphus.auth.app.config.AppConstants;
import com.sisyphus.auth.app.config.AppSecretException;
import com.sisyphus.auth.core.social.SignUpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 17:44
 */
@Component
public class AppSignUpUtils implements SignUpUtils {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    // 目前为止都是自动配置的，直接获取即可
    @Resource
    private UsersConnectionRepository usersConnectionRepository;
    @Resource
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Override
    public void saveConnection(ServletWebRequest request, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(buildKey(request), connectionData);
    }

    /**
     * @param userId
     * @param request
     */
    @Override
    public void doPostSignUp(String userId, ServletWebRequest request) {
        String key = buildKey(request);
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(getConnection(connectionFactoryLocator, connectionData));
    }

    public Connection<?> getConnection(ConnectionFactoryLocator connectionFactoryLocator, ConnectionData connectionData) {
        return connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId()).createConnection(connectionData);
    }

    private String buildKey(ServletWebRequest request) {
        String deviceId = request.getHeader(AppConstants.DEFAULT_HEADER_DEVICE_ID);
        if (StringUtils.isBlank(deviceId)) {
            throw new AppSecretException("设备id参数不能为空");
        }
        return "imooc:security:social.connect." + deviceId;
    }
}

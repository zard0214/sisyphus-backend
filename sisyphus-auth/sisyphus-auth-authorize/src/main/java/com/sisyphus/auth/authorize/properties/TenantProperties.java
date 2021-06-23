package com.sisyphus.auth.authorize.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 21:22
 */

@Data
@Component
@EnableConfigurationProperties(TenantProperties.class)
@ConfigurationProperties(prefix = "sisyphus.tenant")
public class TenantProperties {

    /**
     * 是否开启租户模式
     */
    private Boolean enable = false;

    /**
     * 需要排除的多租户的表
     */
    private List<String> ignoreTables = new ArrayList();

    /**
     * 多租户字段名称
     */
    private String column = "tenant_id";
}

package com.sisyphus.auth.authorize.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.sisyphus.auth.authorize.properties.TenantProperties;
import com.sisyphus.common.base.constant.GlobalConstant;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.support.util.ThreadLocalMap;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.jsqlparser.expression.Expression;

import javax.annotation.Resource;

/**
 * @author zhecheng.zhao
 * @date Created in 12/06/2021 11:51
 */
@Configuration
@MapperScan("com.sisyphus.auth.authorize.mapper")
public class MybatisPlusConfig {

    @Resource
    private TenantProperties tenantProperties;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public String getTenantIdColumn() {
                return tenantProperties.getColumn();
            }
            @Override
            public Expression getTenantId() {
                Long tenantId = (Long) ThreadLocalMap.get(GlobalConstant.Sys.TENANT_ID);
                if (tenantId != null) {
                    return new StringValue(String.valueOf(tenantId));
                }
                return new NullValue();
            }
            @Override
            public boolean ignoreTable(String tableName) {
                return tenantProperties.getIgnoreTables().stream().anyMatch(
                        (t) -> t.equalsIgnoreCase(tableName)
                );
            }
        }));

        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        //分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}

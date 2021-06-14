package com.sisyphus.demo.mongodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 11:26
 */

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String mongoDataBaseName;

    @Bean
    @ConditionalOnProperty(name="spring.data.mongodb.transactionEnabled",havingValue = "true")
    public MongoTransactionManager transactionManager(SimpleMongoClientDatabaseFactory factory){
        return new MongoTransactionManager(factory);
    }

    @Override
    protected String getDatabaseName() {
        return mongoDataBaseName;
    }

}

package com.sisyphus.demo.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author zhecheng.zhao
 * @date Created in 16/06/2021 00:14
 */
@Slf4j
@Service
public class DataService {

//    @Cached(name = "userCache", key = "#userId", expire = 3600, cacheType = CacheType.BOTH, timeUnit =  TimeUnit.MINUTES)
    public String userCache(String userId){
//        Data data = new Data("abc");
        return System.currentTimeMillis() + "";
    }
}

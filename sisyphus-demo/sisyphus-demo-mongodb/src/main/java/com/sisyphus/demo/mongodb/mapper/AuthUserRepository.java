package com.sisyphus.demo.mongodb.mapper;

import com.sisyphus.demo.mongodb.model.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhecheng.zhao
 * @date Created in 14/06/2021 11:29
 */
@Repository
public interface AuthUserRepository extends MongoRepository<AuthUser, String> {
}

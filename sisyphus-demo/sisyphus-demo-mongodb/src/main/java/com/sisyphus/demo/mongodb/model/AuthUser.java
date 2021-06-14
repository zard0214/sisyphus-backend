package com.sisyphus.demo.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author zhecheng.zhao
 * @date Created in 22/05/2021 06:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="authUser")
public class AuthUser {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 登录名
     */
    @Field("loginName")
    private String loginName;

    /**
     * 登录密码
     */
    @Field("loginPwd")
    private String loginPwd;


}

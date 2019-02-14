package com.g7go.redis.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @Author: Mr_li
 * @CreateDate: 2019-02-14$ 13:48$
 * @Version: 1.0
 */
@Data
public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    private String rediskey;


}

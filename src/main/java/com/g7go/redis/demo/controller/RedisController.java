package com.g7go.redis.demo.controller;

import com.g7go.redis.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试redis
 *
 * @Author: Mr_li
 * @CreateDate: 2019-02-14$ 13:45$
 * @Version: 1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    private String testString = "testString";
    private String userKey = "userKey";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public String add() {
        //1,添加一个Value为String
        stringRedisTemplate.opsForValue().set(testString, "测试存储字符串类型");
        //2,添加一个Value为对象
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("1111");
        user.setRediskey(userKey);
        redisTemplate.opsForValue().set(user.getRediskey(), user);
        return "成功";
    }

    @GetMapping("/getUser")
    public User findUserByKey() {
        User user = (User) redisTemplate.opsForValue().get(userKey);
        return user;
    }

    @GetMapping("/getString")
    public String findString() {
        String s = stringRedisTemplate.opsForValue().get(testString);
        return s;
    }

    @GetMapping("/delete")
    public String deleteByKey() {
        //1,删除string类型
        stringRedisTemplate.delete(testString);
        //2,删除user对象
        redisTemplate.delete(userKey);
        return "删除成功";
    }
}
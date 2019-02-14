package com.g7go.redis.demo.controller;

import com.g7go.redis.demo.pojo.User;
import com.g7go.redis.demo.service.RedisServiceForAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 基于注解的缓存
 *
 * @Author: Mr_li
 * @CreateDate: 2019-02-14$ 15:41$
 * @Version: 1.0
 */
@RestController
public class RedisControllerForAnnotation {

    @Autowired
    private RedisServiceForAnnotation redisServiceForAnnotation;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable("id") Integer id) {
        long beginTime = System.currentTimeMillis();
        User cat = redisServiceForAnnotation.findOneUser(id);
        long time = System.currentTimeMillis() - beginTime;
        System.out.println(time);
        return cat;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void createCat(@RequestBody User user) {
        redisServiceForAnnotation.saveUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Integer id) {
        redisServiceForAnnotation.deleteUser(id);
    }
}

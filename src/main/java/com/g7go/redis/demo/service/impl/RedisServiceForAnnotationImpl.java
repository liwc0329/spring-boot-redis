package com.g7go.redis.demo.service.impl;

import com.g7go.redis.demo.pojo.User;
import com.g7go.redis.demo.service.RedisServiceForAnnotation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用描述
 *
 * @Author: Mr_li
 * @CreateDate: 2019-02-14$ 15:44$
 * @Version: 1.0
 */
@Service
public class RedisServiceForAnnotationImpl implements RedisServiceForAnnotation {

    /**
     * @param user
     * @CachePut作用是主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，
     * 和 @Cacheable 不同的是，它每次都会触发真实方法的调用
     */
    @CachePut(value = "baseUserInfo")
    @Override
    public void saveUser(User user) {
        System.out.println("开始保存.....");
        //保存
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    /**
     * @param id
     * @CacheEvict 是用来标注在需要清除缓存元素的方法或类上的。
     * 当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作
     */
    @CacheEvict(value = "user")
    @Override
    public void deleteUser(int id) {
        System.out.println(id);

    }

    @Override
    public List<User> getUserAll() {
        User user = getUser();
        List<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }

    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setPassword("11");
        user.setUsername("张三");
        user.setRediskey("key");
        return user;
    }

    /**
     * @param id
     * @return
     * @Cacheable 作用是主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
     */
    @Cacheable(value = "user", keyGenerator = "accountKeyGenerator")
    @Override
    public User findOneUser(Integer id) {
        System.out.println("开始查询.....");
        return getUser();
    }


}

package com.g7go.redis.demo.service;

import com.g7go.redis.demo.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注解实现缓存服务类
 *
 * @Author: Mr_li
 * @CreateDate: 2019-02-14$ 15:43$
 * @Version: 1.0
 */
public interface RedisServiceForAnnotation {

    void saveUser(User user);

    int updateUser(User user);

    void deleteUser(int id);

    List<User> getUserAll();

    User findOneUser(Integer id);
}

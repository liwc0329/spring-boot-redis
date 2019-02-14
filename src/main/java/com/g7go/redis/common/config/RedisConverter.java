package com.g7go.redis.common.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Redis缓存配置类
 *
 * @Author: Mr_li
 * @CreateDate: 2019-02-14$ 13:28$
 * @Version: 1.0
 */
public class RedisConverter implements RedisSerializer<Object> {

    /**
     * 序列化器
     */
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    /**
     * 反序列化器
     */
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    /**
     * 将对象序列化成字节数组
     *
     * @param o
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return new byte[0];
        }
        try {
            return serializer.convert(o);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /**
     * 将字节数组反序列化成对象
     *
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
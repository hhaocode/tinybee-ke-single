package cn.tinybee.ke.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
@Component
public class RedisService {

    public static final int EXPRIE_TIME = 3600 * 24;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, int exp) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value, exp, TimeUnit.SECONDS);
    }

    public <T> T get(String key, Class<T> clazz) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return (T) value;
    }

    public boolean expire(String key, int exp) {
        return redisTemplate.expire(key, exp, TimeUnit.SECONDS);
    }

    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
}

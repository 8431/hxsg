package com.hxsg.redisService.util;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service("RedisTemplateUtil")
public class RedisTemplateUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    public void set(String key, Object value,long time) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        redisTemplate.expire(key,time,null);
        valueOperations.set(key, value);
        //BoundValueOperations的理解对保存的值做一些细微的操作
//        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setList(String key, Object value) {
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, value);
    }

    public Object getList(String key) {
        return redisTemplate.opsForList().range(key,0l,-1l);
    }
    public void setSet(String key, Set<?> value) {
        SetOperations setOperations = redisTemplate.opsForSet();

        setOperations.add(key, value);
    }

    public Object getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    public void setHash(String key, Map<String, ?> value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, value);
    }

    public Object getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

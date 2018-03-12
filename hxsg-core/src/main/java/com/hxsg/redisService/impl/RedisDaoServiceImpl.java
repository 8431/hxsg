package com.hxsg.redisService.impl;


import com.hxsg.redisService.RedisDaoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Service("RedisDaoService")
public class RedisDaoServiceImpl implements RedisDaoService {
    private Logger logger = Logger.getLogger(RedisDaoServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Boolean set(final String key,final  Object value, final Long time) throws  Exception{
        if(time==null){
            redisTemplate.opsForValue().set(key,value);
        }else{
            redisTemplate.opsForValue().set(key,value,time, TimeUnit.MILLISECONDS);
        }
        return true;
    }
    @Override
    public Boolean set(final String key,final  Object value) throws  Exception{
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    @Override
    public Object get(String key) throws  Exception {

        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean lpush(String key, Object value) throws Exception {
            Boolean re=false;
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.leftPush(key, value);
            re=true;
        return re;
    }

    @Override
    public Object lrange(String key, Long start, Long end) throws Exception {
            Object ob=null;
            ListOperations listOperations = redisTemplate.opsForList();
            ob=listOperations.range(key, start,end);
        return ob;
    }
    @Override
    public Set<String> keys(String key) throws Exception {
        return (Set<String>) redisTemplate.keys(key);
    }

    @Override
    public void del(Collection c) throws Exception {
        redisTemplate.delete(c);
    }

}
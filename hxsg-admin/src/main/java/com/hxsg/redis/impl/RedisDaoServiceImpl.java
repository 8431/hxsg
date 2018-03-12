//package com.hxsg.redis.impl;
//
//import com.hxsg.redis.RedisDaoService;
//import com.hxsg.vo.CasinoMsg;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.core.*;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by Administrator on 2017/5/11 0011.
// */
//@Service("RedisDaoService2")
//public class RedisDaoServiceImpl  implements RedisDaoService  {
//    private Logger logger =Logger.getLogger(RedisDaoServiceImpl.class);
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Override
//    public Object set(final String key,final  Object value, final Long time) {
//        try{
//
//            SessionCallback<Object> sc=new SessionCallback<Object>(){
//                @Override
//                public Object execute(RedisOperations kvRedisOperations) throws DataAccessException {
//                    kvRedisOperations.opsForValue().set(key,value);
//                    if(time!=null)
//                    kvRedisOperations.expire(key,time,TimeUnit.SECONDS);
//                    return null;
//                }
//            };
//            redisTemplate.execute(sc);
//        }catch (Exception e){
//            logger.error("RedisDaoServiceImpl.set(String key, Object value, Long time)异常："+e.getMessage(),e);
//        }
//        return null;
//    }
//
//    @Override
//    public Object get(String key)  {
//        try{
//            return redisTemplate.opsForValue().get(key);
//        }catch (Exception e){
//            logger.error("RedisDaoServiceImpl.get(String key)异常："+e.getMessage(),e);
//
//        }
//        return null;
//    }
//
//    @Override
//    public Object setLpush(String key, Object value) throws Exception {
//        try{
//            ListOperations listOperations = redisTemplate.opsForList();
//            listOperations.leftPush(key, value);
//        }catch (Exception e){
//            logger.error("RedisDaoServiceImpl.setLpush(String key, Object value) 异常："+e.getMessage(),e);
//
//        }
//
//
//        return null;
//    }
//
//    @Override
//    public Object lrange(String key, Long start, Long end) throws Exception {
//        Object ob=null;
//        try{
//            ListOperations listOperations = redisTemplate.opsForList();
//            ob=listOperations.range(key, start,end);
//        }catch (Exception e){
//            logger.error("RedisDaoServiceImpl.setLpush(String key, Object value) 异常："+e.getMessage(),e);
//
//        }
//        return ob;
//    }
//
//
//}

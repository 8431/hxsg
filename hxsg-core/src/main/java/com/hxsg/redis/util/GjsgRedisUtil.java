package com.hxsg.redis.util;

import com.hxsg.po.NewRole;
import com.hxsg.redisService.RedisDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Service("GjsgRedisUtil")
public class GjsgRedisUtil {
    public static final String ROLEMSG="role:msg:";
    @Autowired
    public RedisDaoService redisdaoservice;
    public   NewRole getRole(Integer roleId){
        NewRole role=null;
        try {
            role= (NewRole)redisdaoservice.get(ROLEMSG+roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  role;
    }
    public   void setRole(NewRole role){
        try {
            redisdaoservice.set(ROLEMSG+role.getId(),role,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

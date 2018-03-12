//package com.hxsg.redis.util;
//
//import com.hxsg.index.RoleMsgUtil;
//import com.hxsg.po.NewRole;
//import com.hxsg.redis.RedisDaoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Administrator on 2017/5/12 0012.
// */
//@Service("GjsgRedisUtil")
//public class GjsgRedisUtil {
//    @Autowired
//    public   RedisDaoService redisdaoservice;
//     public   NewRole getRole(Integer roleId){
//        NewRole role=null;
//        try {
//            role= (NewRole)redisdaoservice.get(RoleMsgUtil.ROLEMSG+roleId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  role;
//    }
//    public   void setRole(NewRole role){
//        try {
//            redisdaoservice.set(RoleMsgUtil.ROLEMSG+role.getId(),role,null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

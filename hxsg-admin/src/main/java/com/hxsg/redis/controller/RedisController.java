//package com.hxsg.redis.controller;
//
//import com.hxsg.CommonUtil.util.PkMap;
//import com.hxsg.pk.service.PkService;
//import com.hxsg.redis.RedisDaoService;
//import com.hxsg.redis.util.RedisTemplateUtil;
//import com.hxsg.vo.CasinoMsg;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.ListOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by dlf on 2016/12/28.
// */
//@Controller
//@RequestMapping("cocos2dRedis")
//public class RedisController {
//    @Autowired
//    RedisDaoService redisdaoservice;
//    @Autowired
//    private RedisTemplate redisTemplate;
//    private Logger logger =Logger.getLogger(RedisController.class);
//
//    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String pushSystemMsg(
//            HttpSession session,HttpServletRequest request,HttpServletResponse response){
//        try{
//
//            CasinoMsg cm=new CasinoMsg();
//            cm.setBaoZiJin(1000);
//            cm.getMp().put("dlf", "AAA");
//            ListOperations listOperations = redisTemplate.opsForList();
//            listOperations.leftPush("test:list:1000", cm);
//            listOperations.leftPush("test:list:1000", cm);
//            List<CasinoMsg>li=redisTemplate.opsForList().range("test:list:1000",0L,1L);
//            System.out.println(li.size());
//        }catch (Exception e){
//          e.printStackTrace();
//        }
//        return null;
//    }
//
//}

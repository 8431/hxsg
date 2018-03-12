package com.hxsg.login.controller;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.SimpleResult;
import com.hxsg.erroptype.LoginErrorType;
import com.hxsg.login.service.Cocos2dLoginService;
import com.hxsg.po.Acount;
import com.hxsg.po.NewRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/9/29.
 */
@Controller
@RequestMapping("cocos2dLogin")
public class Cocos2dLoginController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dLoginController.class);
    @Autowired
    Cocos2dLoginService cocos2dloginservice;
    //验证角色名是否重复
    @RequestMapping(value = "/checkRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
        public SimpleResult checkRole(NewRole re){
        SimpleResult sr=null;
        String result=null;
        try{
            result= cocos2dloginservice.checkRole(re);
            sr=SimpleResult.success();
            sr.put("data",result);
        }catch (CommonException e){
            logger.error("控制层--验证角色名是否重复异常checkRole："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        return sr;
    }
    //验证账号名是否重复
    @RequestMapping(value = "/checkAcount", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public SimpleResult checkAcount(Acount re){
        SimpleResult sr=null;
        try{
                String  result= cocos2dloginservice.checkAcount(re);
                sr=SimpleResult.success();
                sr.put("data",result);
        }catch (CommonException e){
            logger.error("控制层--账号名是否重复异常checkAcount："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());


        }
        return sr;
    }
    //选择角色
    @RequestMapping(value = "/selectRole")
    @ResponseBody
    public SimpleResult selectRole(NewRole re,HttpServletRequest request,HttpServletResponse response){
        SimpleResult sr=null;
        try{
            re.setKeys(request.getHeader("key"));
           Object  result=cocos2dloginservice.selectRole(re);
           sr=SimpleResult.success();
           sr.put("data",result);

        }catch (CommonException e){
            logger.error("控制层--选择角色："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        return sr;
    }
    //登录
    @RequestMapping(value = "/login")
    @ResponseBody
    public SimpleResult login(Acount re){
        SimpleResult sr=null;
        try{
            Object result=cocos2dloginservice.login(re);
            sr=SimpleResult.success();
            sr.put("data",result);
        }catch (CommonException e){
            logger.error("控制层--登录游戏login："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        return sr;
    }

    //创建角色
    @RequestMapping(value = "/createRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public SimpleResult createRole(NewRole re,HttpServletRequest request,HttpServletResponse response){
        SimpleResult sr=null;
        try{
           String result=cocos2dloginservice.creatRole(re);
            sr=SimpleResult.success();
            sr.put("data",result);
        }catch (CommonException e){
            logger.error("控制层--创建角色createRole："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        return sr;
    }
    //注册
    @RequestMapping(value = "/creatAccount", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public SimpleResult creatAccount(Acount re){
        SimpleResult sr=null;
        try{

            String result=cocos2dloginservice.creatAccount(re);
            sr=SimpleResult.success();
            sr.put("data",result);
        }catch (CommonException e){
            logger.error("控制层--创建账号creatAccount："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());

        }
        return sr;
    }
    //加载角色选择界面
    @RequestMapping(value = "/loadSelectRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public SimpleResult LoadSelectRole(Acount re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        SimpleResult sr=null;
        try{
            List<NewRole> li=cocos2dloginservice.LoadSelectRole(re.getKey());
            sr=SimpleResult.success();
            sr.put("data",li);
        }catch (CommonException e){
            logger.error("控制层--创建账号creatAccount："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        return sr;
    }
    //检测游戏版本是否需要更新
    @RequestMapping(value = "/checkVersion", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public SimpleResult checkVersion(){
        SimpleResult sr=null;
        try{
            String  re=cocos2dloginservice.checkVersion();
            sr=SimpleResult.success();
            sr.put("data",re);
        }catch (CommonException e){
            logger.error("控制层--checkVersion："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        return sr;
    }
}

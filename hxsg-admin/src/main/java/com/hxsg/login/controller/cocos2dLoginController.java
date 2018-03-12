package com.hxsg.login.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.login.Login;
import com.hxsg.CommonUtil.util.ErrorType;
import com.hxsg.login.service.cocos2dLoginService;
import com.hxsg.po.Acount;
import com.hxsg.po.NewRole;
import com.hxsg.po.Role;
import com.hxsg.redis.RedisDaoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.enterprise.inject.New;
import javax.servlet.http.Cookie;
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
public class cocos2dLoginController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(cocos2dLoginController.class);
    @Autowired
    cocos2dLoginService cocos2dloginservice;

    //验证角色名是否重复
    @RequestMapping(value = "/checkRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
        public String checkRole(NewRole re,HttpServletRequest request,HttpServletResponse response){
        String result=null;
        try{
            if(!StringUtils.isEmpty(re.getRolename())){
                result= cocos2dloginservice.checkRole(re);
                CommonUtilAjax.sendAjax(result, request, response);
            }else{
                result="false";
                CommonUtilAjax.sendAjax(result,request,response);
            }
        }catch (Exception e){
            logger.error("控制层--验证角色名是否重复异常checkRole："+e.getMessage());
            result=ErrorType.SERVERERROR;
            CommonUtilAjax.sendAjax(result,request,response);
        }
        return null;
    }
    //验证账号名是否重复
    @RequestMapping(value = "/checkAcount", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String checkAcount(Acount re,HttpServletRequest request,HttpServletResponse response){
        String result=null;
        try{
            if(!StringUtils.isEmpty(re.getName())){
                result= cocos2dloginservice.checkAcount(re);
                CommonUtilAjax.sendAjax(result, request, response);
            }else{
                result="false";
                CommonUtilAjax.sendAjax(result,request,response);
            }
        }catch (Exception e){
            logger.error("控制层--账号名是否重复异常checkAcount："+e.getMessage());
            result=ErrorType.SERVERERROR;
            CommonUtilAjax.sendAjax(result,request,response);
        }
        return null;
    }
    //选择角色
    @RequestMapping(value = "/selectRole")
    @ResponseBody
    public String selectRole(NewRole re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        Object result=null;
        try{
            Map<String,Object> mp=Constants.loginMap;
            String key=request.getHeader("key");
            Login ln= (Login) mp.get(key);
            session.setAttribute("roleList",ln.getLiRole());
             result=cocos2dloginservice.selectRole(re,request);
            CommonUtilAjax.sendAjax(result,request,response);
        }catch (Exception e){
            logger.error("控制层--选择角色："+e.getMessage());
            result=ErrorType.SERVERERROR;
            CommonUtilAjax.sendAjax(result,request,response);
        }
        return null;
    }
    //登录
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(Acount re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        Object result=null;
        try{
            re.setKey(request.getHeader("key"));
            result=cocos2dloginservice.login(re,session);
            CommonUtilAjax.sendAjax(result,request,response);
        }catch (Exception e){
            logger.error("控制层--登录游戏login："+e.getMessage());
            result=ErrorType.SERVERERROR;
            CommonUtilAjax.sendAjax(result,request,response);
        }
        return null;
    }

    //创建角色
    @RequestMapping(value = "/createRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String createRole(NewRole re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        String result=null;
        try{
            result=cocos2dloginservice.creatRole(re,request);
            CommonUtilAjax.sendAjax(result,request,response);
        }catch (Exception e){
            logger.error("控制层--创建角色createRole："+e.getMessage());
            result=ErrorType.SERVERERROR;
            CommonUtilAjax.sendAjax(result,request,response);
        }
        return null;
    }
    //注册
    @RequestMapping(value = "/creatAccount", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String creatAccount(Acount re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        String result=null;
        try{

            result=cocos2dloginservice.creatAccount(re);
            CommonUtilAjax.sendAjax(result,request,response);
        }catch (Exception e){
            logger.error("控制层--创建账号creatAccount："+e.getMessage());
            result=ErrorType.SERVERERROR;
            CommonUtilAjax.sendAjax(result,request,response);
        }
        return null;
    }
    //加载角色选择界面
    @RequestMapping(value = "/loadSelectRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String LoadSelectRole(Acount re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Map<String,Object> mp=Constants.loginMap;
            String key=request.getHeader("key");
            Login ln= (Login)mp.get(key);
            List<NewRole> li=ln.getLiRole();
            CommonUtilAjax.sendAjax(li,request,response);
        }catch (Exception e){
            logger.error("控制层--创建账号creatAccount："+e.getMessage());
        }
        return null;
    }
}

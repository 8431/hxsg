package com.hxsg.xunlian.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.po.XunLian;
import com.hxsg.xunlian.service.Cocos2dXunLianService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dlf on 2016/9/29.
 */
@Controller
@RequestMapping("Cocos2dXunLian")
public class Cocos2dXunLianController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dXunLianController.class);
    @Autowired
    Cocos2dXunLianService cocos2dxunlianservice;

    //训练
    @RequestMapping(value = "/startTraining", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String startTraining(
            @RequestParam(value = "num", required = false) Integer num,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
           Integer roleId= (Integer) session.getAttribute("roleId");
            String msg=cocos2dxunlianservice.StartTraining(roleId,num);
            CommonUtilAjax.sendAjaxList("msg",msg,request, response);
        }catch (Exception e){
            logger.error("控制层--Cocos2dXunLian/startTraining："+e.getMessage());
        }
        return null;
    }
    //查询训练详情
    @RequestMapping(value = "/training", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String training(
            @RequestParam(value = "status", required = false) String status,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
            List<XunLian> msg=cocos2dxunlianservice.Training(status);
            CommonUtilAjax.sendAjaxList("msg",msg,request, response);
        }catch (Exception e){
            logger.error("控制层--Cocos2dXunLian/training："+e.getMessage());
        }
        return null;
    }
    //
    @RequestMapping(value = "/queryJingYan", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryJingYan(
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
           XunLian msg=cocos2dxunlianservice.queryJingYan(roleId);
            CommonUtilAjax.sendAjaxList("msg",msg,request, response);
        }catch (Exception e){
            logger.error("控制层--Cocos2dXunLian/queryJingYan："+e.getMessage());
        }
        return null;
    }
    //领取经验
    @RequestMapping(value = "/getJingYan", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getJingYan(
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
            String msg=cocos2dxunlianservice.getJingYan(roleId);
            CommonUtilAjax.sendAjaxList("msg",msg,request, response);
        }catch (Exception e){
            logger.error("控制层--Cocos2dXunLian/queryJingYan："+e.getMessage());
        }
        return null;
    }

}

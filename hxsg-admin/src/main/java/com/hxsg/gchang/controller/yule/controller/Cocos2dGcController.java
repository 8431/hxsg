package com.hxsg.gchang.controller.yule.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.gchang.controller.yule.service.Cocos2dGcService;
import com.hxsg.gchang.controller.yule.service.impl.Cocos2dGcServiceImpl;
import com.hxsg.po.Role;
import com.hxsg.po.YlDxXq;
import com.hxsg.vo.CasinoMsg;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dlf on 2016/9/29.
 */
@Controller
@RequestMapping("cocos2Gc")
public class Cocos2dGcController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dGcController.class);
    @Autowired
    Cocos2dGcService cocos2dgcservice;
    //娱乐场-猜猜猜--页面数据加载
    @RequestMapping(value = "/queryCasinoMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryCasinoMsg(HttpServletRequest request,HttpServletResponse response){
        try{
            CasinoMsg cg=cocos2dgcservice.queryCasinoMsg();
            CommonUtilAjax.sendAjaxList("msg", cg, request, response);
        }catch (Exception e){
            logger.error("控制层--赌场页面数据加载异常：" + e.getMessage());
        }
        return null;
    }
    //娱乐场-猜猜猜--历史查询
    @RequestMapping(value = "/queryCasinoHistory", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryCasinoHistory(Role re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Object ot=cocos2dgcservice.queryCasinoHistory();
            CommonUtilAjax.sendAjaxList("msg", ot, request, response);
        }catch (Exception e){
            logger.error("控制层--赌场-历史查询页面数据加载异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-猜猜猜--投注记录
    @RequestMapping(value = "/queryBettingRecord", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryBettingRecord(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            Object ot=cocos2dgcservice.queryBettingRecord(roleId);
            CommonUtilAjax.sendAjaxList("msg", ot, request, response);
        }catch (Exception e){
            logger.error("控制层--赌场-投注记录查询页面数据加载异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-猜猜猜--赚钱排行
    @RequestMapping(value = "/queryWinJinBang", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryWinJinBang(HttpServletRequest request,HttpServletResponse response){
        try{
            Object ot=cocos2dgcservice.queryWinJinBang();
            CommonUtilAjax.sendAjaxList("msg", ot, request, response);
        }catch (Exception e){
            logger.error("控制层--赌场-赚钱排行查询页面数据加载异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-猜猜猜--开盘数据
    @RequestMapping(value = "/DataGeneration", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String DataGeneration(HttpServletRequest request,HttpServletResponse response){
        try{
             cocos2dgcservice.DataGeneration();
        }catch (Exception e){
            logger.error("控制层--赌场-开盘数据异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-猜猜猜--角色押注逻辑操作
    @RequestMapping(value = "/roleStakeResult", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String roleStakeResult(YlDxXq yq,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");

            String result=cocos2dgcservice.roleStakeResult(roleId,yq);
            CommonUtilAjax.sendAjaxList("msg",result,request, response);
        }catch (Exception e){
            logger.error("控制层--赌场-开盘数据异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-猜猜猜--赌场时间
    @RequestMapping(value = "/Casinotime", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String Casinotime(HttpServletRequest request,HttpServletResponse response)throws Exception {
        try {
            CommonUtilAjax.sendAjaxList("times", Cocos2dGcServiceImpl.times,request,response);
        } catch (Exception e) {
            logger.error("控制层--赌场-开盘时间异常："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    //娱乐场-赢家
    @RequestMapping(value = "/queryWinMoneyRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryWinMoneyRole(HttpServletRequest request,HttpServletResponse response)throws Exception {
        try {
            Object ot=cocos2dgcservice.queryWinMoneyRole();
            CommonUtilAjax.sendAjaxList("msg",ot,request,response);
        } catch (Exception e) {
            logger.error("控制层--赌场-赢家异常："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}

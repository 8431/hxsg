package com.hxsg.gchang.controller.yule.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.gchang.controller.yule.service.Cocos2dGcService;
import com.hxsg.gchang.controller.yule.service.Cocos2dGcYuLeChiBiGameService;
import com.hxsg.gchang.controller.yule.service.impl.Cocos2dGcServiceImpl;
import com.hxsg.gchang.controller.yule.service.impl.Cocos2dGcYuLeChiBiGameServiceImpl;
import com.hxsg.po.ChibiYazhuDetail;
import com.hxsg.po.Role;
import com.hxsg.po.YlDxXq;
import com.hxsg.vo.CasinoMsg;
import com.hxsg.vo.ChiBiMsgVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dlf on 2016/9/29.
 */
@Controller
@RequestMapping("cocos2dChiBi")
public class Cocos2dChiBiGameController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dChiBiGameController.class);
    @Autowired
    Cocos2dGcService cocos2dgcservice;
    @Autowired
    Cocos2dGcYuLeChiBiGameService cocos2dgcyulechibigameservice;
    //娱乐场-赤壁--页面数据加载
    @RequestMapping(value = "/queryCasinoMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryCasinoMsg(HttpServletRequest request,HttpServletResponse response){
        try{
            ChiBiMsgVo cg= cocos2dgcyulechibigameservice.loadChiBiDetail(Cocos2dGcYuLeChiBiGameServiceImpl.num);
            CommonUtilAjax.sendAjaxList("msg", cg, request, response);
        }catch (Exception e){
            logger.error("控制层--赤壁页面数据加载异常：" + e.getMessage());
        }
        return null;
    }
    //娱乐场-赤壁--历史查询
    @RequestMapping(value = "/queryCasinoHistory", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryCasinoHistory(HttpServletRequest request,HttpServletResponse response){
        try{
            Object ot=cocos2dgcyulechibigameservice.queryHistoryRecord();
            CommonUtilAjax.sendAjaxList("msg", ot, request, response);
        }catch (Exception e){
            logger.error("控制层--赌场-历史查询页面数据加载异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-赤壁--投注记录
    @RequestMapping(value = "/queryBettingRecord", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryBettingRecord(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            Object ot=cocos2dgcyulechibigameservice.queryMySelfRecord(roleId);
            CommonUtilAjax.sendAjaxList("msg", ot, request, response);
        }catch (Exception e){
            logger.error("控制层--赌场-投注记录查询页面数据加载异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-赤壁--奖励排行
    @RequestMapping(value = "/queryWinJinBang", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryWinJinBang(HttpServletRequest request,HttpServletResponse response){
        try{
            Object ot=cocos2dgcyulechibigameservice.queryWinRecord();
            CommonUtilAjax.sendAjaxList("msg", ot, request, response);
        }catch (Exception e){
            logger.error("控制层--赤壁--奖励排行异常："+e.getMessage());
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
    //娱乐场-赤壁--角色押注逻辑操作
    @RequestMapping(value = "/roleStakeResult", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String roleStakeResult(ChibiYazhuDetail yq,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            String name=(String)session.getAttribute("roleName");
            yq.setRoleid(roleId);
            yq.setRolename(name);
            String result=cocos2dgcyulechibigameservice.roleStakeResult(roleId,yq);
            CommonUtilAjax.sendAjaxList("msg",result,request, response);
        }catch (Exception e){
            logger.error("控制层--赌场-开盘数据异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-赤壁--结算测试
    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String test(ChibiYazhuDetail yq,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
             cocos2dgcyulechibigameservice.GameAI();
        }catch (Exception e){
            logger.error("控制层--赌场-开盘数据异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-赤壁时间
    @RequestMapping(value = "/Casinotime", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String Casinotime(HttpServletRequest request,HttpServletResponse response)throws Exception {
        try {
            CommonUtilAjax.sendAjaxList("times", Cocos2dGcYuLeChiBiGameServiceImpl.CHIBITIME,request,response);
        } catch (Exception e) {
            logger.error("控制层--赌场-开盘时间异常："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    //赤壁押注-页面加载
    @RequestMapping(value = "/loadYaZhuMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String loadYaZhuMsg(HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception {
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            ChiBiMsgVo ot=cocos2dgcyulechibigameservice.loadChiBiYaZhuMsg(roleId,Cocos2dGcYuLeChiBiGameServiceImpl.num);
            CommonUtilAjax.sendAjaxList("msg",ot,request,response);
        } catch (Exception e) {
            logger.error("赤壁押注-页面加载异常："+e.getMessage());
        }
        return null;
    }
    //娱乐场-赤壁-战利品排行
    @RequestMapping(value = "/queryWinMoneyRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryWinMoneyRole(HttpServletRequest request,HttpServletResponse response)throws Exception {
        try {
            Object ot=cocos2dgcyulechibigameservice.queryWinRecordforNum(Cocos2dGcYuLeChiBiGameServiceImpl.num-1);
            CommonUtilAjax.sendAjaxList("msg",ot,request,response);
        } catch (Exception e) {
            logger.error("控制层--赤壁-战利品排行异常："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}

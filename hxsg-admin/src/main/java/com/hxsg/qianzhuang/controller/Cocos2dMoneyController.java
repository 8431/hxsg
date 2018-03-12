package com.hxsg.qianzhuang.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.po.*;
import com.hxsg.qianzhuang.service.MoneyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/11/29.
 */
@Controller
@RequestMapping("Cocos2dMoney")
public class Cocos2dMoneyController {
    private Logger logger =Logger.getLogger(Cocos2dMoneyController.class);
    @Autowired
    MoneyService moneyservice;
    @RequestMapping("/queryRoleMoney")
    @ResponseBody
    public String queryRoleMoney(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            NewRole re=moneyservice.queryRoleMoney(session);
            CommonUtilAjax.sendAjaxList("result",re,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("钱庄获取银两控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/quKuanLoading")
    @ResponseBody
    public String quKuanLoading(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            Map<String,Object> re=moneyservice.queryQuKuanLoading(roleId);
            CommonUtilAjax.sendAjaxList("result",re,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("钱庄获取银两控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/moneyRecord")
    @ResponseBody
    public String moneyRecord(
            MoneyRecord md,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            md.setRoleid(roleId);
          List<MoneyRecord> re=moneyservice.querymoneyRecord(md);
            CommonUtilAjax.sendAjaxList("result",re,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("钱庄获取银两控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/money")
    @ResponseBody
    public String money(
                NewRole ne,
                RoleMoney ry,
                @RequestParam(value = "status", required = false)String status,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {

            String re=moneyservice.money(session,ne,ry,status);
            CommonUtilAjax.sendAjaxList("result",re,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("钱庄存取银两控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/loading")
    @ResponseBody
    public String loading(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            List<GoldBusiness> li=moneyservice.queryGoldBusiness();
            CommonUtilAjax.sendAjaxList("result",li,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("loading加载求购出售金砖控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/shopping")
    @ResponseBody
    public String shopping(
            GoldBusiness gb,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            gb.setRoleid(roleId);
            gb.setRolename(roleName);
           String li=moneyservice.sellOrBuy(gb);
            CommonUtilAjax.sendAjaxList("result",li,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("loading加载求购出售金砖控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/queryGoldShopMsg")
    @ResponseBody
    public String queryGoldShopMsg(
            GoldBusiness gb,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            gb.setRoleid(roleId);
            gb.setRolename(roleName);
            List<GoldBusiness> li=moneyservice.queryGoldShopMsg(gb);
            CommonUtilAjax.sendAjaxList("result",li,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryGoldShopMsg加载求购出售金砖控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/queryTransactionRecordForGold")
    @ResponseBody
    public String queryTransactionRecordForGold(
            GoldRecord gb,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            gb.setBuyrolename(roleName);
            gb.setBuyroleid(roleId);
            List<GoldRecord> li=moneyservice.queryTransactionRecordForGold(gb);
            CommonUtilAjax.sendAjaxList("result",li,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryTransactionRecordForGold查询已成交金砖记录控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/transactionRecord")
    @ResponseBody
    public String queryNoTransactionRecordForGold(
            GoldBusiness gb,
            GoldRecord gr,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            gb.setRoleid(roleId);
            gb.setRolename(roleName);
            gr.setBuyrolename(roleName);
            gr.setBuyroleid(roleId);
            List<GoldBusiness> li=moneyservice.queryNoTransactionRecordForGold(gb);
            List<GoldRecord> li2=moneyservice.queryTransactionRecordForGold(gr);
            CommonUtilAjax.sendAjaxArray(new Object[][]{{"notTransaction",li},{"Transaction",li2}},request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryTransactionRecordForGold查询未成交金砖记录控制层异常："+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/cancelOrder ")
    @ResponseBody
    public String cancelOrder(
            GoldBusiness gb,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            gb.setRoleid(roleId);
            gb.setRolename(roleName);
            String li=moneyservice.cancelOrder(gb);
            CommonUtilAjax.sendAjaxList("msg",li,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("cancelOrder撤销订单控制层异常："+e.getMessage());
        }
        return null;
    }
}

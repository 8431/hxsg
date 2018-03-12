package com.hxsg.battle.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.battle.service.Cocos2dWaBaoService;
import com.hxsg.po.WaBao;
import com.hxsg.po.WaBaoMiaoShu;
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
 * Created by dlf on 2016/11/23.
 */
@Controller
@RequestMapping("/Cocos2dWaBao")
public class Cocos2dWaBaoController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dWaBaoController.class);
    @Autowired
    Cocos2dWaBaoService cocos2dwabaoservice;
    /**
     * 挖宝-消耗藏宝图
     */
    @RequestMapping(value = "/init", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String init(
            HttpSession session,HttpServletResponse response, HttpServletRequest request) {
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            String msg=cocos2dwabaoservice.initWaBao(roleId,roleName,"藏宝图",1);
            CommonUtilAjax.sendAjaxList("msg", msg, request, response);
        } catch (Exception e) {
            logger.error("消耗藏宝图异常"+ e.getMessage());
        }
        return null;
    }
    /**
     * 挖宝-加载挖宝界面
     */
    @RequestMapping(value = "/loadWaBao", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadWaBao(
            HttpSession session,HttpServletResponse response, HttpServletRequest request) {
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            WaBao wb=cocos2dwabaoservice.loadWaBaoCanvas(roleId,roleName);
            CommonUtilAjax.sendAjaxList("msg", wb, request, response);
        } catch (Exception e) {
            logger.error("加载挖宝界面"+ e.getMessage());
        }
        return null;
    }
    /**
     * 挖宝-开始挖宝
     */
    @RequestMapping(value = "/startWaBao", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String startWaBao(
            WaBao wbo,
            @RequestParam(value = "type",required = false)String type,
            HttpSession session,HttpServletResponse response, HttpServletRequest request) {
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            String wb=cocos2dwabaoservice.startWaBao(roleId,roleName,type,wbo);
            CommonUtilAjax.sendAjaxList("msg", wb, request, response);
        } catch (Exception e) {
            logger.error("加载挖宝界面"+ e.getMessage());
        }
        return null;
    }
    /**
     * 挖宝-继续挖宝
     */
    @RequestMapping(value = "/nextWaBao", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String nextWaBao(
            @RequestParam(value = "id",required = false)Integer id,
            HttpSession session,HttpServletResponse response, HttpServletRequest request) {
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            Boolean  wb=cocos2dwabaoservice.nextWaBao(roleId,id);
            CommonUtilAjax.sendAjaxList("msg", wb, request, response);
        } catch (Exception e) {
            logger.error("加载挖宝界面"+ e.getMessage());
        }
        return null;
    }
    /**
     * 挖宝-初始化挖到宝物信息显示
     */
    @RequestMapping(value = "/wbMessage", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String wbMessage(
            HttpSession session,HttpServletResponse response, HttpServletRequest request) {
        try {
            List<WaBaoMiaoShu> wb=cocos2dwabaoservice.getWaBaoMiaoShu();
            CommonUtilAjax.sendAjaxList("msg", wb, request, response);
        } catch (Exception e) {
            logger.error("初始化挖到宝物信息显示"+ e.getMessage());
        }
        return null;
    }
}

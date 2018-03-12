package com.hxsg.rolefujaing.controller;

import com.alibaba.fastjson.JSONObject;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.Dao.*;
import com.hxsg.po.FuJiang;
import com.hxsg.po.Role;
import com.hxsg.po.jiangjun;
import com.hxsg.po.roleFujiang;
import com.hxsg.rolefujaing.service.Cocos2dFuJiangService;
import com.hxsg.vo.FuJiangDetailVo;
import com.hxsg.vo.PeiYangFuJiangVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/Cocos2dFuJiang")
public class Cocos2dFuJiangController {
    @Autowired
    Cocos2dFuJiangService cocos2dfujiangservice;
    private Logger logger = LoggerFactory.getLogger(getClass());
    //副将培养界面数据加载。
    @ResponseBody
    @RequestMapping(value = "/loadChuZhiPeiYang", method = { RequestMethod.GET, RequestMethod.POST })
    public String czpy(@RequestParam(value = "fuId",required = false)Integer fuId,
                       HttpSession session,
                       HttpServletRequest request,HttpServletResponse response){
        Integer roleid=(Integer)session.getAttribute("roleId");
        PeiYangFuJiangVo pv= null;
        try {
            pv = cocos2dfujiangservice.loadPeiYangFuJiang(fuId,roleid);
            CommonUtilAjax.sendAjaxList("msg",pv,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //副将详情页面加载
    @ResponseBody
    @RequestMapping(value = "/loadRoleFujiang", method = { RequestMethod.GET, RequestMethod.POST })
    public String loadRoleFujiang(@RequestParam(value = "fuId",required = false)Integer fuId,
                       HttpSession session,
                       HttpServletRequest request,HttpServletResponse response){
        Integer roleid=(Integer)session.getAttribute("roleId");
        FuJiangDetailVo pv= null;
        try {
            pv = cocos2dfujiangservice.loadRoleFuJiang(roleid,fuId);
            CommonUtilAjax.sendAjaxList("msg",pv,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

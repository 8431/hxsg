package com.hxsg.rolefujaing.controller;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.SimpleResult;
import com.hxsg.rolefujaing.service.Cocos2dFuJiangService;
import com.hxsg.vo.FuJiangDetailVo;
import com.hxsg.vo.PeiYangFuJiangVo;

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

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/Cocos2dFuJiang")
public class Cocos2dFuJiangController {
    @Autowired
    Cocos2dFuJiangService cocos2dfujiangservice;
    private Logger logger =Logger.getLogger(Cocos2dFuJiangController.class);
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
    //提交属性点分配
    @ResponseBody
    @RequestMapping(value = "/commitSxd", method = {RequestMethod.POST })
    public SimpleResult  commitSxd(FuJiangDetailVo f,HttpSession session){
        SimpleResult sr=null;
        Integer roleid=(Integer)session.getAttribute("roleId");
        f.setRoleid(roleid);
        try {
          cocos2dfujiangservice.commitSxd(f);
        } catch (CommonException e) {
            logger.error("提交属性点分配："+e.getMessage());
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        sr=SimpleResult.success();
        return sr;
    }
    //设置副将参战状态
    @ResponseBody
    @RequestMapping(value = "/setFjState", method = {RequestMethod.POST })
    public SimpleResult  setFjState(FuJiangDetailVo f,HttpSession session){
        SimpleResult sr=null;
        Integer roleid=(Integer)session.getAttribute("roleId");
        f.setRoleid(roleid);
        String re="";
        try {
            re= cocos2dfujiangservice.setFjState(f);
        } catch (CommonException e) {
            logger.error("设置副将参战状态："+e.getMessage(),e);
            sr=SimpleResult.error(e.getCode(),e.getDesc());
        }
        sr=SimpleResult.success();
        sr.put("data",re);
        return sr;
    }

}

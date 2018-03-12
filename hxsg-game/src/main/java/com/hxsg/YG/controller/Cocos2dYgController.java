package com.hxsg.YG.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.Dao.RoleMapper;
import com.hxsg.Dao.RoleZbMapper;
import com.hxsg.Dao.roleFujiangMapper;
import com.hxsg.Dao.wuqiMapper;
import com.hxsg.YG.service.Cocos2dYgService;
import com.hxsg.po.Role;
import com.hxsg.po.roleFujiang;
import com.hxsg.vo.IndexFuJiangVo;
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
@RequestMapping("/cocos2dYg")
public class Cocos2dYgController {
    @Autowired
    Cocos2dYgService cocos2dygservice;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Cocos2dYgController.class);
    //主界面副将页面加载
    @RequestMapping(value = "/appfujiang", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appfujiang(HttpSession session, String sx,HttpServletRequest request,HttpServletResponse response) {
        Integer roleid= (Integer) session.getAttribute("roleId");
        try {
           List<IndexFuJiangVo> lifj=cocos2dygservice.queryRoleFuJiang(roleid, 1);
            CommonUtilAjax.sendAjaxList("msg",lifj,request,response);
        } catch (Exception e) {
            logger.error("控制层--cocos2dYg/appfujiang查询角色副将："+e.getMessage());
            e.printStackTrace();
        }
        return null;

    }
    //驿馆---副将招领页面加载
    @RequestMapping(value = "/loadYgFuJiangZhaoLing", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadYgFuJiangZhaoLing(HttpSession session, String sx,HttpServletRequest request,HttpServletResponse response) {
        Integer roleid= (Integer) session.getAttribute("roleId");
        try {
            List<IndexFuJiangVo> lifj=cocos2dygservice.queryRoleFuJiang(roleid,0);
            CommonUtilAjax.sendAjaxList("msg",lifj,request,response);
        } catch (Exception e) {
            logger.error("控制层--cocos2dYg/appfujiang查询角色副将："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    //驿馆---副将招领-留守
    @RequestMapping(value = "/ygFuJiangZhaoLing", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String ygFuJiangZhaoLing(
            @RequestParam(value = "id", required = false) Integer id,
            HttpSession session, String sx,HttpServletRequest request,HttpServletResponse response) {
        Integer roleid= (Integer) session.getAttribute("roleId");
        try {
           String msg=cocos2dygservice.fuJiangZhaoLing(roleid,id);
            CommonUtilAjax.sendAjaxList("msg",msg,request,response);
        } catch (Exception e) {
            logger.error("控制层--cocos2dYg/appfujiang查询角色副将："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
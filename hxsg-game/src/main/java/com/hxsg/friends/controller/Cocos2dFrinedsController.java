package com.hxsg.friends.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.friends.service.Cocos2dFriendsServices;
import com.hxsg.po.NewRole;
import com.hxsg.po.RoleFriends;
import com.hxsg.po.RoleFriendsMsg;
import com.hxsg.vo.FriendsVo;
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

/**
 * Created by dlf on 2016/12/12.
 */
@Controller
@RequestMapping("Cocos2dFrineds")
public class Cocos2dFrinedsController {
    private Logger logger = Logger.getLogger(Cocos2dFrinedsController.class);

    @Autowired
    Cocos2dFriendsServices cocos2dfriendsservices;

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(
            RoleFriendsMsg rf,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            Integer roleId = (Integer) session.getAttribute("roleId");
            String roleName = (String) session.getAttribute("roleName");
            rf.setRoleid(roleId);
            rf.setRolename(roleName);
           RoleFriendsMsg  msg = cocos2dfriendsservices.sendMsgForFriends(rf);
            CommonUtilAjax.sendAjaxList("msg", msg, request, response);
        } catch (Exception e) {
            logger.error("sendMsg控制层异常:" + e.getMessage());
        }
        return null;
    }

    @RequestMapping("/queryRoleFriends")
    @ResponseBody
    public String queryRoleFriends(
            RoleFriends rf,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            Integer roleId = (Integer) session.getAttribute("roleId");
            String roleName = (String) session.getAttribute("roleName");
            rf.setRoleid(roleId);
            rf.setRolename(roleName);
            List<FriendsVo> msg = cocos2dfriendsservices.queryRoleFriends(rf);
            CommonUtilAjax.sendAjaxList("msg", msg, request, response);
        } catch (Exception e) {
            logger.error("queryRoleFriends控制层异常:" + e.getMessage());
        }
        return null;
    }
    @RequestMapping("/loadFrinedsMsg")
    @ResponseBody
    public String loadFrinedsMsg(
            RoleFriendsMsg rf,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            rf.setRoleid(roleId);
            rf.setRolename(roleName);
            List<RoleFriendsMsg> msg=cocos2dfriendsservices.loadFrinedsMsg(rf);
            CommonUtilAjax.sendAjaxList("msg",msg,request,response);
        } catch (Exception e) {
            logger.error("loadFrinedsMsg控制层异常:"+e.getMessage());
        }
        return null;
    }

    /**
     * 断交好友
     * @param rf
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/relieveStatus")
    @ResponseBody
    public String relieveStatus(
            RoleFriends rf,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            rf.setRoleid(roleId);
            rf.setRolename(roleName);
            String msg=cocos2dfriendsservices.relieveStatus(rf);
            CommonUtilAjax.sendAjaxList("msg",msg,request,response);
        } catch (Exception e) {
            logger.error("loadFrinedsMsg控制层异常:"+e.getMessage());
        }
        return null;
    }

    /**
     * 拉黑，禁言
     * @param rf
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/speakStatus")
    @ResponseBody
    public String speakStatus(
            RoleFriends rf,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            rf.setRoleid(roleId);
            rf.setRolename(roleName);
            String msg=cocos2dfriendsservices.speakStatus(rf);
            CommonUtilAjax.sendAjaxList("msg",msg,request,response);
        } catch (Exception e) {
            logger.error("speakStatus控制层异常:"+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/sendAddFriendsMsg")
    @ResponseBody
    public String sendAddFriendsMsg(
            RoleFriendsMsg rf,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            rf.setRoleid(roleId);
            rf.setRolename(roleName);
            cocos2dfriendsservices.sendAddFriendsMsg(rf);
        } catch (Exception e) {
            logger.error("speakStatus控制层异常:"+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/addFriends")
    @ResponseBody
    public String addFriends(
            RoleFriends rf,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            String roleName= (String) session.getAttribute("roleName");
            rf.setFriendid(roleId);
            rf.setFriendname(roleName);
            cocos2dfriendsservices.addFriends(rf);
        } catch (Exception e) {
            logger.error("speakStatus控制层异常:"+e.getMessage());
        }
        return null;
    }
    @RequestMapping("/queryFriends")
    @ResponseBody
    public String queryFriends(
            @RequestParam(value = "msg",required =false ) String msg,
            @RequestParam(value = "type",required =false ) String type,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            List<NewRole>li=cocos2dfriendsservices.queryFriends(msg,type);
           CommonUtilAjax.sendAjaxList("msg",li,request,response);
        } catch (Exception e) {
            logger.error("speakStatus控制层异常:"+e.getMessage());
        }
        return null;
    }

}

package com.hxsg.index.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.index.service.Cocos2dIndexService;
import com.hxsg.po.*;
import com.hxsg.util.RoleUtil;
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
@RequestMapping("cocos2dIndex")
public class Cocos2dIndexController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dIndexController.class);
    @Autowired
    Cocos2dIndexService cocos2dindexservice;
    //获取玩家详情
    @RequestMapping(value = "/indexRoleMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String indexRoleMsg(NewRole re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
            //test
            if(re.getId()==null){
                re.setId(roleId);
            }
            NewRole role= cocos2dindexservice.queryRoleMsg(re.getId());
            CommonUtilAjax.sendAjaxList("roleMsg", role, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/indexRoleMsg获取角色详情："+e.getMessage());
            CommonUtilAjax.sendAjaxList("roleMsg", new Role(), request, response);
        }
        return null;
    }
    //获取玩家详情
    @RequestMapping(value = "/queryRoleMsgQx", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryRoleMsgQx(NewRole re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
            //test
            if(re.getId()==null){
                re.setId(roleId);
            }
           NewRole role=null;
            role= RoleUtil.ROLEMSG.get(roleId);
            if(role==null){
                role=cocos2dindexservice.queryRoleMsgQx(re.getId());
            }
            CommonUtilAjax.sendAjaxList("roleMsg", role, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/indexRoleMsg获取角色详情："+e.getMessage());
            CommonUtilAjax.sendAjaxList("roleMsg", new Role(), request, response);
        }
        return null;
    }
    //获取玩家详情
    @RequestMapping(value = "/queryRoleMsgId", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryRoleMsgId(NewRole re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
            //test
            if(re.getId()==null){
                re.setId(roleId);
            }
            NewRole role=cocos2dindexservice.queryRoleById(re.getId());
            CommonUtilAjax.sendAjaxList("roleMsg", role, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/indexRoleMsg获取角色详情："+e.getMessage());
            CommonUtilAjax.sendAjaxList("roleMsg", new Role(), request, response);
        }
        return null;
    }
    //获取玩家详情NEW
    @RequestMapping(value = "/indexNewRoleMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String indexNewRoleMsg(
            @RequestParam(value = "id",required =false ) Integer id,
           HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            NewRole role=cocos2dindexservice.queryRoleMsg(roleId,id);
            CommonUtilAjax.sendAjaxList("roleMsg", role, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/indexNewRoleMsg获取角色详情："+e.getMessage());
            CommonUtilAjax.sendAjaxList("roleMsg", new Role(), request, response);
        }
        return null;
    }
    //地图方位和野怪
    @RequestMapping(value = "/moveCity", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String moveCity(GjsgMap gp,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            GjsgMap gm=cocos2dindexservice.moveCity(gp.getCenterCity(),session);
            MapGuai mg=cocos2dindexservice.queryMapGuai(gp.getCenterCity());
            List<NewRole> li=cocos2dindexservice.nearbyRole(gp.getCenterCity(),request.getHeader("key"));
            Object[][] obj=new Object[][]{{"map",gm},{"guai",mg},{"near",li}};
            CommonUtilAjax.sendAjaxArray( obj, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/moveCity获取地图数据："+e.getMessage(),e);
        }
        return null;
    }
    //地图野怪
    @RequestMapping(value = "/yeGuaiQun", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String yeGuaiQun(@RequestParam(value = "yeGuai",required =false ) String yeGuai,
                            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            YeGuaiQun gm=cocos2dindexservice.queryYeGuaiQun(yeGuai);
            CommonUtilAjax.sendAjaxList("result", gm, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/yeGuaiQun地图野怪："+e.getMessage());
        }
        return null;
    }
    //附近玩家
    @RequestMapping(value = "/nearbyRole", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String nearbyRole(@RequestParam(value = "city",required =false ) String city,
                            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            List<NewRole> li=cocos2dindexservice.nearbyRole(city,request.getHeader("key"));
            CommonUtilAjax.sendAjaxList("result", li, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/nearbyRole附近玩家："+e.getMessage());
        }
        return null;
    }
    //聊天信息数据加载
    @RequestMapping(value = "/chatMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String chatMsg(HttpServletRequest request,HttpServletResponse response){
        try{
            //test
            Object[] msg=cocos2dindexservice.chatMsg();
            CommonUtilAjax.sendAjaxList("msg", msg, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dIndex/chatMsg加载聊天初始化数据详情："+e.getMessage());
        }
        return null;
    }


}

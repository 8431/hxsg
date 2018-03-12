package com.hxsg.notis.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.notis.service.Cocos2dNotisService;
import com.hxsg.po.Role;
import com.hxsg.po.SystemNotis;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**系统公告推送
 * Created by dlf on 2016/10/14.
 */
@Controller
@RequestMapping("cocos2dNotis")
public class Cocos2dNotisController {
    private Logger logger =Logger.getLogger(Cocos2dNotisController.class);
    @Autowired
    Cocos2dNotisService cocos2dnotisservice;
    @RequestMapping(value = "/pushSystemMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String pushSystemMsg(SystemNotis sn,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            sn.setData(new Date());
            String result=cocos2dnotisservice.pushSystemNotis(sn);
            CommonUtilAjax.sendAjaxList("msg", result, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dNotis推送系统公告：" + e.getMessage());
        }
        return null;
    }
    @RequestMapping(value = "/querySystemNotis", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String querySystemNotis(HttpServletRequest request,HttpServletResponse response){
        try{
            List<SystemNotis> li=cocos2dnotisservice.querySystemNotis();
            CommonUtilAjax.sendAjaxList("msg", li, request, response);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("控制层--cocos2dNotis推送系统公告：" + e.getMessage());
        }
        return null;
    }

}

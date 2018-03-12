package com.hxsg.pk.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.util.PkMap;
import com.hxsg.pk.service.PkService;
import com.hxsg.po.SystemNotis;
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
import java.util.Date;

/**
 * Created by dlf on 2016/12/28.
 */
@Controller
@RequestMapping("cocos2dPk")
public class PkController {
    @Autowired
    PkService pkservice;
    private Logger logger =Logger.getLogger(PkController.class);

    @RequestMapping(value = "/doubleStart", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String pushSystemMsg(
            @RequestParam(value = "pkId", required = true) Integer pkId,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
            pkservice.pushTime(1000,1002);
        }catch (Exception e){
          e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String add(
            @RequestParam(value = "pkId", required = true) Integer pkId,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId= (Integer) session.getAttribute("roleId");
            PkMap.PKMAP.put(pkId,pkId);
        }catch (Exception e){
        }
        return null;
    }
}

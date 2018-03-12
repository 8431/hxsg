package com.hxsg.wupin.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.po.*;
import com.hxsg.vo.IndexFuJiangVo;
import com.hxsg.vo.ZaWuDetailVo;
import com.hxsg.vo.ZhuangBeiDetailVo;
import com.hxsg.wupin.service.Cocos2dGiftPackageService;
import com.hxsg.wupin.service.Cocos2dUserWuPinService;
import com.hxsg.wupin.service.Cocos2dZhuangBeiToRole;
import com.hxsg.wupin.service.cocos2dWuPinService;
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
@RequestMapping("Cocos2dGiftPackage")
public class Cocos2dGiftPackageController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dGiftPackageController.class);
    @Autowired
    Cocos2dGiftPackageService cocos2dgiftpackageservice;

    //加载物品分类
    @RequestMapping(value = "/giftPackage", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String giftPackage(
            @RequestParam(value = "name", required = false) String name,
            HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
           Integer roleId= (Integer) session.getAttribute("roleId");
            String msg=cocos2dgiftpackageservice.giftPackage(roleId,name);
            CommonUtilAjax.sendAjaxList("msg",msg,request, response);
        }catch (Exception e){
            logger.error("控制层--Cocos2dGiftPackage/giftPackage："+e.getMessage());
        }
        return null;
    }

}

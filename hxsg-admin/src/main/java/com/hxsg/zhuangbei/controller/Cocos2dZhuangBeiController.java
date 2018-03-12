package com.hxsg.zhuangbei.controller;

/**
 * Created by dlf on 2018/1/17 0017.
 * Email 1429264916@qq.com
 */

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.SimpleResult;
import com.hxsg.yiguan.Cocos2dYaoPinService;
import com.hxsg.zhuangbei.ZhuangBeiService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cocos2dZhuangBei")
public class Cocos2dZhuangBeiController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger = Logger.getLogger(Cocos2dZhuangBeiController.class);
    @Autowired
    ZhuangBeiService zhuangbeiservice;
    @RequestMapping(value = "/queryZhuangBei", method = RequestMethod.POST)
    public SimpleResult queryZhuangBei(@RequestParam(value = "type", required = true) String type) {
        SimpleResult sr = null;
        List<Map<String, Object>> re = null;
        try {
            re = zhuangbeiservice.queryZhuangBeiShop(type);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(),e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data", re);
        return sr;
    }
    @RequestMapping(value = "/buyZhuangBei", method = RequestMethod.POST)
    public SimpleResult buyZhuangBei(@RequestParam(value = "id", required = true) String id,HttpSession session) {
        SimpleResult sr = null;
        String re = null;
        Integer roleid= (Integer) session.getAttribute("roleId");
        try {
            re = zhuangbeiservice.buyZhuangBei(roleid,id);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(),e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data", re);
        return sr;
    }



}

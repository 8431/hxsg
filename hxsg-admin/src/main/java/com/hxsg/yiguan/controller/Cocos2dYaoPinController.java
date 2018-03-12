package com.hxsg.yiguan.controller;

/**
 * Created by dlf on 2018/1/17 0017.
 * Email 1429264916@qq.com
 */

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.SimpleResult;
import com.hxsg.yiguan.Cocos2dYaoPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cocos2dYaoPin")
public class Cocos2dYaoPinController {
    @Autowired
    Cocos2dYaoPinService cocos2dyaopinservice;

    @RequestMapping(value = "/queryYaoPinDetail", method = RequestMethod.POST)

    public SimpleResult queryYaoPinDetail(@RequestParam(value = "id", required = false) String id) {
        SimpleResult sr = null;
        Map<String, Object> re = null;
        try {
            re = cocos2dyaopinservice.queryYaoPinDetail(id);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(),e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data", re);
        return sr;
    }

    @RequestMapping(value = "/buyYao", method = RequestMethod.POST)
    public SimpleResult buyYao(@RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "num", required = false) String num,
                               HttpSession session) {
        Integer roleid= (Integer) session.getAttribute("roleId");
        SimpleResult sr = null;
        Map<String, Object> re = null;
        try {
            re = cocos2dyaopinservice.buyYao(id,num,roleid.toString());
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(),e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data", re);
        return sr;
    }
    @RequestMapping(value = "/queryRoleYao", method = RequestMethod.POST)
    public SimpleResult queryRoleYao( HttpSession session) {
        Integer roleid= (Integer) session.getAttribute("roleId");
        SimpleResult sr = null;
        List<Map<String, Object> > re = null;
        try {
            re = cocos2dyaopinservice.queryRoleYao(roleid.toString());
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(),e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data", re);
        return sr;
    }

}

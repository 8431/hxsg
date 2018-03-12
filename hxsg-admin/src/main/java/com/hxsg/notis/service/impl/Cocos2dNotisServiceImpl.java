package com.hxsg.notis.service.impl;

import com.hxsg.Dao.SystemNotisMapper;
import com.hxsg.notis.service.Cocos2dNotisService;
import com.hxsg.po.SystemNotis;
import com.hxsg.system.dao.SystemNotification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dlf on 2016/10/14.
 */
@Service("Cocos2dNotisService")
public class Cocos2dNotisServiceImpl implements Cocos2dNotisService {
    @Autowired
    SystemNotisMapper systemnotismapper;
    @Autowired
    SystemNotification systemnotification;
    private Logger logger =Logger.getLogger(Cocos2dNotisServiceImpl.class);
    @Override
    public String pushSystemNotis(SystemNotis sn) {
        String result=null;
        try {
            systemnotismapper.insertSelective(sn);
            systemnotification.sendSystemMsg(sn.getMsg());
            result="true";
        } catch (Exception e) {
            result="error";
            e.printStackTrace();
            logger.error("推送系统公告Cocos2dNotisService:"+e.getMessage());
        }
        return result;

    }

    @Override
    public List<SystemNotis> querySystemNotis() throws Exception {
        return  systemnotismapper.querySystemNotis();
    }


}

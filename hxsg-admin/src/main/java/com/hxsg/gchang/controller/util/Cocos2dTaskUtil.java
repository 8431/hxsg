package com.hxsg.gchang.controller.util;

import com.hxsg.gchang.controller.yule.service.impl.Cocos2dGcServiceImpl;
import com.hxsg.gchang.controller.yule.service.impl.Cocos2dGcYuLeChiBiGameServiceImpl;
import com.hxsg.system.dao.SystemNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dlf on 2016/10/24.
 */
@Service("Cocos2dTaskUtil")
public class Cocos2dTaskUtil {
    public  synchronized void tasktime(){
        if(Cocos2dGcServiceImpl.times>0){
            Cocos2dGcServiceImpl.times-=1000;

        }
    }

}

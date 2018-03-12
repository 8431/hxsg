package com.hxsg.gchang.controller.util;

import com.hxsg.gchang.controller.yule.service.impl.Cocos2dGcYuLeChiBiGameServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by dlf on 2017/3/10.
 */
@Service("Cocos2dChiBiTime")
public class Cocos2dChiBiTime {
    public  synchronized void chiBiTime(){
        if(Cocos2dGcYuLeChiBiGameServiceImpl.CHIBITIME>0){
            Cocos2dGcYuLeChiBiGameServiceImpl.CHIBITIME-=1000;
            int minutes = (int) Math.floor(Cocos2dGcYuLeChiBiGameServiceImpl.CHIBITIME / 1000 / 60 % 60); //所余分钟数
            int second = (int) Math.floor(Cocos2dGcYuLeChiBiGameServiceImpl.CHIBITIME / 1000 % 60); //所余秒数

         //   System.out.println("赤壁时间:"+minutes+ "分" + second+ "秒后决出胜负，请求增援");

        }
    }
}

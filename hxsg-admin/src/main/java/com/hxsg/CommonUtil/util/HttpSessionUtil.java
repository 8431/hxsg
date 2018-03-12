package com.hxsg.CommonUtil.util;

import com.hxsg.CommonUtil.listener.Cocos2dHttpSessionListener;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by dlf on 2016/10/21.
 */
public class HttpSessionUtil {
    public static Object queryHttpSessionValue(String mapkey,String sessionkey)throws  Exception{
        Object ot=null;
        Map<String, HttpSession> mp= Cocos2dHttpSessionListener.HTTPSESSIONMAP;
        HttpSession httpsession=mp.get(mapkey);
        if(httpsession!=null){
            ot= httpsession.getAttribute(sessionkey);
        }
        return ot;
    }



}

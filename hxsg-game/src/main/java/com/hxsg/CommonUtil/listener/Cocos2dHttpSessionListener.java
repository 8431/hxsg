package com.hxsg.CommonUtil.listener;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dlf on 2016/10/21.
 * session管理
 */
public class Cocos2dHttpSessionListener implements HttpSessionListener{
    public static final Map<String,HttpSession>HTTPSESSIONMAP=new HashMap<String,HttpSession>();
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {



    }
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session=httpSessionEvent.getSession();
        Iterator<Map.Entry<String, HttpSession>> it = HTTPSESSIONMAP.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HttpSession> entry = it.next();
            if(entry.getValue().equals(session)){
                HTTPSESSIONMAP.remove(entry.getKey());
            }
        }
        //注销角色登录状态


    }
}

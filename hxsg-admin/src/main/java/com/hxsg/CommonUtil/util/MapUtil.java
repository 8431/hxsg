package com.hxsg.CommonUtil.util;

import org.springframework.web.socket.WebSocketSession;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by dlf on 2016/9/27.
 */
public class MapUtil {
    public static String getRoleId(Map<String,Object> mp,WebSocketSession session) throws Exception{
        String key=null;
            Iterator it = mp.keySet().iterator();
            if(it.hasNext()){
                String id= (String) it.next();
                WebSocketSession wn=(WebSocketSession) mp.get(id);
                if(wn==session){
                    key=id;
                }
            }
        return key;
    }
    public static Boolean remove(Map<String,Object> mp,Object obj) throws Exception{
        Boolean re=null;
        //基于obj被重写了equals方法
        if(mp!=null&&mp.size()>0){
            Iterator it = mp.keySet().iterator();
            while (it.hasNext()){
                String key= (String) it.next();
                Object o=mp.get(key);
                if(obj.equals(o)){
                      it.remove();
                }
            }
        }else{
            throw new RuntimeException("MAP IS NOT NULL");
        }

        return re;
    }

}

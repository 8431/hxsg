package com.hxsg.CommonUtil.util;

import com.google.gson.Gson;
import com.hxsg.po.RoleNewShuXing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/9 0009.
 */
public class MapExchangObjUtil {
    /**
     * 将json格式转换成实体类对象
     * @param map
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getObj(Map<String, Object> map, Class<T> t) throws Exception {
        T tObj = null;
        if (map != null && map.size() > 0) {
            Gson gn = new Gson();
            tObj = gn.fromJson(gn.toJson(map), t);
        }
        return tObj;
    }

    public static void main(String[] args) {


    }
}

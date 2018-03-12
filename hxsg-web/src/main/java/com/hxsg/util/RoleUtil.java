package com.hxsg.util;

import com.hxsg.po.NewRole;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public class RoleUtil {
    /**
     * 角色信息缓存数据集合
     */
    public static final Map<Integer,NewRole> ROLEMSG=new ConcurrentHashMap<Integer,NewRole>();
}

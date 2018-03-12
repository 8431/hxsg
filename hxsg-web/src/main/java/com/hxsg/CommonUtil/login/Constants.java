package com.hxsg.CommonUtil.login;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/7/15 22:04
 */
public class Constants {
    public static final Map<String,Object> loginMap=new ConcurrentHashMap<String, Object>();
    public static final Map<String,Object> loginKey=new ConcurrentHashMap<String, Object>();

    public static  String SESSION_USERNAME;
    public static  Integer roleid;
    public static  String rolename;
    public static  final Map<String,Object>SESSION_NAME=new Hashtable<String,Object>();
    public static final String SESSION_TUTORIALS = "tutorials";
    public static  final Map<String,Object>HTTPSESSION=new Hashtable<String,Object>();


}

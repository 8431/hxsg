package com.hxsg.CommonUtil.util;

import java.util.HashMap;

/**
 * Created by dlf on 2016/12/8.
 * 返回状态异常代码
 */
public class StatusNum {
    public static final String YEBZ="000100";//余额不足
    public static final String SUCCES="true";//true
    public static final String FAIL="false";//false
    public static final String ERROR="000003";//服务器代码抛异常
    public static final String SYSTEMMSG5="5";//向指定用户推送系统消息
    public static final String FAILMSG="000004";//向指定用户推送系统消息
    public static final String CHIBIYAZHU="5000";//有人押赤壁向所有人推送押注信息
    public static final String CCXZ="5001";//超出单期设定限额
    /**
     * 返回提示信息，不做任何处理
     */
    public static final String SYSTEMMSG202="202";
    /**
     * 返回提示信息，需要做处理
     */
    public static final String SYSTEMMSG201="201";
    /**
     * 发送给好友的聊天信息状态
     */
    public static final String FRIENDSMSG203="203";
    /**
     * 被强制下线
     */
    public static final String SYSTEMMSG204="204";
    /**
     * 升级动画
     */
    public static final String SYSTEMMSG210="210";
//    public  static void main(String[][]args){
//        int level=2;
//        for(int i=1;i<50;i++){
//            System.out.println("升到"+i+"级:"+((i-1)^3+60)/5*((i-1)*2+60));
//        }
//
//
//
//    }
  public static void main(String[] args){
      System.out.println((1+1)%2);
    }
}

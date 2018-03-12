package com.hxsg.CommonUtil.util;

import java.util.Date;

/**
 * Created by dlf on 2016/9/29.
 */
public class ErrorType {
    public static final String REGISTER_SUCCES ="账号名可用✔";
    public static final String REGISTER_ERROR="账号名已存在✘";
    public static final String USERNAME_SUCCES ="角色名名可用✔";
    public static final String USERNAME_ERROR="角色名已存在✘";
    public static final String SERVERERROR="ExceptionError";
    public static final String SUCCES="true";
    public static final String FAIL="false";


}
class ThreadTest extends Thread{
    private int count=1000;
    private long time=0;
    @Override
    public synchronized void run(){
        if(count==1000){
            time=new Date().getTime();
            System.out.println(new Date().getTime());
        }
        count--;
        if(count==0){
            time=new Date().getTime()-time;
            System.out.println(time);
        }


    }

    public static void main(String[]a){
        ThreadTest t1=new ThreadTest();
        for(int i=0;i<1000;i++){
            Thread r1=new Thread(t1);
            r1.start();
        }


    }

}
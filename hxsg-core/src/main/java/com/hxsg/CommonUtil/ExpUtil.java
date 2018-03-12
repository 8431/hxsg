package com.hxsg.CommonUtil;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/14 0014.
 * 经验计算工具类
 */
public class ExpUtil {
    /**
     * 经验计算公式
     * @param roleLevel 主等级
     * @param guaiLevel  怪等级
     * @param X     怪物数量
     * @return
     */
    public static  Integer caleExp(Integer roleLevel,Integer guaiLevel,Integer X ){
       Integer N=0;//经验倍数
        Integer expInteger=0;//经验值
         Double exp=0d;//经验值
       if(roleLevel<15)
            N=2;
        if(roleLevel>=15&&roleLevel<=30)
            N=3;
        if(roleLevel>30&&roleLevel<=70)
            N=4;
        if(roleLevel>70&&roleLevel<=100)
            N=5;
        if(roleLevel>100&&roleLevel<=120)
            N=6;
        if(roleLevel>120&&roleLevel<=140)
            N=7;
        if(roleLevel>140&&roleLevel<=160)
            N=8;
        if(roleLevel>160&&roleLevel<=180)
            N=9;

        if(guaiLevel>roleLevel){
            exp=(3*N+(0.6*roleLevel+(5-Math.abs(guaiLevel-roleLevel-5)*0.05*roleLevel)))*N*X;
        }else{
            exp=(3*N+(0.6*roleLevel+(10-Math.abs(guaiLevel-roleLevel-5)*0.05*roleLevel)))*N*X;
        }
        if(exp<=0){
            expInteger = (int)Math.round(Math.random() * 49)+1;

        }else {
            expInteger=exp.intValue();
        }
    return  expInteger;
    }

    public static void main(String[] args) {
        System.out.println(caleExp(30,30,4));
    }
}

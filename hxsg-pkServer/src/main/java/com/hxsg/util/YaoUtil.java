package com.hxsg.util;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
public class YaoUtil {


    public static boolean getYaoName(String name) {
        boolean re = false;
        switch (name) {

            case  "九转丹":
            case "回天丹":
            case "护命单":
            case "大还单":
            case"雪参丸":
            case"首乌丸":
            case "熊胆丸":
            case "百花丸":
            case "三黄丸":
            case "断续膏":
            case "龙涎露":
            case"玄冰露":
            case "镇魂露":
            case "红莲露":
            case "万古酒":
            case"雄黄酒":
            case"虎骨酒":
            case"杜康酒":
            case"花雕酒":
            case "壮胆酒":{
                re = true;
                break;
            }

        }
        return re;
    }
}

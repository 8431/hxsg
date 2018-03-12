package com.hxsg.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
public class MathUtil {
    public static List<Integer> round(List<Integer> li ,Integer size,Integer num) throws Exception {
        if (num > size) {
            System.out.println(num+","+size);
            throw new Exception("产生的随机个数不能大于范围总个数");
        } else {
                if(li.size()<num){
                    int gl = (int) Math.round(Math.random() * (size- 1));
                    if(li.contains(gl)){
                        return round(li,size,num);
                    }else{
                        li.add(gl);
                        if(li.size()<num){
                            return round(li,size,num);
                        }

                    }

                }

        }
        return li;
    }

    public static void main(String[] args) throws Exception {
        List<Integer> li=round(new ArrayList<Integer>(),9,4);
        for(Integer n:li){
            System.out.println(n);
        }

    }

}

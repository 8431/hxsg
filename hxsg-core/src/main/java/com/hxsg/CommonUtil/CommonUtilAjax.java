package com.hxsg.CommonUtil;


import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * json数据返回工具
 * Created by dlf on 2016/2/18.
 */
public class CommonUtilAjax {
    public static void sendAjax(Object bt,HttpServletRequest request, HttpServletResponse response){
        //ajax跨域请求
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        PrintWriter out= null;
        Map<String,Object> mp=new HashMap<String,Object>();
        try {
            out = response.getWriter();
            Gson gb=new Gson();
            mp.put("msg",bt);
            //String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
           // out.println(jsonpCallback+"("+gb.toJson(mp)+")");//返回jsonp格式数据
            out.println(gb.toJson(mp));//返回jsonp格式数据
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.flush();
                out.close();
            }
        }

    }
    public static void sendAjaxList(String msg,Object bt,HttpServletRequest request, HttpServletResponse response){
        //ajax跨域请求
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out= null;
        Map<String,Object> mp=new HashMap<String,Object>();
            try{
                out=response.getWriter();
                Gson gb=new Gson();
                mp.put(msg,bt);
                String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
                //out.println(jsonpCallback+"("+gb.toJson(mp)+")");//返回jsonp格式数据
                out.println(gb.toJson(mp));//返回jsonp格式数据
                System.out.println(gb.toJson(mp));
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(out!=null){
                    out.flush();
                    out.close();
                }
            }
    }
    public  static void sendAjaxArray(Object[][] bt,HttpServletRequest request, HttpServletResponse response) {
        //ajax跨域请求
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //response.setHeader("key",key);
        PrintWriter out = null;
        Map<String,Object> mp=new HashMap<String,Object>();
        try {
            out = response.getWriter();
            Gson gb=new Gson();
            for (int i = 0; i < bt.length; i++) {

                mp.put((String) bt[i][0], bt[i][1]);
            }
            String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
           // out.println(jsonpCallback + "(" +gb.toJson(mp) + ")");//返回jsonp格式数据
            out.println(gb.toJson(mp));//返回jsonp格式数据
            System.out.println(gb.toJson(mp));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }
}

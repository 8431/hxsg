package com.hxsg.wupin.controller;

import com.alibaba.fastjson.JSONObject;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.Dao.*;
import com.hxsg.po.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/wupin")
public class WuPinController{
    @Autowired
    jiangjunMapper jjm;
    @Autowired
    roleyaoMapper rym;
    @Autowired
    RoleZbMapper rzm;

    @RequestMapping(value = "/wupin", method = { RequestMethod.GET, RequestMethod.POST })
    public String wupin( )throws Exception {

        return "物品/物品";

    }
    @RequestMapping(value = "/yaopin",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String yaopin(HttpSession session,HttpServletResponse response) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");

        int id = r.get(0).getId();

        List<roleyao> li= rym.selectRoleId(id);//查询该角色所拥有的药品
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("yao",li);

        }else{
            obj.put("code", 1);
        }
        System.out.println(obj.toString());

        out.write(obj.toString());//传递该角色所拥有的药品
        out.flush();
        out.close();


        return null;
    }
    @RequestMapping(value = "/zhawu",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String zhawu(HttpSession session,HttpServletResponse response) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int id = r.get(0).getId();

        List<jiangjun> jjunli=jjm.selectByRoleId(id);//查询该角色所拥有将军令
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        if(jjunli.size()>0&&jjunli!=null){
            obj.put("code", 0);
            obj.put("jjun",jjunli);

        }else{
            obj.put("code", 1);
        }
        System.out.println(obj.toString());

        out.write(obj.toString());//传递该角色所拥有的将军令
        out.flush();
        out.close();



        return null;
    }
    @RequestMapping(value = "/appzhawu",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appzhawu(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws Exception {
        Integer id = (Integer) session.getAttribute("roleid");
       if(id>0){
           List<jiangjun> jjunli=jjm.selectByRoleId(id);//查询该角色所拥有将军令

           if(jjunli.size()>0&&jjunli!=null){
               CommonUtilAjax.sendAjaxList("jjun",jjunli,request,response);


           }else{
               CommonUtilAjax.sendAjaxList("code",1,request,response);
           }


       }





        return null;
    }
    @RequestMapping(value = "/zhaungbei",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String zhaungbei(HttpSession session,HttpServletResponse response) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");

        int id = r.get(0).getId();

        List<RoleZb> li= rzm.selectRoleId(id);//查询该角色所拥有的药品
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("rzb",li);

        }else{
            obj.put("code", 1);
        }
        System.out.println(obj.toString());

        out.write(obj.toString());//传递该角色所拥有的装备
        out.flush();
        out.close();


        return null;
    }


}

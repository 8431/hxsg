package com.hxsg.admin.controller;
import com.google.gson.Gson;
import com.hxsg.admin.AdminUtil;
import com.hxsg.admin.service.Cocos2dHxsgAdmin;
import com.hxsg.admin.vo.Admin;
import com.hxsg.CommonUtil.CommonUtilAjax;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**系统后台
 * Created by dlf on 2016/10/14.
 */
@Controller
@RequestMapping("hxsgAdmin")
public class Cocos2dHxsgAdminController {
    private Logger logger = Logger.getLogger(Cocos2dHxsgAdminController.class);
    @Autowired
    Cocos2dHxsgAdmin cocos2dhxsgadmin;

    @RequestMapping(value = "/getColumn", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getColumn(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Admin> li = new ArrayList<Admin>();
            Admin an = new Admin();
            an.setCostom1("gjsg_map");
            an.setName("咕叽三国地图");
            li.add(an);
            Admin an2 = new Admin();
            an2.setCostom1("map_guai");
            an2.setName("地图野怪");
            li.add(an2);
            Admin an3 = new Admin();
            an3.setCostom1("zawu_miaoshu");
            an3.setName("杂物详情");
            li.add(an3);
            Admin an4 = new Admin();
            an4.setCostom1("fujiang");
            an4.setName("副将详情");
            li.add(an4);
            Admin an5 = new Admin();
            an5.setCostom1("yeguai_qun");
            an5.setName("野怪群详情");
            li.add(an5);

            CommonUtilAjax.sendAjaxList("dictionary", li, request, response);
        } catch (Exception e) {
            logger.error("控制层--cocos2dNotis推送系统公告：" + e.getMessage());
        }
        return null;
    }
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(Admin admin,HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            //可以被注入，上线后台必须关闭
            if(!StringUtils.isEmpty(admin.getUser())&&!StringUtils.isEmpty(admin.getPassword())){
                String sql="select * from admin where user='"+admin.getUser()+"' and password='"+admin.getPassword()+"'";
                Map<String, Object> mp=new HashMap<String, Object>();
                mp.put("sql",sql);
                List li=cocos2dhxsgadmin.selectEverythingToTableName(mp);
                if(li!=null&&li.size()>0){
                    session.setAttribute("login","true");
                   CommonUtilAjax.sendAjaxList("result", "succes", request, response);
                }else{
                    CommonUtilAjax.sendAjaxList("result", "false", request, response);
                }
            }




        } catch (Exception e) {
            logger.error("控制层--cocos2dNotis推送系统公告：" + e.getMessage());
        }
        return null;
    }
    @RequestMapping(value = "/getDetail2", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getDetail(
            @RequestParam(value = "currentpage", required = false) Integer currentpage,
            @RequestParam(value = "tablename", required = false) String tablename,
            @RequestParam(value = "type", required = false) String type,
            HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
           String msg= (String) session.getAttribute("login");
            if (AdminUtil.tableName.contains(tablename)&&"true".equals(msg)) {

                switch (type) {
                    case "insert": {
                        //增删改查条件Map封装
                        Map mpWhere=new HashMap();
                        String value=request.getParameter("values");
                        String idArray=request.getParameter("idArray");
                        String englishcloum=request.getParameter("englishcloum");
                        Gson gn=new Gson();
                        List<String> englishcloumList= gn.fromJson(englishcloum,List.class);
                        List<Map<String,String>> valueArray=gn.fromJson(value,List.class);
                        List<String> idArrayArray=gn.fromJson(idArray,List.class);
                        mpWhere.put("tablename",tablename);
                        mpWhere.put("key",englishcloumList);
                        mpWhere.put("englishcloum",englishcloumList);
                        mpWhere.put("value",valueArray);
                        if(idArrayArray!=null&&idArrayArray.size()>0){
                            mpWhere.put("del",idArrayArray);
                            cocos2dhxsgadmin.deleteToEverthing(mpWhere);
                        }
                        cocos2dhxsgadmin.insertEverythingToTableName(mpWhere);
                        break;
                    }
                    default:{
                        String[][] arr = cocos2dhxsgadmin.getTableColumn("gjsg", tablename);
                        Map<String, Object> mp2=new HashMap<String, Object>();
                        mp2.put("tablename",tablename);
                        List<Map<String, Object>> li = cocos2dhxsgadmin.getTableValue(mp2);
                        Object[][] obj = null;
                        obj = new Object[][]{{"page", mp2}, {"chinacloum", arr[1]}, {"englishcloum", arr[0]}, {"values", li}, {"costomid", new String[]{"id"}}};
                        CommonUtilAjax.sendAjaxArray(obj, request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("控制层--：" + e.getMessage());
        }
        return null;
    }

}
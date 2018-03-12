package com.hxsg.qianzhuang.controller;

import com.alibaba.fastjson.JSONObject;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/money")
public class MyController {
    @Autowired
    RoleMoneyMapper rmm;

    @Autowired
    RoleMapper rm;


    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/sxmoney", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sxmoney(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        Role re=rm.selectByPrimaryKey(roleid);
        int yin=re.getYin();
        int jin=re.getJin();
        //ajax跨域请求
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out=response.getWriter();
        JSONObject obj = new JSONObject(); //根据需要拼装json
        obj.put("yin",yin);
        obj.put("jin",jin);
        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        out.println(jsonpCallback+"("+obj.toString()+")");//返回jsonp格式数据
        out.flush();
        out.close();
        return null;


    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(HttpSession session, Model model) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        RoleMoney rmy= rmm.selectByRoleid(roleid);
        if(rmy==null){
            RoleMoney roleMoney=new RoleMoney();
            roleMoney.setRoleid(roleid);
            roleMoney.setJin(0);
            roleMoney.setYin(0);
            roleMoney.setMessage(null);
            int a=rmm.insertSelective(roleMoney);
            if(a>0){
                RoleMoney rms= rmm.selectByRoleid(roleid);
                model.addAttribute("rm",rms);
            }

        }else{
            model.addAttribute("rm",rmy);
        }

        return "钱庄/钱庄";

    }

    @RequestMapping(value = "/setyin", method = {RequestMethod.GET, RequestMethod.POST})
   @ResponseBody
    public String setyin(@RequestParam(value = "yin", required = false)Integer yin,HttpSession session, Model model,HttpServletResponse response) throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        Role re = rm.selectByPrimaryKey(roleid);
        Integer rolemoney=re.getYin();
        if(rolemoney>yin){
            re.setYin(rolemoney-yin);
            int a=rm.updateByPrimaryKeySelective(re);
            if(a>0){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                Date now=new Date();
                logger.error(sdf.format(now).toString());

                RoleMoney rmy= rmm.selectByRoleid(roleid);
                Integer rmyin=rmy.getYin();
                Integer totalyin=rmyin+yin;
                rmy.setYin(totalyin);       ;
                rmm.updateByPrimaryKeySelective(rmy);
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.write("succes");
                out.flush();
                out.close();
            }
        }else {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write("输入错误，余额不足");
            out.flush();
            out.close();

        }




        return null;

    }
    @RequestMapping(value = "/getyin", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getyin(@RequestParam(value = "yin", required = false)Integer yin,HttpSession session, Model model,HttpServletResponse response) throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        RoleMoney rmy=rmm.selectByRoleid(roleid);
        if( rmy.getYin()>yin){

            rmy.setYin(rmy.getYin()-yin);
            int a=rmm.updateByPrimaryKeySelective(rmy);
            if(a>0){
                Role re= rm.selectByPrimaryKey(roleid);
                Integer totalyin=re.getYin()+yin;
                re.setYin(totalyin);
                rm.updateByPrimaryKeySelective(re);
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.write("succes");
                out.flush();
                out.close();


            }
        }else{
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write("输入错误，余额不足");
            out.flush();
            out.close();

        }



        return null;

    }
    @RequestMapping(value = "/setjin", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String setjin(@RequestParam(value = "jin", required = false)Integer jin,HttpSession session, Model model,HttpServletResponse response) throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        Role re = rm.selectByPrimaryKey(roleid);
        Integer rolemoney=re.getJin();
        if(rolemoney>jin){
            re.setJin(rolemoney-jin);
            int a=rm.updateByPrimaryKeySelective(re);
            if(a>0){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                Date now=new Date();
                logger.error(sdf.format(now).toString());

                RoleMoney rmy= rmm.selectByRoleid(roleid);
                Integer rmjin=rmy.getJin();
                Integer totaljin=rmjin+jin;
                rmy.setJin(totaljin);
                rmm.updateByPrimaryKeySelective(rmy);
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.write("succes");
                out.flush();
                out.close();
            }
        }else {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write("输入错误，余额不足");
            out.flush();
            out.close();

        }




        return null;

    }
    @RequestMapping(value = "/getjin", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getjin(@RequestParam(value = "jin", required = false)Integer jin,HttpSession session, Model model,HttpServletResponse response) throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        RoleMoney rmy=rmm.selectByRoleid(roleid);
        if( rmy.getJin()>jin){

            rmy.setJin(rmy.getJin()-jin);
            int a=rmm.updateByPrimaryKeySelective(rmy);
            if(a>0){
                Role re= rm.selectByPrimaryKey(roleid);
                Integer totaljin=re.getJin()+jin;
                re.setJin(totaljin);
                rm.updateByPrimaryKeySelective(re);
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.write("succes");
                out.flush();
                out.close();


            }
        }else{
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write("输入错误，余额不足");
            out.flush();
            out.close();

        }



        return null;

    }



}
package com.hxsg.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.Dao.RoleMapper;
import com.hxsg.Dao.RoleMessageMapper;
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
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/mg")
public class MsgController {

    @Autowired
    RoleMapper rm;
    @Autowired
    RoleMessageMapper rmm;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/sxmsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String sxmsg(Model model,HttpServletResponse response,HttpSession session)throws Exception {
        response.setCharacterEncoding("utf-8");
            List<roleMessage> li=rmm.getMsg();
            response.setCharacterEncoding("utf-8");
            PrintWriter out=response.getWriter();
            JSONObject obj=new JSONObject();//使用json
            if(li.size()>0&&li!=null){
                obj.put("code", 0);
                obj.put("rmsg",li);

            }else{
                obj.put("code", 1);
            }
            System.out.println(obj.toString());

            out.write(obj.toString());
            out.flush();
            out.close();
        return null;

    }
    //APP版本刷新MSG
    @RequestMapping(value = "/appsxmsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appsxmsg(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
        List<roleMessage> li=rmm.getMsg();
        if(li.size()>0&&li!=null){
           CommonUtilAjax.sendAjaxList("rmsg",li,request, response);

        }else{
            CommonUtilAjax.sendAjaxList("code",1,request, response);
        }
        return null;

    }
    @RequestMapping(value = "/msg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String msg(@RequestParam(value = "rolemsg",required =true ) String rolemsg,@RequestParam(value = "type",required =true ) String type,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        response.setCharacterEncoding("utf-8");
        System.out.println(rolemsg);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//         sdf.format(new Date());

        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        String rolename = r.get(0).getJuesename();
        int roleid=r.get(0).getId();
        roleMessage rme=new roleMessage();
        rme.setMessage(rolemsg);
        rme.setRoleid(roleid);
        rme.setData(new Date());
        rme.setType(type);
        rme.setRolename(rolename);
        int a=rmm.insertSelective(rme);
        if(a>0){
            List<roleMessage> li=rmm.getMsg();
            response.setCharacterEncoding("utf-8");
            PrintWriter out=response.getWriter();
            JSONObject obj=new JSONObject();//使用json
            if(li.size()>0&&li!=null){
                obj.put("code", 0);
                obj.put("rmsg",li);

            }else{
                obj.put("code", 1);
            }
            System.out.println(obj.toString());

            out.write(obj.toString());
            out.flush();
            out.close();

        }



        return null;

    }
    //app版本MSG
    @RequestMapping(value = "/appmsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appmsg(@RequestParam(value = "rolemsg",required =true ) String rolemsg,@RequestParam(value = "type",required =true ) String type,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {

        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        String rolename = r.get(0).getJuesename();
        if(r.size()>0){
            int roleid=r.get(0).getId();
            roleMessage rme=new roleMessage();
            rme.setMessage(rolemsg);
            rme.setRoleid(roleid);
            rme.setData(new Date());
            rme.setType(type);
            rme.setRolename(rolename);
            int a=rmm.insertSelective(rme);
            if(a>0){
                List<roleMessage> li=rmm.getMsg();
                if(li.size()>0&&li!=null){
                    CommonUtilAjax.sendAjaxList("rmsg",li,request, response);

                }else{
                    CommonUtilAjax.sendAjaxList("code",1,request, response);
                }
            }

        }



        return null;

    }





}

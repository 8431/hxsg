package com.hxsg.friends.controller;

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
@RequestMapping("/friends")
public class FdController {

    @Autowired
    RoleFriendsMapper rfm;
    @Autowired
    RoleFriendsMsgMapper rfmm;
    @Autowired
    RoleMapper rm;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/fd", method = { RequestMethod.GET, RequestMethod.POST })
      @ResponseBody
      public String fd(RoleFriends record,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        response.setCharacterEncoding("utf-8");
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        record.setStatus("0");
        record.setRoleid(roleid);
        List<RoleFriends> li=rfm.selectAll(record);

        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("friends",li);

        }else{
            obj.put("code", 1);
        }
        System.out.println(obj.toString());

        out.write(obj.toString());
        out.flush();
        out.close();
        return null;

    }
    @RequestMapping(value = "/appfd", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appfd(RoleFriends record,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {

        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        record.setStatus("0");
        record.setRoleid(roleid);
        List<RoleFriends> li=rfm.selectAll(record);
        ////////////////
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out=response.getWriter();
        JSONObject obj = new JSONObject(); //根据需要拼装json
        ////////////////////
        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("friends",li);
        }else{
            obj.put("code", 1);
        }
        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        out.println(jsonpCallback + "(" + obj.toString() + ")");//返回jsonp格式数据
        out.flush();
        out.close();
        return null;

    }
    @RequestMapping(value = "/onlinesum", method = { RequestMethod.GET, RequestMethod.POST })
      public String onlinesum(Role record,Model model,HttpServletResponse response,HttpSession session)throws Exception {

        List<Role> li=rm.selectAll(record);

        if(li.size()>0){
            model.addAttribute("rolenum",li);
        }


        return "friends/rolesum";

    }
    @RequestMapping(value = "/apponlinesum", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String apponlinesum(Role record,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {

        List<Role> li=rm.selectAll(record);

        if(li.size()>0){
           CommonUtilAjax.sendAjaxList("rolenum", li,request,response);
        }


        return null;

    }
    @RequestMapping(value = "/hy", method = { RequestMethod.GET, RequestMethod.POST })
    public String hy()throws Exception {
        return "friends/friends";

    }
    // 玩家信息
    @RequestMapping(value = "/wjxx", method = { RequestMethod.GET, RequestMethod.POST })
    public String wjxx(@RequestParam(value = "id",required =true) Integer id,Model model,HttpServletResponse response,HttpSession session)throws Exception {

        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        String result=null;
        Role wanjiaxinxi = rm.selectByPrimaryKey(id);
        RoleFriends rfs=new RoleFriends();
        rfs.setFriendid(id);
        rfs.setRoleid(roleid);
        rfs.setStatus("0");
        List<RoleFriends> li=rfm.selectAll(rfs);
        rfs.setFriendid(roleid);
        rfs.setRoleid(id);
        rfs.setStatus("0");
        List<RoleFriends> li2=rfm.selectAll(rfs);
        if(li.size()>0&&li2.size()>0){

            model.addAttribute("hy", "割袍断交");
        }
        else{
            model.addAttribute("hy", "加好友");
        }

       if(li.size()==0&&li2.size()==0){

                model.addAttribute("hy", "加好友");

        }
        if(li.size()==1){


            String lh=li.get(0).getLahei();
            if(lh.equals("0")||lh==null){
                model.addAttribute("msg", "加黑");
            }else{
                model.addAttribute("msg", "解黑");
            }

        }else{

            model.addAttribute("msg", "加黑");
        }
        if (wanjiaxinxi != null) {

            model.addAttribute("role", wanjiaxinxi);
            model.addAttribute("rolemsg", r.get(0));
            result = "friends/rolemessage";

        } else {
            result = "error/error";
        }

        return result;

    }
    // 玩家信息
    @RequestMapping(value = "/appwjxx", method = { RequestMethod.GET, RequestMethod.POST })
    public String appwjxx(@RequestParam(value = "id",required =true) Integer id,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {

        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        String result=null;
        Role wanjiaxinxi = rm.selectByPrimaryKey(id);
        RoleFriends rfs=new RoleFriends();
        rfs.setFriendid(id);
        rfs.setRoleid(roleid);
        rfs.setStatus("0");
        List<RoleFriends> li=rfm.selectAll(rfs);
        rfs.setFriendid(roleid);
        rfs.setRoleid(id);
        rfs.setStatus("0");
        List<RoleFriends> li2=rfm.selectAll(rfs);
        if(li.size()>0&&li2.size()>0){

           CommonUtilAjax.sendAjaxList("hy", "割袍断交",request,response);
        }
        else{
            CommonUtilAjax.sendAjaxList("hy", "加好友",request,response);
        }

        if(li.size()==0&&li2.size()==0){

            CommonUtilAjax.sendAjaxList("hy", "加好友",request,response);

        }
        if(li.size()==1){


            String lh=li.get(0).getLahei();
            if(lh.equals("0")||lh==null){
                CommonUtilAjax.sendAjaxList("msg", "加黑",request,response);
            }else{
                CommonUtilAjax.sendAjaxList("msg", "解黑",request,response);
            }

        }else{

            CommonUtilAjax.sendAjaxList("msg", "加黑",request,response);
        }
        if (wanjiaxinxi != null) {

            CommonUtilAjax.sendAjaxList("role", wanjiaxinxi,request,response);
            CommonUtilAjax.sendAjaxList("rolemsg", r.get(0),request,response);
            result = "friends/rolemessage";

        } else {
            result = "error/error";
        }

        return result;

    }
    @RequestMapping(value = "/jh", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String jh(@RequestParam(value = "status",required = true)String  status,@RequestParam(value = "friendid",required = true)Integer friendid,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();

        RoleFriends rfs=new RoleFriends();
                    rfs.setFriendid(friendid);
                    rfs.setRoleid(roleid);
            List<RoleFriends> li=rfm.selectAll(rfs);
        if(li.size()==1){
            li.get(0).setLahei(status);
            rfm.updateByPrimaryKeySelective( li.get(0));

        }
        if(li.size()==0){
            int a=rfm.insertSelective(rfs);
            if(a>0){
                List<RoleFriends> lis=rfm.selectAll(rfs);
                    li.get(0).setLahei(status);
                    rfm.updateByPrimaryKeySelective( li.get(0));

            }


        }



            return null;
    }
    @RequestMapping(value = "/appjh", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appjh(@RequestParam(value = "status",required = true)String  status,@RequestParam(value = "friendid",required = true)Integer friendid,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();

        RoleFriends rfs=new RoleFriends();
        rfs.setFriendid(friendid);
        rfs.setRoleid(roleid);
        List<RoleFriends> li=rfm.selectAll(rfs);
        if(li.size()==1){
            li.get(0).setLahei(status);
            rfm.updateByPrimaryKeySelective( li.get(0));

        }
        if(li.size()==0){
            int a=rfm.insertSelective(rfs);
            if(a>0){
                List<RoleFriends> lis=rfm.selectAll(rfs);
                li.get(0).setLahei(status);
                rfm.updateByPrimaryKeySelective( li.get(0));

            }


        }



        return null;
    }
    @RequestMapping(value = "/addfriend", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String jhy(@RequestParam(value = "friendid",required = true)Integer friendid,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();

        if(roleid.equals(friendid)){
            response.setCharacterEncoding("utf-8");
            PrintWriter out=response.getWriter();
            out.write("请不要添加自己为好友！");
            out.flush();
            out.close();


        }
        else{
        RoleFriendsMsg rfg=new RoleFriendsMsg();
        rfg.setRoleid(friendid);
        rfg.setFriendid(roleid);
        rfg.setType("加好友");
        rfg.setStatus("0");
         List<RoleFriendsMsg> li=rfmm.selectAll(rfg);

        response.setCharacterEncoding("utf-8");
        if(li.size()>0){

            PrintWriter out=response.getWriter();
            out.write("已提交过验证，请勿重复提交！");
            out.flush();
            out.close();

        }else{
            String name=r.get(0).getJuesename();
            RoleFriendsMsg rg=new RoleFriendsMsg();
            rg.setRoleid(friendid);
            rg.setFriendid(roleid);
            rg.setMessage("玩家("+name+")请求加你为好友！");
            rg.setData(new Date());
            rg.setType("加好友");
            int a=rfmm.insertSelective(rg);
            response.setCharacterEncoding("utf-8");
            PrintWriter out=response.getWriter();
            out.write("发送成功等待对方验证...");
            out.flush();
            out.close();


        }
        }

        return null;
    }
    @RequestMapping(value = "/appaddfriend", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appjhy(@RequestParam(value = "friendid",required = true)Integer friendid,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();

        if(roleid.equals(friendid)){
            CommonUtilAjax.sendAjaxList("msg","请不要添加自己为好友！",request,response);



        }
        else{
            RoleFriendsMsg rfg=new RoleFriendsMsg();
            rfg.setRoleid(friendid);
            rfg.setFriendid(roleid);
            rfg.setType("加好友");
            rfg.setStatus("0");
            List<RoleFriendsMsg> li=rfmm.selectAll(rfg);


            if(li.size()>0){
                CommonUtilAjax.sendAjaxList("msg","已提交过验证，请勿重复提交！",request,response);


            }else{
                String name=r.get(0).getJuesename();
                RoleFriendsMsg rg=new RoleFriendsMsg();
                rg.setRoleid(friendid);
                rg.setFriendid(roleid);
                rg.setMessage("玩家("+name+")请求加你为好友！");
                rg.setData(new Date());
                rg.setType("加好友");
                int a=rfmm.insertSelective(rg);
                CommonUtilAjax.sendAjaxList("msg","发送成功等待对方验证...",request,response);




            }
        }

        return null;
    }
    @RequestMapping(value = "/yzhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String yzhy(Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        String status="0";
        RoleFriendsMsg rfmsg=new RoleFriendsMsg();
        rfmsg.setRoleid(roleid);
        rfmsg.setStatus(status);
       List<RoleFriendsMsg> li=rfmm.selectAll(rfmsg);


            response.setCharacterEncoding("utf-8");
            PrintWriter out=response.getWriter();
            JSONObject obj=new JSONObject();//使用json
            if(li.size()>0&&li!=null){
                obj.put("code", 0);


            }else{
                obj.put("code", 1);
            }


            out.write(obj.toString());
            out.flush();
            out.close();


        return null;
    }
    @RequestMapping(value = "/appyzhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appyzhy(HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        String status="0";
        RoleFriendsMsg rfmsg=new RoleFriendsMsg();
        rfmsg.setRoleid(roleid);
        rfmsg.setStatus(status);
        List<RoleFriendsMsg> li=rfmm.selectAll(rfmsg);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out=response.getWriter();
        JSONObject obj = new JSONObject(); //根据需要拼装json

        if(li.size()>0&&li!=null){
            obj.put("code", 0);


        }else{
            obj.put("code", 1);
        }



        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        out.println(jsonpCallback+"("+obj.toString()+")");//返回jsonp格式数据
        out.flush();
        out.close();



        return null;
    }
    @RequestMapping(value = "/apptjhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String apptjhy(HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        String status="0";
        RoleFriendsMsg rfmsg=new RoleFriendsMsg();
        rfmsg.setRoleid(roleid);
        rfmsg.setStatus(status);
        List<RoleFriendsMsg> li=rfmm.selectAll(rfmsg);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out=response.getWriter();
        JSONObject obj = new JSONObject(); //根据需要拼装json
        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("tjnews",li.get(0));

        }else{
            obj.put("code", 1);
        }


        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        out.println(jsonpCallback+"("+obj.toString()+")");//返回jsonp格式数据
        out.flush();
        out.close();


        return null;
    }
    @RequestMapping(value = "/tjhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String tjhy(Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Integer roleid=r.get(0).getId();
        String status="0";
        RoleFriendsMsg rfmsg=new RoleFriendsMsg();
        rfmsg.setRoleid(roleid);
        rfmsg.setStatus(status);
        List<RoleFriendsMsg> li=rfmm.selectAll(rfmsg);


        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("tjnews",li.get(0));

        }else{
            obj.put("code", 1);
        }


        out.write(obj.toString());
        out.flush();
        out.close();


        return null;
    }



    @RequestMapping(value = "/suretjhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String suretjhy(@RequestParam(value = "id",required = true)Integer id,@RequestParam(value = "friendid",required = false)Integer friendid,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Role friendrole=rm.selectByPrimaryKey(friendid);
        Integer roleid=r.get(0).getId();
       RoleFriends rfs=new RoleFriends();
        rfs.setRoleid(roleid);
        rfs.setFriendid(friendid);
        rfs.setStatus("0");
        List<RoleFriends> li=rfm.selectAll(rfs);
        RoleFriendsMsg rfmsg=new RoleFriendsMsg();
        rfmsg.setId(id);
        rfmsg.setStatus("1");
        rfmm.updateByPrimaryKeySelective(rfmsg);
        if(li.size()<1){

        RoleFriends rs=new RoleFriends();
        rs.setRoleid(roleid);
        rs.setFriendid(friendid);
        rs.setData(new Date());
        rs.setType("好友");
        rs.setStatus("0");
        rs.setFriendname(friendrole.getJuesename());
        rs.setRolename(r.get(0).getJuesename());
        rfm.insertSelective(rs);
        RoleFriends rs2=new RoleFriends();
        rs2.setRoleid(friendid);
        rs2.setFriendid(roleid);
        rs2.setData(new Date());
        rs2.setType("好友");
        rs2.setStatus("0");
        rs2.setFriendname(r.get(0).getJuesename());
        rs2.setRolename(friendrole.getJuesename());
        rfm.insertSelective(rs2);
        /////////////////////////反馈给对方，同意添加
        String name=r.get(0).getJuesename();
        RoleFriendsMsg rg=new RoleFriendsMsg();
        rg.setRoleid(friendid);
        rg.setFriendid(roleid);
        rg.setMessage("玩家("+name+")同意添加你为好友！");
        rg.setData(new Date());
        rg.setType("通知");
        int a=rfmm.insertSelective(rg);
        }
        return null;
    }
    @RequestMapping(value = "/appsuretjhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appsuretjhy(@RequestParam(value = "id",required = true)Integer id,@RequestParam(value = "friendid",required = false)Integer friendid,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Role friendrole=rm.selectByPrimaryKey(friendid);
        Integer roleid=r.get(0).getId();
        RoleFriends rfs=new RoleFriends();
        rfs.setRoleid(roleid);
        rfs.setFriendid(friendid);
        rfs.setStatus("0");
        List<RoleFriends> li=rfm.selectAll(rfs);
        RoleFriendsMsg rfmsg=new RoleFriendsMsg();
        rfmsg.setId(id);
        rfmsg.setStatus("1");
        rfmm.updateByPrimaryKeySelective(rfmsg);
        if(li.size()<1){

            RoleFriends rs=new RoleFriends();
            rs.setRoleid(roleid);
            rs.setFriendid(friendid);
            rs.setData(new Date());
            rs.setType("好友");
            rs.setStatus("0");
            rs.setFriendname(friendrole.getJuesename());
            rs.setRolename(r.get(0).getJuesename());
            rfm.insertSelective(rs);
            RoleFriends rs2=new RoleFriends();
            rs2.setRoleid(friendid);
            rs2.setFriendid(roleid);
            rs2.setData(new Date());
            rs2.setType("好友");
            rs2.setStatus("0");
            rs2.setFriendname(r.get(0).getJuesename());
            rs2.setRolename(friendrole.getJuesename());
            rfm.insertSelective(rs2);
            /////////////////////////反馈给对方，同意添加
            String name=r.get(0).getJuesename();
            RoleFriendsMsg rg=new RoleFriendsMsg();
            rg.setRoleid(friendid);
            rg.setFriendid(roleid);
            rg.setMessage("玩家("+name+")同意添加你为好友！");
            rg.setData(new Date());
            rg.setType("通知");
            int a=rfmm.insertSelective(rg);
        }
        return null;
    }
    @RequestMapping(value = "/newjujue", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String newjujue(@RequestParam(value = "friendid",required = false)Integer friendid,@RequestParam(value = "id",required = true)Integer id,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Role friendrole=rm.selectByPrimaryKey(friendid);
        Integer roleid=r.get(0).getId();
        RoleFriendsMsg rm=new RoleFriendsMsg();
        rm.setId(id);
        rm.setStatus("1");
        rfmm.updateByPrimaryKeySelective(rm);
        /////////////////////////反馈给对方，拒绝添加
        String name=r.get(0).getJuesename();
        RoleFriendsMsg rg=new RoleFriendsMsg();
        rg.setRoleid(friendid);
        rg.setFriendid(roleid);
        rg.setMessage("玩家("+name+")拒绝添加你为好友！");
        rg.setData(new Date());
        rg.setType("通知");
        int a=rfmm.insertSelective(rg);
        return null;
    }
    @RequestMapping(value = "/appnewjujue", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appnewjujue(@RequestParam(value = "friendid",required = false)Integer friendid,@RequestParam(value = "id",required = true)Integer id,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Role friendrole=rm.selectByPrimaryKey(friendid);
        Integer roleid=r.get(0).getId();
        RoleFriendsMsg rm=new RoleFriendsMsg();
        rm.setId(id);
        rm.setStatus("1");
        rfmm.updateByPrimaryKeySelective(rm);
        /////////////////////////反馈给对方，拒绝添加
        String name=r.get(0).getJuesename();
        RoleFriendsMsg rg=new RoleFriendsMsg();
        rg.setRoleid(friendid);
        rg.setFriendid(roleid);
        rg.setMessage("玩家("+name+")拒绝添加你为好友！");
        rg.setData(new Date());
        rg.setType("通知");
        int a=rfmm.insertSelective(rg);
        return null;
    }
    @RequestMapping(value = "/surebottun", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String surebottun(@RequestParam(value = "friendid",required = false)Integer friendid,@RequestParam(value = "id",required = true)Integer id,Model model,HttpServletResponse response,HttpSession session)throws Exception {

        RoleFriendsMsg rm=new RoleFriendsMsg();
        rm.setId(id);
        rm.setStatus("1");
        rfmm.updateByPrimaryKeySelective(rm);

        return null;
    }
    @RequestMapping(value = "/appsurebottun", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appsurebottun(HttpServletRequest request,@RequestParam(value = "friendid",required = false)Integer friendid,@RequestParam(value = "id",required = false)Integer id,Model model,HttpServletResponse response,HttpSession session)throws Exception {

        RoleFriendsMsg rm=new RoleFriendsMsg();
        rm.setId(id);
        rm.setStatus("1");
        rfmm.updateByPrimaryKeySelective(rm);
        CommonUtilAjax.sendAjaxList("msg","ssss",request,response);
        return null;
    }
    @RequestMapping(value = "/djhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String djhy(@RequestParam(value = "friendid",required = true)Integer friendid,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Role friendrole=rm.selectByPrimaryKey(friendid);
        Integer roleid=r.get(0).getId();
        //断交好友该状态status=1
        RoleFriends rmfs=new RoleFriends();
        rmfs.setRoleid(roleid);
        rmfs.setFriendid(friendid);
        rmfs.setStatus("0");
        List<RoleFriends> li1=rfm.selectAll(rmfs);
        li1.get(0).setStatus("1");
        rfm.updateByPrimaryKeySelective(li1.get(0));
        //断交好友该状态status=1
        rmfs.setRoleid(friendid);
        rmfs.setFriendid(roleid);
        rmfs.setStatus("0");
        List<RoleFriends> li2=rfm.selectAll(rmfs);
        li2.get(0).setStatus("1");
        rfm.updateByPrimaryKeySelective(li2.get(0));
        /////////////////////////反馈给对方，断交好友
        String name=r.get(0).getJuesename();
        RoleFriendsMsg rg=new RoleFriendsMsg();
        rg.setRoleid(friendid);
        rg.setFriendid(roleid);
        rg.setMessage("玩家("+name+")与你断交了好友关系！");
        rg.setData(new Date());
        rg.setType("通知");
        int a=rfmm.insertSelective(rg);
        return null;
    }
    @RequestMapping(value = "/appdjhy", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appdjhy(@RequestParam(value = "friendid",required = true)Integer friendid,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        Role friendrole=rm.selectByPrimaryKey(friendid);
        Integer roleid=r.get(0).getId();
        //断交好友该状态status=1
        RoleFriends rmfs=new RoleFriends();
        rmfs.setRoleid(roleid);
        rmfs.setFriendid(friendid);
        rmfs.setStatus("0");
        List<RoleFriends> li1=rfm.selectAll(rmfs);
        li1.get(0).setStatus("1");
        rfm.updateByPrimaryKeySelective(li1.get(0));
        //断交好友该状态status=1
        rmfs.setRoleid(friendid);
        rmfs.setFriendid(roleid);
        rmfs.setStatus("0");
        List<RoleFriends> li2=rfm.selectAll(rmfs);
        li2.get(0).setStatus("1");
        rfm.updateByPrimaryKeySelective(li2.get(0));
        /////////////////////////反馈给对方，断交好友
        String name=r.get(0).getJuesename();
        RoleFriendsMsg rg=new RoleFriendsMsg();
        rg.setRoleid(friendid);
        rg.setFriendid(roleid);
        rg.setMessage("玩家("+name+")与你断交了好友关系！");
        rg.setData(new Date());
        rg.setType("通知");
        int a=rfmm.insertSelective(rg);
        return null;
    }
    ///////////未处理
    @RequestMapping(value = "/talkmsgs", method = { RequestMethod.GET, RequestMethod.POST })
    //好友私密功能
    public String talkmsg(RoleFriends roleFriends,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        model.addAttribute("msg",roleFriends);
        return "friends/talkmsg";
    }
    @RequestMapping(value = "/apptalkmsgs", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    //好友私密功能
    public String apptalkmsg(RoleFriends roleFriends,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
      CommonUtilAjax.sendAjaxList("msg",roleFriends,request,response);
        return null;
    }
    @RequestMapping(value = "/sendmsgs", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String sendmsg(RoleFriendsMsg roleFriendmsg,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        /////////////////////////反馈给对方信息
        roleFriendmsg.setData(new Date());
        roleFriendmsg.setType("私聊");

        int a=rfmm.insertSelective(roleFriendmsg);
        return null;
    }
    @RequestMapping(value = "/shuaxinmsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String shuaxinmsg(RoleFriendsMsg roleFriendmsg,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        /////////////////////////刷新信息

        int rid1=roleFriendmsg.getRoleid();
        int fid1=roleFriendmsg.getFriendid();
        int rid2=roleFriendmsg.getFriendid();
        int fid2=roleFriendmsg.getRoleid();
        List<RoleFriendsMsg> li=rfmm.getTalkMsgByTime(rid1,fid1,rid2,fid2);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("talkmsg",li);

        }else{
            obj.put("code", 1);
        }


        out.write(obj.toString());
        out.flush();
        out.close();



        return null;
    }
    @RequestMapping(value = "/appshuaxinmsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appshuaxinmsg(RoleFriendsMsg roleFriendmsg,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
        /////////////////////////刷新信息

        int rid1=roleFriendmsg.getRoleid();
        int fid1=roleFriendmsg.getFriendid();
        int rid2=roleFriendmsg.getFriendid();
        int fid2=roleFriendmsg.getRoleid();
        List<RoleFriendsMsg> li=rfmm.getTalkMsgByTime(rid1,fid1,rid2,fid2);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out=response.getWriter();
        JSONObject obj = new JSONObject(); //根据需要拼装json

        if(li.size()>0&&li!=null){
            obj.put("code", 0);
            obj.put("talkmsg",li);

        }else{
            obj.put("code", 1);
        }



        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        out.println(jsonpCallback+"("+obj.toString()+")");//返回jsonp格式数据
        out.flush();
        out.close();



        return null;
    }
    @RequestMapping(value = "/readmsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String readmsg(@RequestParam(value ="id",required = false)Integer id,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        /////////////////////////已读对方信息
        RoleFriendsMsg rf=new RoleFriendsMsg();
                    rf.setId(id);
                    rf.setStatus("1");
        rfmm.updateByPrimaryKeySelective(rf);
        return null;
    }
}

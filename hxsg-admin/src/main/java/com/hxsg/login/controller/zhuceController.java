//package com.hxsg.login.controller;
//import com.alibaba.fastjson.JSONObject;
//import com.google.gson.Gson;
//import com.hxsg.CommonUtil.CommonUtilAjax;
//import com.hxsg.CommonUtil.login.Constants;
//import com.hxsg.Dao.RoleMapper;
//import com.hxsg.Dao.RoleVipMapper;
//import com.hxsg.login.zhuceService;
//import com.hxsg.po.Role;
//import com.hxsg.po.RoleVip;
//
//import com.hxsg.vo.SystemMsg;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.*;
///**
// * Created by dlf on 2015/12/31.
// */
//@Controller
//@RequestMapping("/zhuce")
//public class zhuceController {
//    @Autowired
//    zhuceService zs;
//    @Autowired
//    RoleVipMapper rvm;
//    @Autowired
//    RoleMapper rm;
//    @Autowired
//    RoleVipMapper rmv;
//    private Logger logger =Logger.getLogger(zhuceController.class);
//    @RequestMapping(value = "/toLogin", method = { RequestMethod.GET, RequestMethod.POST })
//    public String toLogin( )throws Exception {
//            return "login/login";
//    }
//    @RequestMapping(value = "/gamezc", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String gamezc()throws Exception {
//        Map<String,Object> mp=Constants.SESSION_NAME;
//        for(String key:mp.keySet()){
//            try{
//                WebSocketSession wksession= (WebSocketSession) mp.get(key);
//                SystemMsg sg=new SystemMsg();
//                sg.setMsg("通知：系统5分钟后即将维护，请广大玩家提前下线");
//                Gson gn = new Gson();
//                TextMessage te = new TextMessage( gn.toJson(sg));
//                wksession.sendMessage(te);
//            }catch (Exception e){
//                System.out.println("session超时");
//            }
//        }
//        return "login/注册";
//
//    }
//    @RequestMapping(value = "/crole", method = { RequestMethod.GET, RequestMethod.POST })
//    public String crole()throws Exception {
//        return "login/创建角色";
//    }
//    @RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
//    public String index( Model model,HttpSession session)throws Exception {
//        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色ID
//        int roleid = r.get(0).getId();
//        System.out.println(roleid);
//        Role role=rm.selectByPrimaryKey(roleid);
//        model.addAttribute("role",role);
//        return "index/index";
//    }
//    @RequestMapping(value = "/roledetail", method = { RequestMethod.GET, RequestMethod.POST })
//    public String roledetail( Model model,HttpSession session)throws Exception {
//        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色ID
//        int roleid = r.get(0).getId();
//        System.out.println(roleid);
//        Role role=rm.selectByPrimaryKey(roleid);
//        model.addAttribute("role",role);
//        return "index/role";
//
//    }
//    @RequestMapping(value = "/approledetail", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String approledetail(HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
//        Integer r =(Integer)session.getAttribute("roleid");//获取session查询角色ID
//        int roleid = r;
//        System.out.println(roleid);
//        Role role=rm.selectByPrimaryKey(roleid);
//        RoleVip rp=new RoleVip();
//        rp.setRoleid(roleid);
//
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/plain");
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        PrintWriter out=response.getWriter();
//        JSONObject obj = new JSONObject(); //根据需要拼装json
//        obj.put("role",role);
//        List<RoleVip> vip=rmv.selectAll(rp);
//        if(vip.size()>0){
//            obj.put("rolevip",vip.get(0).getLevel());
//        }
//
//        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
//        out.println(jsonpCallback+"("+obj.toString()+")");//返回jsonp格式数据
//        out.flush();
//        out.close();
//
//        return null;
//
//    }
//    //注册账号
//    @RequestMapping(value = "/zc", method = { RequestMethod.GET, RequestMethod.POST })
//      public String zhuce(Role re,Model model)throws Exception {
//
//        System.out.println(re.getDengji());
//        if (zs.getZhuCe(re)) {
//            model.addAttribute("xinxi","注册成功！");
//            return "login/login";
//
//        } else {
//            model.addAttribute("xinxi", "注册失败！");
//            return "login/login";
//        }
//    }
//    //注册账号app
//    @RequestMapping(value = "/appzc", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String appzc(Role re,Model model,HttpServletRequest request,HttpServletResponse response)throws Exception {
//
//        System.out.println(re.getDengji());
//        Role res=new Role();
//        res.setName(re.getName());
//        List<Role> r=rm.selectAll(res);
//        if(r.size()>0){
//            CommonUtilAjax.sendAjax("注册失败！",request,response);
//        }else{
//            if (zs.getZhuCe(re)) {
//                CommonUtilAjax.sendAjax("注册成功！",request,response);
//            }
//        }
//
//        return null;
//    }
//    //加载index 页面数据app
//    @RequestMapping(value = "/appindex", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String appindex(Role re,HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception {
//        Integer id= (Integer) session.getAttribute("roleid");
//        if(id>0){
//                Role role=rm.selectByPrimaryKey(id);
//                CommonUtilAjax.sendAjax(role,request,response);
//
//
//
//        }
//
//        return null;
//    }
//    //判断VIP
//    @RequestMapping(value = "/appvip", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String appvip(Role re,HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception {
//        Integer id= (Integer) session.getAttribute("roleid");
//        if(id>0){
//            RoleVip rp=new RoleVip();
//            rp.setRoleid(id);
//            List<RoleVip> li=rvm.selectAll(rp);
//            if(li.size()>0){
//                CommonUtilAjax.sendAjaxList("vip",li.get(0),request,response);
//            }
//        }
//
//        return null;
//    }
//    //验证角色名是否重复
//    @RequestMapping(value = "/approlename", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String approlename(Role re,Model model,HttpServletRequest request,HttpServletResponse response)throws Exception {
//    if(re.getJuesename()!=null){
//    List<Role> li=rm.selectAll(re);
//    if(li.size()>0){
//        CommonUtilAjax.sendAjax(" ",request,response);
//    }else{
//        CommonUtilAjax.sendAjax("名称可用✔",request,response);
//    }
//}
//        return null;
//    }
//    //验证name是否重复
//    @RequestMapping(value = "/appname", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String appname(Role re,Model model,HttpServletRequest request,HttpServletResponse response)throws Exception {
//        if(re.getName()!=null){
//            List<Role> li=rm.selectAll(re);
//            if(li.size()>0){
//                CommonUtilAjax.sendAjax("该用户已被注册",request,response);
//            }else{
//                CommonUtilAjax.sendAjax("欢迎新用户✔",request,response);
//            }
//        }
//
//
//
//        return null;
//    }
//    //验证登录
//    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
//    public String login(Role rp,Model model,HttpSession session)throws Exception {
//
//        List<Role> list=rm.login(rp);//验证登录用户账号和密码；
//        String result = null;
//        if (list.size() > 0) {
//
//            //session.setAttribute(Constants.SESSION_USERNAME,rp.getName());
//
//            if(list.get(0).getDengji()>=1){
//                session.setAttribute("rolelist", list);//设置Session
//                // session.setAttribute("role", list.get(0));//设置Session
//                model.addAttribute("role", list.get(0));
//                Role re = new Role();
//                re.setStatus(1);//登录成功状态改为1;
//                rm.updateByPrimaryKeySelective(re);
//                // System.out.println(re.getYin());//查询携带银两
//
//
//
//
//                result = "index/index";
//            }else{
//                model.addAttribute("id", list.get(0).getId());//设置Session
//                result = "login/创建角色";
//
//            }
//
//
//
//        }else if (list.size()==0){
//            System.out.println("fail");
//            model.addAttribute("xinxi","用户名或者密码错误");
//            result = "login/login";
//
//
//        }
//
//        return result;
//    }
//    //验证登录APP版本
//    @RequestMapping(value = "/applogin", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String applogin(Role rp,Model model,HttpSession session,HttpServletResponse response,HttpServletRequest request)throws Exception {
//
//        List<Role> list=rm.login(rp);//验证登录用户账号和密码；
//        String result = null;
//        if (list.size()> 0) {
//            Constants.roleid=list.get(0).getId();
//            Constants.rolename=list.get(0).getJuesename();
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//            String values=sdf.format(new Date()).toString()+(int) (Math.random() * 10000+10000);
//            if( Constants.SESSION_NAME.get(list.get(0).getId().toString())!=null){
//                Constants.SESSION_NAME.remove(list.get(0).getId().toString());
//                Constants.SESSION_NAME.put(list.get(0).getId().toString(),values);
//            }else{
//                Constants.SESSION_NAME.put(list.get(0).getId().toString(),values);
//            }
//            if( Constants.SESSION_USERNAME!=null){
//                Integer rand = (int) (Math.random() * 10000+10000);
//                String key=rand.toString();
//                Constants.SESSION_USERNAME=key;
//
//            }else{
//                Integer rand = (int) (Math.random() * 10000);
//                String key=rand.toString();
//                Constants.SESSION_USERNAME=key;
//            }
//            session.setAttribute(Constants.SESSION_USERNAME,rp.getName());
//            if(list.get(0).getYin()>5000000){
//                list.get(0).setYin(5000000);
//                rm.updateByPrimaryKeySelective(list.get(0));
//            }
//            session.setAttribute("roleid", list.get(0).getId());//设置Session
//            session.setAttribute("rolekey",values);//设置Session
//            if(list.get(0).getDengji()==null) {
//                CommonUtilAjax.sendAjax("创建角色",request,response);
//            }else{
//                if(list.get(0).getDengji()>=1){
//                    session.setAttribute("rolelist", list);//设置Session
//                    Role re = new Role();
//                    re.setStatus(1);//登录成功状态改为1;
//                    rm.updateByPrimaryKeySelective(re);
//                    //ajax跨域请求
//                    Object[][] obj=new Object[][]{{"msg","直接登录"}};
//                    CommonUtilAjax.sendAjaxArray(obj,request,response);
//
//                }
//            }
//        }else if (list.size()==0){
//            //ajax跨域请求
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/plain");
//            response.setHeader("Pragma", "No-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expires", 0);
//            PrintWriter out=response.getWriter();
//            JSONObject obj = new JSONObject(); //根据需要拼装json
//            obj.put("msg","error");
//            String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
//            out.println(obj.toString());//返回jsonp格式数据
//            out.flush();
//            out.close();
//        }
//
//        return null;
//    }
//    //创建角色
//    @RequestMapping(value = "/creatrole", method = { RequestMethod.GET, RequestMethod.POST })
//    public String creatrole(@RequestParam(value = "id",required = false)Integer id,Role re,HttpSession session,Model model)throws Exception {
//
//        System.out.println(id);
//        String result=null;
//
//
//        Role rolelist =new Role();
//
//
//        rolelist.setId(id);
//        rolelist.setZhiye(re.getZhiye());// 职业
//        rolelist.setDengji(1);// 等级
//        rolelist.setJin(8888);// 金
//        rolelist.setYin(10000);// 银
//        rolelist.setJuesename(re.getJuesename());// 角色名
//        rolelist.setJuzhudi("许昌");
//        rolelist.setSex(re.getSex());// 性别
//        if( rolelist.getTouxiang()==null){
//            rolelist.setTouxiang("../image/wushi1.jpg");
//        }
//        rolelist.setTouxiang(re.getTouxiang());// 头像
//        //根据职业设置
//        rolelist.setQixue1(120);
//        rolelist.setQixue2(120);
//        rolelist.setJingli1(100);
//        rolelist.setJingli2(100);
//        rolelist.setJingli1(100);
//        rolelist.setGongji(130);
//        rolelist.setSudu(20);
//        rolelist.setFangyu(100);
//
//
//        //RoleDao rd = (RoleDao) DaoFactory.getdaoimpl("roledao");
//        // role.setId(id);
//        int a=rm.updateByPrimaryKeySelective(rolelist);
//        if (a>0) {
//
//            result = "index/index";
//        } else {
//            result = "login/createfail";
//        }
//
//
//        Role r=rm.selectByPrimaryKey(id);
//        model.addAttribute("role",r);
//        System.out.println(a);
//        return result;
//
//
//
//
//
//    }
//    //创建角色APP
//    @RequestMapping(value = "/appcreatrole", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String appcreatrole(Role re,HttpSession session,Model model,HttpServletRequest request,HttpServletResponse response)throws Exception {
//        Integer roleid= (Integer) session.getAttribute("roleid");//获取用户IDsession
//        Role role=rm.selectByPrimaryKey(roleid);
//        if(roleid!=null&&role.getDengji()==null){
//            Role rolelist =new Role();
//            rolelist.setId(roleid);
//            rolelist.setZhiye(re.getZhiye());// 职业
//            rolelist.setDengji(1);// 等级
//            rolelist.setJin(8888);// 金
//            rolelist.setYin(10000);// 银
//            rolelist.setJuesename(re.getJuesename());// 角色名
//            rolelist.setJuzhudi("许昌");
//            rolelist.setSex(re.getSex());// 性别
//            rolelist.setTouxiang(re.getTouxiang());// 头像
//            //根据职业设置
//            rolelist.setQixue1(120);
//            rolelist.setQixue2(120);
//            rolelist.setJingli1(100);
//            rolelist.setJingli2(100);
//            rolelist.setJingli1(100);
//            rolelist.setGongji(130);
//            rolelist.setSudu(20);
//            rolelist.setFangyu(100);
//            int a=rm.updateByPrimaryKeySelective(rolelist);
//            if (a>0) {
//                CommonUtilAjax.sendAjax("createsucces",request,response);
//                Role r=rm.selectByPrimaryKey(roleid);
//                CommonUtilAjax.sendAjaxList("list",r,request,response);
//            }
//        }
//        return null;
//    }
//
//
//
//
//}

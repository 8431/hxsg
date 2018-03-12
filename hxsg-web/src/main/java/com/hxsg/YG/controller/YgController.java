package com.hxsg.YG.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.Dao.RoleMapper;
import com.hxsg.Dao.RoleZbMapper;
import com.hxsg.Dao.RoleFujiangMapper;
import com.hxsg.Dao.wuqiMapper;
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
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/yg")
public class YgController {
    @Autowired
    RoleFujiangMapper rfm;
    @Autowired
    wuqiMapper wqm;
    @Autowired
    RoleMapper rm;
    @Autowired
    RoleZbMapper rzm;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() throws Exception {

        return "驿馆/驿馆";

    }

    @RequestMapping(value = "/fjls", method = {RequestMethod.GET, RequestMethod.POST})
    public String fjls(HttpSession session, Model model) throws Exception {
        //跳转到留守页面，显示可以留守的副将
        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        RoleFujiang rfs = new RoleFujiang();
        rfs.setRoleid(roleid);
        rfs.setShuxing(1);
        rfs.setStatus("战斗");
        List<RoleFujiang> li = rfm.getByRoleIdShuxStatus(rfs);
        System.out.println(li.size());
        model.addAttribute("lis", li);
        return "驿馆/副将留守";

    }
    @RequestMapping(value = "/appfjls", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appfjls(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
        //跳转到留守页面，显示可以留守的副将
        Integer r = (Integer) session.getAttribute("roleid");
        if(r>0){
            int roleid = r;
            RoleFujiang rfs = new RoleFujiang();
            rfs.setRoleid(roleid);
            rfs.setShuxing(1);
            rfs.setStatus("战斗");
            List<RoleFujiang> li = rfm.getByRoleIdShuxStatus(rfs);
            CommonUtilAjax.sendAjaxList("rf",li,request,response);


        }


        return null;

    }

    @RequestMapping(value = "/lsshuxing", method = {RequestMethod.GET, RequestMethod.POST})
    public String lsshuxing(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        //执行留守操作

        RoleFujiang rfj = rfm.selectByPrimaryKey(id);
        rfj.setShuxing(0);
        rfm.updateByPrimaryKeySelective(rfj);
        return "redirect:fjls";

    }
    @RequestMapping(value = "/applsshuxing", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String applsshuxing(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestParam(value = "id", required = false) Integer id) throws Exception {
        //执行留守操作
        Integer r = (Integer) session.getAttribute("roleid");
        if(r>0){ RoleFujiang rfj = rfm.selectByPrimaryKey(id);
            rfj.setShuxing(0);
            rfm.updateByPrimaryKeySelective(rfj);
            CommonUtilAjax.sendAjax("",request,response);
        }

        return null;

    }


    @RequestMapping(value = "/fjzl", method = {RequestMethod.GET, RequestMethod.POST})
    public String fjzl(HttpSession session, Model model) throws Exception {
        //跳转到招领页面，显示可以招领的副将
        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        List<RoleFujiang> li = rfm.getByRoleIdShux(roleid, 0);
        System.out.println(li.size());
        model.addAttribute("lis", li);

        return "驿馆/副将招领";

    }
    @RequestMapping(value = "/appfjzl", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appfjzl(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
        //跳转到招领页面，显示可以招领的副将
        Integer r = (Integer) session.getAttribute("roleid");
        if(r>0){
            int roleid = r;
            List<RoleFujiang> li = rfm.getByRoleIdShux(roleid, 0);

            CommonUtilAjax.sendAjaxList("rf",li,request,response);

        }


        return null;

    }

    @RequestMapping(value = "/appzlshuxing", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    //执行招领操作
    public String appzlshuxing(HttpServletRequest request,@RequestParam(value = "id", required = false) Integer id, HttpSession session, Model model, HttpServletResponse response) throws Exception {


        Integer r = (Integer) session.getAttribute("roleid");
        if(r>0){
            int roleid = r;
            List<RoleFujiang> li = rfm.getByRoleIdShux(roleid, 1);
            if (li.size() < 10) {


                Role role = (Role) rm.selectByPrimaryKey(roleid);

                int zlmoney = 1000;//招领副将的价格
                int rolemoney = role.getYin();//角色携带银两

                if (rolemoney >= zlmoney) {//如果携带银两大于招领副将的价格，招领副将执行

                    role.setYin(rolemoney - zlmoney);

                    int result = rm.updateByPrimaryKeySelective(role);
                    if (result > 0) {
                        RoleFujiang rfj = rfm.selectByPrimaryKey(id);
                        rfj.setShuxing(1);
                        rfm.updateByPrimaryKeySelective(rfj);
                        CommonUtilAjax.sendAjaxList("msg","招领副将成功！消费" + zlmoney + "两!",request,response);


                    }

                } else {
                    CommonUtilAjax.sendAjaxList("msg","招领副将失败！银两不足",request,response);


                }
            } else {
                CommonUtilAjax.sendAjaxList("msg","您的随行副将已经有10个，无法招领。",request,response);


            }



        }


        return null;

    }
    @RequestMapping(value = "/zlshuxing", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    //执行招领操作
    public String zlshuxing(@RequestParam(value = "id", required = false) Integer id, HttpSession session, Model model, HttpServletResponse response) throws Exception {


        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        int roleid = r.get(0).getId();
        List<RoleFujiang> li = rfm.getByRoleIdShux(roleid, 1);
        if (li.size() < 10) {


            Role role = (Role) rm.selectByPrimaryKey(roleid);

            int zlmoney = 1000;//招领副将的价格
            int rolemoney = role.getYin();//角色携带银两

            if (rolemoney >= zlmoney) {//如果携带银两大于招领副将的价格，招领副将执行

                role.setYin(rolemoney - zlmoney);

                int result = rm.updateByPrimaryKeySelective(role);
                if (result > 0) {
                    RoleFujiang rfj = rfm.selectByPrimaryKey(id);
                    rfj.setShuxing(1);
                    rfm.updateByPrimaryKeySelective(rfj);
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();

                    Role re = (Role) rm.selectByPrimaryKey(roleid);
                    out.write("招领副将成功！消费" + zlmoney + "两!");
                    out.flush();
                    out.close();


                }

            } else {
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("utf-8");
                out.write("招领副将失败！银两不足");
                out.flush();
                out.close();


                System.out.println("余额不足");
            }
        } else {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();

            out.write("您的随行副将已经有10个，无法招领。");
            out.flush();
            out.close();

        }


        return null;

    }



    @RequestMapping(value = "/fujiang", method = {RequestMethod.GET, RequestMethod.POST})
    public String fujiang(HttpSession session, String sx, Model model) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//通过登录后的Session获取角色ID
        int roleid = r.get(0).getId();
        List<RoleFujiang> li = rfm.getByRoleIdShux(roleid, 1);
        System.out.println(li.size());
        model.addAttribute("lis", li);

        return "驿馆/角色副将";

    }
    @RequestMapping(value = "/appfujiang", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appfujiang(HttpSession session, String sx,HttpServletRequest request,HttpServletResponse response) throws Exception {
        Integer r = (Integer) session.getAttribute("roleid");
        if(r>0){
            int roleid = r;
            List<RoleFujiang> li = rfm.getByRoleIdShux(roleid, 1);
            CommonUtilAjax.sendAjaxList("rf",li,request,response);

        }
        return null;

    }

    @RequestMapping(value = "/status", method = {RequestMethod.GET, RequestMethod.POST})
    public String status(@RequestParam(value = "id", required = false) Integer id, HttpSession session, String sx, Model model) throws Exception {
//设置副将的战斗状态
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//通过登录后的Session获取角色ID
        int roleid = r.get(0).getId();
        RoleFujiang rfj = (RoleFujiang) rfm.selectByPrimaryKey(id);
        System.out.println(rfj.getShuxing());

        if (rfj.getStatus().equals("休息")) {
            rfj.setStatus("战斗");

            int a = rfm.updateByPrimaryKeySelective(rfj);
            System.out.println(a);
        } else {
            //还未设计根据角色等级设置最多可以带几个副将出站
            if (rfj.getStatus().equals("战斗")) {
                RoleFujiang rfs = new RoleFujiang();
                rfs.setRoleid(roleid);
                rfs.setShuxing(1);
                rfs.setStatus("休息");
                Role re = rm.selectByPrimaryKey(roleid);
                List<RoleFujiang> li = rfm.getByRoleIdShuxStatus(rfs);


                if (re.getDengji() <= 19) { //1-19级可以带一个随行副将

                    if (li.size() < 1) {
                        rfj.setStatus("休息");
                        int a = rfm.updateByPrimaryKeySelective(rfj);

                    } else {
                        System.out.println(li.size());
                        RoleFujiang rf = li.get(0);
                        rf.setStatus("战斗");
                        rfm.updateByPrimaryKeySelective(rf);
                        rfj.setStatus("休息");
                        int a = rfm.updateByPrimaryKeySelective(rfj);

                    }

                }
                if (re.getDengji() > 19 && re.getDengji() < 40) { //20-39级可以带2个随行副将
                    if (li.size() < 2) {
                        rfj.setStatus("休息");
                        int a = rfm.updateByPrimaryKeySelective(rfj);

                    } else {
                        System.out.println(li.size());
                        RoleFujiang rf = li.get(1);
                        rf.setStatus("战斗");
                        rfm.updateByPrimaryKeySelective(rf);

                        rfj.setStatus("休息");
                        int a = rfm.updateByPrimaryKeySelective(rfj);


                    }

                }
                if (re.getDengji() > 39) {   //40-？可以带3个随行副将
                    if (li.size() < 3) {
                        rfj.setStatus("休息");
                        int a = rfm.updateByPrimaryKeySelective(rfj);

                    } else {
                        System.out.println(li.size());
                        RoleFujiang rf = li.get(2);
                        rf.setStatus("战斗");
                        rfm.updateByPrimaryKeySelective(rf);

                        rfj.setStatus("休息");
                        int a = rfm.updateByPrimaryKeySelective(rfj);


                    }

                }

            }


        }


        return "redirect:fujiang";


    }
    @RequestMapping(value = "/appstatus", method = {RequestMethod.GET, RequestMethod.POST})
    public String appstatus(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "id", required = false) Integer id, HttpSession session, String sx, Model model) throws Exception {
//设置副将的战斗状态
        Integer r = (Integer) session.getAttribute("roleid");
        if(r>0){
            CommonUtilAjax.sendAjax("",request,response);
            int roleid = r;
            RoleFujiang rfj = (RoleFujiang) rfm.selectByPrimaryKey(id);
            System.out.println(rfj.getShuxing());

            if (rfj.getStatus().equals("休息")) {
                rfj.setStatus("战斗");

                int a = rfm.updateByPrimaryKeySelective(rfj);
                System.out.println(a);
            } else {

                if (rfj.getStatus().equals("战斗")) {
                    RoleFujiang rfs = new RoleFujiang();
                    rfs.setRoleid(roleid);
                    rfs.setShuxing(1);
                    rfs.setStatus("休息");
                    Role re = rm.selectByPrimaryKey(roleid);
                    List<RoleFujiang> li = rfm.getByRoleIdShuxStatus(rfs);


                    if (re.getDengji() <= 19) { //1-19级可以带一个随行副将

                        if (li.size() < 1) {
                            rfj.setStatus("休息");
                            int a = rfm.updateByPrimaryKeySelective(rfj);

                        } else {
                            System.out.println(li.size());
                            RoleFujiang rf = li.get(0);
                            rf.setStatus("战斗");
                            rfm.updateByPrimaryKeySelective(rf);
                            rfj.setStatus("休息");
                            int a = rfm.updateByPrimaryKeySelective(rfj);

                        }

                    }
                    if (re.getDengji() > 19 && re.getDengji() < 40) { //20-39级可以带2个随行副将
                        if (li.size() < 2) {
                            rfj.setStatus("休息");
                            int a = rfm.updateByPrimaryKeySelective(rfj);

                        } else {
                            System.out.println(li.size());
                            RoleFujiang rf = li.get(1);
                            rf.setStatus("战斗");
                            rfm.updateByPrimaryKeySelective(rf);

                            rfj.setStatus("休息");
                            int a = rfm.updateByPrimaryKeySelective(rfj);


                        }

                    }
                    if (re.getDengji() > 39) {   //40-？可以带3个随行副将
                        if (li.size() < 3) {
                            rfj.setStatus("休息");
                            int a = rfm.updateByPrimaryKeySelective(rfj);

                        } else {
                            System.out.println(li.size());
                            RoleFujiang rf = li.get(2);
                            rf.setStatus("战斗");
                            rfm.updateByPrimaryKeySelective(rf);

                            rfj.setStatus("休息");
                            int a = rfm.updateByPrimaryKeySelective(rfj);


                        }

                    }

                }


            }

        }



        return null;


    }
    @RequestMapping(value = "/fjxx", method = {RequestMethod.GET, RequestMethod.POST})
    public String fjxx(@RequestParam(value = "id", required = false) Integer id, String sx, Model model) throws Exception {

        //传递副将信息

        RoleFujiang rfj=(RoleFujiang) rfm.selectByPrimaryKey(id);

        model.addAttribute("rfj",rfj);

        return "副将/玩家副将";

    }
    @RequestMapping(value = "/appfjxx", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appfjxx(HttpSession session,@RequestParam(value = "id", required = false) Integer id, String sx,HttpServletRequest request,HttpServletResponse response) throws Exception {

        //传递副将信息
        Integer r = (Integer) session.getAttribute("roleid");
        if(r>0){
            RoleFujiang rfj=(RoleFujiang) rfm.selectByPrimaryKey(id);
            CommonUtilAjax.sendAjaxList("rfj",rfj,request,response);

        }




        return null;

    }

}
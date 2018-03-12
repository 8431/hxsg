package com.hxsg.youjian.controller;

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
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/youjian")
public class YouJController {
    @Autowired
    YouJianMapper yjm;
    @Autowired
    RoleMapper rm;
    @Autowired
    RoleFriendsMsgMapper rfmm;
    @Autowired
    jiangjunMapper jjm;
    @Autowired
    YouJianWuPinMapper yjwpm;
    @Autowired
    RoleFujiangMapper rfm;
    Integer yjid=null;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/yj", method = {RequestMethod.GET, RequestMethod.POST})
    public String yj() throws Exception {

        return "邮件/邮件";

    }
    @RequestMapping(value = "/yfyj", method = {RequestMethod.GET, RequestMethod.POST})
    public String yfyj() throws Exception {


        return "邮件/已发邮件";

    }
    @RequestMapping(value = "/ydyj", method = {RequestMethod.GET, RequestMethod.POST})
    public String ydyj(Model model,HttpSession session) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色

        Integer roleid = r.get(0).getId();

        List<YouJian> liyj=yjm.selectStatus(roleid);
        model.addAttribute("liyj",liyj);

        return "邮件/已读邮件";

    }
    //已读邮件
    @RequestMapping(value = "/appydyj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appydyj(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception {
        Integer roleid = (Integer) session.getAttribute("roleid");//获取session查询角色
        List<YouJian> liyj=yjm.selectStatus(roleid);
        CommonUtilAjax.sendAjaxList("liyj",liyj,request,response);
        return null;

    }
    //未读邮件
    @RequestMapping(value = "/appwdyj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appwdyj(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception {
        Integer roleid = (Integer) session.getAttribute("roleid");//获取session查询角色
        YouJian yj=new  YouJian();
        yj.setReceiveid(roleid);
        yj.setStatus("0");
        List<YouJian> liyj=yjm.selectAll(yj);
        CommonUtilAjax.sendAjaxList("liyj",liyj,request,response);


        return null;

    }
    //添加物品
    @RequestMapping(value = "/apptjwp", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String apptjwp(HttpServletResponse response,@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpSession session)throws Exception {
        jiangjun jj= jjm.selectByPrimaryKey(id);



        CommonUtilAjax.sendAjaxList("msg",jj,request,response);
        return null;

    }
    //添加副将
    @RequestMapping(value = "/apptjfj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String apptjfj(HttpServletResponse response,@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpSession session)throws Exception {
        RoleFujiang ff=rfm.selectByPrimaryKey(id);

        CommonUtilAjax.sendAjaxList("msg",ff,request,response);
        return null;

    }
    //添加装备
    @RequestMapping(value = "/apptjzb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String apptjzb(HttpServletResponse response,@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpSession session)throws Exception {
        RoleFujiang ff=rfm.selectByPrimaryKey(id);
        CommonUtilAjax.sendAjaxList("msg",ff,request,response);
        return null;

    }
    //添加矿石
    @RequestMapping(value = "/apptjks", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String apptjks (HttpServletResponse response,@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpSession session)throws Exception {
        RoleFujiang ff=rfm.selectByPrimaryKey(id);
        CommonUtilAjax.sendAjaxList("msg",ff,request,response);
        return null;

    }
    //邮件详情
    @RequestMapping(value = "/appckyj", method = {RequestMethod.GET, RequestMethod.POST})
    public String appckyj(HttpSession session,@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception {
        Integer roleid = (Integer) session.getAttribute("roleid");//获取session查询角色
        YouJian yj=yjm.selectByPrimaryKey(id);
        if(yj.getStatus().equals("0")){
            yj.setStatus("已读");
            yjm.updateByPrimaryKey(yj);
        }

        YouJianWuPin yjwp=new YouJianWuPin();

        yjwp.setReceiveid(yj.getReceiveid());
        yjwp.setRoleid(yj.getRoleid());
        yjwp.setYoujianid(id);
        List<YouJianWuPin> liyjwp=yjwpm.selectAll(yjwp);
        for(int i=0;i<liyjwp.size();i++){
            if(liyjwp.get(i).getStatus().equals("0")){
                liyjwp.get(i).setStatus("已读");
                yjwpm.updateByPrimaryKey( liyjwp.get(i));
            }

        }

        YouJian yj2=yjm.selectByPrimaryKey(id);

        Object[][] bt=new Object[][]{{"liyjwp",liyjwp},{"yj",yj2}};
        CommonUtilAjax.sendAjaxArray(bt,request,response);

        return null;

    }


    @RequestMapping(value = "/fsyj", method = {RequestMethod.GET, RequestMethod.POST})
    public String fsyj(@RequestParam(value = "id",required = false)Integer id,Model model) throws Exception {
        model.addAttribute("receiveid",id);
        return "邮件/发送邮件";

    }
    @RequestMapping(value = "/tjwp", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String tjwp(HttpServletResponse response,@RequestParam(value = "id",required = false)Integer id,Model model)throws Exception {
        jiangjun jj= jjm.selectByPrimaryKey(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();//使用json
        obj.put("msg",jj);
        out.write(obj.toString());
        out.flush();
        out.close();

        return null;

    }
    @RequestMapping(value = "/wdyj", method = {RequestMethod.GET, RequestMethod.POST})
    public String wdyj(Model model,HttpSession session) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色

        Integer roleid = r.get(0).getId();
        YouJian yj=new  YouJian();
        yj.setReceiveid(roleid);
        yj.setStatus("0");
        List<YouJian> liyj=yjm.selectAll(yj);

//        for(int i=0;i<liyj.size();i++){
//            for(int j=0;j<liyjwp.size();j++){
//                if(liyj.get(i).getReceiveid().equals(liyjwp.get(j).getReceiveid())){
//                    model.addAttribute("liyjwp",liyjwp);
//                }
//            }
//        }
        model.addAttribute("liyj",liyj);


        return "邮件/未读邮件";

    }
    @RequestMapping(value = "/ckyj", method = {RequestMethod.GET, RequestMethod.POST})
    public String ckyj(HttpSession session,@RequestParam(value = "id",required = false)Integer id,Model model) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色

        Integer roleid = r.get(0).getId();
        YouJian yj=yjm.selectByPrimaryKey(id);
        if(yj.getStatus().equals("0")){
            yj.setStatus("已读");
            yjm.updateByPrimaryKey(yj);
        }

        YouJianWuPin yjwp=new YouJianWuPin();

        yjwp.setReceiveid(yj.getReceiveid());
        yjwp.setRoleid(yj.getRoleid());
        yjwp.setYoujianid(id);
        List<YouJianWuPin> liyjwp=yjwpm.selectAll(yjwp);
        for(int i=0;i<liyjwp.size();i++){
            if(liyjwp.get(i).getStatus().equals("0")){
                liyjwp.get(i).setStatus("已读");
                yjwpm.updateByPrimaryKey( liyjwp.get(i));
            }
            model.addAttribute("liyjwp",liyjwp);
        }
        YouJian yj2=yjm.selectByPrimaryKey(id);

        model.addAttribute("yj",yj2);


        return "邮件/邮件详细";

    }
    @RequestMapping(value = "/sendyj", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String sendyj(YouJian yj,Model model,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        Role role=rm.selectByPrimaryKey(roleid);
        Integer outyin=yj.getYin();
        Integer roleyin=role.getYin();

        if (yj.getReceiveid() != null && yj.getYin() != null && yj.getYin()>= 0) {
            if(roleyin>=outyin){
                role.setYin(roleyin-outyin);
                rm.updateByPrimaryKeySelective(role);

                if(yjid!=null){
                    YouJian yj2=yjm.selectByPrimaryKey(yjid);

                    yj2.setYin(yj.getYin());
                    yj2.setMessage(yj.getMessage());
                    yjm.updateByPrimaryKey(yj2);
                }
                if(yjid==null){
                    YouJian yj3=new YouJian();
                    Role re = rm.selectByPrimaryKey(yj.getReceiveid());
                    yj.setData(new Date());
                    yj.setRoleid(roleid);
                    yj.setReceiveid(yj.getReceiveid());
                    yj.setStatus("0");
                    yj.setRolename(rolename);
                    yj.setReceivedname(re.getJuesename());
                    yjm.insertSelective(yj);

                }

                RoleFriendsMsg rg = new RoleFriendsMsg();
                rg.setRoleid(yj.getReceiveid());
                rg.setFriendid(roleid);
                rg.setMessage("你收到【" + rolename + "】的一封邮件！");
                rg.setData(new Date());
                rg.setType("邮件");
                int a = rfmm.insertSelective(rg);

                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                JSONObject obj = new JSONObject();//使用json
                obj.put("msg", "发送邮件成功！");
                out.write(obj.toString());
                out.flush();
                out.close();
                yjid=null;
            }else{
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                JSONObject obj = new JSONObject();//使用json
                obj.put("msg", "余额不足");
                out.write(obj.toString());
                out.flush();
                out.close();
            }

        } else {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();//使用json
            obj.put("msg", "ID或者银两输入不能为空！");
            out.write(obj.toString());
            out.flush();
            out.close();

        }

        return null;
    }
    @RequestMapping(value = "/appsendyj", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appsendyj(YouJian yj,HttpServletRequest request,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        Role role=rm.selectByPrimaryKey(roleid);
        Integer outyin=yj.getYin();
        Integer roleyin=role.getYin();
        if(yj.getReceiveid().equals(roleid)){
            CommonUtilAjax.sendAjaxList("msg", "请不要给自己发送邮件！",request,response);
        }else{


            if (yj.getReceiveid() != null && yj.getYin() != null && yj.getYin()>= 0) {
                if(roleyin>=outyin){



                    if(yjid!=null){
                        YouJian yj2=yjm.selectByPrimaryKey(yjid);

                        yj2.setYin(yj.getYin());
                        yj2.setMessage(yj.getMessage());
                        yjm.updateByPrimaryKey(yj2);
                        role.setYin(roleyin-outyin);
                        rm.updateByPrimaryKeySelective(role);
                    }
                    if(yjid==null){
                        YouJian yj3=new YouJian();
                        Role re = rm.selectByPrimaryKey(yj.getReceiveid());
                        if(re!=null){
                            yj.setData(new Date());
                            yj.setRoleid(roleid);
                            yj.setReceiveid(yj.getReceiveid());
                            yj.setStatus("0");
                            yj.setRolename(rolename);
                            yj.setReceivedname(re.getJuesename());
                            yjm.insertSelective(yj);
                            role.setYin(roleyin-outyin);
                            rm.updateByPrimaryKeySelective(role);

                        }else{
                            CommonUtilAjax.sendAjaxList("msg", "该ID不存在，无法发送！",request,response);
                            return null;
                        }


                    }

                    RoleFriendsMsg rg = new RoleFriendsMsg();
                    rg.setRoleid(yj.getReceiveid());
                    rg.setFriendid(roleid);
                    rg.setMessage("你收到【" + rolename + "】的一封邮件！");
                    rg.setData(new Date());
                    rg.setType("邮件");
                    int a = rfmm.insertSelective(rg);
                    CommonUtilAjax.sendAjaxList("msg", "发送邮件成功！",request,response);

                    yjid=null;
                }else{
                    CommonUtilAjax.sendAjaxList("msg", "余额不足！",request,response);
                }

            } else {
                CommonUtilAjax.sendAjaxList("msg", "ID或者银两输入不能为空！",request,response);


            }
        }
        return null;
    }
    @RequestMapping(value = "/lqyl", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String lqyl(@RequestParam(value = "id",required = false)Integer id,Model model,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        Role re=rm.selectByPrimaryKey(roleid);
        YouJian yj= yjm.selectByPrimaryKey(id);
        if(yj.getStatus().equals("已领取")){
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();//使用json
            obj.put("msg", "请不要重复领取！");
            out.write(obj.toString());
            out.flush();
            out.close();
        }
        Integer yjyin=yj.getYin();
        Integer  roleyin= re.getYin();
        re.setYin(roleyin+yjyin);
        rm.updateByPrimaryKeySelective(re);
        yj.setStatus("已领取");
        yjm.updateByPrimaryKey(yj);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();//使用json
        obj.put("msg", "你成功领取"+yjyin+"！");
        out.write(obj.toString());
        out.flush();
        out.close();
        //通知对方邮件已经领取
        RoleFriendsMsg rg = new RoleFriendsMsg();
        rg.setRoleid(yj.getReceiveid());
        rg.setFriendid(yj.getRoleid());
        rg.setMessage("【" + yj.getRolename() + "】提取了你发送的物品！");
        rg.setData(new Date());
        rg.setType("通知");
        int a = rfmm.insertSelective(rg);




        return null;
    }
    @RequestMapping(value = "/applqyl", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public synchronized String  applqyl(@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        Role re=rm.selectByPrimaryKey(roleid);
        YouJian yj= yjm.selectByPrimaryKey(id);
        if(yj.getStatus().equals("已领取")){
            CommonUtilAjax.sendAjaxList("msg",  "请不要重复领取！",request,response);
        }else{
            Integer yjyin=yj.getYin();
            Integer  roleyin= re.getYin();
            re.setYin(roleyin+yjyin);
            rm.updateByPrimaryKeySelective(re);
            yj.setStatus("已领取");
            yjm.updateByPrimaryKey(yj);
            CommonUtilAjax.sendAjaxList("msg", "你成功领取"+yjyin+"！",request,response);
            //通知对方邮件已经领取
            RoleFriendsMsg rg = new RoleFriendsMsg();
            rg.setRoleid(yj.getReceiveid());
            rg.setFriendid(yj.getRoleid());
            rg.setMessage("【" + yj.getReceivedname() + "】提取了你发送的物品！");
            rg.setData(new Date());
            rg.setType("通知");
            int a = rfmm.insertSelective(rg);

        }






        return null;
    }
    @RequestMapping(value = "/applqwpyl", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public synchronized String applqwpyl(@RequestParam(value = "name",required = false)String name,@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        Role re=rm.selectByPrimaryKey(roleid);
        YouJianWuPin yj= yjwpm.selectByPrimaryKey(id);
        if(yj.getStatus().equals("已领取")){
            CommonUtilAjax.sendAjaxList("msg", "请不要重复领取",request,response);

        }
        if(yj.getStatus().equals("已读")) {


            Integer yjyin = yj.getYin();
            if(yjyin==null){
                yjyin=0;

            }
            Integer roleyin = re.getYin();
            if (roleyin >= yjyin) {
                re.setYin(roleyin - yjyin);
                rm.updateByPrimaryKeySelective(re);
                //后期加入判断物品是相同就累加
                //药品，杂物，矿石，装备走这个
                jiangjun jj = new jiangjun();
                jj.setRoleid(roleid);

                jj.setJjname(yj.getWupinnname());

                List<jiangjun> li = jjm.selectAll(jj);
                //副将走这个,接收到ID如果从副将表里面查询到了，并且副将表里的副将名和邮件表里存储的名字一样。说明该物品是副将
                RoleFujiang rf=rfm.selectByPrimaryKey(yj.getWupinid());
                if(rf!=null&&yj.getWupinnname().equals(rf.getFujiangname())){
                    rf.setRoleid(yj.getReceiveid());
                    rf.setZhongchengdu(0);
                    rf.setZhen("0");
                    rfm.updateByPrimaryKeySelective(rf);


                }else{
                    if (li.size() > 0) {
                        //if (li.get(0).getJjname().indexOf("将军令") != -1) {
                        li.get(0).setNum(li.get(0).getNum() + yj.getNum());

                        jjm.updateByPrimaryKeySelective(li.get(0));
                        //}
                    } else {
                        jj.setNum(yj.getNum());
                        jjm.insertSelective(jj);
                    }

                }



                Role receiverole = rm.selectByPrimaryKey(yj.getRoleid());
                receiverole.setYin(receiverole.getYin() + yjyin);
                rm.updateByPrimaryKeySelective(receiverole);
                YouJianWuPin yj2 = yjwpm.selectByPrimaryKey(id);
                yj2.setStatus("已领取");
                yjwpm.updateByPrimaryKey(yj2);
                CommonUtilAjax.sendAjaxList("msg", "你成功领取" + yj.getWupinnname() + "！",request,response);

                // Msg mg=new Msg();
                // mg.sendMsg(yj.getReceiveid(),yj.getRoleid(),yj.getRolename());
                //如果对方提取了物品，通知发送方，物品被提取。
                RoleFriendsMsg rg = new RoleFriendsMsg();
                rg.setRoleid(yj.getReceiveid());
                rg.setFriendid(yj.getRoleid());
                rg.setMessage("【" + yj.getRolename() + "】提取了你发送的物品！");
                rg.setData(new Date());
                rg.setType("通知");
                int a = rfmm.insertSelective(rg);







            } else {
                CommonUtilAjax.sendAjaxList("msg", "余额不足无法提取！",request,response);

            }

        }




        return null;
    }
    //领取物品
    @RequestMapping(value = "/lqwpyl", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public synchronized String lqwpyl(@RequestParam(value = "id",required = false)Integer id,Model model,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        Role re=rm.selectByPrimaryKey(roleid);
        YouJianWuPin yj= yjwpm.selectByPrimaryKey(id);
        if(yj.getStatus().equals("已领取")){
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();//使用json
            obj.put("msg", "请不要重复领取！");
            out.write(obj.toString());
            out.flush();
            out.close();
        }
        if(yj.getStatus().equals("已读")) {


            Integer yjyin = yj.getYin();
            Integer roleyin = re.getYin();
            if (roleyin >= yjyin) {
                re.setYin(roleyin - yjyin);
                rm.updateByPrimaryKeySelective(re);
                //后期加入判断物品是相同就累加

                jiangjun jj = new jiangjun();
                jj.setRoleid(roleid);

                jj.setJjname(yj.getWupinnname());
                List<jiangjun> li = jjm.selectAll(jj);
                if (li.size() > 0) {
                    //if (li.get(0).getJjname().indexOf("将军令") != -1) {
                    li.get(0).setNum(li.get(0).getNum() + yj.getNum());

                    jjm.updateByPrimaryKeySelective(li.get(0));
                    //}
                } else {
                    jj.setNum(yj.getNum());
                    jjm.insertSelective(jj);
                }

                Role receiverole = rm.selectByPrimaryKey(yj.getRoleid());
                receiverole.setYin(receiverole.getYin() + yjyin);
                rm.updateByPrimaryKeySelective(receiverole);
                YouJianWuPin yj2 = yjwpm.selectByPrimaryKey(id);
                yj2.setStatus("已领取");
                yjwpm.updateByPrimaryKey(yj2);
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                JSONObject obj = new JSONObject();//使用json
                obj.put("msg", "你成功领取" + yj.getWupinnname() + "！");
                out.write(obj.toString());
                out.flush();
                out.close();
                // Msg mg=new Msg();
                // mg.sendMsg(yj.getReceiveid(),yj.getRoleid(),yj.getRolename());
                //如果对方提取了物品，通知发送方，物品被提取。
                RoleFriendsMsg rg = new RoleFriendsMsg();
                rg.setRoleid(yj.getReceiveid());
                rg.setFriendid(yj.getRoleid());
                rg.setMessage("【" + yj.getReceivedname() + "】提取了你发送的物品！");
                rg.setData(new Date());
                rg.setType("通知");
                int a = rfmm.insertSelective(rg);







            } else {
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                JSONObject obj = new JSONObject();//使用json
                obj.put("msg", "余额不足无法提取！");
                out.write(obj.toString());
                out.flush();
                out.close();
            }

        }




        return null;
    }
    @RequestMapping(value = "/sxnum", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String sxnum(@RequestParam(value = "yin")Integer yin,@RequestParam(value = "receiveid")Integer receiveid,HttpSession session,jiangjun jjn,Model model) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        jiangjun jj=jjm.selectByPrimaryKey(jjn.getId());
        Integer a= jj.getNum();

        if(yjid==null){
            YouJian yj=new YouJian();
            Role re = rm.selectByPrimaryKey(receiveid);
            yj.setData(new Date());
            yj.setRoleid(roleid);
            yj.setReceiveid(receiveid);
            yj.setStatus("0");
            yj.setRolename(rolename);
            yj.setReceivedname(re.getJuesename());
            yjm.insertSelective(yj);
            yjid=yj.getId();
        }
        if(jjn.getNum()>0&&a>=jjn.getNum()){

            jj.setNum(a-jjn.getNum());
            jjm.updateByPrimaryKeySelective(jj);
            //如果查询到物品邮件中包含相同名称，数量累加。

            YouJianWuPin yp=new YouJianWuPin();
            yp.setNum(jjn.getNum());
            yp.setStatus("0");
            yp.setDate(new Date());
            yp.setRoleid(roleid);
            yp.setWupinid(jjn.getId());
            yp.setWupinnname(jjn.getJjname());
            yp.setRolename(rolename);
            Role rc=rm.selectByPrimaryKey(receiveid);
            yp.setReceiveid(receiveid);
            yp.setReceivedname(rc.getJuesename());
            yp.setYin(yin);
            //yp.setTypeid();
            yp.setYoujianid(yjid);
            yjwpm.insert(yp);
        }


        return null;

    }
    @RequestMapping(value = "/appsxnum", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String appsxnum(@RequestParam(value = "yin")Integer yin,@RequestParam(value = "receiveid")Integer receiveid,HttpSession session,jiangjun jjn,HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        jiangjun jj=jjm.selectByPrimaryKey(jjn.getId());
        // jj.setStatus("邮件中");
        //jjm.updateByPrimaryKeySelective(jj);
        Integer a= jj.getNum();

        if(yjid==null){
            YouJian yj=new YouJian();
            Role re = rm.selectByPrimaryKey(receiveid);
            yj.setData(new Date());
            yj.setRoleid(roleid);
            yj.setReceiveid(receiveid);
            yj.setStatus("0");
            yj.setRolename(rolename);
            yj.setReceivedname(re.getJuesename());
            yjm.insertSelective(yj);
            yjid=yj.getId();
        }
        if(jjn.getNum()>0&&a>=jjn.getNum()){

            jj.setNum(a-jjn.getNum());
            jjm.updateByPrimaryKeySelective(jj);
            //如果查询到物品邮件中包含相同名称，数量累加。

            YouJianWuPin yp=new YouJianWuPin();
            yp.setNum(jjn.getNum());
            yp.setStatus("0");
            yp.setDate(new Date());
            yp.setRoleid(roleid);
            yp.setWupinid(jjn.getId());
            yp.setWupinnname(jjn.getJjname());
            yp.setRolename(rolename);
            Role rc=rm.selectByPrimaryKey(receiveid);
            yp.setReceiveid(receiveid);
            yp.setReceivedname(rc.getJuesename());
            yp.setYin(yin);
            //yp.setTypeid();
            yp.setYoujianid(yjid);
            yjwpm.insert(yp);
            Integer ypid=yp.getId();
            CommonUtilAjax.sendAjaxList("msg",yp.getId(),request,response);
        }


        return null;

    }
    @RequestMapping(value = "/appsxnumfj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String appsxnumfj(@RequestParam(value = "yin")Integer yin,@RequestParam(value = "receiveid")Integer receiveid,HttpSession session,jiangjun jjn,HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        String rolename = r.get(0).getJuesename();
        Integer roleid = r.get(0).getId();
        RoleFujiang ff=rfm.selectByPrimaryKey(jjn.getId());
        ff.setZhen("邮件中");
        rfm.updateByPrimaryKeySelective(ff);

        if(yjid==null){
            YouJian yj=new YouJian();
            Role re = rm.selectByPrimaryKey(receiveid);
            yj.setData(new Date());
            yj.setRoleid(roleid);
            yj.setReceiveid(receiveid);
            yj.setStatus("0");
            yj.setRolename(rolename);
            yj.setReceivedname(re.getJuesename());
            yjm.insertSelective(yj);
            yjid=yj.getId();
        }


        YouJianWuPin yp=new YouJianWuPin();
        yp.setNum(jjn.getNum());
        yp.setStatus("0");
        yp.setDate(new Date());
        yp.setRoleid(roleid);
        yp.setWupinid(jjn.getId());
        yp.setWupinnname(jjn.getJjname());
        yp.setRolename(rolename);
        Role rc=rm.selectByPrimaryKey(receiveid);
        yp.setReceiveid(receiveid);
        yp.setReceivedname(rc.getJuesename());
        yp.setYin(yin);
        //yp.setTypeid();
        yp.setYoujianid(yjid);
        yjwpm.insert(yp);
        Integer ypid=yp.getId();
        CommonUtilAjax.sendAjaxList("msg",yp.getId(),request,response);



        return null;

    }
    @RequestMapping(value = "/deletewp", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String deletewp(@RequestParam(value = "id")Integer id,HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception {
        YouJianWuPin yj= yjwpm.selectByPrimaryKey(id);
        yj.setStatus("删除");
        Integer result=yjwpm.updateByPrimaryKeySelective(yj);
        if(result>0){
            Integer jjid=yj.getWupinid();
            Integer num=yj.getNum();
            jiangjun jj=jjm.selectByPrimaryKey(jjid);
            jj.setNum(jj.getNum()+num);

            jjm.updateByPrimaryKeySelective(jj);

        }
        CommonUtilAjax.sendAjaxList("msg","sss",request,response);
        return null;

    }
    @RequestMapping(value = "/deletefj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String deletefj(@RequestParam(value = "id")Integer id,HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception {
        YouJianWuPin yj= yjwpm.selectByPrimaryKey(id);
        yj.setStatus("删除");
        Integer result=yjwpm.updateByPrimaryKeySelective(yj);
        if(result>0){
            RoleFujiang fj=rfm.selectByPrimaryKey(yj.getWupinid());
            if(fj!=null){
                fj.setZhen("3");
                rfm.updateByPrimaryKeySelective(fj);
            }

        }
        CommonUtilAjax.sendAjaxList("msg","sss",request,response);
        return null;

    }





}
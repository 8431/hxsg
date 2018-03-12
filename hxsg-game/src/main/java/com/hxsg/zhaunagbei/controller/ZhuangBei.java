package com.hxsg.zhaunagbei.controller;

import com.alibaba.fastjson.JSONObject;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.Msg;
import com.hxsg.CommonUtil.controller.*;
import com.hxsg.CommonUtil.controller.RoleMoney;
import com.hxsg.Dao.*;
import com.hxsg.po.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/zbxq")
public class ZhuangBei {
    @Autowired
    RoleMapper rm;

    @Autowired
    jiangjunMapper jjm;
    @Autowired
    ZaWuMiaoShuMapper zwmsm;

    @Autowired
    WuQiDetailMapper wqdm;
    @Autowired
    RoleZbDetailMapper rzdm;
    @Autowired
    wuqiMapper wqm;


    private Logger logger = LoggerFactory.getLogger(getClass());
    //人物装备
    @RequestMapping(value = "/rwzb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String rwzb(@RequestParam(required = false,value = "id")Integer id,HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        Integer roleid = r.get(0).getId();
        if(id>0) {
            jiangjun jj = jjm.selectByPrimaryKey(id);
            jj.setStatus("1");
            jjm.updateByPrimaryKeySelective(jj);
            wuqi w = new wuqi();
            w.setName(jj.getJjname());
            List<wuqi> wqli = wqm.selectByAll(w);

            if (wqli.get(0).getShuxing().indexOf("武") > 0) {
                RoleZbDetail rzd = new RoleZbDetail();
                rzd.setRoleid(roleid);
                List<RoleZbDetail> li = rzdm.selectAll(rzd);
                if (li.size() > 0) {
                    if (li.get(0).getWuqi() != null) {
                        int wuqi = li.get(0).getWuqi();
                        jiangjun jjn = jjm.selectByPrimaryKey(wuqi);
                        if(jjn!=null) {
                            jjn.setStatus("0");
                            jjm.updateByPrimaryKeySelective(jjn);
                        }
                            li.get(0).setWuqi(id);
                            rzdm.updateByPrimaryKeySelective(li.get(0));

                    } else {
                        li.get(0).setWuqi(id);
                        rzdm.updateByPrimaryKeySelective(li.get(0));


                    }

                } else {
                    RoleZbDetail rzdl = new RoleZbDetail();
                    rzdl.setRoleid(roleid);
                    rzdl.setWuqi(id);
                    rzdm.insertSelective(rzdl);

                }
            }
            String xls=wqli.get(0).getShuxing();
            if (xls.indexOf("链")> 0) {

                RoleZbDetail rzd = new RoleZbDetail();
                rzd.setRoleid(roleid);
                List<RoleZbDetail> li = rzdm.selectAll(rzd);
                if (li.size() > 0) {
                    if (li.get(0).getXl() != null) {
                        int xl = li.get(0).getXl();
                        jiangjun jjn = jjm.selectByPrimaryKey(xl);
                        if(jjn!=null){
                            jjn.setStatus("0");
                            jjm.updateByPrimaryKeySelective(jjn);
                        }
                            li.get(0).setXl(id);
                            rzdm.updateByPrimaryKeySelective(li.get(0));


                    } else {
                        li.get(0).setXl(id);
                        rzdm.updateByPrimaryKeySelective(li.get(0));

                    }
                } else {
                    RoleZbDetail rzdl = new RoleZbDetail();
                    rzdl.setRoleid(roleid);
                    rzdl.setXl(id);
                    rzdm.insertSelective(rzdl);

                }
            }
            if (wqli.get(0).getShuxing().indexOf("腕") > 0) {
                RoleZbDetail rzd = new RoleZbDetail();
                rzd.setRoleid(roleid);
                List<RoleZbDetail> li = rzdm.selectAll(rzd);
                if (li.size() > 0) {
                    if (li.get(0).getHuwan() != null) {
                        int hw = li.get(0).getHuwan();
                        jiangjun jjn = jjm.selectByPrimaryKey(hw);
                        if(jjn!=null) {
                            jjn.setStatus("0");
                            jjm.updateByPrimaryKeySelective(jjn);
                        }
                            li.get(0).setHuwan(id);
                            rzdm.updateByPrimaryKeySelective(li.get(0));


                    } else {
                        li.get(0).setHuwan(id);
                        rzdm.updateByPrimaryKeySelective(li.get(0));
                    }
                } else {
                    RoleZbDetail rzdl = new RoleZbDetail();
                    rzdl.setRoleid(roleid);
                    rzdl.setHuwan(id);
                    rzdm.insertSelective(rzdl);
                }
            }
            if (wqli.get(0).getShuxing().indexOf("靴") > 0) {
                RoleZbDetail rzd = new RoleZbDetail();
                rzd.setRoleid(roleid);
                List<RoleZbDetail> li = rzdm.selectAll(rzd);
                if (li.size() > 0) {
                    if (li.get(0).getXiezi()!= null) {
                    int zx = li.get(0).getXiezi();
                    jiangjun jjn = jjm.selectByPrimaryKey(zx);
                        if(jjn!=null) {
                            jjn.setStatus("0");
                            jjm.updateByPrimaryKeySelective(jjn);
                        }
                            li.get(0).setXiezi(id);
                            rzdm.updateByPrimaryKeySelective(li.get(0));



                } else {
                        li.get(0).setXiezi(id);
                        rzdm.updateByPrimaryKeySelective(li.get(0));

                    }
            }else{
                    RoleZbDetail rzdl = new RoleZbDetail();
                    rzdl.setRoleid(roleid);
                    rzdl.setXiezi(id);
                    rzdm.insertSelective(rzdl);

                }
        }
            if(wqli.get(0).getShuxing().indexOf("甲")>0) {
                RoleZbDetail rzd = new RoleZbDetail();
                rzd.setRoleid(roleid);
                List<RoleZbDetail> li = rzdm.selectAll(rzd);
                if (li.size() > 0) {
                    if (li.get(0).getYifu() != null) {
                        int kj = li.get(0).getYifu();
                        jiangjun jjn = jjm.selectByPrimaryKey(kj);
                        if(jjn!=null) {
                            jjn.setStatus("0");
                            jjm.updateByPrimaryKeySelective(jjn);
                        }
                            li.get(0).setYifu(id);
                            rzdm.updateByPrimaryKeySelective(li.get(0));



                    } else {
                        li.get(0).setYifu(id);
                        rzdm.updateByPrimaryKeySelective(li.get(0));


                    }
                }else{
                    RoleZbDetail rzdl = new RoleZbDetail();
                    rzdl.setRoleid(roleid);
                    rzdl.setYifu(id);
                    rzdm.insertSelective(rzdl);

                }
            }
            if(wqli.get(0).getShuxing().indexOf("盔")>0) {
                RoleZbDetail rzd = new RoleZbDetail();
                rzd.setRoleid(roleid);
                List<RoleZbDetail> li = rzdm.selectAll(rzd);
                if (li.size() > 0) {
                    if (li.get(0).getToukui() != null) {
                        int tk = li.get(0).getToukui();
                        jiangjun jjn = jjm.selectByPrimaryKey(tk);
                        if(jjn!=null) {
                            jjn.setStatus("0");
                            jjm.updateByPrimaryKeySelective(jjn);
                        }
                            li.get(0).setToukui(id);
                            rzdm.updateByPrimaryKeySelective(li.get(0));


                    } else {
                        li.get(0).setToukui(id);
                        rzdm.updateByPrimaryKeySelective(li.get(0));

                    }
                }else{
                    RoleZbDetail rzdl = new RoleZbDetail();
                    rzdl.setRoleid(roleid);
                    rzdl.setToukui(id);
                    rzdm.insertSelective(rzdl);

                }
            }
            CommonUtilAjax.sendAjaxList("msg","装备成功",request,response);



        }


        return null;
    }
    //人物装备
    @RequestMapping(value = "/xzzb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String xzzb(@RequestParam(required = false,value = "id")Integer id,HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        Integer roleid = r.get(0).getId();
        if(id>0){
            jiangjun jj=jjm.selectByPrimaryKey(id);
            jj.setStatus("0");
            jjm.updateByPrimaryKeySelective(jj);
            WuQiDetail wd=new WuQiDetail();
            wd.setZbid(id);
            List<WuQiDetail> wqd=wqdm.selectAll(wd);
            wqd.get(0).setStatus("1");
            wqdm.updateByPrimaryKeySelective(wqd.get(0));
            CommonUtilAjax.sendAjaxList("msg","卸下装备",request,response);



        }


        return null;
    }

    //
    @RequestMapping(value = "/zbdetail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String zbdetail(HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
        Integer roleid = r.get(0).getId();
       RoleZbDetail rzd=new RoleZbDetail();
        rzd.setRoleid(roleid);
       List<RoleZbDetail> li=rzdm.selectAll(rzd);
        if(li.size()>0){
        Integer wq=null;
        Integer xl=null;
        Integer kj=null;
        Integer tk=null;
        Integer zx=null;
        Integer hw=null;
        String wqimg="";
        String hwimg="";
        String tkimg="";
        String xlimg="";
        String zximg="";
        String kjimg="";

        if(li.get(0).getWuqi()!=null){
            wq=li.get(0).getWuqi();
            jiangjun jj=jjm.selectByPrimaryKey(wq);
            wuqi w=new  wuqi();
            w.setName(jj.getJjname());
            List<wuqi> lis=wqm.selectByAll(w);
            wqimg=lis.get(0).getImg();

        }
        if(li.get(0).getHuwan()!=null){
            hw=li.get(0).getHuwan();
            jiangjun jj=jjm.selectByPrimaryKey(hw);
            wuqi w=new  wuqi();
            w.setName(jj.getJjname());
            List<wuqi> lis=wqm.selectByAll(w);
            hwimg=lis.get(0).getImg();
        }
        if(li.get(0).getToukui()!=null){
            tk=li.get(0).getToukui();
            jiangjun jj=jjm.selectByPrimaryKey(tk);
            wuqi w=new  wuqi();
            w.setName(jj.getJjname());
            List<wuqi> lis=wqm.selectByAll(w);
            tkimg=lis.get(0).getImg();
        }
        if(li.get(0).getXiezi()!=null){
            zx=li.get(0).getXiezi();
            jiangjun jj=jjm.selectByPrimaryKey(zx);
            wuqi w=new  wuqi();
            w.setName(jj.getJjname());
            List<wuqi> lis=wqm.selectByAll(w);
            zximg=lis.get(0).getImg();
        }
        if(li.get(0).getYifu()!=null){
            kj=li.get(0).getYifu();
            jiangjun jj=jjm.selectByPrimaryKey(kj);
            wuqi w=new  wuqi();
            w.setName(jj.getJjname());
            List<wuqi> lis=wqm.selectByAll(w);
            kjimg=lis.get(0).getImg();
        }

        Role re=rm.selectByPrimaryKey(roleid);

        Object[][] bt=new Object[][]{{"zbdetail",li.get(0)},{"roledetail",re},{"kjimg",kjimg},{"tkimg",tkimg},{"hwimg",hwimg},{"zximg",zximg},{"xlimg",xlimg},{"wqimg",wqimg}};


        CommonUtilAjax.sendAjaxArray(bt,request,response);
        }

        return null;
    }
    //升星操作
    /*
    * 升星石----升星 122334/111223/112233
    * */
    @RequestMapping(value = "/zbsx", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String zbxq(@RequestParam(required = false,value = "sxid")Integer sxid,@RequestParam(required = false,value = "id")Integer id,HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        Integer roleid = r.get(0).getId();
        jiangjun jj=jjm.selectByPrimaryKey(sxid);
        Integer roleid2=jj.getRoleid();
        if(roleid.equals(roleid2)&&jj.getJjname().equals("升星石")) {
            if (id > 0) {
                if(jj.getNum()>0){
                    jj.setNum(jj.getNum()-1);
                    jjm.updateByPrimaryKeySelective(jj);//消耗升星石
                    WuQiDetail wqd = new WuQiDetail();
                    wqd.setZbid(id);
                    WuQiDetail wl = wqdm.selectAll(wqd).get(0);
                    WuQiDetail wadl = wqdm.selectAll(wqd).get(0);
                    Integer levels = Integer.parseInt(wadl.getLevel());
                    switch (levels){
                        case 1:{
                            SxLevel1 lv1= (SxLevel1) SpringContextUtil.getBean("SxLevel1");
                            lv1.level1(id,request,response);
                        } break;
                        case 2:{
                            SxLevel2 lv2= (SxLevel2) SpringContextUtil.getBean("SxLevel2");
                            lv2.level1(id,request,response);
                        } break;
                        case 3:{
                            SxLevel3 lv3= (SxLevel3) SpringContextUtil.getBean("SxLevel3");
                            lv3.level1(id,request,response);
                        } break;
                        case 4:{
                            SxLevel4 lv4= (SxLevel4) SpringContextUtil.getBean("SxLevel4");
                            lv4.level1(id,request,response);
                        } break;
                    }
                    }

                    }





            }






        return null;
    }
    @RequestMapping(value = "/wuqidetail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String wuqidetail(@RequestParam(required = false,value = "id")Integer id,HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
        List<Role> r = (List<Role>) session.getAttribute("rolelist");
        Integer roleid = r.get(0).getId();
       WuQiDetail wl=new WuQiDetail();
        wl.setZbid(id);
        List<WuQiDetail> li=wqdm.selectAll(wl);
        String name=jjm.selectByPrimaryKey(id).getJjname();
        if(li.size()>0){
            Object[][] obj=new Object[][]{{"name",name},{"wuqidetail",li.get(0)}};
            CommonUtilAjax.sendAjaxArray(obj,request,response);
        }



        return null;
    }
    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String test(@RequestParam(required = false,value = "roleid")Integer roleid,@RequestParam(required = false,value = "yin")Integer yin,@RequestParam(required = false,value = "jin")Integer jin,HttpSession session, HttpServletResponse response,HttpServletRequest request)  throws Exception {
        RoleMoney a=(RoleMoney)SpringContextUtil.getBean("RoleMoney");
        a.getMoney(roleid,yin,jin);
        return null;
    }



}

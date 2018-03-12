package com.hxsg.wupin.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.Dao.*;
import com.hxsg.po.*;
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
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/jjunl")
public class JjunController {
    @Autowired
    jiangjunMapper jjm;
    @Autowired
    RoleFujiangMapper rfm;
    @Autowired
    FuJiangMapper fjm;
    @Autowired
    RoleFriendsMsgMapper rfmm;
    @Autowired
    ZaWuMiaoShuMapper zwmsm;




    @RequestMapping(value = "/syjjl", method = {RequestMethod.GET, RequestMethod.POST})

    public synchronized String syjjl(@RequestParam(value = "name", required = false) String name,@RequestParam(value = "id", required = false) Integer id, Model model, HttpSession session) throws Exception {
        String result=null;
        jiangjun jj = jjm.selectByPrimaryKey(id);
        if(jj.getType().equals("书")){
            int level=0;
            int level2=0;
            if(jj.getJjname().equals("初级副将心法")){
                level=11;
                level2=40;
            }
            if(jj.getJjname().equals("副将经验书")){
                level=1;
                level2=10;
            }
            if(jj.getJjname().equals("高级副将心法")){
                level=41;
                level2=70;
            }
            if(jj.getJjname().equals("特级副将心法")){
                level=71;
                level2=100;
            }
            if(jj.getJjname()=="顶级副将心法"){
                level=101;
                level2=140;
            }
            List<Role> r = (List<Role>) session.getAttribute("rolelist");//通过登录后的Session获取角色ID
            int roleid = r.get(0).getId();
            List<RoleFujiang> li = rfm.getByRoleIdShuxLevel(roleid,1,level,level2);

            System.out.println(li.size());
            model.addAttribute("lis", li);
            if(li.size()<=0){
                model.addAttribute("size",0);
            }

            model.addAttribute("name",jj.getJjname());
            model.addAttribute("jjid",id);
            result="副将/使用经验书";
        }

        if(jj.getType().equals("令")){
            int num = jj.getNum() - 1;
            jj.setNum(num);
            int a = jjm.updateByPrimaryKeySelective(jj);
            if (a > 0) {
                int rand = (int) (Math.random() * 1000);
                int fjid =0;
                System.out.println(fjid);


                System.out.println(rand);
                Integer gailv=0;
                if(name.equals("将军令")){
                    gailv=800;
                    fjid=(int) (Math.random() * 7 + 1);
                }
                if(name.equals("大将军令")){
                    gailv=900;
                    fjid=(int) (Math.random() * 2 + 8);
                }
                if(name.equals("猛将军令")){
                    gailv=950;
                    fjid=(int) (Math.random() * 2 + 10);
                }
                if(name.equals("元帅将军令")){
                    gailv=995;
                    fjid=(int) (Math.random() * 1 + 13);
                }
                if (rand > gailv) {//百分之40的概率产生

                    FuJiang fg= fjm.selectByPrimaryKey(fjid);
                    List<Role> r = (List<Role>) session.getAttribute("rolelist");//通过登录后的Session获取角色ID
                    int roleid = r.get(0).getId();
                    RoleFujiang rf = new RoleFujiang();
                    float cz =((float)(Math.floor(Math.random() * 21 - 20.0)) / 100)+ fg.getChengzhang();


                    rf.setChengzhang(cz);
                    rf.setChufang(fg.getChufang());
                    rf.setChugong(fg.getChugong());
                    int sd=((int) Math.round(Math.random() * 21 - 20));
                    rf.setChusu(fg.getChusu()+sd);//随机+-20设置速度属性
                    int tsudu=(int)(cz*(fg.getChusu()+sd+1));
                    rf.setTotalsudu(tsudu);
                    rf.setZhuansheng(1);
                    rf.setZhen("0");
                    int qx=((int) Math.round(Math.random() * 21 - 20));
                    rf.setChuxue(fg.getChuxue()+qx );
                    rf.setTotalxue2((int)(cz*(1*1)+cz*(fg.getChuxue()+qx)*1*0.8));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
                    rf.setTotalxue1((int)(cz*(1*1)+cz*(fg.getChuxue()+qx)*1*0.8));
                    int jl=((int) Math.round(Math.random() * 21 - 20));
                    rf.setChujing(fg.getChujing() +jl );
                    rf.setTotaljing1((int)(cz*(1*1)+cz*(fg.getChujing() +jl)*1*0.8 ));
                    rf.setTotaljing2((int)(cz*(1*1)+cz*(fg.getChujing() +jl)*1*0.8 ));
                    int gj=((int) Math.round(Math.random() * 21 - 20));
                    rf.setChugong(fg.getChugong()+gj);
                    rf.setTotalgong((int)((1*cz*(fg.getChugong()+gj))/7+(fg.getChugong()+gj)*cz*0.5+1*cz*0.2*1));

                    rf.setFuid(fg.getId());
                    rf.setFujiangname(fg.getFujiangname());
                    rf.setRoleid(roleid);
                    rf.setLeve(1);
                    rf.setSjjinyan(100);
                    rf.setSumds(5);
                    rf.setKeyongds(0);
                    rf.setQixueds(1);
                    rf.setJinglids(1);
                    rf.setGongjids(1);
                    rf.setSududs(1);




                    rf.setSex(fg.getSex());
                    rf.setStatus("战斗");
                    rf.setTouxian(fg.getTouxian());
                    rf.setTouxiang(fg.getTouxiang());
                    List<RoleFujiang> rolefu = rfm.getByRoleIdShux(roleid, 1);

                    if (rolefu.size() > 10) {
                        rf.setShuxing(0);
                        RoleFriendsMsg rg = new RoleFriendsMsg();
                        rg.setRoleid(roleid);
                        rg.setFriendid(1000);
                        rg.setMessage("随行副将大于10个，招募的副将已放入驿馆");
                        rg.setData(new Date());
                        rg.setType("通知");
                        int b = rfmm.insertSelective(rg);


                    } else {
                        rf.setShuxing(1);
                    }


                    int aa = rfm.insert(rf);

                    model.addAttribute("fjname", rf);
                } else {
                    model.addAttribute("fjfail","很遗憾您没有招到副将！");
                }


            }
            result="副将/招募副将成功";
        }

        return result;
    }
    @RequestMapping(value = "/appsyjjl", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public synchronized String appsyjjl(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "name", required = false) String name,@RequestParam(value = "id", required = false) Integer id, Model model, HttpSession session) throws Exception {
        Integer r = (Integer) session.getAttribute("roleid");//通过登录后的Session获取角色ID
        int roleid = r;
        if(roleid>0){
            jiangjun jj = jjm.selectByPrimaryKey(id);
            //类型为书的使用方法
            if(jj.getType().equals("书")){
                int level=0;
                int level2=0;
                if(jj.getJjname().equals("初级副将心法")){
                    level=11;
                    level2=40;
                }
                if(jj.getJjname().equals("副将经验书")){
                    level=1;
                    level2=10;
                }
                if(jj.getJjname().equals("高级副将心法")){
                    level=41;
                    level2=70;
                }
                if(jj.getJjname().equals("特级副将心法")){
                    level=71;
                    level2=100;
                }
                if(jj.getJjname()=="顶级副将心法"){
                    level=101;
                    level2=140;
                }

                List li = rfm.getByRoleIdShuxLevel(roleid,1,level,level2);

                // System.out.println(li.size());

                //model.addAttribute("lis", li);
                if(li.size()<=0){
                    CommonUtilAjax.sendAjaxList("li",0,request,response );
                }else{
                    int se=li.size();
                    li.add(se,jj.getJjname());
                    li.add(se+1,id);

                    CommonUtilAjax.sendAjaxList("li",li,request,response);



                }



                return null;
            }
            //类型为令的使用方法
            if(jj.getType().equals("令")){
                int num = jj.getNum() - 1;
                jj.setNum(num);
                int a = jjm.updateByPrimaryKeySelective(jj);
                if (a > 0) {
                    int rand = (int) (Math.random() * 1000);
                    int fjid =0;
                    System.out.println(fjid);


                    System.out.println(rand);
                    Integer gailv=0;
                    if(name.equals("将军令")){
                        gailv=800;
                        fjid=(int) (Math.random() * 7 + 1);
                    }
                    if(name.equals("大将军令")){
                        gailv=900;
                        fjid=(int) (Math.random() * 2 + 8);
                    }
                    if(name.equals("猛将军令")){
                        gailv=950;
                        fjid=(int) (Math.random() * 2 + 10);
                    }
                    if(name.equals("元帅将军令")){
                        gailv=995;
                        fjid=(int) (Math.random() * 1 + 13);
                    }
                    if (rand > gailv) {//百分之40的概率产生

                        FuJiang fg= fjm.selectByPrimaryKey(fjid);

                        RoleFujiang rf = new RoleFujiang();
                        float cz =((float)(Math.floor(Math.random() * 21 - 20.0)) / 100)+ fg.getChengzhang();


                        rf.setChengzhang(cz);
                        rf.setChufang(fg.getChufang());
                        rf.setChugong(fg.getChugong());
                        int sd=((int) Math.round(Math.random() * 21 - 20));
                        rf.setChusu(fg.getChusu()+sd);//随机+-20设置速度属性
                        int tsudu=(int)(cz*(fg.getChusu()+sd+1));
                        rf.setTotalsudu(tsudu);
                        rf.setZhuansheng(1);
                        rf.setZhen("0");
                        int qx=((int) Math.round(Math.random() * 21 - 20));
                        rf.setChuxue(fg.getChuxue()+qx );
                        rf.setTotalxue2((int)(cz*(1*1)+cz*(fg.getChuxue()+qx)*1*0.8));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
                        rf.setTotalxue1((int)(cz*(1*1)+cz*(fg.getChuxue()+qx)*1*0.8));
                        int jl=((int) Math.round(Math.random() * 21 - 20));
                        rf.setChujing(fg.getChujing() +jl );
                        rf.setTotaljing1((int)(cz*(1*1)+cz*(fg.getChujing() +jl)*1*0.8 ));
                        rf.setTotaljing2((int)(cz*(1*1)+cz*(fg.getChujing() +jl)*1*0.8 ));
                        int gj=((int) Math.round(Math.random() * 21 - 20));
                        rf.setChugong(fg.getChugong()+gj);
                        rf.setTotalgong((int)((1*cz*(fg.getChugong()+gj))/7+(fg.getChugong()+gj)*cz*0.5+1*cz*0.2*1));

                        rf.setFuid(fg.getId());
                        rf.setFujiangname(fg.getFujiangname());
                        rf.setRoleid(roleid);
                        rf.setLeve(1);
                        rf.setSjjinyan(100);
                        rf.setSumds(5);
                        rf.setKeyongds(0);
                        rf.setQixueds(1);
                        rf.setJinglids(1);
                        rf.setGongjids(1);
                        rf.setSududs(1);




                        rf.setSex(fg.getSex());
                        rf.setStatus("战斗");
                        rf.setTouxian(fg.getTouxian());
                        rf.setTouxiang(fg.getTouxiang());
                        List<RoleFujiang> rolefu = rfm.getByRoleIdShux(roleid, 1);

                        if (rolefu.size() > 10) {
                            rf.setShuxing(0);
                            RoleFriendsMsg rg = new RoleFriendsMsg();
                            rg.setRoleid(roleid);
                            rg.setFriendid(1000);
                            rg.setMessage("随行副将大于10个，招募的副将已放入驿馆");
                            rg.setData(new Date());
                            rg.setType("通知");
                            int b = rfmm.insertSelective(rg);


                        } else {
                            rf.setShuxing(1);
                        }


                        int aa = rfm.insert(rf);

                        CommonUtilAjax.sendAjaxList("fjname", rf.getFujiangname(),request,response);
                    } else {
                        CommonUtilAjax.sendAjaxList("fjfail",1,request,response);

                    }


                }
                return null;
            }

        }
        return null;
    }


    @RequestMapping(value = "/wpxq", method = {RequestMethod.GET, RequestMethod.POST})

    public String wpxq(@RequestParam(value = "name", required = false) String name,Model model, HttpSession session) throws Exception {
        ZaWuMiaoShu zs= zwmsm.selectByName(name);
        if(zs!=null){
            model.addAttribute("zs",zs);
        }


        return "物品/物品详情";
    }
    @RequestMapping(value = "/appwpxq", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String appwpxq(@RequestParam(value = "name", required = false) String name,HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception {
        ZaWuMiaoShu zs= zwmsm.selectByName(name);
        if(zs!=null){
            CommonUtilAjax.sendAjaxList("zs",zs,request,response);
        }


        return null;
    }


}

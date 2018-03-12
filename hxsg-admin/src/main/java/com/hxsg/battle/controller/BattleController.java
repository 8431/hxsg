//package com.hxsg.battle.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hxsg.CommonUtil.CommonUtilAjax;
//import com.hxsg.CommonUtil.Msg;
//import com.hxsg.CommonUtil.RequestParamFormat;
//import com.hxsg.Dao.*;
//import com.hxsg.po.*;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.jws.WebParam;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
//* Created by dlf on 2015/12/31.
//*/
//@Controller
//@RequestMapping("/zhanchang")
//
//public class BattleController {
//    @Autowired
//    RoleMapper rm;
//    @Autowired
//    RoleFriendsMsgMapper rfmm;
//    @Autowired
//    jiangjunMapper jjm;
//    @Autowired
//    ZaWuMiaoShuMapper zwmsm;
//    @Autowired
//    YouJianWuPinMapper yjwpm;
//    @Autowired
//    WaBaoMapper wbm;
//    @Autowired
//    WaBaoMiaoShuMapper wbmsm;
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    @RequestMapping(value = "/yzwb", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String yzwb(HttpSession session, HttpServletResponse response,Model model) throws Exception {
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid = r.get(0).getId();
//        String rolename = r.get(0).getJuesename();
//        WaBao wbo=new WaBao();
//        wbo.setRoleid(roleid);
//        List<WaBao>li = wbm.selectAll(wbo);
//        if(li.size()>0){
//            WaBao wb=li.get(0);
//            if (wb != null && wb.getStatus().equals("0")) {
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out = response.getWriter();
//                JSONObject obj = new JSONObject();//使用json
//                obj.put("msg", "你的铁镐已磨损，确定花费100000银两购买铁镐吗？");
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//            }
//        }
//
//        if(li.size()<=0){
//            response.setCharacterEncoding("utf-8");
//            PrintWriter out = response.getWriter();
//            JSONObject obj = new JSONObject();//使用json
//            obj.put("msg", "小伙子第一次来矿洞吧，留下100000银买路财放你过去。");
//            out.write(obj.toString());
//            out.flush();
//            out.close();
//
//        }
//
//        return null;
//    }
//    @RequestMapping(value = "/appyzwb", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String appyzwb(HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid = r.get(0).getId();
//        String rolename = r.get(0).getJuesename();
//        if(r.size()>0){
//            WaBao wbo=new WaBao();
//            wbo.setRoleid(roleid);
//            List<WaBao>li = wbm.selectAll(wbo);
//            if(li.size()>0){
//                WaBao wb=li.get(0);
//                if (wb != null && wb.getStatus().equals("0")) {
//                    CommonUtilAjax.sendAjaxList("msg", "你的铁镐已磨损，确定花费100000银两购买铁镐吗？",request,response);
//                }
//            }
//            if(li.size()<=0){
//                CommonUtilAjax.sendAjaxList("msg", "小伙子第一次来矿洞吧，留下100000银买路财放你过去。",request,response);
//            }
//        }
//
//
//        return null;
//    }
//    @RequestMapping(value = "/wdbw", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String wdbw(HttpSession session, HttpServletResponse response,Model model) throws Exception {
//
//        List<WaBaoMiaoShu> li=wbmsm.getWbByTime();
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out=response.getWriter();
//        JSONObject obj=new JSONObject();//使用json
//        if(li.size()>0&&li!=null){
//            obj.put("code", 0);
//            obj.put("rmsg",li);
//
//        }else{
//            obj.put("code", 1);
//        }
//        System.out.println(obj.toString());
//
//        out.write(obj.toString());
//        out.flush();
//        out.close();
//        return null;
//    }
//    @RequestMapping(value = "/appwdbw", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String appwdbw(HttpSession session, HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
//
//        List<WaBaoMiaoShu> li=wbmsm.getWbByTime();
//
//        if(li.size()>0&&li!=null){
//            CommonUtilAjax.sendAjaxList("rmsg",li,request, response);
//
//        }else{
//            CommonUtilAjax.sendAjaxList("code",1,request, response);
//        }
//
//        return null;
//    }
//    @RequestMapping(value = "/fee", method = {RequestMethod.GET, RequestMethod.POST})
//
//        public String fee(HttpSession session, HttpServletResponse response,Model model) throws Exception {
//
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid = r.get(0).getId();
//        String rolename = r.get(0).getJuesename();
//
//        WaBao wbo=new WaBao();
//        wbo.setRoleid(roleid);
//        List<WaBao>li = wbm.selectAll(wbo);
//        if(li.size()>0){
//            WaBao wb=li.get(0);
//            model.addAttribute("wb",wb);
//        }
//
//
////        Integer result = SetRoleMoney.setmoney(roleid, fee);
////        if (result == 1) {
////            wb.setStatus("1");
////            wb.setNum(50);
////            model.addAttribute("wb",wb);
////            response.setCharacterEncoding("utf-8");
////            PrintWriter out = response.getWriter();
////            JSONObject obj = new JSONObject();//使用json
////            obj.put("msg", "消费成功，快点进去吧！");
////            out.write(obj.toString());
////            out.flush();
////            out.close();
////
////        } else {
////            response.setCharacterEncoding("utf-8");
////            PrintWriter out = response.getWriter();
////            JSONObject obj = new JSONObject();//使用json
////            obj.put("msg", "钱不够，还想进去？快走快走！");
////            out.write(obj.toString());
////            out.flush();
////            out.close();
////
////        }
//        return "战场/挖宝";
//    }
//    //app fee刷新挖宝主页
//    @RequestMapping(value = "/appfee", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String appfee(HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
//
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid = r.get(0).getId();
//        String rolename = r.get(0).getJuesename();
//        if(r.size()>0){
//            WaBao wbo=new WaBao();
//            wbo.setRoleid(roleid);
//            List<WaBao>li = wbm.selectAll(wbo);
//            if(li.size()>0){
//                WaBao wb=li.get(0);
//                CommonUtilAjax.sendAjaxList("wb",wb,request, response);
//            }
//        }
//        return null;
//    }
//    @RequestMapping(value = "/wabao", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public synchronized String wabao(HttpSession session, HttpServletResponse response,Model model) throws Exception {
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid = r.get(0).getId();
//        String rolename = r.get(0).getJuesename();
//        String rt = null;
//        Integer result =null;
//        int fee = 100000;
//        WaBao wbo=new WaBao();
//        wbo.setRoleid(roleid);
//      List<WaBao>li = wbm.selectAll(wbo);
//        if(li.size()>0){
//            WaBao wb=li.get(0);
//
//            if (wb != null && wb.getStatus().equals("0")) {
//                Role re = rm.selectByPrimaryKey(roleid);
//                int roleyin = re.getYin();
//                if (roleyin >= fee) {
//                    //扣费
//                    re.setYin(roleyin - fee);
//                    rm.updateByPrimaryKeySelective(re);
//                    result = 1;
//                } else {
//                    result = 0;
//                }
//
//                if (result == 1) {
//
//                    wb.setW1(1);
//                    wb.setW2(1);
//                    wb.setW3(1);
//                    wb.setW4(1);
//                    wb.setW5(1);
//                    wb.setW6(1);
//                    wb.setW7(1);
//                    wb.setW8(1);
//                    wb.setW9(1);
//                    wb.setNum(50);
//                    wb.setStatus("1");
//                    wbm.updateByPrimaryKeySelective(wb);
//                    response.setCharacterEncoding("utf-8");
//                    PrintWriter out = response.getWriter();
//                    JSONObject obj = new JSONObject();//使用json
//                    obj.put("msg", "宝藏守卫：拿着铁镐去吧。祝你好运！");
//                    out.write(obj.toString());
//                    out.flush();
//                    out.close();
//                } else {
//                    response.setCharacterEncoding("utf-8");
//                    PrintWriter out = response.getWriter();
//                    JSONObject obj = new JSONObject();//使用json
//                    obj.put("msg", "宝藏守卫：钱不够，还想进去？快走快走！");
//                    out.write(obj.toString());
//                    out.flush();
//                    out.close();
//                }
//            }
//
//        }
//
//
//        if (li.size()<=0) {
//            Role re = rm.selectByPrimaryKey(roleid);
//            int roleyin = re.getYin();
//            if (roleyin >= fee) {
//                //扣费
//                re.setYin(roleyin - fee);
//                rm.updateByPrimaryKeySelective(re);
//                result = 1;
//            } else {
//                result = 0;
//            }
//            if (result == 1) {
//                WaBao wo = new WaBao();
//                wo.setRoleid(roleid);
//                wo.setRolename(rolename);
//                wo.setW1(1);
//                wo.setW2(1);
//                wo.setW3(1);
//                wo.setW4(1);
//                wo.setW5(1);
//                wo.setW6(1);
//                wo.setW7(1);
//                wo.setW8(1);
//                wo.setW9(1);
//                wo.setNum(50);
//                wo.setStatus("1");
//                wo.setDate(new Date());
//                wo.setType("0");
//                wbm.insertSelective(wo);
//            } else {
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out = response.getWriter();
//                JSONObject obj = new JSONObject();//使用json
//                obj.put("msg", "你所携带的银两不足！无法贿赂宝藏守卫");
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//            }
//        }
//
//            return null;
//
//        }
//    @RequestMapping(value = "/appwabao", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public synchronized String appwabao(HttpServletRequest request,HttpSession session, HttpServletResponse response,Model model) throws Exception {
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid = r.get(0).getId();
//        String rolename = r.get(0).getJuesename();
//        if(r.size()>0){
//            String rt = null;
//            Integer result =null;
//            int fee = 100000;
//            WaBao wbo=new WaBao();
//            wbo.setRoleid(roleid);
//            List<WaBao>li = wbm.selectAll(wbo);
//            if(li.size()>0){
//                WaBao wb=li.get(0);
//
//                if (wb != null && wb.getStatus().equals("0")) {
//                    Role re = rm.selectByPrimaryKey(roleid);
//                    int roleyin = re.getYin();
//                    if (roleyin >= fee) {
//                        //扣费
//                        re.setYin(roleyin - fee);
//                        rm.updateByPrimaryKeySelective(re);
//                        result = 1;
//                    } else {
//                        result = 0;
//                    }
//
//                    if (result == 1) {
//
//                        wb.setW1(1);
//                        wb.setW2(1);
//                        wb.setW3(1);
//                        wb.setW4(1);
//                        wb.setW5(1);
//                        wb.setW6(1);
//                        wb.setW7(1);
//                        wb.setW8(1);
//                        wb.setW9(1);
//                        wb.setNum(50);
//                        wb.setStatus("1");
//                        wbm.updateByPrimaryKeySelective(wb);
//                        CommonUtilAjax.sendAjaxList("msg", "宝藏守卫：拿着铁镐去吧。祝你好运！",request,response);
//
//                    } else {
//                        CommonUtilAjax.sendAjaxList("msg", "宝藏守卫：钱不够，还想进去？快走快走！",request,response);
//
//
//                    }
//                }
//
//            }
//
//
//            if (li.size()<=0) {
//                Role re = rm.selectByPrimaryKey(roleid);
//                int roleyin = re.getYin();
//                if (roleyin >= fee) {
//                    //扣费
//                    re.setYin(roleyin - fee);
//                    rm.updateByPrimaryKeySelective(re);
//                    result = 1;
//                } else {
//                    result = 0;
//                }
//                if (result == 1) {
//                    WaBao wo = new WaBao();
//                    wo.setRoleid(roleid);
//                    wo.setRolename(rolename);
//                    wo.setW1(1);
//                    wo.setW2(1);
//                    wo.setW3(1);
//                    wo.setW4(1);
//                    wo.setW5(1);
//                    wo.setW6(1);
//                    wo.setW7(1);
//                    wo.setW8(1);
//                    wo.setW9(1);
//                    wo.setNum(50);
//                    wo.setStatus("1");
//                    wo.setDate(new Date());
//                    wo.setType("0");
//                    wbm.insertSelective(wo);
//                } else {
//                    CommonUtilAjax.sendAjaxList("msg", "你所携带的银两不足！无法贿赂宝藏守卫",request,response);
//
//
//                }
//            }
//        }
//
//
//        return null;
//
//    }
//    @RequestMapping(value="/xhcs", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public  String xhcs(@RequestParam(value = "type",required = false)String type,WaBao wb,HttpSession session, HttpServletResponse response,Model model) throws Exception {
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid=r.get(0).getId();
//        String rolename=r.get(0).getJuesename();
//       int gl=0;
//        String w=type;
//        WaBao wbo=wbm.selectByPrimaryKey(wb.getId());
//
//
//        gl=(int)((Math.random()*100)+1);
//
//        if(wb.getW1()!=null){
//            wbo.setW1(wb.getW1());
//
//        }
//        if(wb.getW2()!=null){
//            wbo.setW2(wb.getW2());
//
//        }
//        if(wb.getW3()!=null){
//            wbo.setW3(wb.getW3());
//
//        }
//        if(wb.getW4()!=null){
//            wbo.setW4(wb.getW4());
//
//        }
//        if(wb.getW5()!=null){
//            wbo.setW5(wb.getW5());
//
//        }
//        if(wb.getW6()!=null){
//            wbo.setW6(wb.getW6());
//
//        }
//        if(wb.getW7()!=null){
//            wbo.setW7(wb.getW7());
//
//        }
//        if(wb.getW8()!=null){
//            wbo.setW8(wb.getW8());
//
//
//        }
//        if(wb.getW9()!=null){
//            wbo.setW9(wb.getW9());
//
//        }
//        wbm.updateByPrimaryKeySelective(wbo);
////刷新继续挖宝
//        WaBao sxwb=wbm.selectByPrimaryKey(wb.getId());
//        if(sxwb.getW1()==0&&sxwb.getW2()==0&&sxwb.getW3()==0&&sxwb.getW4()==0&&sxwb.getW5()==0&&sxwb.getW6()==0&&sxwb.getW7()==0&&sxwb.getW8()==0&&sxwb.getW9()==0){
//            response.setCharacterEncoding("utf-8");
//            PrintWriter out = response.getWriter();
//            JSONObject obj = new JSONObject();//使用json
//            obj.put("sxmsg", "sx");
//            out.write(obj.toString());
//            out.flush();
//            out.close();
//        }
//
//
//        if(gl!=0&&gl>80){
//            int wbgl=(int)((Math.random()*2));
//            int rand=0;
//            if(wbgl==0){
//                rand =(int)((Math.random()*17)+1);
//            }
//            if(wbgl==1){
//                rand =(int)((Math.random()*21)+25);
//
//            }
//
//
//            ZaWuMiaoShu zs=zwmsm.selectByPrimaryKey(rand);
//            jiangjun jj=new jiangjun();
//            jj.setJjname(zs.getName());
//            jj.setRoleid(roleid);
//            //jj.setType(zs.getType());
//            List<jiangjun> li=jjm.selectAll(jj);
//
//            if(li.size()>0){
//                Integer num=li.get(0).getNum();
//                if(num==null){
//                    num=0;
//                }
//                li.get(0).setNum(num+1);
//                jjm.updateByPrimaryKeySelective(li.get(0));
//            }else{
//                jj.setNum(1);
//                jj.setType(zs.getType());
//                jjm.insertSelective(jj);
//            }
//            WaBaoMiaoShu ws=new WaBaoMiaoShu();
//            ws.setRoleid(roleid);
//            ws.setRolename(rolename);
//            ws.setDate(new Date());
//            ws.setStatus("0");
//            ws.setBaoid(zs.getId());
//            ws.setBaoname(zs.getName());
//            wbmsm.insertSelective(ws);
//            response.setCharacterEncoding("utf-8");
//            PrintWriter out = response.getWriter();
//            JSONObject obj = new JSONObject();//使用json
//            obj.put("msg", "恭喜你挖到了"+zs.getName());
//            out.write(obj.toString());
//            out.flush();
//            out.close();
//        }else{
//            String a="很遗憾，你与珍宝擦肩而过！";
//            String b="加油，宝矿中心快挖到了";
//            String c="挖空了，运气真差！";
//            String d="挖空，不要放弃，宝藏就在身边";
//            String e="挖空，该死的，就快挖到了。";
//            response.setCharacterEncoding("utf-8");
//            PrintWriter out = response.getWriter();
//            JSONObject obj = new JSONObject();//使用json
//            int rand=(int)((Math.random()*5)+1);
//            if(rand==1){
//                obj.put("msg", a);
//            }
//            if(rand==2){
//                obj.put("msg", b);
//            }
//            if(rand==3){
//                obj.put("msg", c);
//            }
//            if(rand==4){
//                obj.put("msg", d);
//            }
//            if(rand==5){
//                obj.put("msg", e);
//            }
//
//            out.write(obj.toString());
//            out.flush();
//            out.close();
//        }
//
//
//
//        return null;
//        }
//    @RequestMapping(value="/appxhcs", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public  String appxhcs(@RequestParam(value = "type",required = false)String type,WaBao wb,HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception {
//        List<Role> r = (List<Role>) session.getAttribute("rolelist");//获取session查询角色
//        Integer roleid=r.get(0).getId();
//        String rolename=r.get(0).getJuesename();
//        if(r.size()>0){
//            int gl=0;
//            String w=type;
//            WaBao wbo=wbm.selectByPrimaryKey(wb.getId());
//
//
//            gl=(int)((Math.random()*100)+1);
//
//            if(wb.getW1()!=null){
//                wbo.setW1(wb.getW1());
//
//            }
//            if(wb.getW2()!=null){
//                wbo.setW2(wb.getW2());
//
//            }
//            if(wb.getW3()!=null){
//                wbo.setW3(wb.getW3());
//
//            }
//            if(wb.getW4()!=null){
//                wbo.setW4(wb.getW4());
//
//            }
//            if(wb.getW5()!=null){
//                wbo.setW5(wb.getW5());
//
//            }
//            if(wb.getW6()!=null){
//                wbo.setW6(wb.getW6());
//
//            }
//            if(wb.getW7()!=null){
//                wbo.setW7(wb.getW7());
//
//            }
//            if(wb.getW8()!=null){
//                wbo.setW8(wb.getW8());
//
//
//            }
//            if(wb.getW9()!=null){
//                wbo.setW9(wb.getW9());
//
//            }
//            wbm.updateByPrimaryKeySelective(wbo);
////刷新继续挖宝
//            WaBao sxwb=wbm.selectByPrimaryKey(wb.getId());
//            if(sxwb.getW1()==0&&sxwb.getW2()==0&&sxwb.getW3()==0&&sxwb.getW4()==0&&sxwb.getW5()==0&&sxwb.getW6()==0&&sxwb.getW7()==0&&sxwb.getW8()==0&&sxwb.getW9()==0){
//               CommonUtilAjax.sendAjaxList("msg", "sx",request,response);
//
//            }
//            if(gl!=0&&gl>80){
//                int wbgl=(int)((Math.random()*2));
//                int rand=0;
//                if(wbgl==0){
//                    rand =(int)((Math.random()*17)+1);
//                }
//                if(wbgl==1){
//                    rand =(int)((Math.random()*21)+25);
//
//                }
//
//
//                ZaWuMiaoShu zs=null;
//
//
//
//                    zs=zwmsm.selectByPrimaryKey(rand);
//
//
//                jiangjun jj=new jiangjun();
//                jj.setJjname(zs.getName());
//                jj.setRoleid(roleid);
//                //jj.setType(zs.getType());
//                List<jiangjun> li=jjm.selectAll(jj);
//                //带决绝
//                if(li.size()>0){
//                    Integer num=li.get(0).getNum();
//                    if(num==null){
//                        num=0;
//                    }
//                    li.get(0).setNum(num+1);
//                    jjm.updateByPrimaryKeySelective(li.get(0));
//                }else{
//                    jj.setNum(1);
//                    jj.setType(zs.getType());
//                    jjm.insertSelective(jj);
//                }
//                WaBaoMiaoShu ws=new WaBaoMiaoShu();
//                ws.setRoleid(roleid);
//                ws.setRolename(rolename);
//                ws.setDate(new Date());
//                ws.setStatus("0");
//                ws.setBaoid(zs.getId());
//                ws.setBaoname(zs.getName());
//                wbmsm.insertSelective(ws);
//                CommonUtilAjax.sendAjaxList("msg", "恭喜你挖到了"+zs.getName(),request,response);
//
//            }else{
//                String a="很遗憾，你与珍宝擦肩而过！";
//                String b="加油，宝矿中心快挖到了";
//                String c="挖空了，运气真差！";
//                String d="挖空，不要放弃，宝藏就在身边";
//                String e="挖空，该死的，就快挖到了。";
//
//                int rand=(int)((Math.random()*5)+1);
//                if(rand==1){
//                    CommonUtilAjax.sendAjaxList("msg", a,request,response);
//
//                }
//                if(rand==2){
//                    CommonUtilAjax.sendAjaxList("msg",b,request,response);
//
//                }
//                if(rand==3){
//                    CommonUtilAjax.sendAjaxList("msg", c,request,response);
//                }
//                if(rand==4){
//                    CommonUtilAjax.sendAjaxList("msg",d,request,response);
//                }
//                if(rand==5){
//                    CommonUtilAjax.sendAjaxList("msg", e,request,response);
//                }
//
//
//            }
//
//        }
//
//
//
//        return null;
//    }
//    @RequestMapping(value = "/sxwb", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public  String sxwb(@RequestParam(value = "id",required = false)Integer id,HttpSession session, HttpServletResponse response,Model model) throws Exception {
//        WaBao wb=wbm.selectByPrimaryKey(id);
//        if(wb.getNum()==0){
//            wb.setStatus("0");
//            wbm.updateByPrimaryKeySelective(wb);
//        }else{
//            wb.setNum(wb.getNum()-1);
//            wb.setW1(1);
//            wb.setW2(1);
//            wb.setW3(1);
//            wb.setW4(1);
//            wb.setW5(1);
//            wb.setW6(1);
//            wb.setW7(1);
//            wb.setW8(1);
//            wb.setW9(1);
//            wbm.updateByPrimaryKeySelective(wb);
//        }
//
//
//
//        return null;
//    }
//    //app版本刷新挖宝
//    @RequestMapping(value = "/appsxwb", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public  String appsxwb(@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpSession session, HttpServletResponse response,Model model) throws Exception {
//        WaBao wb=wbm.selectByPrimaryKey(id);
//
//        CommonUtilAjax.sendAjax("",request,response);//不设置返回json时，ajax succes之后无法执行函数
//        if(wb.getNum()==0){
//            wb.setStatus("0");
//            wbm.updateByPrimaryKeySelective(wb);
//        }else{
//            wb.setNum(wb.getNum()-1);
//            wb.setW1(1);
//            wb.setW2(1);
//            wb.setW3(1);
//            wb.setW4(1);
//            wb.setW5(1);
//            wb.setW6(1);
//            wb.setW7(1);
//            wb.setW8(1);
//            wb.setW9(1);
//            int a=   wbm.updateByPrimaryKeySelective(wb);
//            System.out.print(a);
//
//        }
//
//
//
//        return null;
//    }
//
//    }

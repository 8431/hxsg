package com.hxsg.gchang.controller;

import com.alibaba.fastjson.JSONObject;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.JieSuanMoney;
import com.hxsg.Dao.*;
import com.hxsg.po.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
import java.util.*;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/gchang")
public class GcController  {
    public static Integer num=null;
    public static long t_date;
    public static Integer totalsumyin=0;
    public static Integer totalsumjin=0;
    public static YlDaXiao ydx =new YlDaXiao();
    public static long times;
    @Autowired
    RoleMapper rm;
    @Autowired
    RoleFriendsMsgMapper rfmm;
    @Autowired
    YlDaXiaoMapper ydxm;
    @Autowired
    YlDxXqMapper ydxqm;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/gc", method = { RequestMethod.GET, RequestMethod.POST })
    public String gc()throws Exception {

        return "广场/广场";

    }
    @RequestMapping(value = "/yingjia", method = { RequestMethod.GET, RequestMethod.POST })
    public String yingjia(Model model)throws Exception {
       List<YlDxXq> liy=ydxqm.TenWinRole(num-1);
        List<YlDxXq> lij=ydxqm.TenWinRoleJ(num-1);

        model.addAttribute("liy",liy);
        model.addAttribute("lij",lij);


        return "广场/赢家";

    }
    @RequestMapping(value = "/appyingjia", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appyingjia(HttpServletRequest request,HttpServletResponse response)throws Exception {
        List<YlDxXq> liy=ydxqm.TenWinRole(num-1);
        List<YlDxXq> lij=ydxqm.TenWinRoleJ(num-1);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out=response.getWriter();
        JSONObject obj = new JSONObject(); //根据需要拼装json
        obj.put("liy",liy);
        obj.put("lij",lij);
        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        out.println(jsonpCallback+"("+obj.toString()+")");//返回jsonp格式数据
        out.flush();
        out.close();
        return null;

    }
    @RequestMapping(value = "/moneypm", method = { RequestMethod.GET, RequestMethod.POST })
    public String moneypm(Model model)throws Exception {
        List<YlDxXq> liy=ydxqm.winyinbang();

        model.addAttribute("liy",liy);


        return "广场/赚钱排行";

    }
    @RequestMapping(value = "/appmoneypm", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appmoneypm(HttpServletRequest request,HttpServletResponse response)throws Exception {
        List<YlDxXq> liy=ydxqm.winyinbang();
        CommonUtilAjax.sendAjaxList("liy",liy,request,response);



        return null;

    }
    @RequestMapping(value = "/lishi", method = { RequestMethod.GET, RequestMethod.POST })
    public String lishi(Model model)throws Exception {
        List<YlDaXiao> hy=ydxm.getHistory();
        if(hy.size()>0){
            model.addAttribute("hy",hy);
        }
        return "广场/历史查询";

    }
    @RequestMapping(value = "/applishi", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String applishi(HttpServletRequest request,HttpServletResponse response)throws Exception {
        List<YlDaXiao> hy=ydxm.getHistory();
        if(hy.size()>0){
           CommonUtilAjax.sendAjaxList("hy",hy,request,response);
        }
        return null;

    }
    @RequestMapping(value = "/touzhu", method = { RequestMethod.GET, RequestMethod.POST })
    public String touzhu(Model model,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色

        Integer roleid=r.get(0).getId();
        List<YlDxXq> hy=ydxqm.touzhuhistory(roleid);
        if(hy.size()>0){
            model.addAttribute("hy",hy);
        }
        return "广场/投注记录";

    }
    @RequestMapping(value = "/apptouzhu", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String apptouzhu(HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色

//        Integer roleid=r.get(0).getId();
        List<YlDxXq> hy=ydxqm.touzhuhistory(1000);
        if(hy.size()>0){
           CommonUtilAjax.sendAjaxList("hy",hy,request,response);
        }
        return null;

    }


    @RequestMapping(value = "/ylc", method = { RequestMethod.GET, RequestMethod.POST })
    public String ylc()throws Exception {
        System.out.println(num);
        System.out.println(t_date);
        return "广场/娱乐";

    }
    @RequestMapping(value = "/shuju", method = { RequestMethod.GET, RequestMethod.POST })
    public String shuju()throws Exception {
        for(int i=0;i<10000;i++){

            int a= (int)Math.round(Math.random()*5+1);
            int b= (int)Math.round(Math.random()*5+1);
            int c= (int)Math.round(Math.random()*5+1);
            YlDaXiao yd=new  YlDaXiao();
            yd.setNum1(a);
            yd.setNum2(b);
            yd.setNum3(c);

            StringBuffer result=new StringBuffer();

            int sum=a+b+c;
            if(a==b&&b==c){
                System.out.print(a);
                System.out.print(b);
                System.out.print(c);
                System.out.print("豹子");
                result.append("豹子");
            }else {
                if (sum >= 4 && sum <= 10) {
                    System.out.print(a);
                    System.out.print(b);
                    System.out.print(c);
                    System.out.print("小");
                    result.append("小");

                if (sum==5 || sum == 7 || sum == 9 || sum == 11 || sum == 13 || sum == 15 || sum == 17) {
                            System.out.print(a);
                            System.out.print(b);
                            System.out.print(c);
                            System.out.print("单");
                            result.append("单");
               }



                   if (sum == 4 || sum == 6 || sum == 8 || sum == 10 || sum == 12 || sum == 14 || sum == 16) {
                            System.out.print(a);
                            System.out.print(b);
                            System.out.print(c);
                            System.out.print("双");
                            result.append("双");
                        }
                    }





                if (sum >= 11 && sum <= 17) {
                    System.out.print(a);
                    System.out.print(b);
                    System.out.print(c);
                    System.out.print("大");
                    result.append("大");

                        if (sum == 5 || sum == 7 || sum == 9 || sum == 11 || sum == 13 || sum == 15 || sum == 17) {
                            System.out.print(a);
                            System.out.print(b);
                            System.out.print(c);
                            System.out.print("单");
                            result.append("单");
                        }



                        if (sum == 4 || sum == 6 || sum == 8 || sum == 10 || sum == 12 || sum == 14 || sum == 16) {
                            System.out.print(a);
                            System.out.print(b);
                            System.out.print(c);
                            System.out.print("双");
                            result.append("双");
                        }
                    }


            }
            yd.setResult(result.toString());
            ydxm.insertSelective(yd);
        }
        return null;
    }
    @RequestMapping(value = "/ddx", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  synchronized String ddx(YlDxXq yq,Model model,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色
        String rolename=r.get(0).getJuesename();
        Integer roleid=r.get(0).getId();
        Integer roletotalyin=ydxqm.YanZhiByYin(roleid);
        Integer roletotaljin=ydxqm.YanZhiByJin(roleid);
        if(roletotalyin==null){
            roletotalyin=0;
        }
        if(roletotaljin==null){
            roletotaljin=0;
        }
        if(yq.getJin()==null){
            yq.setJin(0);
        }
        if(yq.getYin()==null){
           yq.setYin(0);
        }
        if(yq.getYin()>200000){
            response.setCharacterEncoding("utf-8");
            PrintWriter out=response.getWriter();
            JSONObject obj=new JSONObject();//使用json
            obj.put("msg","输入的银两单次不能超过20万");
            out.write(obj.toString());
            out.flush();
            out.close();
        }else{
            Integer one=roletotalyin+yq.getYin();
            Integer two=roletotaljin+yq.getJin();
            if(one<300000&&two<1000){//限制押银金的最大数量
                yq.setRolename(rolename);
                yq.setRoleid(roleid);
                yq.setNum(num);
                yq.setData(new Date());
                Integer inputYin=0;
                Integer inputJin=0;

                Role re=rm.selectByPrimaryKey(roleid);
                Integer roleYin=re.getYin();
                Integer roleJin=re.getJin();


                if(yq.getYin()!=null&&!"".equals(yq.getYin())){
                    inputYin=yq.getYin();
                }else{
                    inputYin=0;
                }
                if(yq.getJin()!=null&&!"".equals(yq.getJin())){
                    inputJin=yq.getJin();
                }else{
                    inputJin=0 ;
                }

                if(roleYin>=inputYin&&inputYin!=0){
                    re.setYin(roleYin-inputYin);
                    rm.updateByPrimaryKeySelective(re);
                    ydxqm.insertSelective(yq);

                    response.setCharacterEncoding("utf-8");
                    PrintWriter out=response.getWriter();
                    JSONObject obj=new JSONObject();//使用json
                        obj.put("msg","本期你押了"+yq.getResult()+yq.getYin()+"两。祝你好运");
                    out.write(obj.toString());
                    out.flush();
                    out.close();

                }

                if(roleYin<inputYin){
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out=response.getWriter();
                    JSONObject obj=new JSONObject();//使用json
                    obj.put("msg","余额不足！");
                    out.write(obj.toString());
                    out.flush();
                    out.close();
                }
                if(roleJin>=inputJin&&inputJin!=0){
                    re.setJin(roleJin-inputJin);
                    rm.updateByPrimaryKeySelective(re);
                    ydxqm.insertSelective(yq);
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out=response.getWriter();
                    JSONObject obj=new JSONObject();//使用json




                    obj.put("msg","本期你押了"+yq.getResult()+yq.getJin()+"金。祝你好运");


                    out.write(obj.toString());
                    out.flush();
                    out.close();

                }
                if(roleJin<inputJin){
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out=response.getWriter();
                    JSONObject obj=new JSONObject();//使用json
                    obj.put("msg","余额不足！");
                    out.write(obj.toString());
                    out.flush();
                    out.close();
                }


            }else{
                response.setCharacterEncoding("utf-8");
                PrintWriter out=response.getWriter();
                JSONObject obj=new JSONObject();//使用json
                obj.put("msg","押的总数不能超过白银30万，白金1000两");
                out.write(obj.toString());
                out.flush();
                out.close();

            }
        }
        return null;
    }
    @RequestMapping(value = "/appddx", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  synchronized String appddx(HttpServletRequest request,YlDxXq yq,HttpSession session,HttpServletResponse response)throws Exception {

        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色
        String rolename=r.get(0).getJuesename();
        Integer roleid=r.get(0).getId();
        Integer roletotalyin=ydxqm.YanZhiByYin(roleid);
        Integer roletotaljin=ydxqm.YanZhiByJin(roleid);
        if(roletotalyin==null){
            roletotalyin=0;
        }
        if(roletotaljin==null){
            roletotaljin=0;
        }
        if(yq.getJin()==null){
            yq.setJin(0);
        }
        if(yq.getYin()==null){
            yq.setYin(0);
        }
        if(yq.getYin()>200000){
            CommonUtilAjax.sendAjaxList("msg","输入的银两单次不能超过20万",request,response);

        }else{
            Integer one=roletotalyin+yq.getYin();
            Integer two=roletotaljin+yq.getJin();
            if(one<300000&&two<1000){//限制押银金的最大数量
                yq.setRolename(rolename);
                yq.setRoleid(roleid);
                yq.setNum(num);
                yq.setData(new Date());
                Integer inputYin=0;
                Integer inputJin=0;

                Role re=rm.selectByPrimaryKey(roleid);
                Integer roleYin=re.getYin();
                Integer roleJin=re.getJin();


                if(yq.getYin()!=null&&!"".equals(yq.getYin())){
                    inputYin=yq.getYin();
                }else{
                    inputYin=0;
                }
                if(yq.getJin()!=null&&!"".equals(yq.getJin())){
                    inputJin=yq.getJin();
                }else{
                    inputJin=0 ;
                }

                if(roleYin>=inputYin&&inputYin!=0){
                    re.setYin(roleYin-inputYin);
                    rm.updateByPrimaryKeySelective(re);
                    ydxqm.insertSelective(yq);
                    CommonUtilAjax.sendAjaxList("msg","本期你押了"+yq.getResult()+yq.getYin()+"两。祝你好运",request,response);


                }

                if(roleYin<inputYin){
                    CommonUtilAjax.sendAjaxList("msg","余额不足！",request,response);

                }
                if(roleJin>=inputJin&&inputJin!=0){
                    re.setJin(roleJin-inputJin);
                    rm.updateByPrimaryKeySelective(re);
                    ydxqm.insertSelective(yq);
                    CommonUtilAjax.sendAjaxList("msg","本期你押了"+yq.getResult()+yq.getJin()+"金。祝你好运",request,response);



                }
                if(roleJin<inputJin){
                    CommonUtilAjax.sendAjaxList("msg","余额不足！",request,response);


                }


            }else{
                CommonUtilAjax.sendAjaxList("msg","押的总数不能超过白银30万，白金1000两",request,response);



            }



        }
        return null;

    }
//    @RequestMapping(value = "/init", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String init()throws Exception {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new GcController(),new Date(),1000*60);
//      return null;
//    }
    @RequestMapping(value = "/sxsum", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String sxsum(Model model,HttpServletResponse response)throws Exception {
        YlDxXq yq=new YlDxXq();
        yq.setNum(num);
        yq.setResult("大");
        Integer dasum=ydxqm.SumAllByJin(yq);
        Integer dasumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("小");
        Integer xiaosum=ydxqm.SumAllByJin(yq);
        Integer xiaosumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("单");
        Integer dansum=ydxqm.SumAllByJin(yq);
        Integer dansumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("双");
        Integer shuangsum=ydxqm.SumAllByJin(yq);
        Integer shuangsumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("豹子");
        Integer baozisum=ydxqm.SumAllByJin(yq);
        Integer baozisumyin=ydxqm.SumAllByYin(yq);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        obj.put("dasum",dasum);
        obj.put("dasumyin",dasumyin);
        obj.put("xiaosum",xiaosum);
        obj.put("xiaosumyin",xiaosumyin);
        obj.put("dansum",dansum);
        obj.put("dansumyin",dansumyin);
        obj.put("shuangsum",shuangsum);
        obj.put("shuangsumyin",shuangsumyin);
        obj.put("baozisum",baozisum);
        obj.put("baozisumyin",baozisumyin);
        obj.put("totalsumyin",totalsumyin);
        obj.put("totalsumjin",totalsumjin);
        obj.put("ydxid",ydx.getId());
        obj.put("ydxnum1",ydx.getNum1());
        obj.put("ydxnum2",ydx.getNum2());
        obj.put("ydxnum3",ydx.getNum3());
        obj.put("ydxresult",ydx.getResult());
        System.out.println(obj.toString());
        out.write(obj.toString());
        out.flush();
        out.close();

        return null;
    }

    @RequestMapping(value = "/appsxsum", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appsxsum(Model model,HttpServletRequest request,HttpServletResponse response)throws Exception {
        YlDxXq yq=new YlDxXq();
        yq.setNum(num);
        yq.setResult("大");
        Integer dasum=ydxqm.SumAllByJin(yq);
        Integer dasumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("小");
        Integer xiaosum=ydxqm.SumAllByJin(yq);
        Integer xiaosumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("单");
        Integer dansum=ydxqm.SumAllByJin(yq);
        Integer dansumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("双");
        Integer shuangsum=ydxqm.SumAllByJin(yq);
        Integer shuangsumyin=ydxqm.SumAllByYin(yq);
        yq.setResult("豹子");
        Integer baozisum=ydxqm.SumAllByJin(yq);
        Integer baozisumyin=ydxqm.SumAllByYin(yq);
        ////////////////
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out=response.getWriter();
        JSONObject obj = new JSONObject(); //根据需要拼装json
        obj.put("dasum",dasum);
        obj.put("dasumyin",dasumyin);
        obj.put("xiaosum",xiaosum);
        obj.put("xiaosumyin",xiaosumyin);
        obj.put("dansum",dansum);
        obj.put("dansumyin",dansumyin);
        obj.put("shuangsum",shuangsum);
        obj.put("shuangsumyin",shuangsumyin);
        obj.put("baozisum",baozisum);
        obj.put("baozisumyin",baozisumyin);
        obj.put("totalsumyin",totalsumyin);
        obj.put("totalsumjin",totalsumjin);
        obj.put("ydxid",ydx.getId());
        obj.put("ydxnum1",ydx.getNum1());
        obj.put("ydxnum2",ydx.getNum2());
        obj.put("ydxnum3",ydx.getNum3());
        obj.put("ydxresult",ydx.getResult());
        // System.out.println(obj.toString());

        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        out.println(jsonpCallback+"("+obj.toString()+")");//返回jsonp格式数据
        out.flush();
        out.close();
        ///////////////



        return null;
    }
    @RequestMapping(value = "/sxtime", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String sxtime(Model model,HttpServletResponse response)throws Exception {

        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();//使用json
        obj.put("times",times);
        //System.out.println(obj.toString());
        out.write(obj.toString());
        out.flush();
        out.close();

        return null;
    }
    @RequestMapping(value = "/appsxtime", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appsxtime(HttpServletRequest request,HttpServletResponse response)throws Exception {
        CommonUtilAjax.sendAjaxList("times",times,request,response);


        return null;
    }


}

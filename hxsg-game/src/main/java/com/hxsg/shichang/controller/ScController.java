package com.hxsg.shichang.controller;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
@Controller
@RequestMapping("/sc")
public class ScController {
    @Autowired
    wuqiMapper wqm;
    @Autowired
    RoleMapper rm;
    @Autowired
    RoleZbMapper rzm;
    @Autowired
    jiangjunMapper jjm;
    @Autowired
    WuQiDetailMapper wqdm;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/shichang", method = { RequestMethod.GET, RequestMethod.POST })
      public String shichang( )throws Exception {

        return "市场/市场";

    }
    @RequestMapping(value = "/tiejiang", method = { RequestMethod.GET, RequestMethod.POST })
    public String tiejiang( )throws Exception {

        return "市场/铁匠铺";

    }
    @RequestMapping(value = "/gmzb", method = { RequestMethod.GET, RequestMethod.POST })
      public String gmzb( )throws Exception {

        return "市场/装备";

    }
    @RequestMapping(value = "/toukui", method = { RequestMethod.GET, RequestMethod.POST })
    public String toukui( )throws Exception {

        return "市场/头盔";

    }
    @RequestMapping(value = "/wuqi", method = { RequestMethod.GET, RequestMethod.POST })
    public String wuqi( )throws Exception {

        return "市场/武器";

    }
    @RequestMapping(value = "/xifen", method = { RequestMethod.GET, RequestMethod.POST })
    public String xifen(@RequestParam(value="sx",required=false) String sx,Model model )throws Exception {
        List<wuqi> li = wqm.selectAll();
        model.addAttribute("lis", li);
        model.addAttribute("sx", sx);


        return "市场/装备细分";

    }
    @RequestMapping(value = "/appxifen", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appxifen(HttpServletResponse response,HttpServletRequest request)throws Exception {
        List<wuqi> li = wqm.selectAll();
        CommonUtilAjax.sendAjaxList("lis", li,request,response);

        return null;

    }
    @RequestMapping(value = "/zbxq", method = { RequestMethod.GET, RequestMethod.POST })
    public String zbxq(@RequestParam(value="id",required=false) Integer id,Model model )throws Exception {


        wuqi wq= wqm.selectByPrimaryKey(id);

        model.addAttribute("wq", wq);
        return "市场/装备详情";

    }
    @RequestMapping(value = "/appzbxq", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String appzbxq(@RequestParam(value="id",required=false) Integer id,HttpServletRequest request,HttpServletResponse response )throws Exception {


        wuqi wq= wqm.selectByPrimaryKey(id);

       CommonUtilAjax.sendAjaxList("wq", wq,request,response);
        return null;

    }
    @RequestMapping(value = "/shopzb", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public synchronized  String shopzb(@RequestParam(value="id",required=false) String id,Model model,HttpServletResponse response,HttpSession session)throws Exception {
        response.setCharacterEncoding("utf-8");

        String wuqiids =id;
        int wuqiid=0;
        if(wuqiids!=null&&!"".equals(wuqiids)){
            wuqiid=Integer.parseInt(wuqiids);
        }
        wuqi wq= wqm.selectByPrimaryKey(wuqiid);
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        int roleid = r.get(0).getId();
        Role role=(Role)rm.selectByPrimaryKey(roleid);

        int zbmoney=wq.getPrice();//购买装备价格
        int rolemoney=role.getYin();//角色携带银两

        if(rolemoney>=zbmoney){//如果携带银两大于装备价格，购买装备执行


            role.setYin(role.getYin()-zbmoney);//购买装备成功之后角色携带的银两
           int result=rm.updateByPrimaryKeySelective(role);
            if(result>0){

                RoleZb rz=new RoleZb();
                rz.setRoleid(roleid);
                rz.setWqid(wuqiid);
                rz.setName(wq.getName());
                rz.setXiaoguo(wq.getGongji());
                int rzmresult=rzm.insert(rz);
                if(rzmresult>0){
                    System.out.println("购买成功");
                    PrintWriter out=response.getWriter();
                    Role re=(Role)rm.selectByPrimaryKey(roleid);
                    out.write("购买成功！消费"+zbmoney+"两!余额还剩"+re.getYin()+"两");
                    out.flush();
                    out.close();
                }



            }


        }else{
            PrintWriter out=response.getWriter();
            out.write("购买失败！银两不足！或者输入错误");
            out.flush();
            out.close();

            System.out.println("余额不足");
        }


        //request.setAttribute("wq", wuqi);


        return null;

    }
    @RequestMapping(value = "/appshopzb", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public synchronized  String appshopzb(@RequestParam(value="id",required=false) String id,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {


        String wuqiids =id;
        int wuqiid=0;
        if(wuqiids!=null&&!"".equals(wuqiids)){
            wuqiid=Integer.parseInt(wuqiids);
        }
        wuqi wq= wqm.selectByPrimaryKey(wuqiid);
        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色IDD
        int roleid = r.get(0).getId();
        Role role=(Role)rm.selectByPrimaryKey(roleid);

        int zbmoney=wq.getPrice();//购买装备价格
        int rolemoney=role.getYin();//角色携带银两

        if(rolemoney>=zbmoney){//如果携带银两大于装备价格，购买装备执行


            role.setYin(role.getYin()-zbmoney);//购买装备成功之后角色携带的银两
            int result=rm.updateByPrimaryKeySelective(role);
            if(result>0){

                jiangjun rz=new jiangjun();
                rz.setRoleid(roleid);
                rz.setJjname(wq.getName());
                rz.setNum(1);
                rz.setType("武器");
                rz.setData(new Date());
                int rzmresult=jjm.insert(rz);
                int wqid=rz.getId();
                WuQiDetail wqd=new WuQiDetail();
                wqd.setZbid(wqid);//装备ID
                wqd.setGongji(wq.getGongji());//效果
                wqd.setFanji(0);//反击
                wqd.setSudu(0);//速度
                wqd.setJingli(0);//精力
                wqd.setQixue(0);//气血
                wqd.setBaoji(0);//暴击
                wqd.setAddgongji(0);//增加的效果
                wqd.setBs1(0);//宝石1
                wqd.setBs2(0);//宝石2
                wqd.setBs3(0);//宝石3
                wqd.setData(new Date());//时间
                wqd.setDu(0);//毒
                wqd.setDuobi(0);//躲避
                wqd.setZhiming(0);//致命
                wqd.setXuanji(0);//玄机
                wqd.setWuli(0);//物理
                wqd.setFangyu(0);//防御
                wqd.setFanzhen(0);//反震
                wqd.setFashubao(0);//法术爆
                wqd.setFeng(0);//封
                wqd.setWei(0);//围
                wqd.setYaohuo(0);//妖火
                wqd.setType("普通");
                wqd.setStatus("0");//状态

                wqd.setPinzhi("81");
                wqd.setHufeng(0);//呼风
                wqd.setJnid(0);
                wqd.setLuan(0);//乱
                wqd.setLei(0);//雷
                wqd.setLevel("0");//等级
                wqd.setJnjc(0);
                wqd.setName(wq.getName());
                wqd.setMingzhong(0);




                wqdm.insert(wqd);




                if(rzmresult>0){
                    CommonUtilAjax.sendAjaxList("msg","购买成功！消费"+zbmoney+"两!余额还剩"+(rolemoney-zbmoney)+"两",request,response);

                }



            }


        }else{
            CommonUtilAjax.sendAjaxList("msg","购买失败！银两不足！或者输入错误",request,response);

        }


        //request.setAttribute("wq", wuqi);


        return null;

    }




}

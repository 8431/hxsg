package com.hxsg.yiguan.controller;//package com.hxsg.yiguan.controller;
//
//import com.hxsg.CommonUtil.CommonUtilAjax;
//import com.hxsg.Dao.RoleMapper;
//import com.hxsg.Dao.jiangjunMapper;
//import com.hxsg.Dao.roleyaoMapper;
//import com.hxsg.Dao.yaopingMapper;
//import com.hxsg.login.LoginService;
//import com.hxsg.login.zhuceService;
//import com.hxsg.po.*;
//import com.hxsg.yiguan.ZhuangBeiService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by dlf on 2015/12/31.
// */
//@Controller
//@RequestMapping("/yao")
//public class YaoPinController {
//    @Autowired
//    ZhuangBeiService yps;
//    @Autowired
//    RoleMapper rm;
//    @Autowired
//    yaopingMapper ypm;
//    @Autowired
//    roleyaoMapper rym;
//    @Autowired
//    jiangjunMapper jjm;
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    @RequestMapping(value = "/all",method = { RequestMethod.GET, RequestMethod.POST })
//    public String toTao(@RequestParam(value="sx",required=false) Integer sx,Model model )throws Exception {
//       System.out.println(sx);
//        int a=0;
//        if(sx!=null&&!"".equals(sx)){
//             a=sx.intValue();
//        }
//        String  result=null;
//        List<yaoping> yao=yps.selectBySx(sx);
//        if(yao.size()>0){
//            model.addAttribute("yao",yao);
//            model.addAttribute("sx",yao.get(0).getSx());
//            result="医馆/药品概览";
//        }else{
//            logger.error("没有查询到药品信息");
//
//        }
//
//
//            return result;
//
//    }
//    @RequestMapping(value = "/appall",method = { RequestMethod.GET, RequestMethod.POST })
//    public String appall(@RequestParam(value="sx",required=false) Integer sx,HttpServletRequest request,HttpServletResponse response)throws Exception {
//        System.out.println(sx);
//        int a=0;
//        if(sx!=null&&!"".equals(sx)){
//            a=sx.intValue();
//        }
//
//        List<yaoping> yao=yps.selectBySx(sx);
//        if(yao.size()>0){
//            CommonUtilAjax.sendAjaxList("yao",yao,request,response);
//            //model.addAttribute("yao",yao);
//            //model.addAttribute("sx",yao.get(0).getSx());
//
//        }else{
//            logger.error("没有查询到药品信息");
//
//        }
//
//
//        return null;
//
//    }
//    @RequestMapping(value = "/login",method = { RequestMethod.GET, RequestMethod.POST })
//    public String yiguan( )throws Exception {
//
//
//
//        return "医馆/医馆";
//
//    }
//    @RequestMapping(value = "/ypxq",method = { RequestMethod.GET, RequestMethod.POST })
//    public String ypxq(yaoping yp,Model model )throws Exception {
//        String  result=null;
//        model.addAttribute("yaopin", yp);//传递	药品详情
//        if(yp!=null){
//            result="医馆/药品详情";
//
//        }
//
//
//        return result;
//
//    }
//
//    @RequestMapping(value = "/gmyp",method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public Map gmyp(@RequestParam(value="yaoprice",required=false)Integer yaoprice,roleyao ry,Model model,HttpSession session)throws Exception {
//        Map<String, Object> maps = new HashMap<String, Object>();
//        String result=null;
//        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色ID
//        int roleid = r.get(0).getId();
//        System.out.println(roleid);
//        Role role=rm.selectByPrimaryKey(roleid);
//        System.out.println(role.getYin());
//        int rymoney=ry.getYaonum()*yaoprice;//购买药品的总价格
//        Integer rolemoney=role.getYin();//角色携带银两
//        System.out.println(rymoney);
//        if(rolemoney>=rymoney){//如果携带银两大于购买药品的总价格，购买药品执行
//            System.out.println(roleid);
//            List<roleyao> list=(List<roleyao>) rym.selectByYaoIdRoleId(ry.getYaoid(),roleid);	//
//            System.out.println(ry.getYaonum());
//            System.out.println(ry.getRoleid());
//            System.out.println(ry.getYaoid());
//            System.out.println(ry.getYaoname());
//            role.setYin(role.getYin()-rymoney);//购买药品成功之后角色携带的银两
//            int a=rm.updateByPrimaryKeySelective(role);//更新角色银两到数据库
//            System.out.println(a);
//            Role re=rm.selectByPrimaryKey(roleid);
//            System.out.println(re.getYin());
//            if(list.size()==0){//如果从未购买，讲药品信息插入到Roleyao数据库
//                ry.setRoleid(roleid);
//                int b=rym.insertSelective(ry);
//                System.out.println(b);
//            }else{//如果购买过药品，录入到数据库后。增加药品数目！
//                int sum=list.get(0).getYaonum()+ry.getYaonum();
//                list.get(0).setYaonum(sum);
//               rym.updateByPrimaryKeySelective( list.get(0));//更新购买后药品的数量
//            }
//            System.out.println("购买成功");
//
//            maps.put("msg","购买成功！消费"+rymoney+"两!余额还剩"+re.getYin()+"两");
//
//            result="gmsucces";
//        }else{
//
//            maps.put("msg","购买失败！银两不足！或者输入错误");
//
//            result="gmfail";
//            System.out.println("余额不足");
//        }
//
//        return maps;
//    }
//    @RequestMapping(value = "/appgmyp",method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String  appgmyp(@RequestParam(value="yaoprice",required=false)Integer yaoprice,roleyao ry,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
//
//        String result=null;
//       Integer roleid =(Integer)session.getAttribute("roleid");//获取session查询角色ID
//      if(roleid>0){
//          Role role=rm.selectByPrimaryKey(roleid);
//          System.out.println(role.getYin());
//          int rymoney=ry.getYaonum()*yaoprice;//购买药品的总价格
//          Integer rolemoney=role.getYin();//角色携带银两
//          System.out.println(rymoney);
//          if(rolemoney>=rymoney){//如果携带银两大于购买药品的总价格，购买药品执行
//              System.out.println(roleid);
//              jiangjun jjn=new jiangjun();
//              jjn.setRoleid(roleid);
//              jjn.setJjname(ry.getYaoname());
//              List<jiangjun> li=jjm.selectAll(jjn);	//
//              System.out.println(ry.getYaonum());
//              System.out.println(ry.getRoleid());
//              System.out.println(ry.getYaoid());
//              System.out.println(ry.getYaoname());
//              role.setYin(role.getYin()-rymoney);//购买药品成功之后角色携带的银两
//              int a=rm.updateByPrimaryKeySelective(role);//更新角色银两到数据库
//              System.out.println(a);
//              Role re=rm.selectByPrimaryKey(roleid);
//              System.out.println(re.getYin());
//              if(li.size()==0){//如果从未购买，讲药品信息插入到Roleyao数据库
//                  ry.setRoleid(roleid);
//                  jiangjun jj=new jiangjun();
//                  jj.setData(new Date());
//                  jj.setNum(ry.getYaonum());
//                  jj.setJjname(ry.getYaoname());
//                  jj.setRoleid(ry.getRoleid());
//                  jj.setType("药");
//
//
//                  int b=jjm.insertSelective(jj);
//                  System.out.println(b);
//              }else{//如果购买过药品，录入到数据库后。增加药品数目！
//
//
//                  int sum=li.get(0).getNum()+ry.getYaonum();
//                  li.get(0).setNum(sum);
//                  jjm.updateByPrimaryKeySelective( li.get(0));//更新购买后药品的数量
//              }
//              System.out.println("购买成功");
//                CommonUtilAjax.sendAjaxList("msg","购买成功！消费"+rymoney+"两!余额还剩"+re.getYin()+"两",request,response);
//
//          }else{
//              CommonUtilAjax.sendAjaxList("msg","购买失败！银两不足！或者输入错误",request,response);
//          }
//
//
//
//      }
//
//
//
//        return null;
//    }
//
//
//
//
//}

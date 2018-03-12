//package com.hxsg.rolefujaing.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hxsg.CommonUtil.CommonUtilAjax;
//import com.hxsg.Dao.*;
//import com.hxsg.po.*;
//
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
//import java.io.PrintWriter;
//import java.util.List;
//
///**
// * Created by dlf on 2015/12/31.
// */
//@Controller
//@RequestMapping("/fujiang")
//public class FuJController {
//
//    @Autowired
//    RoleFujiangMapper rfm;
//    @Autowired
//    RoleMapper rm;
//    @Autowired
//    FuJiangMapper fjm;
//    @Autowired
//    FjLevelJyMapper fljm;
//    @Autowired
//    jiangjunMapper jjm;
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @RequestMapping(value = "/czpy", method = { RequestMethod.GET, RequestMethod.POST })
//
//    public String czpy(@RequestParam(value = "id",required = false)Integer id,Model model)throws Exception {
//        if(id!=null&&id!=0){
//            RoleFujiang rf= rfm.selectByPrimaryKey(id);
//            model.addAttribute("rf",rf);
//        }
//        return "副将/初值培养";
//    }
//
//    @RequestMapping(value = "/fjqk", method = { RequestMethod.GET, RequestMethod.POST })
//
//    public String fjqk(@RequestParam(value = "id",required = false)Integer id,Model model)throws Exception {
//        if(id!=null&&id!=0){
//            FuJiang fj= fjm.selectByPrimaryKey(id);
//            model.addAttribute("rf",fj);
//        }
//
//
//        return "副将/最优副将";
//
//
//
//
//
//    }
//    @RequestMapping(value = "/appfjqk", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String appfjqk(@RequestParam(value = "id",required = false)Integer id,HttpServletRequest request,HttpServletResponse response)throws Exception {
//        if(id!=null&&id!=0){
//            FuJiang fj= fjm.selectByPrimaryKey(id);
//           CommonUtilAjax.sendAjaxList("rf",fj,request,response);
//        }
//
//
//        return null;
//
//
//
//
//
//    }
//    @RequestMapping(value = "/ckds", method = { RequestMethod.GET, RequestMethod.POST })
//
//    public String ckds(@RequestParam(value = "id",required = false)Integer id,Model model)throws Exception {
//        if(id!=null&&id!=0){
//            RoleFujiang rf= rfm.selectByPrimaryKey(id);
//            model.addAttribute("rf",rf);
//        }
//
//
//        return "副将/副将点数";
//    }
//    @RequestMapping(value = "/appckds", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public String appckds(@RequestParam(value = "id",required = false)Integer id,HttpServletResponse response,HttpServletRequest request)throws Exception {
//        if(id!=null&&id!=0){
//            RoleFujiang rf= rfm.selectByPrimaryKey(id);
//            CommonUtilAjax.sendAjaxList("rf",rf,request,response);
//
//        }
//        return null;
//    }
//
//
//    @RequestMapping(value = "/pyfj", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public synchronized String pyfj(@RequestParam(value = "fuid",required = false)Integer fuid,@RequestParam(value = "id",required = false)Integer id,@RequestParam(value = "type",required = false)String type ,HttpServletResponse response,HttpSession session)throws Exception {
//        List<Role> r =(List<Role>)session.getAttribute("rolelist");//获取session查询角色ID
//        int roleid = r.get(0).getId();
//
//        Role role=rm.selectByPrimaryKey(roleid);
//
//           int rymoney=50;//培养消耗的金
//            Integer rolemoney=role.getJin();//角色携带银两
//
//            RoleFujiang rf= rfm.selectByPrimaryKey(id);
//
//        FuJiang fuJiang=fjm.selectByPrimaryKey(fuid);
//        if(rolemoney>=rymoney){//如果携带银两大于培养消耗的金
//            role.setJin(rolemoney-rymoney);
//            rm.updateByPrimaryKeySelective(role);//更新金
//            if(type.equals("成长")) {
//              float ft=(float) (fuJiang.getChengzhang() + (Math.floor(Math.random() * 20 - 20.0)) / 100);
//                //float  ft=(float)(Math.round(czt*100)/100);
//                rf.setChengzhang(ft);
//                rfm.updateByPrimaryKeySelective(rf);
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out=response.getWriter();
//                JSONObject obj=new JSONObject();//使用json
//                obj.put("num",ft);
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//
//            }
//            if(type.equals("气血")) {
//                float cz=rf.getChengzhang();
//                int ft=fuJiang.getChuxue() + ((int) Math.round(Math.random() * 20 - 20));
//                rf.setChuxue(ft);
//                rf.setTotalxue2((int)(cz*(rf.getQixueds()*rf.getLeve())+ft*rf.getLeve()*cz*0.8 ));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
//
//                rfm.updateByPrimaryKeySelective(rf);
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out=response.getWriter();
//                JSONObject obj=new JSONObject();//使用json
//                obj.put("num",ft);
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//            }
//            if(type.equals("精力")) {
//                float cz=rf.getChengzhang();
//                int ft=fuJiang.getChujing() + ((int) Math.round(Math.random() * 20 - 20));
//                rf.setChujing(ft);
//                rf.setTotaljing2((int)(cz*(rf.getQixueds()*rf.getLeve())+ ft*rf.getLeve()*cz*0.8));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
//
//                rfm.updateByPrimaryKeySelective(rf);
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out=response.getWriter();
//                JSONObject obj=new JSONObject();//使用json
//                obj.put("num",ft);
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//            }
//            if(type.equals("攻击")) {
//                float cz=rf.getChengzhang();
//                int ft=fuJiang.getChugong() + ((int) Math.round(Math.random() * 20 - 20));
//               rf.setChugong(ft);
//                rf.setTotalgong((int)((ft*cz*rf.getLeve())/7+ft*cz*0.5+rf.getLeve()*cz*rf.getGongjids()*0.2));
//                rfm.updateByPrimaryKeySelective(rf);
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out=response.getWriter();
//                JSONObject obj=new JSONObject();//使用json
//                obj.put("num",ft);
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//            }
//            if(type.equals("敏捷")) {
//                float cz=rf.getChengzhang();
//                int ft=fuJiang.getChusu() + ((int) Math.round(Math.random() * 20 - 20));
//                rf.setChusu(ft);//随机+-20设置速度属性
//                float dufuf=cz*(ft+rf.getSududs());
//                int sudu=(int)dufuf;
//                rf.setTotalsudu(sudu);
//                rfm.updateByPrimaryKeySelective(rf);
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out=response.getWriter();
//                JSONObject obj=new JSONObject();//使用json
//                obj.put("num",ft);
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//            }
//
//            }else{
//            response.setCharacterEncoding("utf-8");
//            PrintWriter out=response.getWriter();
//            JSONObject obj=new JSONObject();//使用json
//            obj.put("msg","你金额不足，无法培养。");
//            out.write(obj.toString());
//            out.flush();
//            out.close();
//
//        }
//        return null;
//    }
//    @RequestMapping(value = "/apppyfj", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public synchronized String apppyfj(@RequestParam(value = "fuid",required = false)Integer fuid,
//                                       @RequestParam(value = "id",required = false)Integer id,
//                                       @RequestParam(value = "type",required = false)String type ,
//                                       HttpServletRequest request,HttpServletResponse response,
//                                       HttpSession session)throws Exception {
//        Integer r = (Integer) session.getAttribute("roleid");
//        if(r>0){
//            int roleid = r;
//
//            Role role=rm.selectByPrimaryKey(roleid);
//
//            int rymoney=50;//培养消耗的金
//            Integer rolemoney=role.getJin();//角色携带银两
//
//            RoleFujiang rf= rfm.selectByPrimaryKey(id);
//
//            FuJiang fuJiang=fjm.selectByPrimaryKey(fuid);
//            if(rolemoney>=rymoney){//如果携带银两大于培养消耗的金
//                role.setJin(rolemoney-rymoney);
//                rm.updateByPrimaryKeySelective(role);//更新金
//                if(type.equals("成长")) {
//                    float ft=(float) (fuJiang.getChengzhang() + (Math.floor(Math.random() * 20 - 19.0)) / 100);
//                    //float  ft=(float)(Math.round(czt*100)/100);
//                    rf.setChengzhang(ft);
//                    rfm.updateByPrimaryKeySelective(rf);
//                    CommonUtilAjax.sendAjaxList("num",ft,request,response);
//
//
//                }
//                if(type.equals("气血")) {
//                    float cz=rf.getChengzhang();
//                    int ft=fuJiang.getChuxue() + ((int) Math.round(Math.random() * 20 - 19));
//                    rf.setChuxue(ft);
//                    rf.setTotalxue2((int)(cz*(rf.getQixueds()*rf.getLeve())+ft*rf.getLeve()*cz*0.8 ));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
//
//                    rfm.updateByPrimaryKeySelective(rf);
//                    CommonUtilAjax.sendAjaxList("num",ft,request,response);
//                }
//                if(type.equals("精力")) {
//                    float cz=rf.getChengzhang();
//                    int ft=fuJiang.getChujing() + ((int) Math.round(Math.random() * 20 - 19));
//                    rf.setChujing(ft);
//                    rf.setTotaljing2((int)(cz*(rf.getQixueds()*rf.getLeve())+ ft*rf.getLeve()*cz*0.8));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
//
//                    rfm.updateByPrimaryKeySelective(rf);
//                    CommonUtilAjax.sendAjaxList("num",ft,request,response);
//                }
//                if(type.equals("攻击")) {
//                    float cz=rf.getChengzhang();
//                    int ft=fuJiang.getChugong() + ((int) Math.round(Math.random() * 20 - 19));
//                    rf.setChugong(ft);
//                    rf.setTotalgong((int)((ft*cz*rf.getLeve())/7+ft*cz*0.5+rf.getLeve()*cz*rf.getGongjids()*0.2));
//                    rfm.updateByPrimaryKeySelective(rf);
//                    CommonUtilAjax.sendAjaxList("num",ft,request,response);
//                }
//                if(type.equals("敏捷")) {
//                    float cz=rf.getChengzhang();
//                    int ft=fuJiang.getChusu() + ((int) Math.round(Math.random() * 20 - 19));
//                    rf.setChusu(ft);//随机+-20设置速度属性
//                    float dufuf=cz*(ft+rf.getSududs());
//                    int sudu=(int)dufuf;
//                    rf.setTotalsudu(sudu);
//                    rfm.updateByPrimaryKeySelective(rf);
//                    CommonUtilAjax.sendAjaxList("num",ft,request,response);
//                }
//
//            }else{
//                CommonUtilAjax.sendAjaxList("msg","你金额不足，无法培养。",request,response);
//
//
//            }
//        }
//
//        return null;
//    }
//    @RequestMapping(value = "/fpsx", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public  String fpsx(RoleFujiang rfg, HttpServletResponse response, HttpSession session)throws Exception {
//       if(rfg!=null){
//       RoleFujiang rf=rfm.selectByPrimaryKey(rfg.getId());
//        rf.setKeyongds(rfg.getKeyongds());
//        rf.setTotalxue2(rfg.getTotalxue2());
//        rf.setTotaljing2(rfg.getTotaljing2());
//        rf.setTotalgong(rfg.getTotalgong());
//        rf.setTotalsudu(rfg.getTotalsudu());
//        rf.setQixueds(rfg.getQixueds());
//        rf.setJinglids(rfg.getJinglids());
//        rf.setGongjids(rfg.getGongjids());
//        rf.setSududs(rfg.getSududs());
//        rfm.updateByPrimaryKeySelective(rf);
//            response.setCharacterEncoding("utf-8");
//            PrintWriter out=response.getWriter();
//            JSONObject obj=new JSONObject();//使用json
//            obj.put("msg","恭喜你分配成功！");
//            out.write(obj.toString());
//            out.flush();
//            out.close();
//       }
//        return null;
//    }
//    @RequestMapping(value = "/appfpsx", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public  String appfpsx(HttpServletRequest request, RoleFujiang rfg, HttpServletResponse response, HttpSession session)throws Exception {
//        if(rfg!=null){
//            RoleFujiang rf=rfm.selectByPrimaryKey(rfg.getId());
//            rf.setKeyongds(rfg.getKeyongds());
//            rf.setTotalxue2(rfg.getTotalxue2());
//            rf.setTotaljing2(rfg.getTotaljing2());
//            rf.setTotalgong(rfg.getTotalgong());
//            rf.setTotalsudu(rfg.getTotalsudu());
//            rf.setQixueds(rfg.getQixueds());
//            rf.setJinglids(rfg.getJinglids());
//            rf.setGongjids(rfg.getGongjids());
//            rf.setSududs(rfg.getSududs());
//            rfm.updateByPrimaryKeySelective(rf);
//            CommonUtilAjax.sendAjaxList("msg","恭喜你分配成功！",request,response);
//
//        }
//        return null;
//    }
////    @RequestMapping(value = "/fjjy", method = { RequestMethod.GET, RequestMethod.POST })
////
////    public  String fjjy(RoleFujiang rfg,HttpServletResponse response,HttpSession session)throws Exception {
////
////                for(int i=2;i<100;i++){
////                    FjLevelJy fj=new FjLevelJy();
////                    fj.setData(new Date());
////                    fj.setLevel(i);
////                    fj.setStatus(0);
////                    fj.setType("经验值");
////                    fj.setYingcai(20*(i*i*i+5*i)-40);
////                    fj.setJiangcai(30*(i*i*i+5*i)-80);
////                    fj.setYuanshuai(40*(i*i*i+5*i)-80);
////                    fljm.insertSelective(fj);
////
////                }
////        return null;
////    }
//
//@RequestMapping(value = "/xinfashu", method = { RequestMethod.GET, RequestMethod.POST })
//@ResponseBody
////使用经验书
//public   String xinfashu(@RequestParam(value="jid",required = false)Integer jid,@RequestParam(value="num",required = false)Integer num,@RequestParam(value="id",required = false)Integer id,HttpServletResponse response,HttpSession session)throws Exception {
//    jiangjun jj=jjm.selectByPrimaryKey(jid);
//    int total=jj.getNum();
//    if(total>=num){
//        jj.setNum(total-num);
//        int a=jjm.updateByPrimaryKeySelective(jj);
//        if(a>0){
//
//            RoleFujiang rf=rfm.selectByPrimaryKey(id);
//            if(jj.getJjname().equals("副将经验书")&&rf.getLeve()<11){
//                rf.setLeve(11);
//                rf.setKeyongds(44);
//                rf.setQixueds(11);
//                rf.setJinglids(11);
//                rf.setGongjids(11);
//                rf.setSududs(11);
//                rfm.updateByPrimaryKeySelective(rf);
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out=response.getWriter();
//                JSONObject obj=new JSONObject();//使用json
//                obj.put("sjmsg","恭喜你副将升到11级");
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//
//
//            }
//            int jy=rf.getLeve();
//            //设置经验书使用后获得的经验
//            if(rf.getJingyan()==null){
//                rf.setJingyan(60*jy*jy*num);
//
//                rfm.updateByPrimaryKeySelective(rf);
//            }
//            if(rf.getSjjinyan()==null){
//                if(rf.getTouxian().equals("英才")){
//                    rf.setSjjinyan((20*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-40));
//
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getTouxian().equals("将才")){
//                    rf.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
//
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getTouxian().equals("猛将")){
//                    rf.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
//
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getTouxian().equals("元帅")){
//                    rf.setSjjinyan((40*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
//
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//
//
//            }
//            //设置当前级别的总经验
//            if(rf.getTotajy()==null&&rf.getTouxian().equals("英才")){
//                rf.setTotajy(fljm.sumyingcai(rf.getLeve()+1));
//                rfm.updateByPrimaryKeySelective(rf);
//            }
//            if(rf.getTotajy()==null&&rf.getTouxian().equals("将才")){
//                rf.setTotajy(fljm.sumjiangcai(rf.getLeve()+1));
//                rfm.updateByPrimaryKeySelective(rf);
//            }
//            if(rf.getTotajy()==null&&rf.getTouxian().equals("猛将")){
//                rf.setTotajy(fljm.sumjiangcai(rf.getLeve()+1));
//                rfm.updateByPrimaryKeySelective(rf);
//            }
//            if(rf.getTotajy()==null&&rf.getTouxian().equals("元帅")){
//                rf.setTotajy(fljm.sumyuanshuai(rf.getLeve()+1));
//                rfm.updateByPrimaryKeySelective(rf);
//            }
//            if(rf.getJingyan()>=0){
//                rf.setJingyan(rf.getJingyan()+60*jy*jy*num);
//                rfm.updateByPrimaryKeySelective(rf);
//                RoleFujiang rfg=rfm.selectByPrimaryKey(id);
//                if(rfg.getSjjinyan()<=0){//升了一级之后的属性
//
//
//
//
//
//
//                    rfg.setTotalxue2((int)(Math.round(rfg.getChengzhang()*(rfg.getLeve()+1)*((rfg.getQixueds()+1)+rfg.getChuxue()*0.8))));
//                    rfg.setTotaljing2((int)(Math.round(rfg.getChengzhang()*(rfg.getLeve()+1)*((rfg.getJinglids()+1)+rfg.getChujing()*0.8))));
//                    rfg.setTotalgong((int)( Math.round(((rfg.getLeve()+1)* rfg.getChengzhang()* rfg.getChugong())/7+rfg.getChengzhang()*rfg.getChugong()*0.5+(rfg.getLeve()+1)*rfg.getChengzhang()*(rfg.getGongjids()+1)*0.2)));
//                    rfg.setTotalsudu((int)(rfg.getChengzhang()*(rfg.getSududs()+1+rfg.getChusu())));
//                    rfg.setLeve(rfg.getLeve()+1);
//                    rfg.setQixueds(rfg.getQixueds()+1);
//                    rfg.setJinglids(rfg.getJinglids()+1);
//                    rfg.setGongjids(rfg.getGongjids()+1);
//                    rfg.setSududs(rfg.getSududs()+1);
//                    rfg.setKeyongds(rfg.getKeyongds()+4);
//                    //rfm.updateByPrimaryKeySelective(rfg);
//                    //设置当前级别的总经验
//                    if(rf.getTouxian().equals("英才")){
//                        rfg.setSjjinyan((20*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-40)+rfg.getSjjinyan());
//                        rfg.setJingyan(0);
//                        rf.setTotajy(fljm.sumyingcai(rfg.getLeve()+2));
//                        rfm.updateByPrimaryKeySelective(rfg);
//                    }
//                    if(rf.getTouxian().equals("将才")){
//                        rfg.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80)+rfg.getSjjinyan());
//                        rf.setTotajy(fljm.sumjiangcai(rfg.getLeve()+2));
//                        rfg.setJingyan(0);
//                        rfm.updateByPrimaryKeySelective(rfg);
//                    }
//                    if(rf.getTouxian().equals("猛将")){
//                        rfg.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80)+rfg.getSjjinyan());
//                        rf.setTotajy(fljm.sumjiangcai(rfg.getLeve()+2));
//                        rfg.setJingyan(0);
//                        rfm.updateByPrimaryKeySelective(rfg);
//                    }
//                    if(rf.getTouxian().equals("元帅")){
//                        rfg.setSjjinyan((40*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80)+rfg.getSjjinyan());
//
//                        rf.setTotajy(fljm.sumyuanshuai(rfg.getLeve()+2));
//                        rfg.setJingyan(0);
//                        rfm.updateByPrimaryKeySelective(rfg);
//                    }
//                    response.setCharacterEncoding("utf-8");
//                    PrintWriter out=response.getWriter();
//                    JSONObject obj=new JSONObject();//使用json
//                    obj.put("sjmsg","副将获得"+60*jy*jy*num+"经验，恭喜你副将升到"+(jy+1)+"级");
//                    out.write(obj.toString());
//                    out.flush();
//                    out.close();
//                }
//                if(rfg.getSjjinyan()>0){
//
//
//                    rfg.setSjjinyan(rfg.getSjjinyan()-60*jy*jy*num);
//                    rfm.updateByPrimaryKeySelective(rfg);
//
//
//                }
//                response.setCharacterEncoding("utf-8");
//                PrintWriter out=response.getWriter();
//                JSONObject obj=new JSONObject();//使用json
//                obj.put("msg","副将获得"+60*jy*jy*num+"经验");
//                out.write(obj.toString());
//                out.flush();
//                out.close();
//
//            }
//        }
//
//    }else{
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out=response.getWriter();
//        JSONObject obj=new JSONObject();//使用json
//        obj.put("msg","你身上携带的数量不足，请重新输入。");
//        out.write(obj.toString());
//        out.flush();
//        out.close();
//
//    }
//
//
//
//
//
//    return null;
//}
//    @RequestMapping(value = "/appxinfashu", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
////使用经验书
//    public  String appxinfashu(@RequestParam(value="jid",required = false)Integer jid,@RequestParam(value="num",required = false)Integer num,@RequestParam(value="id",required = false)Integer id,HttpServletResponse response,HttpSession session,HttpServletRequest request)throws Exception {
//        jiangjun jj=jjm.selectByPrimaryKey(jid);
//        int total=jj.getNum();
//        if(total>=num){
//            jj.setNum(total-num);
//            int a=jjm.updateByPrimaryKeySelective(jj);
//            if(a>0){
//
//                RoleFujiang rf=rfm.selectByPrimaryKey(id);
//                if(jj.getJjname().equals("副将经验书")&&rf.getLeve()<=11){
//                    rf.setLeve(11);
//                    rf.setKeyongds(44);
//                    rf.setQixueds(11);
//                    rf.setJinglids(11);
//                    rf.setGongjids(11);
//                    rf.setSududs(11);
//                    rfm.updateByPrimaryKeySelective(rf);
//                   CommonUtilAjax.sendAjaxList("sjmsg","恭喜你副将升到11级",request,response);
//
//
//
//                }
//                int jy=rf.getLeve();
//                //设置经验书使用后获得的经验
//                if(rf.getJingyan()==null){
//                    rf.setJingyan(60*jy*jy*num);
//
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getSjjinyan()==null){
//                    if(rf.getTouxian().equals("英才")){
//                        rf.setSjjinyan((20*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-40));
//
//                        rfm.updateByPrimaryKeySelective(rf);
//                    }
//                    if(rf.getTouxian().equals("将才")){
//                        rf.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
//
//                        rfm.updateByPrimaryKeySelective(rf);
//                    }
//                    if(rf.getTouxian().equals("猛将")){
//                        rf.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
//
//                        rfm.updateByPrimaryKeySelective(rf);
//                    }
//                    if(rf.getTouxian().equals("元帅")){
//                        rf.setSjjinyan((40*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
//
//                        rfm.updateByPrimaryKeySelective(rf);
//                    }
//
//
//                }
//                //设置当前级别的总经验
//                if(rf.getTotajy()==null&&rf.getTouxian().equals("英才")){
//                    rf.setTotajy(fljm.sumyingcai(rf.getLeve()+1));
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getTotajy()==null&&rf.getTouxian().equals("将才")){
//                    rf.setTotajy(fljm.sumjiangcai(rf.getLeve()+1));
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getTotajy()==null&&rf.getTouxian().equals("猛将")){
//                    rf.setTotajy(fljm.sumjiangcai(rf.getLeve()+1));
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getTotajy()==null&&rf.getTouxian().equals("元帅")){
//                    rf.setTotajy(fljm.sumyuanshuai(rf.getLeve()+1));
//                    rfm.updateByPrimaryKeySelective(rf);
//                }
//                if(rf.getJingyan()>=0){
//                    rf.setJingyan(rf.getJingyan()+60*jy*jy*num);
//                    rfm.updateByPrimaryKeySelective(rf);
//                    RoleFujiang rfg=rfm.selectByPrimaryKey(id);
//                    if(rfg.getSjjinyan()<=0){//升了一级之后的属性
//
//
//
//
//
//
//                        rfg.setTotalxue2((int)(Math.round(rfg.getChengzhang()*(rfg.getLeve()+1)*((rfg.getQixueds()+1)+rfg.getChuxue()*0.8))));
//                        rfg.setTotaljing2((int)(Math.round(rfg.getChengzhang()*(rfg.getLeve()+1)*((rfg.getJinglids()+1)+rfg.getChujing()*0.8))));
//                        rfg.setTotalgong((int)( Math.round(((rfg.getLeve()+1)* rfg.getChengzhang()* rfg.getChugong())/7+rfg.getChengzhang()*rfg.getChugong()*0.5+(rfg.getLeve()+1)*rfg.getChengzhang()*(rfg.getGongjids()+1)*0.2)));
//                        rfg.setTotalsudu((int)(rfg.getChengzhang()*(rfg.getSududs()+1+rfg.getChusu())));
//                        rfg.setLeve(rfg.getLeve()+1);
//                        rfg.setQixueds(rfg.getQixueds()+1);
//                        rfg.setJinglids(rfg.getJinglids()+1);
//                        rfg.setGongjids(rfg.getGongjids()+1);
//                        rfg.setSududs(rfg.getSududs()+1);
//                        rfg.setKeyongds(rfg.getKeyongds()+4);
//                        //rfm.updateByPrimaryKeySelective(rfg);
//                        //设置当前级别的总经验
//                        if(rf.getTouxian().equals("英才")){
//                            rfg.setSjjinyan((20*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-40)+rfg.getSjjinyan());
//                            rfg.setJingyan(0);
//                            rf.setTotajy(fljm.sumyingcai(rfg.getLeve()+2));
//                            rfm.updateByPrimaryKeySelective(rfg);
//                        }
//                        if(rf.getTouxian().equals("将才")){
//                            rfg.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80)+rfg.getSjjinyan());
//                            rf.setTotajy(fljm.sumjiangcai(rfg.getLeve()+2));
//                            rfg.setJingyan(0);
//                            rfm.updateByPrimaryKeySelective(rfg);
//                        }
//                        if(rf.getTouxian().equals("猛将")){
//                            rfg.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80)+rfg.getSjjinyan());
//                            rf.setTotajy(fljm.sumjiangcai(rfg.getLeve()+2));
//                            rfg.setJingyan(0);
//                            rfm.updateByPrimaryKeySelective(rfg);
//                        }
//                        if(rf.getTouxian().equals("元帅")){
//                            rfg.setSjjinyan((40*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80)+rfg.getSjjinyan());
//
//                            rf.setTotajy(fljm.sumyuanshuai(rfg.getLeve()+2));
//                            rfg.setJingyan(0);
//                            rfm.updateByPrimaryKeySelective(rfg);
//                        }
//                       CommonUtilAjax.sendAjaxList("sjmsg","副将获得"+60*jy*jy*num+"经验，恭喜你副将升到"+(jy+1)+"级",request,response);
//
//                    }
//                    if(rfg.getSjjinyan()>0){
//
//
//                        rfg.setSjjinyan(rfg.getSjjinyan()-60*jy*jy*num);
//                        rfm.updateByPrimaryKeySelective(rfg);
//
//
//                    }
//                    CommonUtilAjax.sendAjaxList("msg","副将获得"+60*jy*jy*num+"经验",request,response);
//
//
//                }
//            }
//
//        }else{
//            CommonUtilAjax.sendAjaxList("msg","你身上携带的数量不足，请重新输入。",request,response);
//
//
//        }
//
//
//
//
//
//        return null;
//    }
//
//    @RequestMapping(value = "/totalnum", method = { RequestMethod.GET, RequestMethod.POST })
//
//    public  String totalnum(@RequestParam(value = "jjid",required = false)Integer jjid,@RequestParam(value = "id",required = false)Integer id,Model model)throws Exception {
//        model.addAttribute("id",id);
//        model.addAttribute("jid",jjid);
//        return "副将/使用数量";
//    }
//
//    @RequestMapping(value = "/approlefj", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public  String approlefj(HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
//        Integer roleid=(Integer)session.getAttribute("roleid");
//        if(roleid!=null){
//            RoleFujiang ff=new RoleFujiang();
//            ff.setRoleid(roleid);
//            ff.setStatus("战斗");
//            ff.setShuxing(1);//1表示未在驿馆
//
//            List<RoleFujiang> li=rfm.selectAll(ff);
//            if(li.size()>0){
//                CommonUtilAjax.sendAjaxList("jjun",li,request,response);
//
//            }
//
//
//
//        }
//
//        return null;
//    }
//}

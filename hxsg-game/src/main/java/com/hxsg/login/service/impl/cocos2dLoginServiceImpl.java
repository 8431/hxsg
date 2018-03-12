package com.hxsg.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.StringUtil;
import com.hxsg.CommonUtil.listener.Cocos2dHttpSessionListener;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.login.Login;
import com.hxsg.CommonUtil.util.ErrorType;
import com.hxsg.CommonUtil.util.MapUtil;
import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.AcountMapper;
import com.hxsg.Dao.NewRoleMapper;
import com.hxsg.Dao.RoleMapper;
import com.hxsg.Dao.RoleNewShuXingMapper;
import com.hxsg.login.service.cocos2dLoginService;
import com.hxsg.po.*;
import com.hxsg.system.dao.SystemNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by dlf on 2016/9/29.
 */
@Service("cocos2dLoginService")
public class cocos2dLoginServiceImpl implements cocos2dLoginService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    AcountMapper acountmapper;
    @Autowired
    RoleNewShuXingMapper rolenewshuxingmapper;
    @Autowired
    SystemNotification systemnotification;
    @Override
    public String checkRole(NewRole re) {
        String result =StatusNum.SUCCES;
        try {
            List<NewRole> liRole = newrolemapper.selectAll(re);
            if (liRole.size() > 0) {
                result = StatusNum.FAIL;
            }
        } catch (Exception e) {
            result =ErrorType.SERVERERROR;
            logger.error("service层--验证角色名或者账号名是否重复异常checkRole：" + e.getMessage());
        }
        return result;
    }
    @Override
    public String checkAcount(Acount re)  {
        String s= StatusNum.FAIL;
        try {
            Acount at= acountmapper.checkdAcount(re);
            if(at==null){
                s=StatusNum.SUCCES;
            }
        } catch (Exception e) {
            s=StatusNum.ERROR;
            e.printStackTrace();
        }

        return s;
    }
    @Override
    public String creatRole(NewRole re,HttpServletRequest request) {
        String result =StatusNum.FAIL;
        try {
            HttpSession session=request.getSession();
            Integer acount = (Integer) session.getAttribute("acount");;//获取用户IDsession
            NewRole r=new NewRole();
            r.setAccount(acount);
           List<NewRole>  li = newrolemapper.selectAll(r);
            if (li == null||li.size()<=3) {
//                气血=成长*等级（体质点+气血初值*0.8）
//                精力=成长*等级（体质点+气血初值*0.8）
//                攻击=(等级*成长*力量初值)/7+力量初值*成长*0.5+等级*成长*力量点*0.2
//                速度=成长*（速度值加敏捷点）
                NewRole n = new NewRole();
                String zhiye=re.getZhiye();
                String status="0";
                n.setTotaljing1(200);
                n.setTotalxue1(200);
                n.setAccount(acount);
                n.setZhiye(re.getZhiye());// 职业
                n.setLevel(1);// 等级
                n.setJin(88888);// 金
                n.setYin(1000000);// 银
                if(re.getRolename().length()>5){
                    n.setRolename(re.getRolename().substring(0, 6));
                }else{
                    n.setRolename(re.getRolename());
                }
                n.setZuobiao("新手村");
                n.setSex(re.getSex());// 性别
                n.setImg(re.getImg());
                n.setCreatedata(new Date());
                n.setChenghao("村民");
                n.setShengjijingyan(100);
                n.setKeyongds(4);
                n.setJinglids(1);
                n.setSududs(1);
                n.setQixueds(1);
                n.setGongjids(1);
                n.setSumds(0);
                n.setTotaljy(0);
                int a = newrolemapper.insertSelective(n);
                if (a > 0) {
                    insertRoleShuXing(zhiye, re.getId(), status);
                    Login ln= (Login) Constants.loginMap.get(request.getHeader("key"));
                    if(ln!=null){
                        ln.setRoleId( n.getId());
                        ln.setRoleName(n.getRolename());
                    }
                    result =StatusNum.SUCCES;

                }
            }else {
               logger.info("该账号只能建立3个角色："+acount);
            }



        } catch (Exception e) {
            result=StatusNum.ERROR;
            logger.error("创建角色异常" + e.getMessage());
        }
        return result;
    }

    public  void insertRoleShuXing(String zhiye, Integer roleid, String status) throws Exception {
        switch (zhiye){
            case "异人":{

                insertShuXing("命中率", roleid, 75, status);
                insertShuXing("暴击率",roleid,10,status);
                insertShuXing("反击率",roleid,10,status);
                insertShuXing("致命率",roleid,5,status);
                insertShuXing("法术暴",roleid,5,status);
                insertShuXing("反震率",roleid,10,status);
                insertShuXing("躲避率",roleid,5,status);
                insertShuXing("抗物理",roleid,0,status);
                insertShuXing("抗玄击",roleid,0,status);
                insertShuXing("抗围困",roleid,0,status);
                insertShuXing("抗扰乱",roleid,0,status);
                insertShuXing("抗封锁",roleid,0,status);
                insertShuXing("抗风沙",roleid,10,status);
                insertShuXing("抗妖火",roleid,10,status);
                insertShuXing("抗落雷",roleid,10,status);
                insertShuXing("抗毒术",roleid,10,status);
               //异人加点方式
                /**
                 * 命中率75   暴击率10
                 * 反击率10    致命率5
                 * 法术暴5     反震率10
                 * 躲避率5     抗物理0
                 * 抗玄击0     抗围困0
                 * 抗扰乱0     抗封锁0
                 * 抗风沙10     抗妖火10
                 * 抗落雷10     抗毒术10
                 */
                break;
            }
            case "文人":{
                /**
                 * 命中率80   暴击率10
                 * 反击率10    致命率5
                 * 法术暴0     反震率10
                 * 躲避率5     抗物理20
                 * 抗玄击0    抗围困10
                 * 抗扰乱10     抗封锁10
                 * 抗风沙0     抗妖火0
                 * 抗落雷0     抗毒术0
                 */
                insertShuXing("命中率",roleid,80,status);
                insertShuXing("暴击率",roleid,10,status);
                insertShuXing("反击率",roleid,10,status);
                insertShuXing("致命率",roleid,5,status);
                insertShuXing("法术暴",roleid,0,status);
                insertShuXing("反震率",roleid,10,status);
                insertShuXing("躲避率",roleid,5,status);
                insertShuXing("抗物理",roleid,0,status);
                insertShuXing("抗玄击",roleid,0,status);
                insertShuXing("抗围困",roleid,10,status);
                insertShuXing("抗扰乱",roleid,10,status);
                insertShuXing("抗封锁",roleid,10,status);
                insertShuXing("抗风沙",roleid,0,status);
                insertShuXing("抗妖火",roleid,0,status);
                insertShuXing("抗落雷",roleid,0,status);
                insertShuXing("抗毒术",roleid,0,status);

                break;
            }
            case "武士":{
                /**
                 * 命中率85   暴击率15
                 * 反击率15    致命率5
                 * 法术暴0     反震率15
                 * 躲避率5     抗物理20
                 * 抗玄击5     抗围困0
                 * 抗扰乱0     抗封锁0
                 * 抗风沙0     抗妖火0
                 * 抗落雷0     抗毒术0
                 */
                insertShuXing("命中率",roleid,85,status);
                insertShuXing("暴击率",roleid,15,status);
                insertShuXing("反击率",roleid,15,status);
                insertShuXing("致命率",roleid,5,status);
                insertShuXing("法术暴",roleid,0,status);
                insertShuXing("反震率",roleid,15,status);
                insertShuXing("躲避率",roleid,5,status);
                insertShuXing("抗物理",roleid,20,status);
                insertShuXing("抗玄击",roleid,5,status);
                insertShuXing("抗围困",roleid,0,status);
                insertShuXing("抗扰乱",roleid,0,status);
                insertShuXing("抗封锁",roleid,0,status);
                insertShuXing("抗风沙",roleid,0,status);
                insertShuXing("抗妖火",roleid,0,status);
                insertShuXing("抗落雷",roleid,0,status);
                insertShuXing("抗毒术",roleid,0,status);

                break;
            }
        }
    }

    private  void insertShuXing(String kx,Integer roleid,Integer tx,String status) throws Exception{
        RoleNewShuXing bx=new RoleNewShuXing();
        bx.setKangxing(kx);
        bx.setRoleid(roleid);
        if(bx.getKangxingtotal()==null){
            bx.setKangxingtotal(0);
        }
        bx.setStatus(status);
        bx.setKangxingtotal(bx.getKangxingtotal()+tx);
        rolenewshuxingmapper.insert(bx);
    }


    @Override
    public String creatAccount(Acount re) {
        String result =StatusNum.FAIL;
        try {
            Acount at= new Acount();
            at.setName(re.getName());
            String rts=checkAcount(at);
            if("true".equals(rts)){
                re.setLogintime(new Date());
                int rt = acountmapper.insertSelective(re);
                if (rt > 0) {
                    result =StatusNum.SUCCES;
                }
            }
        } catch (Exception e) {
            result= StatusNum.ERROR;
            logger.error("注册账号失败" + e.getMessage());
        }
        return result;
    }

    /**
     * 跳转到主界面
     * @param re

     * @return
     */
    @Override
    public Object selectRole(NewRole re,HttpServletRequest request)  {
        Object ot = null;
        try {
           HttpSession session= request.getSession();
            List<NewRole> li = (List<NewRole>) session.getAttribute("roleList");
            if(li.size()>0&&li!=null){
                for(NewRole n:li){
                    if(re.getId().equals(n.getId())){
                        //移除
                        String key=request.getHeader("key");
                        Login ln= (Login) Constants.loginMap.get(key);

//
//                        if(n.getId()==ln.getRoleId()){
//                            //利用通知用户账号在别处登录，被强制下线
//                            RoleFriendsMsg rf=new RoleFriendsMsg();
//                            rf.setMessage("该户账号在别处登录，您被强制下线");
//                            systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG204, rf}, n.getId().toString());
//                            //销毁之前的sesison
//
//
//                        }

                        ln.setRoleId( n.getId());
                        ln.setRoleName(n.getRolename());
//                        session.setAttribute("roleId", n.getId());
//                        session.setAttribute("roleName",n.getRolename());
                        //Cocos2dHttpSessionListener.HTTPSESSIONMAP.put(n.getId().toString(),session);
                        ot="success";
                        break;
                    }else{
                        ot="恶意攻击";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            ot="服务器出问题了";
        }
        return ot;
    }

    @Override
    public Object login(Acount re, HttpSession session) {
        Object obj = null;
        try {
            Acount ct= acountmapper.login(re);
            if (ct!=null) {
                NewRole ne = new NewRole();
                ne.setAccount(ct.getId());
                List<NewRole> li = newrolemapper.selectAll(ne);
                Login ln=new Login();
                if(!StringUtil.isEmpty(re.getKey())){
                    //不允许重复登录
                    if(Constants.loginMap.size()>0){
                    if(li!=null&&li.size()>0){
                        for(NewRole r:li){
                            ln.setRoleId(r.getId());
                                MapUtil.remove(Constants.loginMap,ln);
                                //通知该玩家被强制下线
                                RoleFriendsMsg rf=new RoleFriendsMsg();
                                rf.setMessage("该户账号在别处登录，您被强制下线");

                                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG204, rf}, r.getId().toString());
                            }
                        }
                    }

                    ln.setAcount(ct);
                    ln.setLiRole(li);
                    Constants.loginMap.put(re.getKey(),ln);


                }

                //session.setAttribute("acount",ct.getId());//设置账号session
                if (li.size() > 0 && li != null) {
                    obj ="selectRole";
                } else {
                    obj = "creatRole";//跳转到创建角色
                }
            }else{
                obj="nameError";
            }
        } catch (Exception e) {
            obj=StatusNum.ERROR;
            logger.error("登录异常:"+e.getMessage(),e);
        }
        return obj;
    }

    /**
     * 加载选择角色界面
     * @param session
     * @return
     * @throws Exception
     */
    @Override
    public List<NewRole> LoadSelectRole(HttpSession session){
        List<NewRole> li=null;
        try {
              // li = (List<NewRole>) session.getAttribute("roleList");
               li = (List<NewRole>) session.getAttribute("roleList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }
}

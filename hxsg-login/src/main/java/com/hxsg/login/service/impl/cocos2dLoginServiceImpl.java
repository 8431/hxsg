package com.hxsg.login.service.impl;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.StringUtil;
import com.hxsg.Dao.AcountMapper;
import com.hxsg.Dao.IHxsgBaseDao;
import com.hxsg.Dao.NewRoleMapper;
import com.hxsg.Dao.RoleNewShuXingMapper;
import com.hxsg.erroptype.LoginErrorType;
import com.hxsg.login.service.Cocos2dLoginService;
import com.hxsg.po.Acount;
import com.hxsg.po.NewRole;
import com.hxsg.po.RoleFriendsMsg;
import com.hxsg.po.RoleNewShuXing;
import com.hxsg.redisService.RedisDaoService;
import com.hxsg.systemdao.SystemNotification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by dlf on 2016/9/29.
 */
@Service("cocos2dLoginService")
public class cocos2dLoginServiceImpl implements Cocos2dLoginService {
    private Logger logger =Logger.getLogger(cocos2dLoginServiceImpl.class);
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    AcountMapper acountmapper;
    @Autowired
    RoleNewShuXingMapper rolenewshuxingmapper;
    @Autowired
    SystemNotification systemnotification;
    @Autowired
    RedisDaoService redisdaoservice;
    @Autowired
    IHxsgBaseDao   ihxsgbasedao;
    public String checkRole(NewRole re) throws CommonException{
        String result =LoginErrorType.SUCCESS.getCode();
        if(StringUtils.isEmpty(re.getRolename())){
            throw new CommonException( LoginErrorType.FAIL.getCode(),"角色名不能为空");
        }
        try {
            List<NewRole> liRole = newrolemapper.selectAll(re);
            if (liRole!=null&&liRole.size() > 0) {
                result = LoginErrorType.FAIL.getCode();
            }
        } catch (Exception e) {
            logger.error("service层--验证角色名或者账号名是否重复异常checkRole：" + e.getMessage());
            throw new CommonException( LoginErrorType.ERROR.getCode(),"验证角色名或者账号名是否重复异常checkRole");
        }
        return result;
    }
    @Override
    public String checkAcount(Acount re)throws CommonException  {
        if(StringUtils.isEmpty(re.getName())){
            throw new CommonException( LoginErrorType.FAIL.getCode(),"账号不能为空");
        }
        String result =LoginErrorType.SUCCESS.getCode();
        try {
            Acount at= acountmapper.checkdAcount(re);
            if(at!=null){
                result=LoginErrorType.FAIL.getCode();
            }
        } catch (Exception e) {
            logger.error("service层--验证角账号名是否重复异常checkRole：" + e.getMessage());
            throw new CommonException( LoginErrorType.ERROR.getCode(),"验证账号名是否重复异常checkRole");
        }
        return result;
    }
    @Override
    public String creatRole(NewRole re) throws CommonException{
        String result =LoginErrorType.FAIL.getCode();
        try {
            Integer acount = (Integer) redisdaoservice.get(re.getKeys());;//获取用户IDsession
            if(acount==null){
                throw new CommonException(LoginErrorType.ERROR.getCode(),"创建id失效，请重新创建");
            }

            NewRole r=new NewRole();
            r.setAccount(acount);
            List<NewRole>  li = newrolemapper.selectAll(r);
            if (li == null||li.size()<3) {
//                气血=成长*等级（体质点+气血初值*0.8）
//                精力=成长*等级（体质点+气血初值*0.8）
//                攻击=(等级*成长*力量初值)/7+力量初值*成长*0.5+等级*成长*力量点*0.2
//                速度=成长*（速度值加敏捷点）
                NewRole n = new NewRole();
                String zhiye=re.getZhiye();
                String status="0";
                n.setTotaljing1(20000);
                n.setTotalxue1(20000);
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
                    insertRoleShuXing(zhiye, n.getId(), status);
                    result =LoginErrorType.SUCCESS.getCode();
                    redisdaoservice.set(re.getKeys(),n.getId());
                }
            }else {
                result =LoginErrorType.FAIL.getCode();
            }

        } catch (Exception e) {
            logger.error("创建角色异常：" + e.getMessage());
            throw new CommonException( LoginErrorType.ERROR.getCode(),"创建角色异常");
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
    public String creatAccount(Acount re)   throws CommonException{
        String result =LoginErrorType.FAIL.getCode();
        try {
            Acount at= new Acount();
            at.setName(re.getName());
            String rts=checkAcount(at);
            if("000".equals(rts)){
                re.setLogintime(new Date());
                int rt = acountmapper.insertSelective(re);
                if (rt > 0) {
                    result =LoginErrorType.SUCCESS.getCode();;
                }
            }
        } catch (Exception e) {
            logger.error("注册账号失败" + e.getMessage());
            throw new CommonException( LoginErrorType.ERROR.getCode(),"注册账号失败");

        }
        return result;
    }

    /**
     * 跳转到主界面
     * @param re
     * @return
     */
    @Override
    public Object selectRole(NewRole re)  throws CommonException{
        Object ot = null;
        List<NewRole> li=null;
        try {
            Integer id= (Integer) redisdaoservice.get(re.getKeys());
             li = (List<NewRole>) redisdaoservice.get(re.getKeys()+":"+id+":SELECTROLE");
            if(li!=null&&li.size()>0){
                for(NewRole n:li){
                    if(re.getId().equals(n.getId())){
                        NewRole role=newrolemapper.queryTotalShuXingToRole(n.getId());
                        redisdaoservice.set("role:msg:"+n.getId(),role);
                        redisdaoservice.set(re.getKeys(),n.getId());
                        ot=LoginErrorType.SUCCESS.getCode();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new CommonException(LoginErrorType.ERROR.getCode(),"选择角色异常："+e.getMessage());
        }
        if(li==null||li.size()<1){
            throw new CommonException(LoginErrorType.ERROR.getCode(),"选择角色异常，缓存中没有该登录用户，请重新登录");
        }
        return ot;
    }

    @Override
    public Object login(Acount re) throws CommonException {
        Object obj = null;
           if(StringUtil.isEmpty(re.getKey())){
               throw new CommonException(LoginErrorType.ERROR.getCode(),"key不能为空");
           }
        try {
            Acount ct = acountmapper.login(re);

            if (ct != null) {
                NewRole ne = new NewRole();
                ne.setAccount(ct.getId());
                List<NewRole> li = newrolemapper.selectAll(ne);

                Set setKeys = redisdaoservice.keys("*" + ct.getId() + ":SELECTROLE");

                if (!setKeys.isEmpty()) {
                    //不允许重复登录
                    if (li != null && li.size() > 0) {
                        for (NewRole r : li) {
                            //通知该玩家被强制下线
                            RoleFriendsMsg rf = new RoleFriendsMsg();
                            rf.setMessage("该户账号在别处登录，您被强制下线");
                            systemnotification.sendSystemMsg(new Object[]{LoginErrorType.LOGINED.getCode(), rf}, r.getId().toString());
                        }
                    }

                    redisdaoservice.del(setKeys);
                    //需增加删除key
                }
                if (li != null && li.size() > 0) {
                    obj = LoginErrorType.SELECTROLE.getCode();
                    redisdaoservice.set(re.getKey() + ":" + ct.getId() + ":SELECTROLE", li);
                } else {
                    obj = LoginErrorType.CREATEROLE.getCode();//跳转到创建角色

                }
                redisdaoservice.set(re.getKey(), ct.getId(), 1000l*60);


            } else {
                obj = LoginErrorType.FAIL.getCode();
            }
        } catch (Exception e) {
            logger.error("登录异常" + e.getMessage());
            throw new CommonException(LoginErrorType.ERROR.getCode(), "登录异常");
        }
            return obj;


        }

    /**
     * 加载选择角色界面
     * @return
     * @throws Exception
     */
    @Override
    public List<NewRole> LoadSelectRole(String key)throws CommonException{
        List<NewRole> li=null;
        try {
           Integer id= (Integer) redisdaoservice.get(key);
            li = (List<NewRole>) redisdaoservice.get(key+":"+id+":SELECTROLE");

        } catch (Exception e) {
            logger.error("加载选择角色界面" + e.getMessage());
            throw new CommonException(LoginErrorType.ERROR.getCode(), "加载选择角色界面");
        }
        return li;
    }

    @Override
    public String checkVersion() throws CommonException {
        String re="";
         Map<String,Object> param=new HashMap<>();
        try {
            param.put("sql","select * from gjsg_version");
            List<Map<String,Object>> rpLi=ihxsgbasedao.exuSql(param);
            re= (String) rpLi.get(0).get("status");
        } catch (Exception e) {
            logger.error("校验游戏版本" + e.getMessage());
            throw new CommonException(LoginErrorType.ERROR.getCode(), "校验游戏版本");
        }
        return re;
    }


}

package com.hxsg.xunlian.service.impl;

import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.NewRoleMapper;
import com.hxsg.Dao.XunLianMapper;
import com.hxsg.po.NewRole;
import com.hxsg.po.XunLian;
import com.hxsg.system.dao.SystemNotification;
import com.hxsg.xunlian.service.Cocos2dXunLianService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by dlf on 2017/1/7.
 */
@Service("Cocos2dXunLianService")
public class Cocos2dXunLianServiceImpl implements Cocos2dXunLianService {
    private Logger logger = Logger.getLogger(Cocos2dXunLianServiceImpl.class);
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    XunLianMapper xunlianmapper;
    @Autowired
    SystemNotification systemnotification;
    @Override
    public List<XunLian> Training(String status) {
        List<XunLian> liX = null;
        String type = "0";
        try {
            if (!StringUtils.isEmpty(status)) {
                if (!"0".equals(status)) {
                    status = "1";
                    type = "1";
                }
                liX = xunlianmapper.queryTraining(status, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liX;
    }

    @Override
    public String StartTraining(Integer roleId, Integer time) {

        /**
         * 人物等级经验计算公式y=30x∧3+150x-80;
         */
        String msg = "操作无效";
        try {
            if (roleId != null && time != null && time <= 10 && time >= 1) {
                XunLian x = xunlianmapper.selectByPrimaryKey(1);
                int tl = 20 * Integer.parseInt(x.getType());
                XunLian xla1 = new XunLian();
                xla1.setRoleid(roleId);
                List<XunLian> liXla = xunlianmapper.selectAll(xla1);
                NewRole role = newrolemapper.selectByPrimaryKey(roleId);
                int level = role.getLevel();
                int jngYan = (23 * level * level * time * (1 + time / 10));

                if (role.getTilizhi() != null && role.getTilizhi() >= tl) {
                    int jin = 60;
                    if (level >= 120) {
                        jin = 140;
                    } else if (level >= 100) {
                        jin = 120;
                    } else if (level >= 71) {
                        jin = 100;
                    } else if (level >= 40) {
                        jin = 80;
                    } else if (level >= 1) {
                        jin = 60;
                    }
                    if (role.getJin() >= jin * time) {
                        if (liXla != null && liXla.size() > 0) {
                            final XunLian xla = liXla.get(0);
                            if ("1".equals(xla.getStatus())) {
                                //可以接受训练
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(c.getTimeInMillis() + time * 60 * 60 * 1000);
                                xla.setDate(c.getTime());
                                xla.setJingyan(xla.getJingyan() + jngYan);
                                xla.setStatus("0");
                                xla.setType("0");
                                xunlianmapper.updateByPrimaryKeySelective(xla);
                                Timer t = new Timer();
                                TimerTask task = new TimerTask() {
                                    @Override
                                    public void run() {
                                        //需要增加定时器处理每次重启服务器的经验决算
                                        System.out.println("------执行结算经验操作update:" + new Date());// 此处可以插入自己想运行的代码片段
                                        xla.setStatus("1");
                                        try {
                                            xunlianmapper.updateByPrimaryKeySelective(xla);
                                        } catch (Exception e) {
                                            logger.error("定时器结算训练场经验异常:" + e.getMessage());
                                        }


                                    }
                                };

                                t.schedule(task, c.getTime());
                            } else {
                                //已经在训练了
                                msg = "已处在训练中，请等待训练结束";
                                return msg;
                            }
                        } else {
                            //第一次直接插入
                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(c.getTimeInMillis() + time * 60 * 60 * 1000);
                            final XunLian xl = new XunLian();
                            xl.setRoleid(role.getId());
                            xl.setDate(c.getTime());
                            xl.setJingyan(jngYan);
                            xl.setStatus("0");
                            xl.setType("0");
                            xunlianmapper.insertSelective(xl);
                            Timer t = new Timer();
                            TimerTask task = new TimerTask() {
                                @Override
                                public void run() {
                                    xl.setStatus("1");
                                    try {
                                        xunlianmapper.updateByPrimaryKeySelective(xl);
                                    } catch (Exception e) {
                                        logger.error("定时器结算训练场经验异常:" + e.getMessage());
                                    }
                                }
                            };

                            t.schedule(task, c.getTime());
                        }
                        //扣除体力值
                        role.setTilizhi(role.getTilizhi() - tl);
                        //扣除金砖
                        role.setJin(role.getJin() - jin * time);
                        newrolemapper.updateByPrimaryKeySelective(role);
                        msg = "正在接受训练,本次可获得经验" + jngYan;
                        return msg;
                    } else {
                        msg = "金砖不足，无法训练";
                    }

                } else {
                    msg = "体力值不够无法训练";
                    return msg;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return msg;
    }

    @Override
    public XunLian queryJingYan(Integer roleId) {
        XunLian xn=null;
        try {
            if (roleId != null) {
                XunLian x = new XunLian();
                x.setRoleid(roleId);
               List<XunLian> liX=xunlianmapper.selectAll(x);
                if(liX!=null&&liX.size()>0){
                    xn=liX.get(0);
                    xn.setStatus(String.valueOf(xn.getDate().getTime()));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return xn;
    }

    @Override
    public String getJingYan(Integer roleId) throws Exception {
        String msg="训练时间未结束，无法领取";
            if(roleId!=null){
                XunLian x=new XunLian();
                x.setRoleid(roleId);
                x.setStatus("1");
                x.setType("0");
                List<XunLian> liX=xunlianmapper.selectAll(x);
                if(liX!=null&&liX.size()>0){
                    XunLian xn=liX.get(0);
                    Integer xlJy=xn.getJingyan();
                    expConversionLevel(roleId,xlJy);
                    msg="恭喜您成功领取经验:"+xlJy;
                    //将训练清零
                    xn.setStatus("1");
                    xn.setType("1");
                    xn.setJingyan(0);
                    xunlianmapper.updateByPrimaryKeySelective(xn);
                }
            }
        return msg;
    }

    @Override
    public void expConversionLevel(Integer roleId, Integer jingYan) throws Exception {
         NewRole role = newrolemapper.queryTotalShuXingToRole(roleId);
        if(role!=null){
            int level=role.getLevel();
            Integer roleJy=role.getJingyan()+jingYan;
            int[] js=jieSuanJy(level,roleJy);
            int nowLevel=js[0];
            int le=nowLevel-level;
            int exp=js[1];
            NewRole r=new NewRole();
                r.setId(roleId);
                r.setLevel(nowLevel);
                r.setJingyan(exp);
                r.setKeyongds(role.getKeyongds()+le*4);
                r.setQixueds(role.getQixueds()+le);
                r.setJinglids(role.getJinglids()+le);
                r.setGongjids(role.getGongjids()+le);
                r.setSududs(role.getSududs()+le);
            newrolemapper.updateByPrimaryKeySelective(r);
            if(le>0){
                //推送升级动画，恭喜您升级到XX级
           systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202,"恭喜您升级到"+nowLevel+"级"},roleId.toString());
            }
        }
    }

    private   int[] jieSuanJy(Integer level ,Integer jingYan) {
        int[] result=new int[2];
        int nextLevel=level+1;
        int levelJy=30*nextLevel*nextLevel*nextLevel+150*nextLevel-80;
        if(jingYan>=levelJy){
            return jieSuanJy(nextLevel,jingYan-levelJy);
        }else{
            result[0]=level;
            result[1]=jingYan;
            return result;
        }
    }
    public static void main(String[]aa){
        String a="2016/22/222.xml";
        System.out.println(a.substring(a.lastIndexOf("/"),a.length()));
    }
}

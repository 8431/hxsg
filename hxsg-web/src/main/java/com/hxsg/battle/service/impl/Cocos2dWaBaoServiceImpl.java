package com.hxsg.battle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.*;
import com.hxsg.battle.service.Cocos2dWaBaoService;
import com.hxsg.po.*;
import com.hxsg.system.dao.SystemNotification;
import com.hxsg.wupin.service.cocos2dWuPinService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2016/11/23.
 */
@Service("Cocos2dWaBaoService")
public class Cocos2dWaBaoServiceImpl implements Cocos2dWaBaoService {
    private Logger logger =Logger.getLogger(Cocos2dWaBaoServiceImpl.class);

    @Autowired
    cocos2dWuPinService  cocos2dwupinservice;
    @Autowired
    WaBaoMapper wbm;
    @Autowired
    WaBaoMiaoShuMapper wbmsm;
    @Autowired
    ZaWuMiaoShuMapper zwmsm;
    @Autowired
    RoleWuPinMapper rolewupinmapper;
    @Autowired
    SystemNotification systemnotification;
    @Autowired
    WorldMsgMapper worldmsgmapper;
    @Override
    public String initWaBao(Integer roleid, String roleName,String name, Integer num)  {
        String msg= StatusNum.FAIL;
        try {
            RoleWuPin rn=new RoleWuPin();
            rn.setRoleid(roleid);
            rn.setName(name);
            List<RoleWuPin> lirn=rolewupinmapper.selectAll(rn);
            //重复读取-------不够简洁
            Boolean result=cocos2dwupinservice.xiaoHaoWuPin(roleid,lirn.get(0).getId(),num);
            if(result){
                //初始化挖宝数据
                WaBao wbo=new WaBao();
                wbo.setRoleid(roleid);
                List<WaBao> li = wbm.selectAll(wbo);
                if(li.size()>0){
                    WaBao wb=li.get(0);
                    if (wb != null) {
                            wb.setW1(1);
                            wb.setW2(1);
                            wb.setW3(1);
                            wb.setW4(1);
                            wb.setW5(1);
                            wb.setW6(1);
                            wb.setW7(1);
                            wb.setW8(1);
                            wb.setW9(1);
                            wb.setNum(50);
                            wb.setStatus("1");
                            wbm.updateByPrimaryKeySelective(wb);
                        }
                }else{
                        WaBao wo = new WaBao();
                        wo.setRoleid(roleid);
                        wo.setRolename(roleName);
                        wo.setW1(1);
                        wo.setW2(1);
                        wo.setW3(1);
                        wo.setW4(1);
                        wo.setW5(1);
                        wo.setW6(1);
                        wo.setW7(1);
                        wo.setW8(1);
                        wo.setW9(1);
                        wo.setNum(50);
                        wo.setStatus("1");
                        wo.setDate(new Date());
                        wo.setType("0");
                        wbm.insertSelective(wo);
                }
                msg=StatusNum.SUCCES;
            }
        } catch (Exception e) {
            msg=StatusNum.ERROR;
        }
        return msg;
    }

    @Override
    public WaBao loadWaBaoCanvas(Integer roleid,String roleName){
        WaBao wbo= null;
        try {
            wbo = new WaBao();
            wbo.setRoleid(roleid);
            List<WaBao> li = wbm.selectAll(wbo);
            if(li.size()>0&&li!=null){
                wbo=li.get(0);
            }else{
                WaBao wo = new WaBao();
                wo.setRoleid(roleid);
                wo.setRolename(roleName);
                wo.setW1(0);
                wo.setW2(0);
                wo.setW3(0);
                wo.setW4(0);
                wo.setW5(0);
                wo.setW6(0);
                wo.setW7(0);
                wo.setW8(0);
                wo.setW9(0);
                wo.setNum(0);
                wo.setStatus("0");
                wo.setDate(new Date());
                wo.setType("0");
                wbm.insertSelective(wo);
                wbo=wo;
            }
        } catch (Exception e) {
            logger.error("loadWaBaoCanvas加载挖宝界面数据异常："+e.getMessage());
            e.printStackTrace();
        }
        return wbo;
    }

    /**
     * 挖宝产出物品需要重写，必须根据物品难度系数设计算法实现
     * @param roleid
     * @param roleName
     * @param type
     * @param wb
     * @return
     */
    @Override
    public String startWaBao(Integer roleid,String roleName,String type, WaBao wb) {
        String msg="";
        int gl=0;
        try {
            WaBao wbo=wbm.selectByPrimaryKey(wb.getId());
            if(roleid.equals(wbo.getRoleid())){
                gl=(int)((Math.random()*100)+1);
                Boolean key=false;
                if(wb.getW1()!=null&&wb.getW1().equals(0)&&wbo.getW1()!=0){
                    wbo.setW1(0);
                    key=true;
                }
                if(wb.getW2()!=null&&wb.getW2().equals(0)&&wbo.getW2()!=0){
                    wbo.setW2(0);
                    key=true;
                }
                if(wb.getW3()!=null&&wb.getW3().equals(0)&&wbo.getW3()!=0){
                    wbo.setW3(0);
                    key=true;
                }
                if(wb.getW4()!=null&&wb.getW4().equals(0)&&wbo.getW4()!=0){
                    wbo.setW4(0);
                    key=true;
                }
                if(wb.getW5()!=null&&wb.getW5().equals(0)&&wbo.getW5()!=0){
                    wbo.setW5(0);
                    key=true;
                }
                if(wb.getW6()!=null&&wb.getW6().equals(0)&&wbo.getW6()!=0){
                    wbo.setW6(0);
                    key=true;
                }
                if(wb.getW7()!=null&&wb.getW7().equals(0)&&wbo.getW7()!=0){
                    wbo.setW7(0);
                    key=true;
                }
                if(wb.getW8()!=null&&wb.getW8().equals(0)&&wbo.getW8()!=0){
                    wbo.setW8(0);
                    key=true;
                }
                if(wb.getW9()!=null&&wb.getW9().equals(0)){
                    wbo.setW9(wb.getW9());
                    key=true;
                }
                wbm.updateByPrimaryKeySelective(wbo);
                //刷新继续挖宝
                WaBao sxwb=wbm.selectByPrimaryKey(wb.getId());
                if(sxwb.getW1()==0&&sxwb.getW2()==0&&sxwb.getW3()==0&&sxwb.getW4()==0&&sxwb.getW5()==0&&sxwb.getW6()==0&&sxwb.getW7()==0&&sxwb.getW8()==0&&sxwb.getW9()==0){
                    msg="sx";
                }
                if(gl!=0&&gl>80&&key){
                    int wbgl=(int)((Math.random()*2));
                    int rand=0;
                    if(wbgl==0){
                        rand =(int)((Math.random()*13)+1);
                    }
                    if(wbgl==1){
                        rand =(int)((Math.random()*8)+25);

                    }
                    ZaWuMiaoShu zs=null;
                    zs=zwmsm.selectByPrimaryKey(rand);
                    RoleWuPin rw=new RoleWuPin();
                    rw.setName(zs.getName());
                    rw.setRoleid(roleid);
                    List<RoleWuPin> li=rolewupinmapper.selectAll(rw);
                    //带决绝
                    if(li.size()>0&&li!=null){
                        Integer num=li.get(0).getNum();
                        if(num==null){
                            num=0;
                        }
                        li.get(0).setNum(num+1);
                        rolewupinmapper.updateByPrimaryKeySelective(li.get(0));
                    }else{
                        rw.setWupinid(rand);
                        rw.setNum(1);
                        rw.setType1(zs.getType());
                        rw.setType2("杂物");
                        rw.setCustom1(zs.getStatus());
                        rolewupinmapper.insertSelective(rw);
                    }
                    WaBaoMiaoShu ws=new WaBaoMiaoShu();
                    ws.setRoleid(roleid);
                    ws.setRolename(roleName);
                    ws.setDate(new Date());
                    ws.setStatus("0");
                    ws.setBaoid(zs.getId());
                    ws.setBaoname(zs.getName());
                    wbmsm.insertSelective(ws);
                    //开启webscoket通知界面刷新.未完成
                    msg="恭喜你挖到了【"+zs.getName()+"】";
                    if("宝石珍矿".equals(zs.getName())){
                        //向所有在线挖加聊天【世】推送消息
                        WorldMsg wg=new WorldMsg();
                        wg.setRolename("系统");
                        wg.setType("0");
                        wg.setData(new Date());
                        wg.setMessage(roleName+"(id:"+roleid+")"+"在宝石矿洞中挖到"+zs.getName());
                        wg.setRoleid(999);
                        int worldResult=worldmsgmapper.insert(wg);
                        if(worldResult>0){
                            /**    通知主界面-世界频道刷新消息---*/
                            List<WorldMsg> liMsg= worldmsgmapper.queryWorldMsg();
                            systemnotification.sendSystemMsg(new Object[]{4,liMsg});
                        }
                    }
                    //向挖宝信息界面推送最新挖宝消息
                    systemnotification.sendSystemMsg(new Object[]{1005,ws});

                }else{
                    String a="很遗憾，你与珍宝擦肩而过！";
                    String b="加油，宝矿中心快挖到了";
                    String c="挖空了，运气真差！";
                    String d="挖空，不要放弃，宝藏就在身边";
                    String e="挖空，该死的，就快挖到了。";
                    int rand=(int)((Math.random()*5)+1);
                    switch (rand){
                        case 1:{
                            msg=a;
                            break;
                        }
                        case 2:{
                            msg=b;
                            break;
                        }
                        case 3:{
                            msg=c;
                            break;
                        }
                        case 4:{
                            msg=d;
                            break;
                        }
                        case 5:{
                            msg=e;
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public Boolean nextWaBao(Integer roleid, Integer id)  {
        Boolean result=false;
        try {
            WaBao wb = wbm.selectByPrimaryKey(id);
            if(wb.getRoleid().equals(roleid)){
                if(wb.getNum()==0){
                    wb.setStatus("0");
                    wbm.updateByPrimaryKeySelective(wb);
                }else{
                    wb.setNum(wb.getNum()-1);
                    wb.setW1(1);
                    wb.setW2(1);
                    wb.setW3(1);
                    wb.setW4(1);
                    wb.setW5(1);
                    wb.setW6(1);
                    wb.setW7(1);
                    wb.setW8(1);
                    wb.setW9(1);
                    int a=   wbm.updateByPrimaryKeySelective(wb);
                    result=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<WaBaoMiaoShu> getWaBaoMiaoShu() throws Exception {
        return wbmsm.getWbByTime();
    }
}

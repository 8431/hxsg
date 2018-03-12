package com.hxsg.gchang.controller.util;

import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.*;
import com.hxsg.gchang.controller.yule.service.impl.Cocos2dGcServiceImpl;
import com.hxsg.po.*;
import com.hxsg.system.dao.SystemNotification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2016/1/20.
 */
@Service("Cocos2dTotalSum")
public class Cocos2dTotalSum {
    private Logger logger =Logger.getLogger(Cocos2dTotalSum.class);
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    RoleFriendsMsgMapper rfmm;
    @Autowired
    YlDaXiaoMapper ydxm;
    @Autowired
    YlDxXqMapper ydxqm;
    @Autowired
    YouJianMapper yjm;
    @Autowired
    YouJianWuPinMapper yjwpm;
    @Autowired
    RoleFujiangMapper rfm;
    @Autowired
    jiangjunMapper jjm;
    @Autowired
    SystemNotification systemnotification;
    @Autowired
    WorldMsgMapper worldmsgmapper;
    public synchronized void jiesuan(){
        //a.刷新赌场开盅数据
        try {
            List<YlDaXiao>  li2 = ydxm.getAll();
            li2.get(0).setStatus("1");
            ydxm.updateByPrimaryKeySelective( li2.get(0));
            List<YlDaXiao> lis=ydxm.getAll();
            Cocos2dGcServiceImpl.num=lis.get(0).getId();//传递给 GcController类num值
            //GcController.t_date=new Date().getTime();//传递给 GcController时间
            //c.将上把数据传入
            Cocos2dGcServiceImpl.ydx=ydxm.getDxAgo();
            //b.结算赌场数据，发送银两给玩家
            YlDxXq yq=new YlDxXq();
            yq.setStatus(0);
            List<YlDxXq> li=ydxqm.selectAll(yq);
            Cocos2dGcServiceImpl.totalsumjin=0;
            Cocos2dGcServiceImpl.totalsumyin=0;

            if(li.size()>0&&li!=null){
                Integer nums=li.get(0).getNum();
                YlDaXiao yx=ydxm.selectByPrimaryKey(nums);
                String results=yx.getResult();
                Integer sumyin=0;
                Integer sumjin=0;
                for(int i=0;i<li.size();i++){
                    Integer roleid=li.get(i).getRoleid();
                    Integer yin=li.get(i).getYin();
                    Integer jin=li.get(i).getJin();
                    String result=li.get(i).getResult();
                    Integer totaljin=0;
                    Integer totalyin=0;

                    if(results.equals(result)){//豹子
                        if(yin!=0&&yin!=null){
                            NewRole re=newrolemapper.selectByPrimaryKey(roleid);
                            re.setYin(re.getYin()+yin*10);
                            newrolemapper.updateByPrimaryKeySelective(re);
                            totalyin+=yin*10;
                            sumyin+=yin*10;
                        }
                        if(jin!=0&&jin!=null){
                            NewRole re=newrolemapper.selectByPrimaryKey(roleid);
                            re.setJin(re.getJin()+jin*10);
                            newrolemapper.updateByPrimaryKeySelective(re);
                            totaljin+=jin*10;
                            sumjin+=jin*10;
                        }
                        li.get(i).setStatus(1);
                        li.get(i).setJieguo("赢");
                        ydxqm.updateByPrimaryKeySelective( li.get(i));

                    }
                    if(results.contains(result)&&result.length()==1){//大小单双
                        if(yin!=0&&yin!=null){
                            NewRole re=newrolemapper.selectByPrimaryKey(roleid);
                            re.setYin(re.getYin()+yin*2);
                            newrolemapper.updateByPrimaryKeySelective(re);
                            totalyin+=yin*2;
                            sumyin+=yin*2;
                        }
                        if(jin!=0&&jin!=null){
                            NewRole re=newrolemapper.selectByPrimaryKey(roleid);
                            re.setJin(re.getJin()+jin*2);
                            newrolemapper.updateByPrimaryKeySelective(re);
                            totaljin+=jin*2;
                            sumjin+=jin*2;
                        }
                        li.get(i).setStatus(1);
                        li.get(i).setJieguo("赢");
                        ydxqm.updateByPrimaryKeySelective( li.get(i));
                    }
                    if(results.indexOf(result)==-1){
                        li.get(i).setJieguo("输");
                        ydxqm.updateByPrimaryKeySelective( li.get(i));
                    }

                   li.get(i).setStatus(1);
                    ydxqm.updateByPrimaryKeySelective( li.get(i));
                }

                List<YlDxXq> ydxLi=ydxqm.queryGroupBy(li2.get(0).getId());
                if(ydxLi!=null&&ydxLi.size()>0){
                    for(YlDxXq y:ydxLi){
                        RoleFriendsMsg rg=new RoleFriendsMsg();
                        int jin=0;
                        int yin=0;
                        if("豹子".equals(y.getResult())){
                            jin=y.getJin()*10;
                            yin=y.getYin()*10;
                        }else{
                            jin=y.getJin()*2;
                            yin=y.getYin()*2;
                        }
                        rg.setRoleid(y.getRoleid());
                        rg.setFriendid(1000);
                        rg.setData(new Date());
                        rg.setType("通知");
                        if("赢".equals(y.getJieguo())){
                            rg.setMessage("本期开出"+results+"!恭喜你在赌场赢得"+jin+"金"+yin+"银");
                        }else{
                            rg.setMessage("本期开出"+results+"!很遗憾您输了"+jin/2+"金"+yin/2+"银");
                        }
                        rfmm.insertSelective(rg);

                        systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202,rg}, rg.getRoleid().toString());
                    }
                }

                Cocos2dGcServiceImpl.totalsumjin=sumjin;
                Cocos2dGcServiceImpl.totalsumyin=sumyin;
                WorldMsg wg=new WorldMsg();
                wg.setData(new Date());
                wg.setRoleid(999);
                wg.setType("0");
                wg.setRolename("系统");
                wg.setMessage("本期猜猜猜 开"+yx.getNum1()+","+yx.getNum2()+","+yx.getNum3()+","+results+"。玩家共赢的"+sumjin+"金"+sumyin+"银");
                worldmsgmapper.insertSelective(wg);
                List<WorldMsg> worldli=worldmsgmapper.queryWorldMsg();//世界
                systemnotification.sendSystemMsg(new Object[]{"4",worldli});
            }
            Cocos2dGcServiceImpl.times=(5*60*1000)-(30*1000);
        } catch (Exception e) {
            Cocos2dGcServiceImpl.times=(5*60*1000)-(30*1000);
            logger.error("结算赌场数据异常");
            e.printStackTrace();
        }

        }
        //邮件物品返还
        public   void ChaEmail(){


        }
}

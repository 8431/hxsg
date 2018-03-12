//package com.hxsg.gchang.controller;
//
//import com.hxsg.Dao.RoleFriendsMsgMapper;
//import com.hxsg.Dao.RoleMapper;
//import com.hxsg.Dao.YlDaXiaoMapper;
//import com.hxsg.Dao.YlDxXqMapper;
//import com.hxsg.po.Role;
//import com.hxsg.po.RoleFriendsMsg;
//import com.hxsg.po.YlDaXiao;
//import com.hxsg.po.YlDxXq;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by dlf on 2016/1/20.
// */
//@Service
//public class caipiao {
//    @Autowired
//    RoleMapper rm;
//    @Autowired
//    RoleFriendsMsgMapper rfmm;
//    @Autowired
//    YlDaXiaoMapper ydxm;
//    @Autowired
//    YlDxXqMapper ydxqm;
//
//    public void jiesuan(){
//        //a.刷新赌场开盅数据
//        List<YlDaXiao> li2=ydxm.getAll();
//        li2.get(0).setStatus("1");
//        ydxm.updateByPrimaryKeySelective( li2.get(0));
//        List<YlDaXiao> lis=ydxm.getAll();
//        GcController.num=lis.get(0).getId();//传递给 GcController类num值
//        //GcController.t_date=new Date().getTime();//传递给 GcController时间
//        //c.将上把数据传入
//        GcController.ydx=ydxm.getDxAgo();
//        //b.结算赌场数据，发送银两给玩家
//        YlDxXq yq=new YlDxXq();
//        yq.setStatus(0);
//        List<YlDxXq> li=ydxqm.selectAll(yq);
//        GcController.totalsumjin=0;
//        GcController.totalsumyin=0;
//        if(li.size()>0){
//            Integer nums=li.get(0).getNum();
//            YlDaXiao yx=ydxm.selectByPrimaryKey(nums);
//            String results=yx.getResult();
//            Integer sumyin=0;
//            Integer sumjin=0;
//            for(int i=0;i<li.size();i++){
//
//                Integer roleid=li.get(i).getRoleid();
//                Integer yin=li.get(i).getYin();
//                Integer jin=li.get(i).getJin();
//                String result=li.get(i).getResult();
//                Integer totaljin=0;
//                Integer totalyin=0;
//                if(results.indexOf(result)==-1){
//                    li.get(i).setJieguo("输");
//                    ydxqm.updateByPrimaryKeySelective( li.get(i));
//
//                }
//                if(results.equals(result)){//豹子
//
//
//                    if(yin!=0&&yin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setYin(re.getYin()+yin*10);
//                        rm.updateByPrimaryKeySelective(re);
//                        totalyin+=yin*10;
//                        sumyin+=yin*10;
//
//                    }
//                    if(jin!=0&&jin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setYin(re.getJin()+jin*10);
//                        rm.updateByPrimaryKeySelective(re);
//                        totaljin+=jin*10;
//                        sumjin+=jin*10;
//                    }
//                    li.get(i).setStatus(1);
//                    li.get(i).setJieguo("赢");
//                    ydxqm.updateByPrimaryKeySelective( li.get(i));
//                    RoleFriendsMsg rg=new RoleFriendsMsg();
//                    rg.setRoleid(li.get(i).getRoleid());
//                    rg.setFriendid(1000);
//                    rg.setMessage("本期开出"+results+"!恭喜你在赌场赢得"+totaljin+"金"+totalyin+"银");
//                    rg.setData(new Date());
//                    rg.setType("通知");
//                    int a=rfmm.insertSelective(rg);
//                }
//                if(results.contains(result)&&result.length()==1){//大小单双
//
//
//                    if(yin!=0&&yin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setYin(re.getYin()+yin*2);
//                        rm.updateByPrimaryKeySelective(re);
//                        totalyin+=yin*2;
//                        sumyin+=yin*2;
//
//                    }
//                    if(jin!=0&&jin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setYin(re.getJin()+jin*2);
//                        rm.updateByPrimaryKeySelective(re);
//                        totaljin+=jin*2;
//                        sumjin+=jin*2;
//                    }
//                    li.get(i).setStatus(1);
//                    li.get(i).setJieguo("赢");
//                    ydxqm.updateByPrimaryKeySelective( li.get(i));
//                    RoleFriendsMsg rg=new RoleFriendsMsg();
//                    rg.setRoleid(li.get(i).getRoleid());
//                    rg.setFriendid(1000);
//                    rg.setMessage("本期开出"+results+"!恭喜你在赌场赢得"+totaljin+"金"+totalyin+"银");
//                    rg.setData(new Date());
//                    rg.setType("通知");
//                    int a=rfmm.insertSelective(rg);
//                }
//                li.get(i).setStatus(1);
//                ydxqm.updateByPrimaryKeySelective( li.get(i));
//            }
//            GcController.totalsumjin=sumjin;
//            GcController.totalsumyin=sumyin;
//        }
//        GcController.times=(5*60*1000)-(30*1000);
//
//        }
//
//
//
//}

//package com.hxsg.gchang.controller.util;
//
//import com.hxsg.Dao.*;
//import com.hxsg.gchang.controller.GcController;
//import com.hxsg.po.*;
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
//public class totalsum {
//    @Autowired
//    RoleMapper rm;
//    @Autowired
//    RoleFriendsMsgMapper rfmm;
//    @Autowired
//    YlDaXiaoMapper ydxm;
//    @Autowired
//    YlDxXqMapper ydxqm;
//    @Autowired
//    YouJianMapper yjm;
//    @Autowired
//    YouJianWuPinMapper yjwpm;
//    @Autowired
//    RoleFujiangMapper rfm;
//    @Autowired
//    jiangjunMapper jjm;
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
//                Integer roleid=li.get(i).getRoleid();
//                Integer yin=li.get(i).getYin();
//                Integer jin=li.get(i).getJin();
//                String result=li.get(i).getResult();
//                Integer totaljin=0;
//                Integer totalyin=0;
//
//                if(results.equals(result)){//豹子
//                    if(yin!=0&&yin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setYin(re.getYin()+yin*10);
//                        rm.updateByPrimaryKeySelective(re);
//                        totalyin+=yin*10;
//                        sumyin+=yin*10;
//                    }
//                    if(jin!=0&&jin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setJin(re.getJin()+jin*10);
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
//                    if(yin!=0&&yin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setYin(re.getYin()+yin*2);
//                        rm.updateByPrimaryKeySelective(re);
//                        totalyin+=yin*2;
//                        sumyin+=yin*2;
//                    }
//                    if(jin!=0&&jin!=null){
//                        Role re=rm.selectByPrimaryKey(roleid);
//                        re.setJin(re.getJin()+jin*2);
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
//                if(results.indexOf(result)==-1){
//                    li.get(i).setJieguo("输");
//                    ydxqm.updateByPrimaryKeySelective( li.get(i));
//                    RoleFriendsMsg rg=new RoleFriendsMsg();
//                    rg.setRoleid(li.get(i).getRoleid());
//                    rg.setFriendid(1000);
//                    rg.setMessage("本期开出"+results+"!很遗憾您输了"+totaljin+"金"+totalyin+"银");
//                    rg.setData(new Date());
//                    rg.setType("通知");
//                    int a=rfmm.insertSelective(rg);
//
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
//
//
//        public   void ChaEmail(){
//           List<YouJian> yj=yjm.selectAllBytime();
//            if(yj!=null){
//                for(YouJian y:yj){
//                    Integer roleid=y.getRoleid();
//                    Integer yin=y.getYin();
//                   Role re= rm.selectByPrimaryKey(roleid);
//                    re.setYin(re.getYin()+yin);
//                    rm.updateByPrimaryKeySelective(re);//将银两退回给玩家吧
//                    //更新邮件状态为已退回
//                    y.setStatus("已退回");
//                    yjm.updateByPrimaryKey(y);
//
//                    YouJianWuPin yjp= new YouJianWuPin();
//                    yjp.setYoujianid(roleid);
//                    List<YouJianWuPin> li=yjwpm.selectAll(yjp);
//                    if(li.size()>0){
//                        for(YouJianWuPin yp:li){
//                           Integer wuid= yp.getWupinid();
//                            RoleFujiang rf=rfm.selectByPrimaryKey(wuid);
//                            //物品是副将时
//                            if(rf!=null&&rf.getFujiangname().equals(yp.getWupinnname())){
//                                yp.setStatus("删除");
//                                Integer result=yjwpm.updateByPrimaryKeySelective(yp);
//                                if(result>0){
//
//                                       rf.setZhen("3");
//                                        rfm.updateByPrimaryKeySelective(rf);
//
//                                }
//                            }else{
//                                yp.setStatus("删除");
//                                Integer result=yjwpm.updateByPrimaryKeySelective(yp);
//                                if(result>0){
//
//                                    Integer num=yp.getNum();
//                                    jiangjun jj=jjm.selectByPrimaryKey(wuid);
//                                    jj.setNum(jj.getNum()+num);
//
//                                    jjm.updateByPrimaryKeySelective(jj);
//
//                                }
//
//                            }
//
//
//                        }
//
//                    }
//                        //通知对方物品已退回
//                    RoleFriendsMsg rg=new RoleFriendsMsg();
//                    rg.setRoleid(y.getRoleid());
//                    rg.setFriendid(1000);
//                    rg.setMessage("您发送给【"+y.getReceivedname()+"】的邮件，对方在10分钟内未提取,系统已退回给您！");
//                    rg.setData(new Date());
//                    rg.setType("通知");
//                    int a=rfmm.insertSelective(rg);
//                }
//
//            }
//
//
//        }
//
//
//}

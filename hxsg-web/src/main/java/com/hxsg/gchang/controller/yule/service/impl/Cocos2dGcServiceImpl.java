package com.hxsg.gchang.controller.yule.service.impl;

import com.hxsg.Dao.*;
import com.hxsg.gchang.controller.yule.service.Cocos2dGcService;
import com.hxsg.po.NewRole;
import com.hxsg.po.Role;
import com.hxsg.po.YlDaXiao;
import com.hxsg.po.YlDxXq;
import com.hxsg.system.dao.SystemNotification;
import com.hxsg.vo.CasinoMsg;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.enterprise.inject.New;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/9/29.
 */
@Service("cocos2dGcService")
public class Cocos2dGcServiceImpl implements Cocos2dGcService {
    private Logger logger =Logger.getLogger(Cocos2dGcServiceImpl.class);
    public static Integer num=null;
    public static long t_date;
    public static Integer totalsumyin=0;
    public static Integer totalsumjin=0;
    public static YlDaXiao ydx =new YlDaXiao();
    public static long times=0;
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    RoleFriendsMsgMapper rolefriendsmsgmapper;
    @Autowired
    YlDaXiaoMapper yldaxiaomapper;
    @Autowired
    YlDxXqMapper yldxxqmapper;
    @Autowired
    SystemNotification systemnotification;
    @Override
    /**
     * 查询赌场赢家
     */
    public Object queryWinMoneyRole() {
        Object ot=null;
        try {
            Map<String, List<YlDxXq>> mp=new HashMap<String, List<YlDxXq>>();
            List<YlDxXq> liy = yldxxqmapper.TenWinRole(num-1);
            List<YlDxXq> lij=yldxxqmapper.TenWinRoleJ(num-1);
            mp.put("yin",liy);
            mp.put("jin",lij);
            ot=mp;
        } catch (Exception e) {
            logger.error("赌场-娱乐场-赢家页面查询异常："+e.getMessage());
            e.printStackTrace();
        }
        return ot;
    }
    /**
     * 赚钱排行
     * @return
     */
    @Override
    public Object queryWinJinBang(){
        Object ot=null;
        try {
            List<YlDxXq> liy=yldxxqmapper.winJinBang();
            ot=liy;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("赌场-娱乐场-赚钱排行页面查询异常："+e.getMessage());
        }
        return ot;
    }
    /**
     * 历史查询
     * @return
     */
    @Override
    public Object queryCasinoHistory() {
        Object ot=null;
        try {
            List<YlDaXiao> hy=yldaxiaomapper.getHistory();
            ot=hy;
        } catch (Exception e) {
            logger.error("赌场-娱乐场-历史查询页面查询异常："+e.getMessage());
            e.printStackTrace();
        }
        return ot;
    }
    /**
     * 投注记录
     * @return
     */
    @Override
    public Object queryBettingRecord(Integer id) {
        Object ot=null;
        try {
            List<YlDxXq> hy=yldxxqmapper.touzhuhistory(id);
            ot=hy;
        } catch (Exception e) {
            logger.error("赌场-娱乐场-投注记录页面查询异常："+e.getMessage());
            e.printStackTrace();
        }
        return ot;
    }
    /**
     * 赌场开盘数据---可人为控制结果
     */
    @Override
    public void DataGeneration() throws Exception{
        for(int i=0;i<10000;i++){
            int a= (int)Math.round(Math.random()*5+1);
            int b= (int)Math.round(Math.random()*5+1);
            int c= (int)Math.round(Math.random()*5+1);
            YlDaXiao yd=new  YlDaXiao();
            yd.setNum1(a);
            yd.setNum2(b);
            yd.setNum3(c);
            StringBuffer result=new StringBuffer();
            int sum=a+b+c;
            if(a==b&&b==c){
                System.out.print(a);
                System.out.print(b);
                System.out.print(c);
                System.out.print("豹子");
                result.append("豹子");
            }else {
                if (sum >= 4 && sum <= 10) {
                    System.out.print(a);
                    System.out.print(b);
                    System.out.print(c);
                    System.out.print("小");
                    result.append("小");
                    if (sum==5 || sum == 7 || sum == 9 || sum == 11 || sum == 13 || sum == 15 || sum == 17) {
                        System.out.print(a);
                        System.out.print(b);
                        System.out.print(c);
                        System.out.print("单");
                        result.append("单");
                    }
                    if (sum == 4 || sum == 6 || sum == 8 || sum == 10 || sum == 12 || sum == 14 || sum == 16) {
                        System.out.print(a);
                        System.out.print(b);
                        System.out.print(c);
                        System.out.print("双");
                        result.append("双");
                    }
                }
                if (sum >= 11 && sum <= 17) {
                    System.out.print(a);
                    System.out.print(b);
                    System.out.print(c);
                    System.out.print("大");
                    result.append("大");

                    if (sum == 5 || sum == 7 || sum == 9 || sum == 11 || sum == 13 || sum == 15 || sum == 17) {
                        System.out.print(a);
                        System.out.print(b);
                        System.out.print(c);
                        System.out.print("单");
                        result.append("单");
                    }
                    if (sum == 4 || sum == 6 || sum == 8 || sum == 10 || sum == 12 || sum == 14 || sum == 16) {
                        System.out.print(a);
                        System.out.print(b);
                        System.out.print(c);
                        System.out.print("双");
                        result.append("双");
                    }
                }
            }
            yd.setResult(result.toString());
            yldaxiaomapper.insertSelective(yd);
        }
    }
    /**
     * 赌场加载页面数据
     */
    @Override
    public CasinoMsg queryCasinoMsg() {
        CasinoMsg cg=new CasinoMsg();
        try {
            YlDxXq yq = new YlDxXq();
            yq.setNum(num);
            yq.setResult("大");
            Integer dasum=yldxxqmapper.SumAllByJin(yq);
            Integer dasumyin=yldxxqmapper.SumAllByYin(yq);
            if(dasum==null){
                dasum=0;
            }
            if(dasumyin==null){
                dasumyin=0;
            }
            cg.setDaJin(dasum);
            cg.setDaYin(dasumyin);
            yq.setResult("小");
            Integer xiaosum=yldxxqmapper.SumAllByJin(yq);
            Integer xiaosumyin=yldxxqmapper.SumAllByYin(yq);
            if(xiaosum==null){
                xiaosum=0;
            }if(xiaosumyin==null){
                xiaosumyin=0;
            }
            cg.setXiaoJin(xiaosum);
            cg.setXiaoYin(xiaosumyin);
            yq.setResult("单");
            Integer dansum=yldxxqmapper.SumAllByJin(yq);
            Integer dansumyin=yldxxqmapper.SumAllByYin(yq);
            if(dansum==null){
                dansum=0;
            }if(dansumyin==null){
                dansumyin=0;
            }
            cg.setDanJin(dansum);
            cg.setDanYin(dansumyin);
            yq.setResult("双");
            Integer shuangsum=yldxxqmapper.SumAllByJin(yq);
            Integer shuangsumyin=yldxxqmapper.SumAllByYin(yq);
            if(shuangsum==null){
                shuangsum=0;
            }if(shuangsumyin==null){
                shuangsumyin=0;
            }
            cg.setShuangJin(shuangsum);
            cg.setShuangYin(shuangsumyin);
            yq.setResult("豹子");
            Integer baozisum=yldxxqmapper.SumAllByJin(yq);
            Integer baozisumyin=yldxxqmapper.SumAllByYin(yq);
            if(baozisum==null){
                baozisum=0;
            }if(baozisumyin==null){
                baozisumyin=0;
            }
            cg.setBaoZiJin(baozisum);
            cg.setBaoZiYin(baozisumyin);
            cg.setTotalSumJin(totalsumjin);
            cg.setTotalSumYin(totalsumyin);
            cg.setYldaxiao(ydx);
        } catch (Exception e) {
            logger.error("赌场-娱乐场-页面数据显示异常："+e.getMessage());
            e.printStackTrace();
        }
        return cg;
    }

    /**
     *  角色押注结果信息
     */
    @Override
    public String roleStakeResult(Integer roleId,YlDxXq yq) {
        String result= null;
        try {
            Integer roletotalyin=yldxxqmapper.YanZhiByYin(roleId);
            Integer roletotaljin=yldxxqmapper.YanZhiByJin(roleId);
            if(roletotalyin==null){
                roletotalyin=0;
            }
            if(roletotaljin==null){
                roletotaljin=0;
            }
            if(yq.getJin()==null){
                yq.setJin(0);
            }
            if(yq.getYin()==null){
                yq.setYin(0);
            }
            if(yq.getYin()>=200000){
                result="输入的银两单次不能超过20万";
            }else{
                Integer one=roletotalyin+yq.getYin();
                Integer two=roletotaljin+yq.getJin();
                if(one<=200000&&two<=1000){//限制押银金的最大数量
                    Integer inputYin=0;
                    Integer inputJin=0;
                    NewRole re=newrolemapper.selectByPrimaryKey(roleId);
                    yq.setRolename(re.getRolename());
                    yq.setRoleid(roleId);
                    yq.setNum(num);
                    yq.setData(new Date());
                    Integer roleYin=re.getYin();
                    Integer roleJin=re.getJin();
                    if(!StringUtils.isEmpty(yq.getYin())){
                        inputYin=yq.getYin();
                    }else{
                        inputYin=0;
                    }
                    if(!StringUtils.isEmpty(yq.getJin())){
                        inputJin=yq.getJin();
                    }else{
                        inputJin=0 ;
                    }

                    if(roleYin>=inputYin&&inputYin>0){
                        re.setYin(roleYin-inputYin);
                        newrolemapper.updateByPrimaryKeySelective(re);
                        yldxxqmapper.insertSelective(yq);
                       result="本期你押了"+yq.getResult()+yq.getYin()+"两。祝你好运";
                    }
                    if(roleYin<inputYin){
                        result="余额不足！";
                    }
                    if(roleJin>=inputJin&&inputJin>0){
                        re.setJin(roleJin-inputJin);
                        newrolemapper.updateByPrimaryKeySelective(re);
                        yldxxqmapper.insertSelective(yq);
                        result="本期你押了"+yq.getResult()+yq.getJin()+"金。祝你好运";
                    }
                    if(roleJin<inputJin){
                        result="余额不足！";                    }
                }else{
                    result="押的总数不能超过银20万，金1000两";
                }
                systemnotification.sendSystemMsg(new Object[]{"1001",null});
            }


            return result;
        } catch (Exception e) {
            result="服务器异常，押注失败";
            e.printStackTrace();
            logger.error("玩家押注失败"+e.getMessage());
        }

        return result;
    }

}

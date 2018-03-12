package com.hxsg.wupin.service.impl;

import com.hxsg.Dao.*;
import com.hxsg.po.ZaWuMiaoShu;
import com.hxsg.po.*;
import com.hxsg.system.dao.SystemNotification;
import com.hxsg.vo.ZaWuDetailVo;
import com.hxsg.vo.ZhuangBeiDetailVo;
import com.hxsg.wupin.service.cocos2dWuPinService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.*;

/**
 * Created by dlf on 2016/9/29.
 *  /**
 * A:钻石：增加抗物理，暴击率，速度。

 　　绿宝石：增加抗围困，抗风沙，反击率。

 　　红宝石：增加抗扰乱，抗妖火，致命率。

 　　蓝宝石：增加抗封锁，抗落雷，体质。

 　　猫眼石：增加抗风沙，抗毒术，智力。

 　　水晶：增加抗妖火，抗物理，力量。

 　　玛瑙：增加抗落雷，敏捷，攻击

 　　白玉：增加抗毒术，血气，防御。

 　　琥珀：增加抗围困，反震率，精力。

 　　翡翠：增加抗扰乱，躲避率，攻击。

 　　珍珠：增加抗封锁，命中率，防御。
        1头盔，2项链，3武器，4护腕，5铠甲，6靴子
 抗性-152头盔铠甲项链
 气血-14头盔护腕
 攻击-34武器护腕
 速度-26靴子项链
 防御-15头盔铠甲
 精力-12头盔项链
 暴击率-34武器护腕
 躲避率-16头盔靴子
 命中率-34武器护腕
 反震率-152头盔铠甲项链
 致命率-34武器护腕
 反击率-15头盔铠甲
 */
@Service("cocos2dWuPinService")
public class cocos2dWuPinServiceImpl implements cocos2dWuPinService {
    public static final String KWULI="抗物理";
    public static final String KWEIKUN="抗围困";
    public static final String KRAOLUAN="抗扰乱";
    public static final String KFENGSUO="抗封锁";
    public static final String KFENGSHA="抗风沙";
    public static final String KYAOHUO="抗妖火";
    public static final String KLUOLEI="抗落雷";
    public static final String KDUSHU="抗毒术";
    public static final String BAOJILV="暴击率";
    public static final String DUOBILV="躲避率";
    public static final String MINGHZONGLV="命中率";
    public static final String FANZHENLV="反震率";
    public static final String ZHIMINGLV="致命率";
    public static final String FANJILV="反击率";
    public static final String QIXUE="气血";
    public static final String JINGLI="精力";
    public static final String SUDU="速度";
    public static final String GONGJI="攻击";
    public static final String FANGYU="防御";
   public static final String BSYK="宝石原矿";
   public static final String BSJK="宝石精矿";
   public static final String BSZK="宝石珍矿";
    public static final String TC="铁锤";
    public static final String YC="银锤";
    public static final String JC="金锤";
    public static final String ZUANS="钻石";
    public static final String LVBS="绿宝石";
    public static final String HONGBS="红宝石";
    public static final String LANBS="蓝宝石";
    public static final String MAOYS="猫眼石";
    public static final String SHUIJ="水晶";
    public static final String MAN="玛瑙";
    public static final String BAIY="白玉";
    public static final String HUP="琥珀";
    public static final String FEIC="翡翠";
    public static final String ZHENZ="珍珠";
    @Autowired
    WuqiDescribeMapper wuqidescribemapper;
    private Logger logger =Logger.getLogger(cocos2dWuPinServiceImpl.class);
    @Autowired
    RoleWuPinMapper rolewupinmapper;
    @Autowired
    ZaWuMiaoShuMapper zawumiaoshumapper;
    @Autowired
    BaoShiMapper baoshimapper;
    @Autowired
    WorldMsgMapper worldmsgmapper;
    @Autowired
    SystemNotification systemnotification;
    @Autowired
    RoleZhuangbeiMsMapper rolezhuangbeimsmapper;
    @Autowired
    RoleNewZhuangBeiMapper rolenewzhuangbeimapper;
    @Autowired
    roleFujiangMapper rolefujiangmapper;
    @Autowired
    FjLevelJyMapper fjleveljymapper;
    @Autowired
    NewRoleMapper newrolemapper;
   //查询角色物品--药品--矿石-- 装备---杂物
    @Override
    public List<RoleWuPin> queryRoleWupin(RoleWuPin rw) {
        List<RoleWuPin> rwli=null;
        try {
           rwli=rolewupinmapper.queryZaWu(rw);
        } catch (Exception e) {
            logger.error("角色物品查询selectAll方法异常："+e.getMessage());
            e.printStackTrace();
        }
        return rwli;
    }

    @Override
    public ZaWuMiaoShu queryWuPinDescribe(Integer id) {
        ZaWuMiaoShu zu=null;
        try {
            zu=zawumiaoshumapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询物品详情selectByPrimaryKey方法异常："+e.getMessage());
        }

        return zu;
    }
    //查询角色拥有的宝石
    @Override
    public List<ZaWuDetailVo> queryWuPinByBaoShi(Integer roleid,String rolename)  {
        List<ZaWuDetailVo> li=null;
        try {
            li=new ArrayList<ZaWuDetailVo>();
            List<BaoShi> libs=baoshimapper.queryWuPinByBaoShi(roleid);
            for(BaoShi b:libs){
                ZaWuDetailVo zo=new ZaWuDetailVo();
                String level="0";
                zo.setId(b.getId());
                zo.setRoleId(roleid);
                zo.setRoleName(rolename);
                zo.setZwMiaoShu(b.getMiaoshuid());
                zo.setZwXiaoGuo(b.getKangxing()+"+"+b.getXiaoguo());
                zo.setZwName(b.getName());
                zo.setZwUrl(b.getImg());
                if(b.getXiaoguo()>15){
                    Integer a=(b.getXiaoguo()/100);
                    level=a.toString();
                }else{
                    level=b.getXiaoguo().toString();
                }
                zo.setZwLevel(level);
                li.add(zo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询角色拥有的宝石bskangxingmapper.selectAll方法异常："+e.getMessage());
        }
        return li;
    }

    @Override
    public ZaWuDetailVo queryBaoShiDescribe(Integer id)  {
        ZaWuDetailVo zo=null;
        try {
            zo=new ZaWuDetailVo();
            BaoShi  bkx=baoshimapper.selectByPrimaryKey(id);
            zo.setId(bkx.getId());
            zo.setRoleId(bkx.getRoleid());
            zo.setZwMiaoShu(bkx.getMiaoshuid());
            zo.setZwXiaoGuo(bkx.getKangxing()+"+"+bkx.getXiaoguo());
            zo.setZwName(bkx.getName());
            zo.setZwUrl(bkx.getImg());
            String level="";
            if(bkx.getXiaoguo()>15){
                Integer a=(bkx.getXiaoguo()/100);
                level=a.toString();
            }else{
                level=bkx.getXiaoguo().toString();
            }
            zo.setZwLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询角色拥有的宝石bskangxingmapper.selectAll方法异常："+e.getMessage());
        }
        return zo;
    }

    /**
     * 使用锤砸开矿石
     * 铁---宝石原矿***银---宝石精矿***金---宝石珍矿
     * 4,5,6,7    ***5,6,7,8    ***7,8,9,10
     * @return
     * @throws Exception
     */
    @Override
    public String userMineral(
            RoleWuPin rn,String roleName){
        String msg=null;
        try {
            //1.通过roleid和物品ID确认玩家是否拥有该物品//
            RoleWuPin rwp=rolewupinmapper.selectByPrimaryKey(rn.getId());
            if(!rwp.getRoleid().equals(rn.getRoleid())){
                msg="异常操作";
            }else{
                //数量》0
                    if(rwp.getNum()>0){
                        String name= rwp.getName();
                        BaoShi bs=null;
                        RoleWuPin r=new RoleWuPin();
                        r.setRoleid(rn.getRoleid());
                        switch (name){
                            case BSYK:{
                                r.setName(TC);
                                List<RoleWuPin> tcli=rolewupinmapper.selectAll(r);
                                if(tcli!=null&&tcli.size()>0){
                                    bs=insertBaoShi(rwp,tcli);
                                }else{
                                    msg="开宝石珍矿需要铁锤，请拥有后再来！";
                                    return msg;
                                }
                                break;
                            }
                            case BSJK:{
                                r.setName(YC);
                                List<RoleWuPin> tcli=rolewupinmapper.selectAll(r);
                                if(tcli!=null&&tcli.size()>0){
                                    bs=insertBaoShi(rwp,tcli);
                                }else{
                                    msg="开宝石珍矿需要银锤，请拥有后再来！";
                                    return msg;
                                }
                                break;
                            }
                            case BSZK:{
                                r.setName(JC);
                                List<RoleWuPin> tcli=rolewupinmapper.selectAll(r);
                                if(tcli!=null&&tcli.size()>0){
                                    if(tcli.get(0).getNum()>0){
                                        bs=insertBaoShi(rwp,tcli);
                                    }else{
                                        msg="开宝石珍矿需要金锤，请拥有后再来！";
                                        return msg;
                                    }
                                }else{
                                    msg="开宝石珍矿需要金锤，请拥有后再来！";
                                    return msg;
                                }
                                break;
                            }
                        }
                        bs.setRoleid(rn.getRoleid());
                        bs.setData(new Date());
                        bs.setStatus("0");
                        bs.setSell(0);
                        int result=baoshimapper.insertSelective(bs);
                        if(result>0){
                            msg="恭喜你砸到一颗【"+bs.getName()+"("+bs.getKangxing()+"+"+bs.getXiaoguo()+")】";
                            if(bs.getXiaoguo()>=9&&bs.getXiaoguo()<=15){
                                StringBuffer bf=new StringBuffer();
                                bf.append(roleName);
                                bf.append("(id:"+rn.getRoleid());
                                bf.append(")天降鸿运人品爆发,砸开宝石珍矿竟然意外获得【");
                                bf.append(bs.getName()+"("+bs.getKangxing()+"+"+bs.getXiaoguo()+")】");
                                bf.toString();
                                WorldMsg wg=new WorldMsg();
                                wg.setRolename("系统");
                                wg.setType("0");
                                wg.setData(new Date());
                                wg.setMessage(bf.toString());
                                wg.setRoleid(999);
                                int worldResult=worldmsgmapper.insertSelective(wg);

                                    /**    通知主界面-世界频道刷新消息---*/
                                    List<WorldMsg> li= worldmsgmapper.queryWorldMsg();
                                    systemnotification.sendSystemMsg(new Object[]{4,li});
                            }
                        }

                    }else{
                        msg="没有矿石，错误的操作";
                    }
                }
        } catch (Exception e) {
            logger.error("使用锤子砸开矿石userMineral方法异常："+e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * 装备详情查看
     * @return
     * @throws Exception
     */
    @Override
    public ZhuangBeiDetailVo queryZhuangBeiDetail(ZhuangBeiDetailVo zb,String roleName) throws Exception {
        ZhuangBeiDetailVo zbDetail=null;
        try {
             zbDetail=new ZhuangBeiDetailVo();
            RoleZhuangbeiMs rm=rolezhuangbeimsmapper.selectByPrimaryKey(zb.getId());
            if(rm!=null){
                zbDetail.setId(rm.getId());
                zbDetail.setRoleId(rm.getRoleid());
                zbDetail.setRoleName(roleName);
                zbDetail.setZbFuZhong("0");
                zbDetail.setZbMiaoShu(rm.getMiaoshu());
                zbDetail.setZbName(rm.getWuqiname());
                zbDetail.setImgUrl(rm.getImgurl());
                Integer bs1=rm.getBaoshi1();
                Integer bs2=rm.getBaoshi2();
                Integer bs3=rm.getBaoshi3();
                List<BaoShiTotalKx> libx=rolezhuangbeimsmapper.queryZhuangBeiKx(bs1,bs2,bs3);
                StringBuffer sb=new StringBuffer();
                if(libx.size()>0&&libx!=null){
                    Integer num=0;
                    for(int i=0;i<libx.size();i++){
                        num+=libx.get(i).getNum();
                        if(i==libx.size()-1){
                            BaoShiTotalKx bx=libx.get(i);
                            sb.append( bx.getKangXing());
                            sb.append("+");
                            sb.append(bx.getKangXingTotal());
                            sb.append(".");
                        }else{
                            BaoShiTotalKx bx=libx.get(i);
                            sb.append( bx.getKangXing());
                            sb.append("+");
                            sb.append(bx.getKangXingTotal());
                            sb.append(",");
                        }
                    }

                    Integer synum=3-num;
                    if(synum>0&&synum<3){
                        zbDetail.setZbBaoShiKong(synum.toString());
                    }
                    if(synum==0){
                        zbDetail.setZbBaoShiKong("已满");
                    }

                }
                sb.append(rm.getKangxing()+"+"+rm.getWqxiaoguo());
                zbDetail.setZbXiaoGuo(sb.toString());
                //佩戴所需条件：等级大于90级，体质大于200点
                StringBuffer sbf=new StringBuffer();
                sbf.append("佩戴所需条件：等级大于");
                sbf.append(rm.getRolelevel());
                sbf.append("级，");
                String type=rm.getWuqitype();
                Integer powerNum=rm.getPowernum();

                //需要加上是否能被装备
                NewRole nrole= newrolemapper.selectByPrimaryKey(rm.getRoleid());
                Integer level=nrole.getLevel();
                Integer roleLevel=rm.getRolelevel();
                if(powerNum.equals(0)){
                    if(level>=roleLevel){
                        sbf.append("您当前"+level+"级");
                        sbf.append("【可以装备】");
                    }else{
                        sbf.append("您当前"+level+"级");
                        sbf.append("【不可装备】");
                    }
                }
                if(type.contains("力")&&powerNum>0){
                    sbf.append("力量点大于");
                    sbf.append(rm.getPowernum());
                    sbf.append(".您当前力量点"+  nrole.getGongjids());
                    sbf.append("您当前"+level+"级");
                    if(nrole.getGongjids()>=powerNum){
                        sbf.append("【可以装备】");
                    }else{
                        sbf.append("【不可装备】");
                    }
                }
                if(type.contains("血")&&powerNum>0){
                    sbf.append("体质点大于");
                    sbf.append(rm.getPowernum());
                    sbf.append(".您当前体质点"+  nrole.getQixueds());
                    sbf.append("您当前"+level+"级");
                    if(nrole.getGongjids()>=powerNum){
                        sbf.append("【可以装备】");
                    }else{
                        sbf.append("【不可装备】");
                    }
                }
                if(type.contains("速")&&powerNum>0){
                    sbf.append("敏捷点大于");
                    sbf.append(rm.getPowernum());
                    sbf.append(".您当前敏捷点"+  nrole.getSududs());
                    sbf.append("您当前"+level+"级");
                    if(nrole.getGongjids()>=powerNum){
                        sbf.append("【可以装备】");
                    }else{
                        sbf.append("【不可装备】");
                    }
                }
                zbDetail.setZbTiaoJian(sbf.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zbDetail;
    }
    /**
     * 杂物-药品-矿石详情查看
     * @param zw
     * @return
     * @throws Exception
     */
    @Override
    public ZaWuDetailVo queryZaWuDetail(ZaWuDetailVo zw) throws Exception {
        return null;
    }

    /**
     *将宝石镶嵌到装备上
     * @param roleid
     * @param bsId
     * @param zbId
     * @return
     * @throws Exception
     */


    @Override
    public String mosaicGemtoZhuangBei(Integer roleid,Integer bsId,Integer zbId) throws Exception {
        String re=null;
        try {
            BaoShi bs= baoshimapper.selectBaoShiForStatus(bsId,0);
            //验证宝石拥有者
            if(roleid.equals(bs.getRoleid())){
                bs.setStatus("1");
                //更新已装备
                baoshimapper.updateByPrimaryKeySelective(bs);
                //2.查询宝石可以镶嵌的装备
                String type=bs.getZbtype();
                String[] zbtype=type.split(",");
                List<RoleZhuangbeiMs>  result=rolezhuangbeimsmapper.queryRoleZhuangbeiForBaoShi(roleid);
                    for(int j=0;j<result.size();j++) {
                            //检验传过来的武器ID是否是可以被装备的武器
                            //判断装备可以镶嵌的宝石
                            if (result.get(j).getId().equals(zbId)) {
                                for (int i = 0; i < zbtype.length; i++) {
                                    String t = zbtype[i];
                                        if((result.get(j).getWuqitype().contains(t))){
                                            //该装备能装备这颗宝石
                                            //更新装备描述视图的baoshi1,2,3
                                            RoleNewZhuangBei bm = rolenewzhuangbeimapper.selectByPrimaryKey(zbId);
                                            //验证是否属于自己的装备
                                            if (roleid.equals(bm.getRoleid())) {
                                                if (StringUtils.isEmpty(bm.getBaoshi1())) {
                                                    bm.setBaoshi1(bsId);
                                                } else if (StringUtils.isEmpty(bm.getBaoshi2())) {
                                                    bm.setBaoshi2(bsId);
                                                } else if (StringUtils.isEmpty(bm.getBaoshi3())) {
                                                    bm.setBaoshi3(bsId);
                                                }
                                                rolenewzhuangbeimapper.updateByPrimaryKeySelective(bm);
                                                return  re = "镶嵌成功," + bm.getName() + "提升" + bs.getKangxing() + "+" + bs.getXiaoguo();
                                            }
                                        }
                                }

                            } else {
                                logger.info(roleid + "【该玩家行为存在封号风险】");

                            }
                        }


            }else{
                logger.info("该宝石不属于" + bs.getRoleid() + " 【该玩家行为存在封号风险】");
                re="该宝石不属于你，你承担被系统封号的风险！";
            }
        } catch (Exception e) {
            logger.info("宝石id"+bsId+"装备id+"+zbId+"--服务器出现错误："+e.getMessage());
            re="服务器出现错误";
            e.printStackTrace();
        }
        return re;
    }



    private BaoShi insertBaoShi(RoleWuPin rwp,   List<RoleWuPin> tcli) throws Exception {
        BaoShi bs=new BaoShi();
        Integer xiaoguo=queryBaoShi(rwp, tcli);//效果
        String baoShiName=queryBaoShiName();//宝石名称
        String bsName=null;
        if(xiaoguo>=10&&xiaoguo<=15){
            bsName="10级"+baoShiName;
        }else{
            bsName=xiaoguo+"级"+baoShiName;
        }
        String kName=queryKangXingName(baoShiName);//抗性名称
        if("气血精力攻击防御".contains(kName)){
            xiaoguo=100*xiaoguo;
        }
        String[] arr=queryZbTypeAndMiaoShu(kName);
        String zbType=arr[0];//装备类型
        String miaoShu=arr[1];//宝石描述
        //后期需补上图片路径
        bs.setKangxing(kName);
        bs.setName(bsName);
        bs.setZbtype(zbType);
        bs.setMiaoshuid(miaoShu);
        bs.setXiaoguo(xiaoguo);
        return bs;
    }
    //验证物品是否属于角色
    public Boolean yanZhengWuPin(Integer wupinId,Integer roleid) throws Exception{
    Boolean result=false;
    RoleWuPin rn=rolewupinmapper.selectByPrimaryKey(wupinId);
    if(!StringUtils.isEmpty(rn)){
        if(roleid.equals(rn.getRoleid())){
                result=true;
        }
    }
    return result;
}
    //使用物品后，消耗掉一个
    public Boolean consumeWuPin(RoleWuPin rn,Integer num) throws Exception{
        Boolean result=false;
        if(rn.getNum()>=num){
            rn.setNum(rn.getNum()-num);
            int re=rolewupinmapper.updateByPrimaryKeySelective(rn);
            if(re>0){
                result=true;
            }
        }
        return result;
    }
    private Integer queryBaoShi(RoleWuPin rwp, List<RoleWuPin> tcli) throws Exception{
        Integer xiaoguo=null;
        if(tcli!=null&&tcli.size()>0){
            RoleWuPin rn1=tcli.get(0);
            if(rn1.getNum()>0){
                //铁锤大于0的话，消耗铁锤和原矿-----随机产生对应的宝石
                rn1.setNum(rn1.getNum()-1);
                int tcre=rolewupinmapper.updateByPrimaryKeySelective(rn1);
                rwp.setNum(rwp.getNum()-1);
                int bsre =rolewupinmapper.updateByPrimaryKeySelective(rwp);
                if(tcre+bsre>1){
                    switch(rwp.getName()){
                        case BSYK:{
                            //随机产生4,5,6,7
                            int gl=(int)(1+Math.random()*100);
                            //产生7的概率为百分之10
                            if(gl>90){
                                xiaoguo=7;
                            }else if(gl>70){
                                //产生7的概率为百分之20
                                xiaoguo=6;
                            }else if(gl>40){
                                //产生7的概率为百分之30
                                xiaoguo=5;
                            }else if(gl>0){
                                //产生7的概率为百分之40
                                xiaoguo=4;
                            }
                            break;
                        }
                        case BSJK:{
                            //随机产生5,6,7,8
                            int gl=(int)(1+Math.random()*100);
                            //产生8的概率为百分之10
                            if(gl>90){
                                xiaoguo=8;
                            }else if(gl>70){
                                //产生7的概率为百分之20
                                xiaoguo=7;
                            }else if(gl>40){
                                //产生6的概率为百分之30
                                xiaoguo=6;
                            }else if(gl>0){
                                //产生5的概率为百分之40
                                xiaoguo=5;
                            }
                            break;
                        }
                        case BSZK:{
                            //随机产生7,8,9,10
                            int gl=(int)(1+Math.random()*100);
                            //产生7的概率为百分之10
                            if(gl>95){
                                //10级宝石分为 10,11,12,13,14(0.4%),15(0.1%)随机顶级属性值
                                int gl2=(int)(1+Math.random()*100);
                                if(gl2>98){
                                    xiaoguo=15;
                                }else if(gl2>93){
                                    xiaoguo=14;
                                }else if(gl2>83){
                                    xiaoguo=13;
                                }else if(gl2>73){
                                    xiaoguo=12;//200分之一
                                }else if(gl2>53){
                                    xiaoguo=11;//200分之一
                                }else if(gl2>0){
                                    xiaoguo=10;//200分之一
                                }

                            }else if(gl>85){
                                //产生7的概率为百分之20
                                xiaoguo=9;
                            }else if(gl>60){
                                //产生7的概率为百分之30
                                xiaoguo=8;
                            }else if(gl>0){
                                //产生7的概率为百分之40
                                xiaoguo=7;
                            }
                            break;
                        }


                    }

                    //宝石随机封装函数
                }

            }else{

            }
        }
        return xiaoguo;
    }
    private String queryBaoShiName() throws Exception{
        String xiaoguo=null;
        Integer gl=(int)(1+Math.random()*11);
        switch (gl){
            case 1:{
                xiaoguo=ZUANS;
                break;
            }
            case 2:{
                xiaoguo=LVBS;
                break;
            }
            case 3:{
                xiaoguo=HONGBS;
                break;
            }
            case 4:{
                xiaoguo=LANBS;
                break;
            }
            case 5:{
                xiaoguo=MAOYS;
                break;
            }
            case 6:{
                xiaoguo=SHUIJ;
                break;
            }
            case 7:{
                xiaoguo=MAN;
                break;
            }
            case 8:{
                xiaoguo=BAIY;
                break;
            }
            case 9:{
                xiaoguo=HUP;
                break;
            }
            case 10:{
                xiaoguo=FEIC;
                break;
            }
            case 11:{
                xiaoguo=ZHENZ;
                break;
            }


        }
        return xiaoguo;
    }
    private String queryKangXingName(String name) throws Exception{
        String kname=null;
        Integer gl=(int)(1+Math.random()*3);
        switch (name){
            case ZUANS:{
                if(gl==1){kname=KWULI;}
                if(gl==2){kname=BAOJILV;}
                if(gl==3){kname=SUDU;}
                break;
            }
            case LVBS:{
                if(gl==1){kname=KWEIKUN;}
                if(gl==2){kname=KFENGSHA;}
                if(gl==3){kname=FANJILV;}
                break;
            }
            case HONGBS:{
                if(gl==1){kname=KRAOLUAN;}
                if(gl==2){kname=KYAOHUO;}
                if(gl==3){kname=ZHIMINGLV;}
                break;
            }
            case LANBS:{
                if(gl==1){kname=KFENGSUO;}
                if(gl==2){kname=KLUOLEI;}
                if(gl==3){kname=QIXUE;}
                break;
            }
            case MAOYS:{
                if(gl==1){kname=KFENGSHA;}
                if(gl==2){kname=KDUSHU;}
                if(gl==3){kname=JINGLI;}
                break;
            }
            case SHUIJ:{
                if(gl==1){kname=KWULI;}
                if(gl==2){kname=KYAOHUO;}
                if(gl==3){kname=GONGJI;}
                break;
            }
            case MAN:{
                if(gl==1){kname=KLUOLEI;}
                if(gl==2){kname=SUDU;}
                if(gl==3){kname=GONGJI;}
                break;
            }
            case BAIY:{
                if(gl==1){kname=KDUSHU;}
                if(gl==2){kname=QIXUE;}
                if(gl==3){kname=FANGYU;}
                break;
            }
            case HUP:{
                if(gl==1){kname=KWEIKUN;}
                if(gl==2){kname=FANZHENLV;}
                if(gl==3){kname=JINGLI;}
                break;
            }
            case FEIC:{
                if(gl==1){kname=KRAOLUAN;}
                if(gl==2){kname=DUOBILV;}
                if(gl==3){kname=GONGJI;}
                break;
            }
            case ZHENZ:{
                if(gl==1){kname=KFENGSUO;}
                if(gl==2){kname=MINGHZONGLV;}
                if(gl==3){kname=FANGYU;}
                break;
            }


        }
        return kname;
    }
    private String[] queryZbTypeAndMiaoShu(String kname) throws Exception{
        String[] arr=null;
        String type=null;
        String miaoshu=null;
        switch (kname){
            case QIXUE:{
                type="盔,腕";
                miaoshu="可镶嵌于头盔,护腕上";
                break;
            }
            case GONGJI:{
                type="武,腕";
                miaoshu="可镶嵌于武器,护腕上";
                break;
            }
            case SUDU:{
                miaoshu="可镶嵌于项链,靴子上";
                type="链,靴";
                break;
            }
            case FANGYU:{
                 miaoshu="可镶嵌于头盔,铠甲上";
                 type="盔,甲";
                break;
            }
            case JINGLI:{
                miaoshu="可镶嵌于头盔,项链上";
                type="盔,链";
                break;
            }
            case BAOJILV:{
                miaoshu="可镶嵌于武器,护腕上";
                type="武,腕";
                break;
            }
            case DUOBILV:{
                miaoshu="可镶嵌于头盔,靴子上";
                type="盔,靴";
                break;
            }
            case MINGHZONGLV:{
                miaoshu="可镶嵌于武器,护腕上";
                type="武,腕";
                break;
            }
            case FANZHENLV:{
                miaoshu="可镶嵌于头盔,项链,武器上";
                type="盔,链,武";
                break;
            }
            case ZHIMINGLV:{
                miaoshu="可镶嵌于武器,护腕上";
                type="武,腕";
                break;
            }
            case FANJILV:{
                miaoshu="可镶嵌于头盔,铠甲上";
                type="盔,甲";
                break;
            }
            default:{
                miaoshu="可镶嵌于头盔,项链,武器上";
                type="盔,链,武";
            }
        }
        arr=new String[]{type,miaoshu};
        System.out.println("数组-------"+arr.toString());
        return arr;
    }
    /**
     * 装备宝石镶嵌功能
     *
     */
    //获取可以被宝石装备的武器列表
    @Override
    public  List<RoleZhuangbeiMs>  getBaoShiByZhaungbei(Integer wupinId,Integer roleId){
        List<RoleZhuangbeiMs> result= null;
        try {
            //1.验证物品是否属于玩家
            BaoShi bs=baoshimapper.selectBaoShiForStatus(wupinId, 0);
            if(roleId.equals(bs.getRoleid())){
                //2.查询宝石可以镶嵌的装备
                String type=bs.getZbtype();
                String[] zbtype=type.split(",");
                result=rolezhuangbeimsmapper.queryRoleZhuangbeiForBaoShi(roleId);
                for(int i=0;i<zbtype.length;i++){
                    String t=zbtype[i];
                    for(int j=0;j<result.size();j++){
                        if(!(result.get(j).getWuqitype().contains(t))){
                            result.remove(j);
                        }
                    }
                }

            }else{
                logger.info(roleId+":异常操作使用物品，该物品ID"+wupinId+"---或者改装备已被装备");
            }
        } catch (Exception e) {
            logger.info("异常数据，请联系管理员"+e.getMessage());
            e.printStackTrace();
        }
        return  result;
    }
    //使用副将心法书
    @Override
    public String UserXinFaShu(Integer fuId,Integer wupinId, Integer roleId,Integer num)  {
        String msg="";
       try {
           //1.验证物品
           RoleWuPin rn=rolewupinmapper.selectByPrimaryKey(wupinId);
           if(rn!=null&&rn.getNum()>=num&&rn.getRoleid()==roleId){
/*********************************************************************************************/
                   rn.setNum(rn.getNum()-num);
                   int a=rolewupinmapper.updateByPrimaryKeySelective(rn);
                   if(a>0){
                       roleFujiang rf=rolefujiangmapper.selectByPrimaryKey(fuId);
                       //验证副将是否属于玩家
                       if(rf.getRoleid()==roleId){
                            if(rn.getName().equals("副将经验书")&&rf.getLeve()<=11){
                                rf=getRoleFuJiang(rf,1);
                                rolefujiangmapper.updateByPrimaryKeySelective(rf);
                                return msg="恭喜你副将升到11级";
                            }
                           int jy=rf.getLeve();
                           //设置经验书使用后获得的经验
                           if(rf.getJingyan()==null){
                               rf.setJingyan(60*jy*jy*num);
                           }
                           //初始化总经验
                           if(rf.getTotajy()==null){
                               switch (rf.getTouxian()){
                                   case "英才":{
                                       rf.setTotajy(fjleveljymapper.sumyingcai(rf.getLeve()+1));
                                       break;

                                   }
                                   case "将才":{
                                       rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve()+1));
                                       break;

                                   }
                                   case "猛将":{
                                       rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve()+1));
                                       break;

                                   }
                                   case "元帅":{
                                       rf.setTotajy(fjleveljymapper.sumyuanshuai(rf.getLeve()+1));
                                       break;

                                   }
                               }
                           }
                           //升级经验初始化或者升级经验减去经验书获得经验
                           if(rf.getSjjinyan()==null){
                               switch (rf.getTouxian()){
                                   case "英才":{
                                       rf.setSjjinyan((20*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-40));
                                       break;

                                   }
                                   case "将才":{
                                       rf.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
                                       break;

                                   }
                                   case "猛将":{
                                       rf.setSjjinyan((30*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
                                       break;

                                   }
                                   case "元帅":{
                                       rf.setSjjinyan((40*((jy+1)*(jy+1)*(jy+1)+5*(jy+1))-80));
                                       break;

                                   }
                               }

                               }

                           if(rf.getSjjinyan()>0){
                               rf.setSjjinyan(rf.getSjjinyan()-60*jy*jy*num);
                           }else if(rf.getSjjinyan()<=0){//升了一级之后的属性
                                rf=getRoleFuJiang(rf,1);
                               msg="副将获得"+60*jy*jy*num+"经验，恭喜你副将升到"+(jy+1)+"级";
                           }
                           rolefujiangmapper.updateByPrimaryKeySelective(rf);
                           }
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public  List<RoleNewZhuangBei> queryWuPinZhuangBei(Integer roleid,Integer type) {
        List<RoleNewZhuangBei> lizb=null;
        try {
            lizb=rolenewzhuangbeimapper.queryRoleZhaungBei(roleid,type);
        } catch (Exception e) {
            logger.error("查询物品-装备数据库异常:"+e.getMessage());
            e.printStackTrace();
        }
        return lizb;
    }

    @Override
    public Boolean xiaoHaoWuPin(Integer roleid, Integer wupinId, Integer num)throws Exception{
        Boolean result=false;
        RoleWuPin rn=rolewupinmapper.selectByPrimaryKey(wupinId);
        if(rn.getNum()>=num&&num>0&&rn.getRoleid().equals(roleid)){
            rn.setNum(rn.getNum()-num);
            int re=rolewupinmapper.updateByPrimaryKeySelective(rn);
            if(re>0){
                result=true;
            }
        }
        return result;
    }


    private roleFujiang  getRoleFuJiang(roleFujiang rf,Integer level) {
        int jy=rf.getLeve();
        rf.setTotalxue2((int)(Math.round(rf.getChengzhang()*(rf.getLeve()+level)*((rf.getQixueds()+level)+rf.getChuxue()*0.8))));
        rf.setTotaljing2((int)(Math.round(rf.getChengzhang()*(rf.getLeve()+level)*((rf.getJinglids()+level)+rf.getChujing()*0.8))));
        rf.setTotalgong((int)( Math.round(((rf.getLeve()+level)* rf.getChengzhang()* rf.getChugong())/7+rf.getChengzhang()*rf.getChugong()*0.5+(rf.getLeve()+level)*rf.getChengzhang()*(rf.getGongjids()+level)*0.2)));
        rf.setTotalsudu((int)(rf.getChengzhang()*(rf.getSududs()+level+rf.getChusu())));
        rf.setLeve(rf.getLeve()+level);
        rf.setQixueds(rf.getQixueds()+level);
        rf.setJinglids(rf.getJinglids()+level);
        rf.setGongjids(rf.getGongjids()+level);
        rf.setSududs(rf.getSududs()+level);
        rf.setKeyongds(rf.getKeyongds()+4);
        //rfm.updateByPrimaryKeySelective(rf);
        //设置当前级别的总经验
        if(rf.getTouxian().equals("英才")){
            rf.setSjjinyan((20*((jy+level)*(jy+level)*(jy+level)+5*(jy+level))-40)+rf.getSjjinyan());
            rf.setJingyan(0);
            rf.setTotajy(fjleveljymapper.sumyingcai(rf.getLeve()+2));
        }
        if(rf.getTouxian().equals("将才")){
            rf.setSjjinyan((30*((jy+level)*(jy+level)*(jy+level)+5*(jy+level))-80)+rf.getSjjinyan());
            rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve()+2));
            rf.setJingyan(0);
        }
        if(rf.getTouxian().equals("猛将")){
            rf.setSjjinyan((30*((jy+level)*(jy+level)*(jy+level)+5*(jy+level))-80)+rf.getSjjinyan());
            rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve()+2));
            rf.setJingyan(0);
        }
        if(rf.getTouxian().equals("元帅")){
            rf.setSjjinyan((40*((jy+level)*(jy+level)*(jy+level)+5*(jy+level))-80)+rf.getSjjinyan());
            rf.setTotajy(fjleveljymapper.sumyuanshuai(rf.getLeve()+2));
            rf.setJingyan(0);
        }
        return rf;
    }
}

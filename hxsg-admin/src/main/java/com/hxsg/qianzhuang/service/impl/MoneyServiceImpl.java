package com.hxsg.qianzhuang.service.impl;

import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.*;
import com.hxsg.po.*;
import com.hxsg.qianzhuang.service.MoneyService;
import com.hxsg.redis.RedisDaoService;
import com.hxsg.redis.util.GjsgRedisUtil;
import com.hxsg.system.dao.SystemNotification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/11/29.
 */
@Service("MoneyService")
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    RoleMoneyMapper rmm;
    @Autowired
    RedisDaoService   redisdaoservice;
    @Autowired
    NewRoleMapper rm;
    @Autowired
    GoldRecordMapper goldrecordmapper;
    @Autowired
    GoldBusinessMapper goldbusinessmapper;
    @Autowired
    TaxMapper taxmapper;
    @Autowired
    SystemNotification systemnotification;
    @Autowired
    MoneyRecordMapper moneyrecordmapper;
    @Autowired
    GjsgRedisUtil gjsgredisutil;
    private Logger logger =Logger.getLogger(MoneyServiceImpl.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public NewRole queryRoleMoney(HttpSession session) {
        NewRole re=null;
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            re=gjsgredisutil.getRole(roleId);
//            re=rm.selectByPrimaryKey(roleId);
        } catch (Exception e) {
            logger.error("钱庄获取角色银两失败："+e.getMessage());
            e.printStackTrace();
        }
        return re;
    }

    @Override
    public String setMoney(HttpSession session,NewRole ne,String status) throws Exception {
        String msg=null;
            NewRole re=queryRoleMoney(session);
            Integer roleJin=re.getJin();
            Integer roleYin=re.getYin();
            Integer jin=ne.getJin();
            Integer yin=ne.getYin();
            if(roleJin>=jin||roleYin>=yin){
                if("add".equals(status)){
                    re.setJin(re.getJin()+jin);
                    re.setYin(re.getYin() + yin);
                }else if("del".equals(status)){
                    re.setJin(re.getJin()-jin);
                    re.setYin(re.getYin()-yin);
                }
                try {
                    rm.updateByPrimaryKeySelective(re);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                msg="succes";
            }

        return msg;
    }
    @Override
    public String setQianZhuangMoney(HttpSession session, RoleMoney ne, String status) {
        String msg=null;
        try {
            Integer roleId= (Integer) session.getAttribute("roleId");
            RoleMoney ry=rmm.selectByRoleid(roleId);
            Integer jin=ne.getJin();
            Integer yin=ne.getYin();
            if(ry==null){
                ry=new RoleMoney();
                ry.setDate(new Date());
                ry.setRoleid(roleId);
                ry.setJin(jin);
                ry.setYin(yin);
                rmm.insertSelective(ry);
                msg="succes";
            }else{
                Integer roleJin=ry.getJin();
                Integer roleYin=ry.getYin();
                if(roleJin>=jin||roleYin>=yin){
                    if("add".equals(status)){
                        ry.setJin(ry.getJin()+jin);
                        ry.setYin(ry.getYin() + yin);
                    }else if("del".equals(status)){
                        ry.setJin(ry.getJin()-jin);
                        ry.setYin(ry.getYin()-yin);
                    }
                    try {
                        rmm.updateByPrimaryKeySelective(ry);
                        ry.setJin(ry.getJin()+1000);
                       int a=1/0;
                        rmm.updateByPrimaryKeySelective(ry);

                    } catch (Exception e) {
                        throw new RuntimeException();
                    }

                    //msg="succes";


                }
            }
        } catch (RuntimeException e) {
            msg="fail";
            logger.error("钱庄存取银两失败："+e.getMessage());
            e.printStackTrace();
           //throw new RuntimeException();
        }
        return msg;
    }
    @Override
    public String money(HttpSession session, NewRole ne, RoleMoney re, String status) {
        String msg=null;
        String bz=null;
        NewRole nre=null;

        try {
            Integer roleId = (Integer) session.getAttribute("roleId");

            Integer jin=ne.getJin();
            Integer yin=ne.getYin();
            if(jin==null||jin<0){
                jin=0;
            }
            if(yin==null||yin<0){
                yin=0;
            }
             nre=queryRoleMoney(session);
            RoleMoney ry=rmm.selectByRoleid(roleId);
            Integer roleJin=nre.getJin();
            Integer roleYin=nre.getYin();
                MoneyRecord md=new MoneyRecord();
                md.setRoleid(roleId);
                if(ry==null){
                    ry=new RoleMoney();
                    ry.setDate(new Date());
                    ry.setRoleid(roleId);
                    ry.setJin(0);
                    ry.setYin(0);
                    rmm.insertSelective(ry);
                }
                    if("add".equals(status)){
                        if(roleJin>=jin&&roleYin>=yin){
                            nre.setJin(nre.getJin()-jin);
                            nre.setYin(nre.getYin() - yin);
                            ry.setJin(ry.getJin() + jin);
                            ry.setYin(ry.getYin() + yin);
                            md.setDate(new Date());
                            md.setType("存款");
                            if(jin>0){
                                md.setMsg("存"+jin+"金,余额"+ry.getJin()+"金");
                                md.setStatus("金");
                                msg=md.getMsg();
                                moneyrecordmapper.insertSelective(md);
                            }
                            if(yin>0){
                                md.setMsg("存"+yin+"两,余额"+ry.getYin()+"两");
                                md.setStatus("银");
                                msg=md.getMsg();
                                moneyrecordmapper.insertSelective(md);
                            }
                        }else{
                            msg=StatusNum.YEBZ;
                        }


                    }else if("del".equals(status)){
                        if(ry.getJin()>=jin&&ry.getYin()>=yin){
                            nre.setJin(nre.getJin()+jin);
                            nre.setYin(nre.getYin() + yin);
                            ry.setJin(ry.getJin()-jin);
                            ry.setYin(ry.getYin()-yin);
                            md.setDate(new Date());
                            md.setType("取款");
                            if(jin>0){
                                md.setMsg("取"+jin+"金,余额"+ry.getJin()+"金");
                                md.setStatus("金");
                                msg=md.getMsg();
                                moneyrecordmapper.insertSelective(md);
                            }
                            if(yin>0){
                                md.setMsg("取"+yin+"两,余额"+ry.getYin()+"两");
                                md.setStatus("银");
                                msg=md.getMsg();
                                moneyrecordmapper.insertSelective(md);
                            }
                        }else{
                            msg=StatusNum.YEBZ;
                        }

                    }
               NewRole nr=new NewRole();
               nr.setId(nre.getId());
               nr.setJin(nre.getJin());
               nr.setYin(nre.getYin());
                rm.updateByPrimaryKeySelective(nr);
                rmm.updateByPrimaryKeySelective(ry);
                bz="succes";
        } catch (Exception e) {
            msg=StatusNum.ERROR;
           throw new RuntimeException("存取款出现异常");

        }
        if(bz!=null)
            gjsgredisutil.setRole(nre);
        return msg;
    }

    @Override
    public List<GoldBusiness> queryGoldBusiness() {
        List<GoldBusiness> li=null;
        try {
            li=goldbusinessmapper.queryGoldBusiness();
        } catch (Exception e) {
            logger.error("queryGoldBusiness:查询金砖供求信息："+e.getMessage());
        }
        return li;
    }

    @Override
    public List<GoldBusiness> queryGoldShopMsg(GoldBusiness gb) {
        List<GoldBusiness> li= null;
        try {
            if(!StringUtils.isEmpty(gb.getType())&&!StringUtils.isEmpty(gb.getPrice())){
                li=goldbusinessmapper.queryGoldShopMsg(gb);
            }
        } catch (Exception e) {
           logger.error("queryGoldShopMsg:查询详细账单："+e.getMessage());
        }
        return li;
    }

    @Override
    public String sellOrBuy(GoldBusiness gb) {
        String msg=StatusNum.SUCCES;
        try {
            Integer price=gb.getPrice();
            Integer num=gb.getNum();
            Integer totalnum=gb.getNum();
            Integer roleId=gb.getRoleid();
            if(price!=null&&num!=null&&roleId!=null&&price>0&&num>0){
                Integer sum=price*num;
                NewRole re=rm.selectByPrimaryKey(roleId);
                Integer  jin=re.getJin();
                Integer  yin=re.getYin();
                    //卖家消费金砖获得所得银两的95%上交百分之5%的税收
                    //查询金砖交易所税收
                    Tax tx=taxmapper.selectByPrimaryKey(1);
                    Double t=tx.getTax();
                DecimalFormat dt = new DecimalFormat("0");
                    if ("求".equals(gb.getType())) {
                        if(yin>sum){
                            //获取出售的最低价格
                            Integer prices=goldbusinessmapper.minSell();
                            //如果输入的价格大于等于出售的价格，一律按照等于出售价格交易
                            if(price>=prices){
                                gb.setPrice(prices);
                                //真实的交易价格
                                sum=prices*num;
                                gb.setType("供");
                                //处理卖家数据
                                num = getInteger(gb, num, t, dt, prices);
                                re.setJin(re.getJin()+totalnum-num);
                                if(num>0){
                                    //求购比最低出售价格还低的金砖
                                    gb.setNum(num);
                                    gb.setDate(new Date());
                                    gb.setType("求");
                                    gb.setStatus("出售中");
                                    goldbusinessmapper.insertSelective(gb);
                                }

                            }else{
                                //求购比最低出售价格还低的金砖
                                gb.setDate(new Date());
                                gb.setType("求");
                                gb.setStatus("出售中");
                                goldbusinessmapper.insertSelective(gb);
                            }
                            //当求购数量大于供应数量的时候，消耗完供应数量.
                            re.setYin(re.getYin()-totalnum*prices);
                            rm.updateByPrimaryKeySelective(re);
                        }else{
                            return StatusNum.YEBZ;//余额不足
                        }


                    }else if("供".equals(gb.getType())){
                        if(re.getJin()>=gb.getNum()){
                            //如果输入的价格大于等于求购的价格，一律按照等于出求购价格交易
                            //卖家消费金砖获得所得银两的95%上交百分之5%的税收
                            //查询金砖交易所税收
                            Integer prices=goldbusinessmapper.maxBuy();
                            if(price>prices){
                                gb.setDate(new Date());
                                gb.setStatus("出售中");
                                gb.setType("供");
                                goldbusinessmapper.insertSelective(gb);
                            }else{
                                while(num>0){
                                    //获取求购的最高价格
                                    Integer p=goldbusinessmapper.maxBuy();
                                    if(p>0){
                                        //处理买家数据
                                        gb.setType("求");
                                        gb.setPrice(p);
                                        num = getNum(gb, num, p, t);
                                        re.setYin(re.getYin()+Integer.parseInt(dt.format(num*p*t)));
                                        //加入加以记录【未加】
                                    }else{
                                        //当全部售完金砖，并且求购的全部买空时。剩余的加到表中
                                        gb.setDate(new Date());
                                        gb.setStatus("出售中");
                                        gb.setType("供");
                                        gb.setNum(num);
                                        goldbusinessmapper.insertSelective(gb);
                                        num=0;
                                    }


                                }
                            }
                            //当供应数量大于求购数量的时候，消耗完供应数量.
                            re.setJin(re.getJin()-totalnum);
                            rm.updateByPrimaryKeySelective(re);
                        }else{
                            return StatusNum.YEBZ;//余额不足
                        }

                    }
            }
        }catch (Exception e) {
            msg=StatusNum.ERROR;
            logger.error("sellOrBuy求供金砖异常:"+e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public List<GoldBusiness> queryNoTransactionRecordForGold(GoldBusiness gb) {
        List<GoldBusiness> li=null;
        try{
            if(!StringUtils.isEmpty(gb.getType())&&!StringUtils.isEmpty(gb.getRoleid())){
               li= goldbusinessmapper.queryNoTransactionRecordForGold(gb);
            }
        }catch (Exception e){
                logger.error("queryNoTransactionRecordForGold查询金砖未成交记录异常:"+e.getMessage());
        }
        return li;
    }

    @Override
    public String cancelOrder(GoldBusiness gb) {
        String msg=null;
        try {
            GoldBusiness g=goldbusinessmapper.selectByPrimaryKey(gb.getId());
            if(g.getRoleid().equals(gb.getRoleid())&&"出售中".equals(g.getStatus())){
                g.setStatus("已撤销");
                goldbusinessmapper.updateByPrimaryKeySelective(g);
                //将撤销订单返还给玩家
                NewRole role=rm.selectByPrimaryKey(g.getRoleid());
                if("求".equals(g.getType())){
                    role.setYin(role.getYin()+g.getNum()*g.getPrice());
                    msg="您成功撤销订单,系统返还您"+g.getNum()*g.getPrice()+"银两";
                }else{
                    role.setJin(role.getJin()+g.getNum());
                    msg="您成功撤销订单,系统返还您"+g.getNum()+"金钻";
                }
                rm.updateByPrimaryKeySelective(role);

            }else{
                logger.info(gb.getRoleid()+"违规操作，撤销"+g.getRoleid()+"的【"+g.getId()+"】订单");
                msg="您违规操作,已被系统记录!";
            }
        } catch (Exception e) {
            msg="服务器出现问题";
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public Map<String, Object> queryQuKuanLoading(Integer roleId) throws Exception{
        Map<String, Object> mp=new HashMap<String, Object>();
        NewRole role=rm.selectByPrimaryKey(roleId);
        RoleMoney ry=rmm.selectByRoleid(roleId);
        if(ry==null){
            ry=new RoleMoney();
            ry.setDate(new Date());
            ry.setRoleid(roleId);
            ry.setJin(0);
            ry.setYin(0);
            rmm.insertSelective(ry);
        }
        mp.put("role",role);
        mp.put("money",ry);
        return mp;
    }

    @Override
    public List<MoneyRecord> querymoneyRecord(MoneyRecord md) throws Exception{
        List<MoneyRecord> li=null;
        if(!StringUtils.isEmpty(md.getType())&&!StringUtils.isEmpty(md.getStatus())){
            li=moneyrecordmapper.querymoneyRecord(md);
        }
        return li;
    }

    @Override
    public List<GoldRecord> queryTransactionRecordForGold(GoldRecord gb) {
        List<GoldRecord> li=null;
        try{
            if(!StringUtils.isEmpty(gb.getType())&&!StringUtils.isEmpty(gb.getBuyroleid())){
                li= goldrecordmapper.queryTransactionRecordForGold(gb);
            }
        }catch (Exception e){
            logger.error("queryTransactionRecordForGold查询金砖已成交记录异常:"+e.getMessage());
        }
        return li;
    }

    public Integer getInteger(GoldBusiness gb, Integer num, Double t, DecimalFormat dt, Integer prices) throws Exception {
        List<GoldBusiness> ligb=goldbusinessmapper.queryGoldShopMsg(gb);
        GoldRecord gd=new GoldRecord();
        index:
        for(GoldBusiness g:ligb){
             if(num>=g.getNum()){
                 gd.setType("求");
                 gd.setBuyroleid(gb.getRoleid());
                 gd.setBuyrolename(gb.getRolename());
                 gd.setSellnum(g.getNum());
                 gd.setSellroleid(g.getRoleid());
                 gd.setSellrolename(g.getRolename());
                 gd.setDate(new Date());
                 goldrecordmapper.insertSelective(gd);
                num=num-g.getNum();
                g.setNum(0);
                g.setStatus("售罄");
                goldbusinessmapper.updateByPrimaryKeySelective(g);

                 //将金砖所得返回给玩家
                 Double pr=g.getNum()*prices*t;
                 NewRole role= rm.selectByPrimaryKey(g.getRoleid());
                 role.setYin(role.getYin() + Integer.parseInt(dt.format(pr)));
                 rm.updateByPrimaryKeySelective(role);
                 //通知玩家【2016-12-08 14:22:34 您成功出售100个金砖,获得银两1000】
                 StringBuffer sb=new StringBuffer(sdf.format(new Date()));
                 sb.append(" 您成功出售" + g.getNum() + "个金砖,获得银两" + dt.format(pr));
                 systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb.toString()},g.getRoleid().toString());
                 //通知该玩家求购成功【2016-12-08 14:22:34 【系统】:您订购的金砖以单价800的价格成功买入1个】
                 StringBuffer sb2=new StringBuffer(sdf.format(new Date()));
                 sb2.append("【系统】:您订购的金砖以单价"+prices+"的价格成功买入"+g.getNum()+"个");
                 systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb2.toString()},gb.getRoleid().toString());
               }else if(num>0){
                g.setNum(g.getNum()-num);
                goldbusinessmapper.updateByPrimaryKeySelective(g);
                 gd.setType("求");
                 gd.setBuyroleid(gb.getRoleid());
                 gd.setBuyrolename(gb.getRolename());
                 gd.setSellnum(num);
                 gd.setSellroleid(g.getRoleid());
                 gd.setSellrolename(g.getRolename());
                 gd.setDate(new Date());
                 goldrecordmapper.insertSelective(gd);
                 //将金砖所得返回给玩家
                 Double pr=num*prices*t;
                 NewRole role= rm.selectByPrimaryKey(g.getRoleid());
                 role.setYin(role.getYin()+Integer.parseInt(dt.format(pr)));
                 rm.updateByPrimaryKeySelective(role);
                 //通知玩家【2016-12-08 14:22:34 您成功出售100个金砖,获得银两1000】
                 StringBuffer sb=new StringBuffer(sdf.format(new Date()));
                 sb.append(" 您成功出售" +num + "个金砖,获得银两" + dt.format(pr));
                 systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb.toString()},g.getRoleid().toString());
                 //通知该玩家求购成功【2016-12-08 14:22:34 【系统】:您订购的金砖以单价800的价格成功买入1个】
                 StringBuffer sb2=new StringBuffer(sdf.format(new Date()));
                 sb2.append("【系统】:您订购的金砖以单价"+prices+"的价格成功买入"+num+"个");
                 systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb2.toString()},gb.getRoleid().toString());

                 num=0;
                break index;
            }


        }
        return num;
    }

    public Integer getNum(GoldBusiness gb, Integer num, Integer prices, Double t) throws Exception {
        List<GoldBusiness> ligb=goldbusinessmapper.queryGoldShopMsg(gb);
        DecimalFormat dt = new DecimalFormat("0");
        GoldRecord gd=new GoldRecord();
        index:
        for(GoldBusiness g:ligb){
            //将金砖所得返回给玩家
            if(num>=g.getNum()){
                /*记录交易详情*/
                gd.setType("供");
                gd.setBuyroleid(gb.getRoleid());
                gd.setBuyrolename(gb.getRolename());
                gd.setSellnum(g.getNum());
                gd.setSellroleid(g.getRoleid());
                gd.setSellrolename(g.getRolename());
                gd.setDate(new Date());
                goldrecordmapper.insertSelective(gd);
                 /*记录交易详情*/
                num=num-g.getNum();
                g.setNum(0);
                g.setStatus("售罄");
                goldbusinessmapper.updateByPrimaryKeySelective(g);

                Integer pr=g.getNum()*prices;
                NewRole role= rm.selectByPrimaryKey(g.getRoleid());
                role.setJin(role.getJin()+g.getNum());
                rm.updateByPrimaryKeySelective(role);
                //通知玩家【2016-12-08 14:22:34 您成功出售100个金砖,获得银两1000】
                StringBuffer sb=new StringBuffer(sdf.format(new Date()));
                sb.append(" 您成功求购" + g.getNum() + "个金砖,花费银两" + pr);
                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb.toString()},g.getRoleid().toString());
                //通知该玩家求购成功【2016-12-08 14:22:34 【系统】:您供货的金砖以单价800的价格成功出售1个】
                StringBuffer sb2=new StringBuffer(sdf.format(new Date()));
                sb2.append("【系统】:您供货的金砖以单价"+prices+"的价格成功出售"+g.getNum()+"个");
                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb2.toString()},gb.getRoleid().toString());

            }else if(num>0){
                g.setNum(g.getNum()-num);
                goldbusinessmapper.updateByPrimaryKeySelective(g);
                gd.setType("供");
                gd.setBuyroleid(gb.getRoleid());
                gd.setBuyrolename(gb.getRolename());
                gd.setSellnum(num);
                gd.setSellroleid(g.getRoleid());
                gd.setSellrolename(g.getRolename());
                gd.setDate(new Date());
                goldrecordmapper.insertSelective(gd);
                Integer pr=num*prices;
                NewRole role= rm.selectByPrimaryKey(g.getRoleid());
                role.setJin(role.getJin()+num);
                rm.updateByPrimaryKeySelective(role);
                //通知玩家【2016-12-08 14:22:34 您成功出售100个金砖,获得银两1000】
                StringBuffer sb=new StringBuffer(sdf.format(new Date()));
                sb.append(" 您成功求购" +num + "个金砖,花费银两" +pr);
                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb.toString()},g.getRoleid().toString());
                //通知该玩家求购成功【2016-12-08 14:22:34 【系统】:您供货的金砖以单价800的价格成功出售1个】
                StringBuffer sb2=new StringBuffer(sdf.format(new Date()));
                sb2.append("【系统】:您供货的金砖以单价"+prices+"的价格成功出售"+num+"个");
                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,sb2.toString()},gb.getRoleid().toString());

                num=0;
                break index;
            }


        }
        return num;
    }

}

package com.hxsg.wupin.service.impl;

import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.*;
import com.hxsg.po.*;
import com.hxsg.system.dao.SystemNotification;
import com.hxsg.vo.IndexFuJiangVo;
import com.hxsg.wupin.service.Cocos2dUserWuPinService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2016/10/25.
 */
@Service("Cocos2dUserWuPinService")
public class Cocos2dUserWuPinServiceImpl implements Cocos2dUserWuPinService {
    private Logger logger =Logger.getLogger(cocos2dWuPinServiceImpl.class);
    @Autowired
    FuJiangMapper fujiangmapper;
    @Autowired
    SkillMapper skillmapper;
    @Autowired
    WuqiDescribeMapper wuqidescribemapper;
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
    RoleFujiangMapper rolefujiangmapper;
    @Autowired
    FjLevelJyMapper fjleveljymapper;
    @Autowired
    RoleNewShuXingMapper rolenewshuxingmapper;
    @Autowired
    SkillDescribeMapper skilldescribemapper;
    @Autowired
    NewRoleMapper newrolemapper;
    //使用道具
    @Override
    public String userDaoJu(Integer wupinId,Integer roleId,Integer num,Integer jnId,Integer fjId) throws Exception{
        String msg="";
//        RoleWuPin rn=rolewupinmapper.selectByPrimaryKey(wupinId);
//        int sumNum = rn.getNum() -num;
//        rn.setNum(sumNum);
//              int a = rolewupinmapper.updateByPrimaryKeySelective(rn);
            RoleWuPin rn=rolewupinmapper.selectByPrimaryKey(wupinId);
            //验证物品
            if(rn!=null&&roleId.equals(rn.getRoleid())&&num>0){
                String type=rn.getType1();
                int sumNum = rn.getNum() -num;
                if(sumNum>=0){
                    rn.setNum(sumNum);
                    int a = rolewupinmapper.updateByPrimaryKeySelective(rn);
                    // int cc=1/0;

                    switch (type){
                        case "技能":{
                            //使用技能书学习技能
                            try {
                                msg=userJiNengBook(roleId,fjId,rn);
                            } catch (Exception e) {
                                msg="已经学过该技能，不需要在学习了";
                                throw new RuntimeException("已经学习过该技能了，不需要再次学习");

                            }
                            break;
                        }
                        case "技能书":
                        {
                            //使用技能熟练度书，提升熟练度
                            msg = userJiNengShu(roleId, num, jnId, rn);
                            break;
                        }
                        case "令":{
                            msg=getFuJiang(roleId, rn);
                            break;
                        }
                        case "动":{
                            break;
                        }
                        case "心法书":{
                            msg=UserXinFaShu(fjId, rn, roleId, num);
                            break;
                        }
                    }
                }else{
                    msg="数量不足,请重新输入";
                }

            }
        return msg;
    }
    private String userJiNengBook(Integer roleId,Integer fjId,RoleWuPin r) throws Exception{
        String msg="";
        List<IndexFuJiangVo>  li = queryFuJiangJiNengBook(r.getId(),roleId);
            for(IndexFuJiangVo v:li){
                if(fjId.equals(v.getRoleId())){
                    //学习技能
                    SkillDescribe skill=new SkillDescribe();
                    skill.setName(r.getName());
                    List<SkillDescribe> liSkill=skilldescribemapper.selectAll(skill);
                    if(liSkill!=null&&liSkill.size()>0){
                        Skill s=new Skill();
                        s.setSkillid(liSkill.get(0).getId());
                        s.setRfid(fjId);
                        List<Skill> lis=skillmapper.selectAll(s);
                        if(lis!=null&&lis.size()>0){
                            msg="已经学过该技能，不需要在学习了";
                            //抛异常事物回滚
                            throw new Exception();
                        }else{
                            //插入技能表
                            Skill sk=new Skill();
                            sk.setDate(new Date());
                            sk.setLevel("1");
                            sk.setShuliandu(0);
                            sk.setType(roleId);
                            sk.setRfid(fjId);
                            RoleFujiang rf=rolefujiangmapper.selectByPrimaryKey(fjId);
                            if(rf==null){
                                NewRole role=newrolemapper.selectByPrimaryKey(fjId);
                                sk.setRolename(role.getRolename());

                            }else{
                                sk.setRolename(rf.getFujiangname());

                            }
                            sk.setSkillid(liSkill.get(0).getId());
                            sk.setSkillname(liSkill.get(0).getName());
                            skillmapper.insertSelective(sk);
                            msg="恭喜【"+sk.getRolename()+"】学会了新的技能【"+sk.getSkillname()+"】";
                        }


                    }
                }else{
                    msg="违规操作，已被系统记录";
                    logger.info(roleId+"试图将不符合该技能的副将去学习此技能");
                }
            }
        //判断
        return msg;
    }
    private String userJiNengShu(Integer roleId, Integer num, Integer jnId,RoleWuPin rn) {
        String msg="";
        Skill skill=skillmapper.selectByPrimaryKey(jnId);
        String level=skill.getLevel();
            if("一级技能书".equals(rn.getName())){
                if(level.equals("1")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if(skill.getShuliandu()>=6000){
                        skill.setLevel("2");
                    }
                    msg=level+"级技能【"+skill.getSkillname()+"】成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
            }
            if("二级技能书".equals(rn.getName())){
                if(level.equals("2")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=12000){
                        skill.setLevel("3");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
            }
            if("三级技能书".equals(rn.getName())){
                if(level.equals("3")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=18000){
                        skill.setLevel("4");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
            }
            if("四级技能书".equals(rn.getName())){
                if(level.equals("4")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=24000){
                        skill.setLevel("5");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
            }
            if("五级技能书".equals(rn.getName())){
                if(level.equals("5")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=30000){
                        skill.setShuliandu(0);
                        skill.setLevel("化境1");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
                if(level.equals("化境1")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=30000){
                        skill.setShuliandu(0);
                        skill.setLevel("化境2");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
                if(level.equals("化境2")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=30000){
                        skill.setShuliandu(0);
                        skill.setLevel("化境3");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
                if(level.equals("化境3")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=30000){
                        skill.setShuliandu(0);
                        skill.setLevel("化境4");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
                if(level.equals("化境4")){
                    skill.setShuliandu(skill.getShuliandu()+num*100);
                    if((skill.getShuliandu()+num*100)>=30000){
                        skill.setShuliandu(0);
                        skill.setLevel("化境5");
                    }
                    msg="成功提高熟练度至"+skill.getShuliandu();
                }else{
                    msg="无法使用不相符的心法书给副将增加熟练度";
                }
            }
            skillmapper.updateByPrimaryKey(skill);
        return msg;
    }

    @Override
    public Object[] peiYangFuJiang(Integer fuId, Integer roleId,String type) throws Exception{
        Object[] ot=null;
        String msg="";
        try {
            RoleWuPin rn=new RoleWuPin();
            rn.setName("将军令");
            rn.setRoleid(roleId);
           List<RoleWuPin>  lirn=rolewupinmapper.selectAll(rn);
            if(lirn.size()==1&&lirn!=null){
                RoleWuPin r=lirn.get(0);
            if(r.getNum()>0){
                r.setNum(r.getNum()-1);
                int a=rolewupinmapper.updateByPrimaryKeySelective(r);
                if(a>0){
                    RoleFujiang rfg=rolefujiangmapper.selectByPrimaryKey(fuId);
                    FuJiang fuJiang=fujiangmapper.selectByPrimaryKey(rfg.getFuid());
                    float cz=rfg.getChengzhang();
                    switch (type){
                        case "成长":{
                            DecimalFormat dt =new DecimalFormat("0.00");
                            float ft=(float) (fuJiang.getChengzhang() + (Math.floor(Math.random() * 20 - 19.0)) / 100);
                            rfg.setChengzhang(Float.parseFloat(dt.format(ft)));
                            break;
                        }
                        case "气血":{
                            int ft=fuJiang.getChuxue() + ((int) Math.round(Math.random() * 20 - 19));
                            rfg.setChuxue(ft);
                            rfg.setTotalxue2((int)(cz*(rfg.getQixueds()*rfg.getLeve())+ft*rfg.getLeve()*cz*0.8 ));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
                            break;
                        }
                        case "精力":{
                            int ft=fuJiang.getChujing() + ((int) Math.round(Math.random() * 20 - 19));
                            rfg.setChujing(ft);
                            rfg.setTotaljing2((int)(cz*(rfg.getJinglids()*rfg.getLeve())+ ft*rfg.getLeve()*cz*0.8));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
                            break;
                        }
                        case "攻击":{
                            int ft=fuJiang.getChugong() + ((int) Math.round(Math.random() * 20 - 19));
                            rfg.setChugong(ft);
                            rfg.setTotalgong((int)((ft*cz*rfg.getLeve())/7+ft*cz*0.5+rfg.getLeve()*cz*rfg.getGongjids()*0.2));
                            break;
                        }
                        case "敏捷":{
                            int ft=fuJiang.getChusu() + ((int) Math.round(Math.random() * 20 - 19));
                            rfg.setChusu(ft);//随机+-20设置速度属性
                            float dufuf=cz*(ft+rfg.getSududs());
                            int sudu=(int)dufuf;
                            rfg.setTotalsudu(sudu);
                            break;
                        }
                    }
                    rolefujiangmapper.updateByPrimaryKeySelective(rfg);
                    ot=new Object[]{"1",rfg};
                }
            }else{

                msg="你没有足够的将军令";
                ot=new Object[]{"0",msg};
            }
            }else{
                msg="你没有足够的将军令";
                ot=new Object[]{"0",msg};
            }
            //int a=9/0;
        } catch (Exception e) {
            logger.error("服务器异常："+e.getMessage());
            throw new Exception();

        }
        return ot;
    }

    /**
     * 获取技能书使用的人物和技能列表
     * @param roleid 角色ID
     * @param wupinId  物品ID
     * @return
     * @throws Exception
     */
    @Override
    public List<Skill> querySkillForRole(Integer roleid,Integer wupinId,Integer fuId){
        List<Skill> lisk=null;
        try {
            RoleWuPin rn=rolewupinmapper.selectByPrimaryKey(wupinId);
            Integer dengji=0;
            switch (rn.getName()){
                case "一级技能书":{
                    dengji=1;
                    break;
                }
                case "二级技能书":{
                    dengji=2;
                    break;
                }
                case "三级技能书":{
                    dengji=3;
                    break;
                }
                case "四级技能书":{
                    dengji=4;
                    break;
                }
                case "五级技能书":{
                    dengji=5;
                    break;
                }
            }
            if(fuId==null){
                lisk= skillmapper.queryNameForRole(roleid,dengji);
            }else{
                lisk= skillmapper.querySkillForRole(roleid,dengji,fuId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lisk;
    }

    /**
     * 加载使用心法书的副将界面
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleFujiang> getRoleFuforUserXinFaShu(Integer roleid, Integer id)  {
        List<RoleFujiang> li=null;
        try {
            RoleWuPin  rn = rolewupinmapper.selectByPrimaryKey(id);
            if(rn.getRoleid().equals(roleid)){
                   Integer level1=null;
                   Integer level2=null;

                switch (rn.getName()){
                    case"初级副将心法":{
                        level1=1;
                        level2=40;
                        break;
                    }
                    case"高级副将心法":{
                        level1=41;
                        level2=70;
                        break;
                    }
                    case"特级副将心法":{
                        level1=71;
                        level2=100;
                        break;
                    }
                    case"顶级副将心法":{
                        level1=101;
                        level2=200;
                        break;
                    }

                }
                if(level1!=null&&level2!=null){
                    li =rolefujiangmapper.getByRoleIdShuxLevel(roleid,1,level1,level2);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }

    @Override
    public List<IndexFuJiangVo> queryFuJiangJiNengBook( Integer wuPinId,Integer roleId) {
        List<IndexFuJiangVo> li=new ArrayList<IndexFuJiangVo>();
        try {
            RoleWuPin rn = rolewupinmapper.selectByPrimaryKey(wuPinId);
            NewRole role=newrolemapper.selectByPrimaryKey(roleId);
            String zhiYe=null;
            String sex=null;
            if(rn!=null){
                SkillDescribe sd=new SkillDescribe();
                sd.setName(rn.getName());
               List<SkillDescribe> lisd=skilldescribemapper.selectAll(sd);
               if(lisd!=null&&lisd.size()>0){
                   zhiYe=lisd.get(0).getZhiye();
                   switch (lisd.get(0).getXingbie()){
                       case "1":{
                           sex="女";
                           break;
                       }
                       case "0":{
                           sex="男";
                           break;
                       }
                   }
               }

            }
            if(role!=null){
                Boolean re1=false;
                Boolean re2=null;
                if(zhiYe!=null){
                    re1=role.getZhiye().equals(zhiYe);
                }
                if(sex!=null){
                    re2=role.getSex().equals(sex);
                }
               if(re2==null||re2){

                   //将角色加载进去，未来还需要根据角色是否转生添加技能学习的限制
                   IndexFuJiangVo fv=new IndexFuJiangVo();
                   fv.setName("("+role.getLevel()+"级"+role.getZhiye()+")"+role.getRolename());
                   fv.setRoleId(roleId);
                   li.add(fv);
                   //将副将加载进去
                   RoleFujiang rf=new RoleFujiang();
                   rf.setRoleid(roleId);
                   rf.setShuxing(1);
                   rf.setZhiye(zhiYe);
                   rf.setSex(sex);
                   List<RoleFujiang> liFu=rolefujiangmapper.selectAll(rf);
                   for(RoleFujiang r:liFu){
                       int num=1+  r.getZhuansheng();
                       Skill s=new Skill();
                       s.setRfid(r.getId());
                       List<Skill> lis=skillmapper.selectAll(s);
                       if(lis.size()<num){
                           IndexFuJiangVo fj=new IndexFuJiangVo();
                           fj.setName("("+r.getLeve()+"级"+r.getZhiye()+")"+r.getFujiangname());
                           fj.setRoleId(r.getId());
                           li.add(fj);
                       }
                   }

               }
            }
        } catch (Exception e) {
            logger.error("queryFuJiangJiNengBook()加载获取学习技能的副将异常:" + e.getMessage());
        }
        return li;
    }

    private String getFuJiang(Integer roleId, RoleWuPin rn) throws Exception {
        String msg = "";
        String name = rn.getName();
            int rand = (int) (Math.random() * 1000);
            int fjid = 0;
            Integer gailv = 0;
            if ("将军令".equals(name)) {
                gailv = 500;
                fjid = (int) (Math.random() * 7 + 1);
            }
            if (name.equals("大将军令")) {
                gailv = 500;//900
                fjid = (int) (Math.random() * 2 + 8);
            }
            if (name.equals("猛将军令")) {
                gailv = 500;//950
                fjid = (int) (Math.random() * 2 + 10);
            }
            if (name.equals("元帅将军令")) {
                gailv = 500;  //995
                fjid = (int) (Math.random() * 1 + 13);
            }
            if (rand > gailv) {//百分之40的概率产生
                FuJiang fg = fujiangmapper.selectByPrimaryKey(fjid);
                RoleFujiang rf = new RoleFujiang();
                DecimalFormat dt = new DecimalFormat("0.00");
                float cz = ((float) (Math.floor(Math.random() * 21 - 20.0)) / 100) + fg.getChengzhang();
                rf.setChengzhang(Float.parseFloat(dt.format(cz)));
                rf.setChufang(fg.getChufang());
                rf.setChugong(fg.getChugong());
                int sd = ((int) Math.round(Math.random() * 21 - 20));
                rf.setChusu(fg.getChusu() + sd);//随机+-20设置速度属性
                int tsudu = (int) (cz * (fg.getChusu() + sd + 1));
                rf.setTotalsudu(tsudu);
                rf.setZhuansheng(1);
                rf.setZhen("0");
                int qx = ((int) Math.round(Math.random() * 21 - 20));
                rf.setChuxue(fg.getChuxue() + qx);
                rf.setTotalxue2((int) (cz * (1 * 1) + cz * (fg.getChuxue() + qx) * 1 * 0.8));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
                rf.setTotalxue1((int) (cz * (1 * 1) + cz * (fg.getChuxue() + qx) * 1 * 0.8));
                int jl = ((int) Math.round(Math.random() * 21 - 20));
                rf.setChujing(fg.getChujing() + jl);
                rf.setTotaljing1((int) (cz * (1 * 1) + cz * (fg.getChujing() + jl) * 1 * 0.8));
                rf.setTotaljing2((int) (cz * (1 * 1) + cz * (fg.getChujing() + jl) * 1 * 0.8));
                int gj = ((int) Math.round(Math.random() * 21 - 20));
                rf.setChugong(fg.getChugong() + gj);
                rf.setTotalgong((int) ((1 * cz * (fg.getChugong() + gj)) / 7 + (fg.getChugong() + gj) * cz * 0.5 + 1 * cz * 0.2 * 1));
                rf.setFuid(fg.getId());
                rf.setFujiangname(fg.getFujiangname());
                rf.setRoleid(roleId);
                rf.setLeve(1);
                rf.setSjjinyan(100);
                rf.setSumds(5);
                rf.setKeyongds(4);
                rf.setQixueds(1);
                rf.setJinglids(1);
                rf.setGongjids(1);
                rf.setSududs(1);
                rf.setSex(fg.getSex());
                rf.setStatus("战斗");
                int zy = (int) (Math.random() *3);
                switch (zy){
                    case 0:
                        rf.setZhiye("武士");
                        break;
                    case 1:
                        rf.setZhiye("文人");
                        break;
                    case 2:
                        rf.setZhiye("异人");
                        break;
                }

                rf.setTouxian(fg.getTouxian());
                rf.setTouxiang(fg.getTouxiang());
                List<RoleFujiang> rolefu = rolefujiangmapper.getByRoleIdShux(roleId, 1);

                if (rolefu.size() > 10) {
                    rf.setShuxing(0);
                 systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG5,"随行副将大于10个，招募的副将已放入驿馆"},roleId.toString());
                } else {
                    rf.setShuxing(1);
                }
                rf.setDate(new Date());
                int aa = rolefujiangmapper.insertSelective(rf);
                insertRoleShuXing( rf.getZhiye(),rf.getId(),"1");
                //根据职业随机技能
//                String sex="";
//                if(fg.getSex().equals("男")){
//                    sex="0";
//                }else{
//                    sex="1";
//                }
//                List<SkillDescribe> skillLi=skilldescribemapper.querySkillByZhiYe(zhiye,sex,"3");
//                int s=skillLi.size();
//                 s = (int) (Math.random() *s);
//                SkillDescribe skill=skillLi.get(s);
//                //插入技能表
//                Skill sk=new Skill();
//                sk.setDate(new Date());
//                sk.setLevel("1");
//                sk.setShuliandu(0);
//                sk.setType(roleId);
//                sk.setRfid(rf.getId());
//                sk.setRolename(rf.getFujiangname());
//                sk.setSkillid(skill.getId());
//                //还需要根据性别选择技能
//                skillmapper.insertSelective(sk);
                msg = "恭喜你获得副将【<color=#FAF116>" + rf.getFujiangname() + "</color>】(ID:" + rf.getId() + ")";
            } else {
                msg = "很遗憾您没有招到副将！";
            }
        return  msg;
    }

    private RoleFujiang getRoleFuJiang(RoleFujiang rf, Integer level) {
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
    public String UserXinFaShu(Integer fuId ,RoleWuPin rn,Integer roleId,Integer num) throws Exception{
        String msg="";
        try {
                    RoleFujiang rf=rolefujiangmapper.selectByPrimaryKey(fuId);
                    //验证副将是否属于玩家
                    if(roleId.equals(rf.getRoleid())) {
                        System.out.println("-------------");

                        if (rn.getName().equals("副将经验书") && rf.getLeve() <= 11) {
                            rf = getRoleFuJiang(rf, 1);
                            rolefujiangmapper.updateByPrimaryKeySelective(rf);
                            return msg = "恭喜你副将升到11级";
                        }
                        int jy = rf.getLeve();
                        //设置经验书使用后获得的经验
                        if (rf.getJingyan() == null) {
                            rf.setJingyan(60 * jy * jy * num);
                        }
                        //初始化总经验
                        if (rf.getTotajy() == null) {
                            switch (rf.getTouxian()) {
                                case "英才": {
                                    rf.setTotajy(fjleveljymapper.sumyingcai(rf.getLeve() + 1));
                                    break;

                                }
                                case "将才": {
                                    rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve() + 1));
                                    break;

                                }
                                case "猛将": {
                                    rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve() + 1));
                                    break;

                                }
                                case "元帅": {
                                    rf.setTotajy(fjleveljymapper.sumyuanshuai(rf.getLeve() + 1));
                                    break;

                                }
                            }
                        }
                        //升级经验初始化或者升级经验减去经验书获得经验
                        if (rf.getSjjinyan() == null) {
                            switch (rf.getTouxian()) {
                                case "英才": {
                                    rf.setSjjinyan((20 * ((jy + 1) * (jy + 1) * (jy + 1) + 5 * (jy + 1)) - 40));
                                    break;

                                }
                                case "将才": {
                                    rf.setSjjinyan((30 * ((jy + 1) * (jy + 1) * (jy + 1) + 5 * (jy + 1)) - 80));
                                    break;

                                }
                                case "猛将": {
                                    rf.setSjjinyan((30 * ((jy + 1) * (jy + 1) * (jy + 1) + 5 * (jy + 1)) - 80));
                                    break;

                                }
                                case "元帅": {
                                    rf.setSjjinyan((40 * ((jy + 1) * (jy + 1) * (jy + 1) + 5 * (jy + 1)) - 80));
                                    break;

                                }
                            }

                        }

                        if (rf.getSjjinyan() > 0) {
                            rf.setSjjinyan(rf.getSjjinyan() - 60 * jy * jy * num);
                            msg = "副将获得" + 60 * jy * jy * num + "经验";
                        } else if (rf.getSjjinyan() <= 0) {//升了一级之后的属性
                            rf = getRoleFuJiang(rf, 1);
                            msg = "副将获得" + 60 * jy * jy * num + "经验，恭喜你副将升到" + (jy + 1) + "级";
                        }
                        rolefujiangmapper.updateByPrimaryKeySelective(rf);

                    }

        }catch (Exception e) {
            throw new Exception();
        }
        return msg;
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
















}

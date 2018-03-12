package com.hxsg.rolefujaing.service.impl;

import com.hxsg.Dao.*;
import com.hxsg.po.RoleWuPin;
import com.hxsg.po.Skill;
import com.hxsg.po.roleFujiang;
import com.hxsg.rolefujaing.service.Cocos2dFuJiangService;
import com.hxsg.system.dao.impl.SystemNotificationImpl;
import com.hxsg.vo.FuJiangDetailVo;
import com.hxsg.vo.PeiYangFuJiangVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dlf on 2016/10/26.
 */
@Service("Cocos2dFuJiangService")
public class Cocos2dFuJiangServiceImpl implements Cocos2dFuJiangService {
    @Autowired
    WuqiDescribeMapper wuqidescribemapper;
    private Logger logger =Logger.getLogger(Cocos2dFuJiangServiceImpl.class);
    @Autowired
    RoleWuPinMapper rolewupinmapper;
    @Autowired
    ZaWuMiaoShuMapper zawumiaoshumapper;
    @Autowired
    roleFujiangMapper rolefujiangmapper;
    @Autowired
    FuJiangMapper fujiangmapper;
    @Autowired
    SkillMapper skillmapper;
    //加载副将培养界面
    @Override
    public PeiYangFuJiangVo loadPeiYangFuJiang(Integer fuid, Integer roleid) {
        PeiYangFuJiangVo pv=null;
        try {
             pv= new PeiYangFuJiangVo();
            roleFujiang rf=new roleFujiang();
            rf.setRoleid(roleid);
            rf.setId(fuid);
            rf.setStatus("战斗");
            List<roleFujiang> lirf=rolefujiangmapper.selectAll(rf);
            if(lirf!=null&lirf.size()==1){
                RoleWuPin rn=new RoleWuPin();
                rn.setName("将军令");
                rn.setRoleid(roleid);
                List<RoleWuPin>  lirn=rolewupinmapper.selectAll(rn);
                if(lirn.size()==1&&lirn!=null){
                    pv.setJjMsg("<color=#E3E303>您现在拥有"+lirn.get(0).getNum()+"枚将军令</c>");
                }else{
                    pv.setJjMsg("<color=#E3E303>您现在拥有0枚将军令</c>");
                }
                pv.setCzl("成长率:"+lirf.get(0).getChengzhang());
                pv.setJl("精力::"+lirf.get(0).getChujing());
                pv.setGj("攻击:"+lirf.get(0).getChugong());
                pv.setMj("敏捷:"+lirf.get(0).getChusu());
                pv.setFuName("副将:"+lirf.get(0).getFujiangname());
                pv.setQx("气血:"+lirf.get(0).getChuxue());
                pv.setFuId(lirf.get(0).getFuid());
            }else{
                pv.setTiShiMsg("不能培养参战状态下的副将");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pv;
    }

    @Override
    public FuJiangDetailVo loadRoleFuJiang(Integer roleid, Integer fuid) {
        FuJiangDetailVo fo=null;
        try {
            fo=new FuJiangDetailVo();
            roleFujiang r=rolefujiangmapper.selectByPrimaryKey(fuid);
            if(roleid.equals(r.getRoleid())){
                List<Skill> lis=skillmapper.selectById(fuid);
                fo.setJiNeng(lis);
                fo.setName(r.getFujiangname());
                fo.setId(r.getId());
                fo.setFangYu(r.getChufang());
                fo.setGongJi(r.getTotalgong());
                fo.setMoQiDu(0);
                fo.setJingLi1(r.getTotaljing1());
                fo.setJingLi2(r.getTotaljing2());
                fo.setQiXue1(r.getTotalxue1());
                fo.setQiXue2(r.getTotalxue2());
                fo.setShengJiJingYan("升级还需要"+r.getSjjinyan()+"经验");
                fo.setShuXingDian(0);
                fo.setSuDu(r.getTotalsudu());
                fo.setZhongChengDu(r.getZhongchengdu()+"/100");
                fo.setTouXian(r.getTouxian());
                fo.setZhiYe(r.getLeve()+"级");//职业还需添加
                fo.setImgUrl("fu.png");//副将图片还未定义
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fo;
    }
}

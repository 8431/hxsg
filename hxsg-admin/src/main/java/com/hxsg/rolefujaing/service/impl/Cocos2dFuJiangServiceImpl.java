package com.hxsg.rolefujaing.service.impl;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.Dao.*;
import com.hxsg.po.RoleFujiang;
import com.hxsg.po.RoleWuPin;
import com.hxsg.po.Skill;
import com.hxsg.rolefujaing.service.Cocos2dFuJiangService;
import com.hxsg.vo.FuJiangDetailVo;
import com.hxsg.vo.PeiYangFuJiangVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/10/26.
 */
@Service("Cocos2dFuJiangService")
public class Cocos2dFuJiangServiceImpl implements Cocos2dFuJiangService {
    @Autowired
    WuqiDescribeMapper wuqidescribemapper;
    private Logger logger = Logger.getLogger(Cocos2dFuJiangServiceImpl.class);
    @Autowired
    RoleWuPinMapper rolewupinmapper;
    @Autowired
    ZaWuMiaoShuMapper zawumiaoshumapper;
    @Autowired
    RoleFujiangMapper rolefujiangmapper;
    @Autowired
    FuJiangMapper fujiangmapper;
    @Autowired
    SkillMapper skillmapper;
    @Autowired
    HxsgBaseDaoMapper hxsgbasedaomapper;

    //加载副将培养界面
    @Override
    public PeiYangFuJiangVo loadPeiYangFuJiang(Integer fuid, Integer roleid) {
        PeiYangFuJiangVo pv = null;
        try {
            pv = new PeiYangFuJiangVo();
            RoleFujiang rf = new RoleFujiang();
            rf.setRoleid(roleid);
            rf.setId(fuid);
            rf.setStatus("战斗");
            List<RoleFujiang> lirf = rolefujiangmapper.selectAll(rf);
            if (lirf != null & lirf.size() == 1) {
                RoleWuPin rn = new RoleWuPin();
                rn.setName("将军令");
                rn.setRoleid(roleid);
                List<RoleWuPin> lirn = rolewupinmapper.selectAll(rn);
                if (lirn.size() == 1 && lirn != null) {
                    pv.setJjMsg("<color=#E3E303>您现在拥有" + lirn.get(0).getNum() + "枚将军令</c>");
                } else {
                    pv.setJjMsg("<color=#E3E303>您现在拥有0枚将军令</c>");
                }
                pv.setCzl("成长率:" + lirf.get(0).getChengzhang());
                pv.setJl("精力::" + lirf.get(0).getChujing());
                pv.setGj("攻击:" + lirf.get(0).getChugong());
                pv.setMj("敏捷:" + lirf.get(0).getChusu());
                pv.setFuName("副将:" + lirf.get(0).getFujiangname());
                pv.setQx("气血:" + lirf.get(0).getChuxue());
                pv.setFuId(lirf.get(0).getFuid());
            } else {
                pv.setTiShiMsg("不能培养参战状态下的副将");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pv;
    }

    @Override
    public FuJiangDetailVo loadRoleFuJiang(Integer roleid, Integer fuid) {
        FuJiangDetailVo fo = null;
        try {
            fo = new FuJiangDetailVo();
            RoleFujiang r = rolefujiangmapper.selectByPrimaryKey(fuid);
            if (roleid.equals(r.getRoleid())) {
                List<Skill> lis = skillmapper.selectById(fuid);
                if (lis != null && lis.size() > 0) {
                    fo.setJiNeng(lis);
                }
                fo.setName(r.getFujiangname());
                fo.setId(r.getId());
                fo.setFangYu(r.getChufang());
                fo.setGongJi(r.getTotalgong());
                fo.setMoQiDu(0);
                fo.setJingLi1(r.getTotaljing1());
                fo.setJingLi2(r.getTotaljing2());
                fo.setQiXue1(r.getTotalxue1());
                fo.setQiXue2(r.getTotalxue2());
                fo.setShengJiJingYan("升级还需要" + r.getSjjinyan() + "经验");
                fo.setShuXingDian(0);
                fo.setSuDu(r.getTotalsudu());
                fo.setZhongChengDu(r.getZhongchengdu() + "/100");
                fo.setTouXian(r.getTouxian());
                fo.setZhiYe(r.getLeve() + "级"+r.getZhiye());//职业还需添加
                fo.setImgUrl(r.getTouxiang());//副将图片还未定义
                fo.setQixueds(r.getQixueds());
                fo.setJinglids(r.getJinglids());
                fo.setGongJids(r.getGongjids());
                fo.setSududs(r.getSududs());
                fo.setKeyongds(r.getKeyongds());
                fo.setChengzhang(r.getChengzhang());
                fo.setChuxue(r.getChuxue());
                fo.setChujing(r.getChujing());
                fo.setChugong(r.getChugong());
                fo.setChusu(r.getChusu());
                fo.setLevel(r.getLeve());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fo;
    }

    @Override
    public void commitSxd(FuJiangDetailVo f) throws CommonException {
        try {
            Integer fuid = f.getId();//副将id
            RoleFujiang rf = rolefujiangmapper.selectByPrimaryKey(fuid);
            if (f.getRoleid().equals(rf.getRoleid())) {
                Integer qxds = f.getQixueds();
                Integer jlds = f.getJinglids();
                Integer gjds = f.getGongJids();
                Integer sdds = f.getSududs();
                //判断加点是否有修改
                Integer rfqxds = rf.getQixueds();
                Integer rfjlds = rf.getJinglids();
                Integer rfgjds = rf.getGongjids();
                Integer rfsdds = rf.getSududs();
                Integer kyds = (qxds + jlds + gjds + sdds) - (rfqxds + rfjlds + rfgjds + rfsdds);
                if (kyds <= rf.getKeyongds() && kyds > 0) {
                    //正常操作
                    rf.setQixueds(qxds);
                    rf.setJinglids(jlds);
                    rf.setGongjids(gjds);
                    rf.setSududs(sdds);
                    rf.setKeyongds(rf.getKeyongds() - kyds);
                    //气血计算公式
                    rf.setTotalxue2((int) Math.round(rf.getChengzhang() * rf.getLeve() * (qxds + rf.getChuxue() * 0.8)));
                    //精力计算公式
                    rf.setTotaljing2((int) Math.round(rf.getChengzhang() * rf.getLeve() * (jlds + rf.getChujing() * 0.8)));
                    //攻击计算公式
                    rf.setTotalgong((int) Math.round((rf.getLeve() * rf.getChengzhang() * rf.getChugong()) / 7 + rf.getChugong() * rf.getChengzhang() * 0.5 + rf.getLeve() * rf.getChengzhang() * gjds * 0.2));
                    //速度公式
                    rf.setTotalsudu((int) Math.round(rf.getChengzhang() * (rf.getChusu() + rfsdds)));

                    rolefujiangmapper.updateByPrimaryKeySelective(rf);
                } else {
                    logger.info(f.getRoleid() + ":违规加点------作弊");
                }

            } else {
                logger.info(f.getRoleid() + ":设置不属于自己的副将------作弊");
            }

        } catch (Exception e) {
            logger.error("commitSxd异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "commitSxd异常" + e.getMessage(), e);
        }
    }

    @Override
    public String setFjState(FuJiangDetailVo f) throws CommonException {
        String re = "";
        try {
            Integer fuid = f.getId();//副将id
            Integer roleid = f.getRoleid();
            Map<String, Object> param = new HashMap<>();
            param.put("sql", "select  status  from role_fujiang where roleid=#{roleid} and shuxing='1' and id=#{fuid}");
            param.put("fuid", fuid);
            param.put("roleid", roleid);
            List<Map<String, Object>> paramLi2 = hxsgbasedaomapper.QuerySql(param);
            if (paramLi2 != null && paramLi2.size() > 0) {
                String status = (String) paramLi2.get(0).get("status");
                if ("休息".equals(status)) {
                    //设置战斗
                    param.put("sql", "update role_fujiang set status='战斗' where id=#{fuid}  ");
                    hxsgbasedaomapper.UpdateSql(param);
                } else {
                    param.put("sql", "select  id from role_fujiang where roleid=#{roleid} and shuxing='1' and status='休息' order by date desc ");
                    param.put("roleid", roleid);
                    List<Map<String, Object>> paramLi = hxsgbasedaomapper.QuerySql(param);
                    if (paramLi != null && paramLi.size() >=0) {
                        if (paramLi.size() < 3) {
                            param.put("sql", "update role_fujiang set status='休息' where id=#{fuid}  ");
                            hxsgbasedaomapper.UpdateSql(param);
                        } else {
                            //替换副将
                            Long id = (Long) paramLi.get(2).get("id");
                            param.put("sql", "update role_fujiang set status='战斗' where id=#{fuid}  ");
                            param.put("fuid", id);
                            hxsgbasedaomapper.UpdateSql(param);
                            param.put("sql", "update role_fujiang set status='休息' where id=#{fuid}  ");
                            param.put("fuid", fuid);
                            hxsgbasedaomapper.UpdateSql(param);
                        }
                    }
                }
            }else{
                logger.info(f.getRoleid() + ":违规设置副将参战状态------作弊");
                re="违规设置副将参战状态------作弊";
            }
            re="设置成功！";
        } catch (Exception e) {
            logger.error("setFjState异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "setFjState异常" + e.getMessage(), e);
        }
        return re;

    }
}

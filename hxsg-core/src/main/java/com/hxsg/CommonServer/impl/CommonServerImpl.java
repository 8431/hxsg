package com.hxsg.CommonServer.impl;

import com.hxsg.CommonServer.CommonServer;
import com.hxsg.CommonUtil.ExpUtil;
import com.hxsg.Dao.*;
import com.hxsg.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
@Service("CommonServer")
public class CommonServerImpl implements CommonServer {
    @Autowired
    RoleYaoMapper roleyaomapper;
    @Autowired
    ZaWuMiaoShuMapper zwmsm;
    @Autowired
    RoleWuPinMapper rolewupinmapper;
    @Autowired
    FuJiangMapper fujiangmapper;
    @Autowired
    RoleFujiangMapper rolefujiangmapper;
    @Autowired
    FjLevelJyMapper fjleveljymapper;
    @Autowired
    NewRoleMapper newrolemapper;

    @Override
    public Map<String, Object> userRoleYao(String roleid, String name) throws Exception {
        Map<String, Object> yaMp = roleyaomapper.selectByYaoNameRoleId(roleid, name);
        if (yaMp != null) {
            RoleYao ry = new RoleYao();
            ry.setId((Integer) yaMp.get("id"));
            Integer yaonum = (Integer) yaMp.get("yaonum");
            ry.setYaonum(yaonum - 1);
            roleyaomapper.updateByPrimaryKey(ry);
        }
        return null;
    }

    @Override
    public List<RoleWuPin> getDaoJu(Integer roleid, String rolename, int m) throws Exception {
        List<RoleWuPin> liArr = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            //道具掉落
            int gl = (int) ((Math.random() * 100) + 1);
            if (gl != 0 && gl > 10) {
                int wbgl = (int) ((Math.random() * 2));
                int rand = 0;
                if (wbgl == 0) {
                    rand = (int) ((Math.random() * 12) + 1);
                }
                if (wbgl == 1) {
                    rand = (int) ((Math.random() * 15) + 27);
                }
                ZaWuMiaoShu zs = null;
                zs = zwmsm.selectByPrimaryKey(rand);
                System.out.println("-------------------" + rand);
                RoleWuPin rw = new RoleWuPin();
                rw.setName(zs.getName());
                rw.setRoleid(roleid);
                List<RoleWuPin> li = rolewupinmapper.selectAll(rw);

                //带决绝
                if (li.size() > 0 && li != null) {
                    Integer num = li.get(0).getNum();
                    if (num == null) {
                        num = 0;
                    }
                    li.get(0).setNum(num + 1);
                    rolewupinmapper.updateByPrimaryKeySelective(li.get(0));
                    liArr.add(li.get(0));
                } else {
                    rw.setWupinid(rand);
                    rw.setNum(1);
                    rw.setType1(zs.getType());
                    rw.setType2("杂物");
                    rw.setCustom1(zs.getStatus());
                    rolewupinmapper.insertSelective(rw);
                    liArr.add(rw);

                }

            }
        }
        return liArr;
    }

    @Override
    public boolean fuJiangLevel(Integer fuid, Integer jyz) throws Exception {
        boolean key = false;
        RoleFujiang rf = rolefujiangmapper.selectByPrimaryKey(fuid);
        int jy = rf.getLeve();
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
            rf.setSjjinyan(rf.getSjjinyan() - jyz);
        } else if (rf.getSjjinyan() <= 0) {//升了一级之后的属性
            rf = getRoleFuJiang(rf, 1);
            key = true;
            // msg = "副将获得" + 60 * jy * jy * num + "经验，恭喜你副将升到" + (jy + 1) + "级";
        }
        rolefujiangmapper.updateByPrimaryKeySelective(rf);
        return key;
    }

    @Override
    public boolean expConversionLevel(Integer roleId, Integer jyz) throws Exception {
        boolean re = false;
        NewRole role = newrolemapper.queryTotalShuXingToRole(roleId);
        if (role != null) {
            int level = role.getLevel();
            Integer roleJy = role.getJingyan() + jyz;
            int[] js = jieSuanJy(level, roleJy);
            int nowLevel = js[0];
            int le = nowLevel - level;
            int exp = js[1];
            NewRole r = new NewRole();
            r.setId(roleId);
            r.setLevel(nowLevel);
            r.setJingyan(exp);
            r.setKeyongds(role.getKeyongds() + le * 4);
            r.setQixueds(role.getQixueds() + le);
            r.setJinglids(role.getJinglids() + le);
            r.setGongjids(role.getGongjids() + le);
            r.setSududs(role.getSududs() + le);
            newrolemapper.updateByPrimaryKeySelective(r);
            if (le > 0) {
                re = true;
            }
        }
        return re;
    }

    private int[] jieSuanJy(Integer level, Integer jingYan) {
        int[] result = new int[2];
        int nextLevel = level + 1;
        int levelJy = 30 * nextLevel * nextLevel * nextLevel + 150 * nextLevel - 80;
        if (jingYan >= levelJy) {
            return jieSuanJy(nextLevel, jingYan - levelJy);
        } else {
            result[0] = level;
            result[1] = jingYan;
            return result;
        }
    }

    private RoleFujiang getRoleFuJiang(RoleFujiang rf, Integer level) {
        int jy = rf.getLeve();
        rf.setTotalxue2((int) (Math.round(rf.getChengzhang() * (rf.getLeve() + level) * ((rf.getQixueds() + level) + rf.getChuxue() * 0.8))));
        rf.setTotaljing2((int) (Math.round(rf.getChengzhang() * (rf.getLeve() + level) * ((rf.getJinglids() + level) + rf.getChujing() * 0.8))));
        rf.setTotalgong((int) (Math.round(((rf.getLeve() + level) * rf.getChengzhang() * rf.getChugong()) / 7 + rf.getChengzhang() * rf.getChugong() * 0.5 + (rf.getLeve() + level) * rf.getChengzhang() * (rf.getGongjids() + level) * 0.2)));
        rf.setTotalsudu((int) (rf.getChengzhang() * (rf.getSududs() + level + rf.getChusu())));
        rf.setLeve(rf.getLeve() + level);
        rf.setQixueds(rf.getQixueds() + level);
        rf.setJinglids(rf.getJinglids() + level);
        rf.setGongjids(rf.getGongjids() + level);
        rf.setSududs(rf.getSududs() + level);
        rf.setKeyongds(rf.getKeyongds() + 4);
        //rfm.updateByPrimaryKeySelective(rf);
        //设置当前级别的总经验
        if (rf.getTouxian().equals("英才")) {
            rf.setSjjinyan((20 * ((jy + level) * (jy + level) * (jy + level) + 5 * (jy + level)) - 40) + rf.getSjjinyan());
            rf.setJingyan(0);
            rf.setTotajy(fjleveljymapper.sumyingcai(rf.getLeve() + 2));
        }
        if (rf.getTouxian().equals("将才")) {
            rf.setSjjinyan((30 * ((jy + level) * (jy + level) * (jy + level) + 5 * (jy + level)) - 80) + rf.getSjjinyan());
            rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve() + 2));
            rf.setJingyan(0);
        }
        if (rf.getTouxian().equals("猛将")) {
            rf.setSjjinyan((30 * ((jy + level) * (jy + level) * (jy + level) + 5 * (jy + level)) - 80) + rf.getSjjinyan());
            rf.setTotajy(fjleveljymapper.sumjiangcai(rf.getLeve() + 2));
            rf.setJingyan(0);
        }
        if (rf.getTouxian().equals("元帅")) {
            rf.setSjjinyan((40 * ((jy + level) * (jy + level) * (jy + level) + 5 * (jy + level)) - 80) + rf.getSjjinyan());
            rf.setTotajy(fjleveljymapper.sumyuanshuai(rf.getLeve() + 2));
            rf.setJingyan(0);
        }
        return rf;
    }
}

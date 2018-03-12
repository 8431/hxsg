package com.hxsg.index.service.impl;

import com.google.gson.Gson;
import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.util.MapExchangObjUtil;
import com.hxsg.Dao.IHxsgBaseDao;
import com.hxsg.index.service.Cocos2dRoleInitService;
import com.hxsg.po.NewRole;
import com.hxsg.po.RoleNewShuXing;
import com.hxsg.redis.RedisDaoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/26 0026.
 */
@Service("Cocos2dRoleInitService")
public class Cocos2dRoleInitServiceImpl implements Cocos2dRoleInitService {
    private Logger logger = Logger.getLogger(Cocos2dRoleInitServiceImpl.class);
    @Autowired
    IHxsgBaseDao ihxsgbasedao;
    @Autowired
    RedisDaoService redisdaoservice;
    @Autowired
    Gson gsonBean;

    @Override
    public List<RoleNewShuXing> getShuxing(String key) throws CommonException {

        List<RoleNewShuXing> sx = new ArrayList<>();
        if (key == null) {
            throw new CommonException("key不能为空");
        }

        try {
            Integer roleId = (Integer) redisdaoservice.get(key);
            Map<String, Object> exMap = new HashMap();

            String sql = "select kangxing, sum(kangXingTotal) as kangxingtotal" +
                    "  from (select kangXingTotal, kangxing" +
                    "          from role_new_shuxing" +
                    "         where roleid = #{roleid}" +
                    "           and status = 1" +
                    "        union ALL" +
                    "        SELECT xiaoGuo as kangXingTotal, kangxing" +
                    "          FROM baoshi b" +
                    "         where roleid = #{roleid}" +
                    "           and wqid in" +
                    "               (select id FROM role_new_zhuangbei where roleid =  #{roleid})" +
                    "           and sell = 0) m" +
                    " GROUP BY kangxing";
            exMap.put("sql", sql);
            exMap.put("roleid", roleId);
            List<Map<String, Object>> sxMp = ihxsgbasedao.exuSql(exMap);
            Gson gn = new Gson();
            for (Map<String, Object> m : sxMp) {
                RoleNewShuXing r = MapExchangObjUtil.getObj(m, RoleNewShuXing.class);
                sx.add(r);
            }
        } catch (Exception e) {
            logger.error("getShuxing异常:");
            throw new CommonException("001", "roleid不能为空");
        }
        return sx;
    }

    @Override
    public NewRole getRoleDetail(String key) throws CommonException {
        if (key == null) {
            throw new CommonException("key不能为空");
        }
        NewRole role = null;
        try {
            Integer roleId = (Integer) redisdaoservice.get(key);
//            byte[] roleBy = (byte[]) redisdaoservice.get(roleId.toString() + ":NewRole");
//            role = (NewRole) SerializationUtils.deserialize(roleBy);
//
//            if (role == null) {
                Map<String, Object> exMap = new HashMap();

                exMap.put("roleid", roleId);
                String sql = "select m.*" +

                        "  from ( select  " +
                        "               round(r.level * r.chengzhang * (r.qiXueDs + r.chuxue * 0.8),0) as totalXue2," +

                        "               round(r.level * r.chengzhang * (r.jingLiDs + r.chujing * 0.8),0) as totalJing2," +

                        "            round((r.level*r.chengzhang*r.chugong/7)+r.chugong*r.chengzhang*0.5+r.level*r.chengzhang*r.gongJiDs*0.2)  as totalgong," +

                        " round(r.chengzhang*(r.suDuDs+r.chusu)) as        totalsudu," +

                        "               r.*" +
                        "        " +
                        "          from (select x.*," +
                        "                       y.chengzhang," +
                        "                       y.chuxue," +
                        "                       y.chugong," +
                        "                       y.chujing," +
                        "                       y.chusu" +
                        "                  from new_role x, role_jichu y" +
                        "                 where x.zhiye = y.zhiye) r" +
                        "         where id = #{roleid}) m";


                String qixueSql = "select sum(kangXingTotal) as kangxingtotal" +
                        "                         from (SELECT xiaoGuo as kangXingTotal, kangxing" +
                        "                                 FROM baoshi b" +
                        "                                where roleid = #{roleid}" +
                        "                                  and wqid in (select id" +
                        "                                                 FROM role_new_zhuangbei" +
                        "                                                where roleid = #{roleid})" +
                        "                                  and sell = 0) m" +
                        "                        where kangXing = '气血'" +
                        "                        GROUP BY kangxing";
                exMap.put("sql", qixueSql);
                List<Map<String, Object>> qixueMp = ihxsgbasedao.exuSql(exMap);
                Integer qxVal = 0;
                if (qixueMp != null && qixueMp.size() > 0) {
                    qxVal = ((BigDecimal) qixueMp.get(0).get("kangxingtotal")).intValue();
                }

                String jingliSql = "select sum(kangXingTotal) as kangxingtotal" +
                        "                         from (SELECT xiaoGuo as kangXingTotal, kangxing" +
                        "                                 FROM baoshi b" +
                        "                                where roleid = #{roleid}" +
                        "                                  and wqid in (select id" +
                        "                                                 FROM role_new_zhuangbei" +
                        "                                                where roleid = #{roleid})" +
                        "                                  and sell = 0) m" +
                        "                        where kangXing = '精力'" +
                        "                        GROUP BY kangxing";
                exMap.put("sql", jingliSql);
                List<Map<String, Object>> jingliMp = ihxsgbasedao.exuSql(exMap);
                Integer jlVal = 0;
                if (jingliMp != null && jingliMp.size() > 0) {
                    jlVal = ((BigDecimal) jingliMp.get(0).get("kangxingtotal")).intValue();
                }
                String gongjiSql = "select sum(kangXingTotal) as kangxingtotal" +
                        "                         from (SELECT xiaoGuo as kangXingTotal, kangxing" +
                        "                                 FROM baoshi b" +
                        "                                where roleid = #{roleid}" +
                        "                                  and wqid in (select id" +
                        "                                                 FROM role_new_zhuangbei" +
                        "                                                where roleid = #{roleid})" +
                        "                                  and sell = 0) m" +
                        "                        where kangXing = '攻击'" +
                        "                        GROUP BY kangxing";
                exMap.put("sql", gongjiSql);
                List<Map<String, Object>> gongjiMp = ihxsgbasedao.exuSql(exMap);
                Integer gjVal = 0;
                if (gongjiMp != null && gongjiMp.size() > 0) {
                    gjVal = ((BigDecimal) gongjiMp.get(0).get("kangxingtotal")).intValue();
                }
                String suduSql = "select sum(kangXingTotal) as kangxingtotal" +
                        "                         from (SELECT xiaoGuo as kangXingTotal, kangxing" +
                        "                                 FROM baoshi b" +
                        "                                where roleid = #{roleid}" +
                        "                                  and wqid in (select id" +
                        "                                                 FROM role_new_zhuangbei" +
                        "                                                where roleid = #{roleid})" +
                        "                                  and sell = 0) m" +
                        "                        where kangXing = '速度'" +
                        "                        GROUP BY kangxing";
                exMap.put("sql", suduSql);
                List<Map<String, Object>> suduMp = ihxsgbasedao.exuSql(exMap);
                Integer sdVal = 0;
                if (suduMp != null && suduMp.size() > 0) {
                    sdVal = ((BigDecimal) suduMp.get(0).get("kangxingtotal")).intValue();
                }
                exMap.put("sql", sql);
                List<Map<String, Object>> sxMp = ihxsgbasedao.exuSql(exMap);
                if (sxMp != null && sxMp.size() == 1) {
                    role = MapExchangObjUtil.getObj(sxMp.get(0), NewRole.class);

                    role.setTotalgong(role.getTotalgong() + gjVal);
                    role.setTotaljing2(role.getTotaljing2() + jlVal);
                    role.setTotalxue2(role.getTotalxue2() + qxVal);
                    role.setTotalsudu(role.getTotalsudu() + sdVal);
                    byte[] roleByte = SerializationUtils.serialize(role);
                    redisdaoservice.set(roleId.toString() + ":NewRole", roleByte, null);
                } else {
                    logger.error("一个账号出现多次");
                }
          //  }

        } catch (Exception e) {
            logger.error("getRoleDetail异常:");
            throw new CommonException("001", e.getMessage());
        }
        return role;
    }


}

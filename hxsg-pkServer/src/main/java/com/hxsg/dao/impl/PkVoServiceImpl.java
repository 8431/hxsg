package com.hxsg.dao.impl;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.Dao.HxsgBaseDaoMapper;
import com.hxsg.Dao.NewRoleMapper;
import com.hxsg.Dao.RoleNewShuXingMapper;
import com.hxsg.Dao.SkillMapper;
import com.hxsg.controller.PkDataSa;
import com.hxsg.dao.FuJiangService;
import com.hxsg.dao.PkVoService;
import com.hxsg.pk.websoket.PkServerInitData;
import com.hxsg.po.*;
import com.hxsg.redis.RedisDaoService;
import com.hxsg.systemdao.SystemNotification;
import com.hxsg.vo.PkRoleVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by dlf on 2017/11/14 0014.
 * Email 1429264916@qq.com
 */
@Service("PkVoService")
public class PkVoServiceImpl implements PkVoService {
    private Logger logger = Logger.getLogger(PkVoServiceImpl.class);

    @Autowired
    FuJiangService fujiangservice;
    @Autowired
    RoleNewShuXingMapper rolenewshuxingmapper;
    @Autowired
    SkillMapper skillmapper;
    @Autowired
    HxsgBaseDaoMapper hxsgbasedaomapper;
    @Autowired
    PkServerInitData pkserverinitdata;
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    RedisDaoService redisdaoservice;
    @Autowired
    SystemNotification systemnotification;

    @Override
    public List<PkRoleVo> getPkRoleVo(String roleid, String state) throws Exception {
        List<PkRoleVo> pvLi = new ArrayList<>();

        List<Map<String, Object>> reLi = fujiangservice.queryStateFujiang(roleid, state);
        if(reLi!=null&&reLi.size()>0){
            for (Map<String, Object> m : reLi) {
                PkRoleVo pv = new PkRoleVo();
                pv.setId(((Long) m.get("id")).toString());
                pv.setName((String) m.get("fujiangname"));
                pv.setJingli(((Long) m.get("totaljing1")).intValue());
                pv.setJingli2(((Long) m.get("totaljing2")).intValue());
                pv.setQixue(((Long) m.get("totalxue1")).intValue());
                pv.setQixue2(((Long) m.get("totalxue2")).intValue());
                pv.setSudu(((Double) m.get("sudu")).intValue());
                pv.setTotalgong(((Double) m.get("gong")).intValue());
                pv.setLevel(((Long) m.get("leve")).intValue());
                pvLi.add(pv);
                //抗性
                List<RoleNewShuXing> shuxili = rolenewshuxingmapper.queryRoleShuXing(Integer.parseInt(pv.getId()), 1);
                pv.setShuXingLi(shuxili);
                //技能
                Skill s = new Skill();
                s.setType(Integer.parseInt(roleid));
                s.setRfid(Integer.parseInt(pv.getId()));
                List<Skill> skillA = skillmapper.selectAll(s);
                pv.setJineng(skillA);
            }
        }

        return pvLi;
    }

    @Override
    public Map<String, Object> getYeGuaiData(String level, String name, String key) throws CommonException {
        logger.debug(new Date().getTime());

        Map<String, Object> mp = new HashMap<String, Object>();
        try {
            if (StringUtils.isEmpty(level) || StringUtils.isEmpty(name)) {
                throw new CommonException("name,level不能为空");
            }

            Integer roleId= (Integer) redisdaoservice.get(key);
            String uuidRrdis = (String) redisdaoservice.get("pkuuid:" + roleId.toString());
            byte[] pkByte2 = (byte[]) redisdaoservice.get("init:" + roleId.toString() + uuidRrdis);
            PkDataSa pk2 = (PkDataSa) SerializationUtils.deserialize(pkByte2);
            if (pk2 != null) {
                mp.put("uuid", uuidRrdis);
                mp.put("rvLiB", pk2.getRoleB());
                mp.put("rvLiA", pk2.getRoleA());
                mp.put("rightOne", roleId.toString());
                systemnotification.sendSystemMsg(new Object[]{mp, "roleMsg"}, roleId.toString());
                return mp;
            }

            NewRole roleaData = newrolemapper.queryTotalShuXingToRole(roleId);
            Map<String, Object> param = new HashMap<>();
            String sql = "select * from yeguai_qun where name=#{name}";
            param.put("sql", sql);
            param.put("name", name);
            //等级初始化
            String[] ls = level.split(",");
            Integer start = Integer.parseInt(ls[0]);
            Integer end =0;
            if(ls.length==1){
                end=start+1;
            }else{
                end=Integer.parseInt(ls[1]);
            }
            long t1= new Date().getTime();
            logger.debug("getRoleFuJiangList:"+t1);
            List<Map<String, Object>> ParamLi = hxsgbasedaomapper.QuerySql(param);
            if (ParamLi != null && ParamLi.size() > 0) {
                List<RoleFujiang> roleFuLi = getRoleFuJiangList(start, end, ParamLi.get(0), roleId);
                if (roleFuLi == null || roleFuLi.size() == 0) {
                    param.put("name", "侍卫群");
                    ParamLi = hxsgbasedaomapper.QuerySql(param);
                    roleFuLi = getRoleFuJiangList(start, end, ParamLi.get(0), roleId);
                }
                long t2= new Date().getTime();
                logger.debug("getRoleFuJiangList:"+(t2-t1));
                //封装返回客户端数据
                List<PkRoleVo> rvLiTotal = new ArrayList<>();
                List<PkRoleVo> rvLiA = new ArrayList<>();
                List<PkRoleVo> rvLiB = new ArrayList<>();
                List<Integer> sortLi = new ArrayList<>();
                //uuid封装
                List<String> uuidLi = new ArrayList<>();
                Map<String, PkRoleVo> rvMap = new HashMap<>();
                for (RoleFujiang r : roleFuLi) {
                    PkRoleVo rv = new PkRoleVo();
                    UUID uuid = UUID.randomUUID();
                    rv.setId(uuid.toString());
                    System.out.println("uuid:" + uuid.toString());
                    rv.setName(r.getFujiangname());
                    rv.setJingli(r.getTotaljing1());
                    rv.setJingli2(r.getTotaljing2());
                    rv.setQixue(r.getTotalxue1());
                    rv.setQixue2(r.getTotalxue2());
                    rv.setSudu(r.getTotalsudu());
                    rv.setShuXingLi(r.getShuXingLi());
                    rv.setTotalgong(r.getTotalgong());
                    //总体数据
                    rvLiTotal.add(rv);
                    //map，以uuid为key保存玩家pk信息
                    rvMap.put(rv.getId(), rv);
                    //B集合数据
                    rvLiB.add(rv);
                    uuidLi.add(rv.getId());
                    sortLi.add(r.getTotalsudu());
                }
                //构建玩家数据 start
                PkRoleVo rv = new PkRoleVo();
                rv.setId(roleaData.getId().toString());
                rv.setName(roleaData.getRolename());
                rv.setJingli(roleaData.getTotaljing1());
                rv.setJingli2(roleaData.getTotaljing2());
                rv.setQixue(roleaData.getTotalxue1());
                rv.setQixue2(roleaData.getTotalxue2());
                rv.setSudu(roleaData.getTotalsudu());
                rv.setTotalgong(roleaData.getTotalgong());
                long t31= new Date().getTime();
                 //test
                List<RoleNewShuXing> shuxili=null;
                shuxili = rolenewshuxingmapper.queryRoleShuXing(roleaData.getId(), 0);
//                byte[] shuxiliByte= (byte[]) redisdaoservice.get(roleaData.getId()+":rolenewshuxingmapper.queryRoleShuXing");
//                shuxili= (List<RoleNewShuXing>) SerializationUtils.deserialize(shuxiliByte);
//                if(shuxili==null){
//                     shuxili = rolenewshuxingmapper.queryRoleShuXing(roleaData.getId(), 0);
//                    redisdaoservice.set(roleaData.getId()+":rolenewshuxingmapper.queryRoleShuXing", SerializationUtils.serialize(shuxili),null);
//                }
                //test
                long t3= new Date().getTime();
                logger.debug("queryRoleShuXing:"+(t3-t31));
                Skill s = new Skill();
                s.setType(0);
                s.setRfid(roleaData.getId());
                List<Skill> skillA = skillmapper.selectAll(s);
                rv.setShuXingLi(shuxili);
                rv.setJineng(skillA);

                rvLiTotal.add(rv);
                rvLiA.add(rv);
                rvMap.put(rv.getId(), rv);
                uuidLi.add(rv.getId());
                sortLi.add(rv.getSudu());
                //构建玩家副将
                List<PkRoleVo> pvLi = getPkRoleVo(rv.getId(), "休息");
                for (PkRoleVo p : pvLi) {
                    rvLiTotal.add(p);
                    rvLiA.add(p);
                    rvMap.put(p.getId(), p);
                    uuidLi.add(p.getId());
                    sortLi.add(p.getSudu());
                }
                //构建玩家数据 end

                Collections.sort(sortLi);
                for (PkRoleVo pv : rvLiTotal) {
                    Integer sd = pv.getSudu();
                    for (int i = 0; i < sortLi.size(); i++) {
                        if (sd == sortLi.get(i)) {
                            pv.setSudu(i + 1);
                            sortLi.set(i, i - 10);
                            break;
                        }
                    }
                }
                //本次PK对方标志
                String uuidA = UUID.randomUUID().toString();
                mp.put("uuid", uuidA);
                mp.put("rvLiB", rvLiB);
                mp.put("rvLiA", rvLiA);
                mp.put("rightOne", roleId.toString());

                PkDataSa pk = new PkDataSa();
                pk.setRoleA(rvLiA);
                pk.setRoleB(rvLiB);
                pk.setPkUuid(uuidA);
                pk.setMp(rvMap);
                pk.setRoleAId(roleId.toString());
                pk.setTotalAB(rvLiTotal);
                pk.setUuidStr(uuidLi);
                pk.setFujiang(pvLi);
                pk.setStart(start);
                pk.setSize(ParamLi.size());
                pk.setNewRole(roleaData);
                byte[] pkByte = SerializationUtils.serialize(pk);
                redisdaoservice.set("init:" + roleId.toString() + uuidA, pkByte, null);
                redisdaoservice.set("pkuuid:" + roleId, uuidA, null);
                HandlePkServiceImpl.HANDLEPKSERVICEPKMAP.put(uuidA, uuidA);
                //推送消息
                systemnotification.sendSystemMsg(new Object[]{mp, "roleMsg"}, roleId.toString());
                logger.debug(new Date().getTime());
//                byte[] pkByte2= (byte[]) redisdaoservice.get(roleId.toString());
//                PkDataSa pk2= (PkDataSa) SerializationUtils.deserialize(pkByte2);
//                System.out.println(pk2.getPkUuid());
            } else {
                throw new CommonException("没有找到该野怪");
            }
        } catch (Exception e) {
            logger.error("初始化野怪数据失败"+e.getMessage(),e);
            throw new CommonException("queryMyZhuangBei:" + e.getMessage(), e.getMessage(), e);
        }
        return mp;
    }

    @Override
    public Map<String, Object> handlePkData(String data) throws CommonException {
        Map<String, Object> mp = new HashMap<>();
        try {


        } catch (Exception e) {
            throw new CommonException("handlePkData:" + e.getMessage(), e.getMessage(), e);
        }


        return mp;
    }

    @Override
    public void usrYao(String id, String key) throws CommonException {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(key)) {
            throw new CommonException("药id和key不能为空");
        }


        try {
            Integer roleid= (Integer) redisdaoservice.get(key);
            //消耗一品药
            Map<String, Object> param = new HashMap();
            param.put("id", id);
            param.put("roleid", roleid);

            param.put("sql", "select id from  roleyao  where yaoid=#{id} and roleid=#{roleid} and yaonum>0 limit 0,1");
            List<Map<String, Object>> roleyaoMp = hxsgbasedaomapper.QuerySql(param);
            if (roleyaoMp != null && roleyaoMp.size() > 0) {
                Long roleyaoid = (Long) roleyaoMp.get(0).get("id");
                param.put("sql", "update roleyao set  yaonum=yaonum-1 where id=#{id} and roleid=#{roleid}");
                param.put("id", roleyaoid);
                hxsgbasedaomapper.UpdateSql(param);
            } else {
                throw new CommonException("没有该药品，无法使用");
            }

        } catch (Exception e) {
            throw new CommonException("usrYao:" + e.getMessage(), e.getMessage(), e);
        }

    }

    /**
     * 2001 存在pk  2002不存在
     *
     * @param key
     * @return
     * @throws CommonException
     */
    @Override
    public String queryPk(String key) throws CommonException {

        String re = "2002";
        try {
            Integer roleid= (Integer) redisdaoservice.get(key);
            String uuidRrdis = (String) redisdaoservice.get("pkuuid:" + roleid);
            byte[] pkByte2 = (byte[]) redisdaoservice.get("init:" + roleid + uuidRrdis);
            PkDataSa pk2 = (PkDataSa) SerializationUtils.deserialize(pkByte2);
            if (pk2 != null) {
                re = "2001";
            }
        } catch (Exception e) {
            throw new CommonException("queryPk:" + e.getMessage(), e.getMessage(), e);
        }
        return re;
    }

    public List<RoleFujiang> getRoleFuJiangList(Integer start, Integer end, Map<String, Object> yq, Integer roleId) throws Exception {
        List<RoleFujiang> rfLi = new ArrayList<RoleFujiang>();
        if (yq != null) {
            String g1 = (String) yq.get("guai1");
            RoleFujiang rf1 = pkserverinitdata.CreateRoleFujiang(g1, start, end, roleId);

            String g2 = (String) yq.get("guai2");
            RoleFujiang rf2 = pkserverinitdata.CreateRoleFujiang(g2, start, end, roleId);

            String g3 = (String) yq.get("guai3");
            RoleFujiang rf3 = pkserverinitdata.CreateRoleFujiang(g3, start, end, roleId);

            String g4 = (String) yq.get("guai4");
            RoleFujiang rf4 = pkserverinitdata.CreateRoleFujiang(g4, start, end, roleId);

            String g5 = (String) yq.get("guai5");
            RoleFujiang rf5 = pkserverinitdata.CreateRoleFujiang(g5, start, end, roleId);

            String g6 = (String) yq.get("guai6");
            RoleFujiang rf6 = pkserverinitdata.CreateRoleFujiang(g6, start, end, roleId);
            if (rf1 != null) {
                rfLi.add(rf1);
            }
            if (rf2 != null) {
                rfLi.add(rf2);
            }
            if (rf3 != null) {
                rfLi.add(rf3);
            }
            if (rf4 != null) {
                rfLi.add(rf4);
            }
            if (rf5 != null) {
                rfLi.add(rf5);
            }
            if (rf6 != null) {
                rfLi.add(rf6);
            }

        }
        return rfLi;
    }
}

package com.hxsg.dao.impl;

import com.google.gson.Gson;
import com.hxsg.CommonServer.CommonServer;
import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.ExpUtil;
import com.hxsg.CommonUtil.MathUtil;
import com.hxsg.Dao.*;
import com.hxsg.controller.PkDataSa;
import com.hxsg.dao.FuJiangService;
import com.hxsg.dao.HandlePkService;
import com.hxsg.dao.HxsgBattle;
import com.hxsg.dao.PkVoService;
import com.hxsg.pk.websoket.LoadGuaiService;
import com.hxsg.pk.websoket.PkServerInitData;
import com.hxsg.po.*;
import com.hxsg.redisService.RedisDaoService;
import com.hxsg.systemdao.SystemNotification;
import com.hxsg.util.AttackUtil;
import com.hxsg.vo.AttackA;
import com.hxsg.vo.PkPlayData;
import com.hxsg.vo.PkRoleVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by dlf on 2018/1/15 0015.
 * Email 1429264916@qq.com
 */
@Service("handlepkservice")
public class HandlePkServiceImpl implements HandlePkService {

    public final static Map<String, Object> HANDLEPKSERVICEPKMAP = new ConcurrentHashMap<String, Object>();
    //PK对象是玩家
    public static final String PKROLE = "1000";
    //PK对象是野怪
    public static final String PKGUAI = "1001";

    @Autowired
    SystemNotification systemnotification;
    @Autowired
    RedisDaoService redisdaoservice;
    @Autowired
    LoadGuaiService loadguaiservice;
    @Autowired
    RoleFujiangMapper RoleFujiangmapper;
    @Autowired
    YeGuaiQunMapper yeguaiqunmapper;
    @Autowired
    FuJiangMapper fujiangmapper;
    @Autowired
    PkServerInitData pkserverinitdata;
    @Autowired
    RoleNewShuXingMapper rolenewshuxingmapper;
    @Autowired
    SkillMapper skillmapper;
    @Autowired
    HxsgBattle hxsgbattle;
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    FuJiangService fujiangservice;
    @Autowired
    PkVoService pkvoservice;
    @Autowired
    CommonServer commonserver;
    @Autowired
    HxsgBaseDaoMapper hxsgbasedaomapper;

    private static Log logger = LogFactory.getLog(HandlePkServiceImpl.class);


    @Override
    public void handlePk(String key, String uuid) throws CommonException {

        try {
            Integer roleid= (Integer) redisdaoservice.get(key);
            byte[] pkByte2 = (byte[]) redisdaoservice.get("init:" + roleid.toString() + uuid);
            PkDataSa pk2 = (PkDataSa) SerializationUtils.deserialize(pkByte2);
            if (pk2 != null) {
                boolean re = notices(pk2.getMp(), pk2.getUuidStr(), roleid.toString(), "", pk2.getPkUuid(), pk2.getTotalAB(), pk2.getRoleA(), pk2.getRoleB());
                if (re) {
                    List<String> pkuuid = new ArrayList<>();
                    pkuuid.add("pkuuid:" + roleid);
                    redisdaoservice.del(pkuuid);
                    //战斗结算
                    //结算数据
                    //pk完成，数据结算，奖励发放
                    Map<String, Object> jyMp = new HashMap<>();
                    //1.主角经验
                    NewRole roleaData = pk2.getNewRole();
                    Integer start = pk2.getStart();
                    List<PkRoleVo> pvLi = pk2.getFujiang();

                    Integer zjJy = ExpUtil.caleExp(roleaData.getLevel(), start, pk2.getSize());
                    boolean zjRe = commonserver.expConversionLevel(roleaData.getId(), zjJy);
                    if (zjRe) {
                        jyMp.put(roleaData.getId().toString(), roleaData.getRolename() + "获得" + zjJy + "经验," + "升至" + (roleaData.getLevel() + 1) + "级");
                    } else {
                        jyMp.put(roleaData.getId().toString(), roleaData.getRolename() + "获得" + zjJy + "经验");
                    }
                    //副将经验
                    for (PkRoleVo p : pvLi) {
                        Integer fjjy = ExpUtil.caleExp(p.getLevel(), start, pk2.getSize());
                        boolean fjRe = commonserver.fuJiangLevel(Integer.parseInt(p.getId()), fjjy);
                        if (fjRe) {
                            jyMp.put(p.getId(), p.getName() + "获得" + fjjy + "经验," + "升至" + (p.getLevel() + 1) + "级");
                        } else {
                            jyMp.put(p.getId(), p.getName() + "获得" + fjjy + "经验");
                        }
                    }
                    List<RoleWuPin> li = commonserver.getDaoJu(roleaData.getId(), roleaData.getRolename(), 3);
                    Map<String, Object> mpJson = new HashMap<>();
                    mpJson.put("jy", jyMp);
                    mpJson.put("djArr", li);
                    systemnotification.sendSystemMsg(new Object[]{mpJson, "gameOver"}, roleid.toString());

                } else {
                    redisdaoservice.set("init:" + roleid + uuid, SerializationUtils.serialize(pk2), null);
                }
            }
        } catch (Exception e) {
            logger.error("handlePk异常:" + e.getMessage(), e);
            throw new CommonException("handlePk异常:" + e.getMessage());
        }
    }

    //客户端速度很快的点击自动攻击有可能造成2次计算
    private boolean notices(Map<String, PkRoleVo> rvMap, List<String> uuidLi, String roleA, String roleB, String uuidA, List<PkRoleVo> rvLiTotal, List<PkRoleVo> rvLiA, List<PkRoleVo> rvLiB) throws Exception {

        //开始等待时间，最多60s
        long pkWaitTimeStart = new Date().getTime();
        boolean endKey = false;
        String winPlay = "6000";//战斗中
        int hhNUm = 0;
        Object uuidAOb = HandlePkServiceImpl.HANDLEPKSERVICEPKMAP.get(uuidA);
        String bmsg = (String) HandlePkServiceImpl.HANDLEPKSERVICEPKMAP.get("B");
        bmsg = "test";
        long pkWaitTimeEnd = new Date().getTime();
        /**
         * 客户端默认60秒倒计时后将下发默认攻击指令，服务器默认65秒
         */
//            System.out.println();
//            if (pkWaitTimeEnd - pkWaitTimeStart >= 65 * 1000) {
//                if (amsg == null) {
//                    //执行A默认攻击动作
//                }
//                if (bmsg == null) {
//
//                    //执行B默认攻击动作
//                }
//            }

        //野怪默认执行动作，后期需要设置野怪ai

        if (uuidAOb instanceof List && (bmsg != null)) {
            //重置双方玩家信息
            resetBuff(rvLiA);
            resetBuff(rvLiB);
            //获取到AB客户端数据
            // 根据指令计算伤害,如果有一方气血全部为0.结束
            List<Map<String, Object>> playJson = (List<Map<String, Object>>) uuidAOb;

            Iterator it = playJson.iterator();
            while (it.hasNext()) {
                Map<String, Object> m = (Map<String, Object>) it.next();
                String pj1 = (String) m.get("player1");
                logger.error("--------------" + pj1);
                PkRoleVo p = rvMap.get(pj1);
                if (p != null) {
                    String buff = yzBuff(p.getBuff());
                    m.put("status", buff);
                    if (p.getQixue() <= 0 && !roleA.equals(pj1)) {
                        it.remove();
                    }
                } else {
                    logger.error(new Gson().toJson(p));
                }


            }

            //校验 playJson buff
//                for (Map<String, Object> m : playJson) {
//
//
//                }

            //排序-----start
            Collections.sort(rvLiTotal);
            //野怪攻击集合
            List<Map<String, Object>> yeguaiMapLi = getYeGuaiAi(rvLiA, rvLiB);

            List<Map<String, Object>> pkJson = sortList(yeguaiMapLi, playJson, rvLiTotal);
            List<Map<String, Object>> pkJsonTemp = new ArrayList<>();


            //排序-----end
            for (int i = 0; i < pkJson.size(); i++) {
                //攻击者
                String player1 = (String) pkJson.get(i).get("player1");
                //被攻击者
                List<String> playerArr = (List<String>) pkJson.get(i).get("player2");

                String player2 = playerArr.get(0);

                //攻击者气血为0，攻击失效
                //封乱状态不可主动使用药品你---代加入
                PkRoleVo p1Pv = rvMap.get(player1);
                if (p1Pv.getQixue() > 0) {
                    //每次攻击前需要验证被攻击者是否气血为0，为0的话则被攻击者为对方玩家集合中气血不为0的第一个
                    //使用药品时，只有主角气血为0可以救活
                    PkRoleVo p2Pv = rvMap.get(player2);
                    Map<String, Map<String, Object>> battleData = (Map<String, Map<String, Object>>) pkJson.get(i).get("battleData");
                    Map<String, String> attackType = (Map<String, String>) pkJson.get(i).get("attackType");
                    String skillName = attackType.get("name");
                    String regex = "^-?\\d+$";
                    boolean skillBoolean = Pattern.matches(regex, skillName);
                    if (p2Pv.getQixue() <= 0 && !skillBoolean && !p2Pv.getId().equals(roleA)) {
                        List<PkRoleVo> pvLi = null;
                        if (rvLiA.contains(p2Pv)) {
                            pvLi = rvLiA;
                        } else {
                            pvLi = rvLiB;

                        }
                        for (PkRoleVo p : pvLi) {
                            if (p.getQixue() > 0) {
                                List<String> arr = new ArrayList<>();
                                arr.add(p.getId());
                                pkJson.get(i).put("player2", arr);
                                battleData.put(p.getId(), battleData.get(player2));
                                battleData.remove(player2);
                                playerArr = arr;
                                break;
                            }
                        }

                    }

                    //战斗数据

                    //验证攻击
                    if (uuidLi.contains(player2)) {
                        //构造攻击者玩家数据
                        PkRoleVo pvb1 = rvMap.get(player1);
                        //根据技能和熟练度，为群体技能设置攻击人数
                        //判断skillName是使用道具还是技能
                        //被攻击者详细数据
                        List<PkRoleVo> pvLi = new ArrayList<>();

                        //判断使用道具还是使用攻击
                        if (skillBoolean) {
                            for (String s : playerArr) {
                                PkRoleVo pv = rvMap.get(s);
                                pv.setMpBuff(new HashMap<String, Integer>());
                                pvLi.add(pv);
                            }

                            //使用道具(药品）目标只有一个
                            /*判断逻辑-----主将不管气血是否为0都可以使用药品，副将必须气血值>0*/
                            if (pvLi.get(0).getQixue() > 0 || roleA.equals(pvLi.get(0).getId())) {
                                List<Map<String, Object>> re = null;
                                //构建使用药品数据
                                Map<String, Object> param = new HashMap();
                                param.put("id", skillName);
                                param.put("roleid", roleA);

                                param.put("sql", "select id from roleyao where roleid=#{roleid}  and yaoid=#{id} ");
                                re = hxsgbasedaomapper.QuerySql(param);
                                if (re != null && re.size() > 0) {
                                    Long yaoId = (Long) re.get(0).get("id");
                                    param.put("sql", "select * from yaoping where yaoid=#{id} ");
                                    List<Map<String, Object>> ypMp = hxsgbasedaomapper.QuerySql(param);
                                    if (ypMp != null && ypMp.size() > 0) {
                                        Double qxz = (Double) ypMp.get(0).get("qixuezhi");
                                        String yaoname = (String) ypMp.get(0).get("yaoname");

                                        //给角色使用使用药品
                                        PkRoleVo p = pvLi.get(0);
                                        Integer qxVal = 0;
                                        Integer jlVal = 0;
                                        Integer hfVal = 0;
                                        if (qxz > 1) {
                                            if (Integer.parseInt(skillName) > 10) {

                                                jlVal = Math.toIntExact((long) (p.getJingli() + qxz));
                                                p.setJingli(jlVal >= p.getJingli2() ? p.getJingli2() : jlVal);
                                            } else {
                                                hfVal = qxz.intValue();
                                                qxVal = Math.toIntExact((long) (p.getQixue() + qxz));
                                                p.setQixue(qxVal > p.getQixue2() ? p.getQixue2() : qxVal);
                                            }
                                        }
                                        if (qxz < 1) {

                                            if (Integer.parseInt(skillName) > 10) {
                                                //hfVal=Math.toIntExact((long)(qxz * p.getJingli2()));
                                                jlVal = Math.toIntExact((long) (p.getJingli() + qxz * p.getJingli2()));
                                                p.setJingli(jlVal >= p.getJingli2() ? p.getJingli2() : jlVal);
                                            } else {
                                                qxVal = Math.toIntExact((long) (p.getQixue() + qxz * p.getQixue2()));
                                                p.setQixue(qxVal > p.getQixue2() ? p.getQixue2() : qxVal);
                                                hfVal = Math.toIntExact((long) (qxz * p.getQixue2()));
                                            }
                                        }
                                        if (jlVal > 0) {
                                            hfVal = null;
                                        }
                                        //使用道具
                                        String fzJson =
                                                "{" +
                                                        //说明:player1----攻击者
                                                        "'player1': '" + player1 + "'," +
                                                        "'gameOver':'6000'," +
                                                        //说明:player2---被攻击者,群体技能有多个被攻击者，普通攻击只有一个目标，所以设计数组形式
                                                        "'player2': ['" + player2 + "']," +
                                                        //说明:Qx代表气血，负数代表被扣除的气血，正数代表增加的气血（增加气血，来自于玩家使用回血道具）
                                                        //说明:Jl代表蓝量，负数代表被扣除的蓝量，正数代表增加的蓝量（增加蓝量，来自于玩家使用蓝量道具）
                                                        "'battleData': { '" + player2 + "': { 'Qx2': '" + p.getQixue() + "', 'Qx': '" + hfVal + "', 'Jl': '" + jlVal + "' }}," +
                                                        // ---status所有指令（'攻击'，'暴击','致命一击','反击','反震','未中'）
                                                        " 'attackType': { 'name': '" + yaoname + "', 'status': '暴击', 'type': 'usryao', 'rolePosition': 'right' }," +
                                                        "'status':'0000'" +
                                                        " }";
                                        Gson gn = new Gson();
                                        Map<String, Object> fzJsonMap = gn.fromJson(fzJson, Map.class);
                                        pkJsonTemp.add(fzJsonMap);


                                    } else {
                                        logger.error("玩家id" + roleA + "违规使用不存在的药品：" + skillName);
                                    }


                                }

                            }

                        } else {
                            pkJsonTemp.add(pkJson.get(i));
                            Skill skill = AttackUtil.getSkill(pvb1, skillName);
                            if (skill != null) {
                                String level = skill.getLevel();
                                Integer g = Integer.parseInt(level);
                                Integer rs = 1;
                                switch (skillName) {
                                    case "排山倒海":
                                    case "巫蛊极毒":
                                    case "妖火燎原":
                                    case "气冲斗牛":
                                    case "呼风唤雨": {

                                        if (g >= 5) {
                                            rs = 4;
                                        } else if (g >= 4) {
                                            rs = 3;
                                        } else {
                                            rs = 2;
                                        }
                                        addAttacked(rvMap, yeguaiMapLi, pkJson.get(i), rs);
                                        break;
                                    }
                                    case "画地为牢": {
                                        //文人技能命中概率，以及命中个数，3级以上3个
                                        if (g >= 3) {
                                            rs = 3;
                                        } else if (g >= 2) {
                                            rs = 2;
                                        } else {
                                            rs = 1;
                                        }
                                        addAttacked(rvMap, yeguaiMapLi, pkJson.get(i), rs);
                                        break;
                                    }
//                                    case "气冲斗牛": {
//                                        addAttacked(rvMap, yeguaiMapLi, pkJson.get(i), 1);
//
//
//                                        break;
//                                    }
                                    //.....
                                }

                            }
                            for (String s : playerArr) {
                                PkRoleVo pv = rvMap.get(s);
                                pv.setMpBuff(new HashMap<String, Integer>());
                                pvLi.add(pv);
                            }
                            AttackA atta = hxsgbattle.skillAttack(pvb1, pvLi, skillName);
                            //20180117修复普工攻击类型都显示普通攻击
                            Map attackTypeMap = (Map) pkJson.get(i).get("attackType");
                            attackTypeMap.put("name", atta.getAttakType_A());
                            if("精力不足".equals(atta.getAttakType_A())){
                                attackTypeMap.put("type","jlbz");
                            }

                            //攻击者与被攻击者结算
                            String shvalue = "-" + atta.getShValue_A() + "";

                            //构建buff
                            for (int j = 0; j < pvLi.size(); j++) {
                                PkRoleVo p2 = pvLi.get(j);
                                Map<String, Object> battleDataJson = battleData.get(playerArr.get(j));
                                battleDataJson.put("Qx", p2.getKouXue());
                                battleDataJson.put("Qx2", p2.getQixue());
                                battleDataJson.put("buff", p2.getBuff());
//                            System.out.println("-------------------------------------------");
//                            System.out.println("被攻击者当前气血：" + p2.getQixue2());
//                            System.out.println("-------------------------------------------");
                            }


                            //反震或者反击 处理
                            Map<String, Integer> mpbuff = pvb1.getMpBuff();
                            if (mpbuff.get("反震") != null) {
                                String fzJson =
                                        "{" +
                                                //说明:player1----攻击者
                                                "'player1': '" + player2 + "'," +
                                                "'gameOver':'6000'," +
                                                //说明:player2---被攻击者,群体技能有多个被攻击者，普通攻击只有一个目标，所以设计数组形式
                                                "'player2': ['" + player1 + "']," +
                                                //说明:Qx代表气血，负数代表被扣除的气血，正数代表增加的气血（增加气血，来自于玩家使用回血道具）
                                                //说明:Jl代表蓝量，负数代表被扣除的蓝量，正数代表增加的蓝量（增加蓝量，来自于玩家使用蓝量道具）
                                                "'battleData': { '" + player1 + "': { 'Qx2': '" + pvb1.getQixue() + "', 'Qx': '" + mpbuff.get("反震") + "', 'Jl': '0' }}," +
                                                // ---status所有指令（'攻击'，'暴击','致命一击','反击','反震','未中'）
                                                " 'attackType': { 'name': '反震', 'status': '暴击', 'type': 'jnzhanpugong', 'rolePosition': 'left' }," +
                                                "'status':'0000'" +
                                                " }";
                                Gson gn = new Gson();
                                Map<String, Object> fzJsonMap = gn.fromJson(fzJson, Map.class);
                                pkJsonTemp.add(fzJsonMap);

                            }
                            if (mpbuff.get("反击") != null) {
                                String fzJson =
                                        "{" +
                                                //说明:player1----攻击者
                                                "'player1': '" + player2 + "'," +
                                                "'gameOver':'6000'," +
                                                //说明:player2---被攻击者,群体技能有多个被攻击者，普通攻击只有一个目标，所以设计数组形式
                                                "'player2': ['" + player1 + "']," +
                                                //说明:Qx代表气血，负数代表被扣除的气血，正数代表增加的气血（增加气血，来自于玩家使用回血道具）
                                                //说明:Jl代表蓝量，负数代表被扣除的蓝量，正数代表增加的蓝量（增加蓝量，来自于玩家使用蓝量道具）
                                                "'battleData': { '" + player1 + "': { 'Qx2': '" + pvb1.getQixue() + "', 'Qx': '" + mpbuff.get("反击") + "', 'Jl': '0' }}," +
                                                // ---status所有指令（'攻击'，'暴击','致命一击','反击','反震','未中'）
                                                " 'attackType': { 'name': '反击', 'status': '暴击', 'type': 'jnzhanpugong', 'rolePosition': 'left' }," +
                                                "'status':'0000'" +
                                                " }";
                                Gson gn = new Gson();
                                Map<String, Object> fzJsonMap = gn.fromJson(fzJson, Map.class);
                                pkJsonTemp.add(fzJsonMap);

                            }

                        }


                        //判断是否存在一方玩家所有气血全部为0的情况
                        //1.判断玩家A是否气血全部为o
                        Boolean play1 = ganmeOver2(rvLiA);
                        Boolean play2 = ganmeOver2(rvLiB);
                        pkJson.get(i).put("gameOver", "6000");
                        if (play1) {
                            //哪个玩家获得胜利
                            winPlay = roleA;
                            pkJson.get(i).put("gameOver", roleA);
                            endKey = true;
                            break;
                        }
                        if (play2) {
                            //哪个玩家获得胜利
                            winPlay = "yeguai";
                            pkJson.get(i).put("gameOver", "yeguai");
                            endKey = true;
                            break;
                        }
                    } else {
                        //错误传参数据，有可能人为修改数据。做封号处理
                        endKey = true;
                    }


                }

            }
            //回合数++
            hhNUm++;
            // 返回数据到AB客户端,并清除标记状态
            //下发数据到AB客户端，等待AB响应
            Map<String, Object> jsonMp = new HashMap<>();
            jsonMp.put("uuid", uuidA);
            jsonMp.put("pkJson", pkJsonTemp);
            Map<String, Object> tempData = new HashMap<>();
            jsonMp.put("uuid", uuidA);
            jsonMp.put("tempData", tempData);
            jsonMp.put("winPlay", winPlay);
            jsonMp.put("hhNum", hhNUm);
            jsonMp.put("rvLiA", rvLiA);
            jsonMp.put("rvLiB", rvLiB);
            jsonMp.put("rightOne", roleA);
            jsonMp.put("leftOne", "");
            HandlePkServiceImpl.HANDLEPKSERVICEPKMAP.put(uuidA, uuidA);
            countBuff(rvLiA);
            countBuff(rvLiB);
            //统一处理回合数buff --
            systemnotification.sendSystemMsg(new Object[]{jsonMp, "sendPkMsg"}, roleA);


        }
        return endKey;


    }

    private List<Map<String, Object>> getYeGuaiAi(List<PkRoleVo> rvLiA, List<PkRoleVo> rvLiB) {
        List<Map<String, Object>> yeguaiMapLi = new ArrayList<>();
        Gson gn = new Gson();
        for (PkRoleVo p : rvLiB) {
            //气血大于0的话，才能执行攻击动作
            if (p.getQixue() > 0) {
                //验证buff
                String buff = yzBuff(p.getBuff());
                int random = 0;
                PkRoleVo pv = null;
                if ("luan".equals(buff)) {
                    buff = "0000";
                    random = (int) (Math.random() * 2);
                    if (random >= 1) {
                        pv = getRandom(rvLiA);
                    } else {
                        pv = getRandom(rvLiB);
                    }
                } else {
                    pv = getRandom(rvLiA);
                }
                String yeguaiJson =
                        "{" +
                                //说明:player1----攻击者
                                "'player1': '" + p.getId() + "'," +
                                "'gameOver':'6000'," +
                                //说明:player2---被攻击者,群体技能有多个被攻击者，普通攻击只有一个目标，所以设计数组形式
                                "'player2': ['" + pv.getId() + "']," +
                                //说明:Qx代表气血，负数代表被扣除的气血，正数代表增加的气血（增加气血，来自于玩家使用回血道具）
                                //说明:Jl代表蓝量，负数代表被扣除的蓝量，正数代表增加的蓝量（增加蓝量，来自于玩家使用蓝量道具）
                                "'battleData': { '" + pv.getId() + "': { 'Qx2': '0', 'Qx': '0', 'Jl': '0' }}," +
                                //说明：type代表相应的动作指令，比如 普通攻击，使用道具，五雷轰顶，。。。。等等攻击名称，技能名称，使用道具等等一系列指令
                                // TYPE所有的指令【普通攻击】【使用道具】【五雷轰顶】【妖火燎原】【呼风唤雨】【舍命一击】【力劈华山】【排山倒海】【画地为牢】【四面楚歌】【趁火打劫】
                                //说明：status代表动作指令中出现的方式
                                // ---status所有指令（'攻击'，'暴击','致命一击','反击','反震','未中'）
                                " 'attackType': { 'name': '普通攻击', 'status': '暴击', 'type': 'jnzhanpugong', 'rolePosition': 'left' }," +
                                "'status':'" + buff + "'" +
                                " }";
                Map<String, Object> yeguaiMap = gn.fromJson(yeguaiJson, Map.class);
                yeguaiMapLi.add(yeguaiMap);
            }

        }
        return yeguaiMapLi;
    }

    /**
     * 校验人物携带buff,封，围，不可以攻击目标
     *
     * @return
     */
    private static String yzBuff(List<Map<String, Object>> buff) {
        String re = "0000";
        if (buff != null) {
            for (Map<String, Object> m : buff) {
                if (m.get("feng") != null) {
                    re = "feng";
                }
                if (m.get("wei") != null) {
                    re = "wei";
                }
                if (m.get("luan") != null) {
                    re = "luan";
                }
            }
        }
        return re;
    }

    /**
     * 随机气血不为0的角色
     *
     * @param rvLiA
     * @return
     */
    public static PkRoleVo getRandom(List<PkRoleVo> rvLiA) {
        int random = (int) (Math.random() * (rvLiA.size()));
        if (rvLiA.get(random).getQixue() <= 0) {
            return getRandom(rvLiA);
        } else {
            return rvLiA.get(random);
        }
    }

    public void countBuff(List<PkRoleVo> rvLiA1) {
        for (PkRoleVo ra : rvLiA1) {
            switch (ra.getAttakType()) {
                case "普通攻击": {
                    if (ra.getKouXue() > 0) {
                        AttackUtil.resetBuff(ra.getBuff(), true);
                    } else {
                        AttackUtil.resetBuff(ra.getBuff(), false);
                    }
                    break;

                }
                case "无":
                case "四面楚歌":
                case "趁火打劫":
                case "画地为牢": {
                    AttackUtil.resetBuff(ra.getBuff(), false);
                    break;

                }
                default: {
                    AttackUtil.resetBuff(ra.getBuff(), true);
                }
            }


        }
    }

    public void resetBuff(List<PkRoleVo> rvLiA1) {
        for (PkRoleVo ra : rvLiA1) {
            ra.setKouXue(0);
            ra.setAttakType("无");
            ra.setMpBuff(new HashMap<String, Integer>());
        }
    }

    /**
     * 随机选择攻击敌方英雄
     *
     * @param yeguaiMapLi
     * @return
     * @throws Exception
     */
    public static List<String> addAttacked(Map<String, PkRoleVo> rvMap, List<Map<String, Object>> yeguaiMapLi, Map<String, Object> pkJsonSon, Integer num) throws Exception {
        int size = yeguaiMapLi.size();
        List<String> playerArr = (List<String>) pkJsonSon.get("player2");
        //对方只有一个人的时候，群体效果不叠加
        if (size > 1) {

            //构建气血不为0的集合
            List<Map<String, Object>> selectedLi = new ArrayList<>();
            for (Map<String, Object> m : yeguaiMapLi) {
                String id = (String) m.get("player1");
                //PkRoleVo pv= rvMap.get(id);
                if (!playerArr.get(0).equals(id)) {
                    selectedLi.add(m);
                }
            }
            if (selectedLi.size() > 0) {
                if (num > selectedLi.size()) {
                    num = selectedLi.size();
                }

                //随机获取num个
                List<Integer> rundInt = MathUtil.round(new ArrayList<Integer>(), selectedLi.size(), num);
                Map<String, Map<String, Object>> battleData = (Map<String, Map<String, Object>>) pkJsonSon.get("battleData");
                for (Integer n : rundInt) {
                    Map<String, Object> selectedLiSon = selectedLi.get(n);
                    String p1 = (String) selectedLiSon.get("player1");
                    playerArr.add(p1);
                    //构建被攻击这状态信息
                    Map<String, Object> mp = new HashMap<>();
                    mp.put("Qx2", "0");
                    mp.put("Qx", "0");
                    mp.put("Jl", "0");
                    battleData.put(p1, mp);
                }
                pkJsonSon.put("player2", playerArr);

            }
        }
        return playerArr;
    }

    private PkPlayData copyPkRoleVo(PkRoleVo pvb) {
        List<RoleNewShuXing> lisxb = pvb.getShuXingLi();
        PkPlayData pb2 = new PkPlayData();
        //转化类型
        Map<String, Integer> roleMap = new HashMap<>();
        for (RoleNewShuXing r : lisxb) {
            roleMap.put(r.getKangxing(), r.getKangxingtotal());
        }

        pb2.setTotalgong(pvb.getTotalgong());
        pb2.setTotaljing1(pvb.getJingli());
        pb2.setTotaljing2(pvb.getJingli2());
        pb2.setTotalxue1(pvb.getQixue());

        pb2.setTotalxue2(pvb.getQixue2());
        pb2.setSkill(pvb.getJineng());
        pb2.setRoleMap(roleMap);
        return pb2;
    }

    public static Boolean ganmeOver(List<Map<String, Object>> playJson, Map<String, PkRoleVo> rvMap) {
        Boolean re = true;
        for (Map<String, Object> play : playJson) {

            String player1 = (String) play.get("player1");
            PkRoleVo p = rvMap.get(player1);
            if (p.getQixue() > 0) {
                re = false;
            }
        }
        return re;
    }

    public static Boolean ganmeOver2(List<PkRoleVo> pkv) {
        Boolean re = true;
        for (PkRoleVo p : pkv) {
            if (p.getQixue() > 0) {
                re = false;
            }
        }
        return re;
    }


    /**
     * 合并集合并排序
     *
     * @param source
     * @param targer
     * @return
     */
    public static List sortList(List<Map<String, Object>> source, List<Map<String, Object>> targer, List<PkRoleVo> rvLiTotal) {
        List<Map<String, Object>> li = new ArrayList<>();
        for (PkRoleVo p : rvLiTotal) {
            String id = p.getId();
            Map<String, Object> mp = getMapById(source, id);
            if (mp == null) {
                mp = getMapById(targer, id);
            }
            if (mp != null) {
                li.add(mp);
            }

        }
        return li;
    }

    public static Map<String, Object> getMapById(List<Map<String, Object>> source, String id) {
        for (Map<String, Object> m : source) {
            //0000正常数据，feng,wei,luan,需要自减
            String key = (String) m.get("status");
            switch ((String) m.get("status")) {
                case "0000": {
                    String p1 = (String) m.get("player1");

                    if (id.equals(p1)) {
                        return m;
                    }
                    break;
                }

//             case "yin":
//             case "gu":
//             case "su":
//             case "du":
//             case "gong":
//             case "luan":
//             case "wei":
//             case "feng":{
//                 //将buff --
//                 Map<String,Object> mp= (Map<String, Object>) m.get("battleData");
//                 if(mp!=null){
//                     //
//                     for (String in: mp.keySet()) {
//                         Map<String,Object> play = (Map<String, Object>) mp.get(in);
//                        List<Map<String,Object>> buffLi= (List<Map<String, Object>>) play.get("buff");
//                        if(buffLi!=null&&buffLi.size()>0){
//                            Iterator<Map<String, Object>> it = buffLi.iterator();
//                            while(it.hasNext()) {
//                                Map<String, Object> b= it.next();
//                                Integer valu= (Integer) b.get(key);
//                                valu--;
//                                if(valu==0){
//                                    it.remove();
//                                }
//
//
//                            }
//                        }
//                     }
//                 }
//
//
//                 break;
//             }
            }

        }
        return null;
    }

    public static Map<String, PkRoleVo> getPkRoleVoMap(Map<String, PkRoleVo> m, String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            Iterator<String> iter = m.keySet().iterator();
            while (iter.hasNext()) {
                if (m.size() > 0) {
                    String key = iter.next();
                    PkRoleVo p = m.get(key);
                    if (p.getQixue2() <= 0) {
                        iter.remove();
                        if (m.size() > 0) {
                            System.out.println("m的size:" + m.size());
                            return getPkRoleVoMap(m, null);
                        }
                    }

                }
            }
        } else {
            PkRoleVo pv = m.get(uuid);
            if (pv.getQixue2() <= 0) {
                m.remove(uuid);
                return getPkRoleVoMap(m, null);
            }

        }
        return m;
    }


//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        logger.debug("pkServer Connection open！");
//    }
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        try {
//            System.out.println("Connection Closed！");
//            String key= MapUtil.getRoleId(PKMAP, session);
//            PKMAP.remove(key);
//        } catch (Exception e) {
//           logger.error("移除PKsession异常:"+e.getMessage(),e);
//        }
//    }
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        super.handleTransportError(session, exception);
//    }

    /**
     * 创建副将
     *
     * @param fg     副将数据类
     * @param fLevel 创建等级
     * @param roleId 所属角色Id
     * @param key    是否随机分配属性点
     * @return
     */
    public static RoleFujiang CreateRoleFujiang(FuJiang fg, Integer fLevel, Integer fLeve2, Boolean key, Integer roleId) throws Exception {
        RoleFujiang rf = new RoleFujiang();
        //等级
        Integer tsum = fLevel * 4;
        //随机分配可用点数
        int ll = 0;
        int tz = 0;
        int zl = 0;
        int mj = 0;
        if (key) {
            // 力量点数
            ll = (int) Math.round(Math.random() * tsum);
            // 体质点
            tz = (int) Math.round(Math.random() * (tsum - ll));
            //智力点
            zl = (int) Math.round(Math.random() * (tsum - ll - tz));
            // 敏捷点数
            mj = tsum - ll - tz - zl;
            rf.setKeyongds(0);
        } else {
            rf.setKeyongds(tsum);
        }
        rf.setQixueds(fLevel + tz);
        rf.setJinglids(fLevel + zl);
        rf.setGongjids(fLevel + ll);
        rf.setSududs(fLevel + mj);
        //精确2位小数
        DecimalFormat dt = new DecimalFormat("0.00");
        float cz = ((float) (Math.floor(Math.random() * 21 - 20.0)) / 100) + fg.getChengzhang();
        rf.setChengzhang(Float.parseFloat(dt.format(cz)));

        rf.setChufang(fg.getChufang());

        rf.setChugong(fg.getChugong());

        int sd = ((int) Math.round(Math.random() * 21 - 20));

        rf.setChusu(fg.getChusu() + sd);//随机+-20设置速度属性
        //速度值=（初速+敏捷点）*成长*0.9
        int tsudu = (int) (cz * (fg.getChusu() + sd + mj));

        //转生
        rf.setZhuansheng(1);
        //真假副将
        rf.setZhen("0");

        int qx = ((int) Math.round(Math.random() * 21 - 20));
        //初血
        rf.setChuxue(fg.getChuxue() + qx);
        //总气血点数=成长*等级*（气血值*0.8+体质点）
        rf.setTotalxue2((int) (cz * (rf.getQixueds() * fLevel) + cz * (fg.getChuxue() + qx) * fLevel * 0.8));//成长X（体质X等级）+71*成长 （4,5）*等级+等级*气血值
        rf.setTotalxue1((int) (cz * (rf.getQixueds() * fLevel) + cz * (fg.getChuxue() + qx) * fLevel * 0.8));
        int jl = ((int) Math.round(Math.random() * 21 - 20));
        rf.setChujing(fg.getChujing() + jl);
        //总气血精力点数=成长*等级*（精力值*0.8+智力点）
        rf.setTotaljing1((int) (cz * (rf.getJinglids() * fLevel) + cz * (fg.getChujing() + jl) * fLevel * 0.8));
        rf.setTotaljing2((int) (cz * (rf.getJinglids() * fLevel) + cz * (fg.getChujing() + jl) * fLevel * 0.8));
        int gj = ((int) Math.round(Math.random() * 21 - 20));
        //初攻
        rf.setChugong(fg.getChugong() + gj);
        //攻击=成长*（攻击初值*(力量点*/7+0.5)+力量点*0.2*等级）
        rf.setTotalgong((int) ((rf.getGongjids() * cz * (fg.getChugong() + gj)) / 7 + (fg.getChugong() + gj) * cz * 0.5 + rf.getGongjids() * cz * 0.2 * fLevel));
        rf.setFuid(fg.getId());
        rf.setRoleid(roleId);
        rf.setFujiangname(fg.getFujiangname());
        rf.setLeve(fLevel);
        //升级经验
        rf.setSjjinyan(0);
        rf.setSex(fg.getSex());
        rf.setStatus("战斗");
        rf.setTouxian(fg.getTouxian());
        rf.setTouxiang(fg.getTouxiang());
        int zy = (int) (Math.random() * 2);
        switch (zy) {
            case 0:
                rf.setZhiye("武士");
                rf.setTotalsudu(tsudu);
                break;
            case 1:
                rf.setZhiye("文人");//文人减速
                rf.setTotalsudu((int) Math.round(tsudu * 0.9));
                break;
            case 2:
                rf.setZhiye("异人");
                rf.setTotalsudu(tsudu);
                break;
        }
        List<RoleNewShuXing> sxLi = insertRoleShuXing(rf.getZhiye(), roleId, "1");
        rf.setShuXingLi(sxLi);
        //抗性植入
        return rf;
    }


    public static List<RoleNewShuXing> insertRoleShuXing(String zhiye, Integer roleid, String status) throws Exception {
        List<RoleNewShuXing> li = new ArrayList<RoleNewShuXing>();
        switch (zhiye) {
            case "异人": {
                li.add(insertShuXing("命中率", roleid, 75, status));
                li.add(insertShuXing("暴击率", roleid, 10, status));
                li.add(insertShuXing("反击率", roleid, 10, status));
                li.add(insertShuXing("致命率", roleid, 5, status));
                li.add(insertShuXing("法术暴", roleid, 5, status));
                li.add(insertShuXing("反震率", roleid, 10, status));
                li.add(insertShuXing("躲避率", roleid, 5, status));
                li.add(insertShuXing("抗物理", roleid, 0, status));
                li.add(insertShuXing("抗玄击", roleid, 0, status));
                li.add(insertShuXing("抗围困", roleid, 0, status));
                li.add(insertShuXing("抗扰乱", roleid, 0, status));
                li.add(insertShuXing("抗封锁", roleid, 0, status));
                li.add(insertShuXing("抗风沙", roleid, 10, status));
                li.add(insertShuXing("抗妖火", roleid, 10, status));
                li.add(insertShuXing("抗落雷", roleid, 10, status));
                li.add(insertShuXing("抗毒术", roleid, 10, status));
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
            case "文人": {
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
                li.add(insertShuXing("命中率", roleid, 80, status));
                li.add(insertShuXing("暴击率", roleid, 10, status));
                li.add(insertShuXing("反击率", roleid, 10, status));
                li.add(insertShuXing("致命率", roleid, 5, status));
                li.add(insertShuXing("法术暴", roleid, 0, status));
                li.add(insertShuXing("反震率", roleid, 10, status));
                li.add(insertShuXing("躲避率", roleid, 5, status));
                li.add(insertShuXing("抗物理", roleid, 0, status));
                li.add(insertShuXing("抗玄击", roleid, 0, status));
                li.add(insertShuXing("抗围困", roleid, 10, status));
                li.add(insertShuXing("抗扰乱", roleid, 10, status));
                li.add(insertShuXing("抗封锁", roleid, 10, status));
                li.add(insertShuXing("抗风沙", roleid, 0, status));
                li.add(insertShuXing("抗妖火", roleid, 0, status));
                li.add(insertShuXing("抗落雷", roleid, 0, status));
                li.add(insertShuXing("抗毒术", roleid, 0, status));

                break;
            }
            case "武士": {
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
                li.add(insertShuXing("命中率", roleid, 85, status));
                li.add(insertShuXing("暴击率", roleid, 15, status));
                li.add(insertShuXing("反击率", roleid, 15, status));
                li.add(insertShuXing("致命率", roleid, 5, status));
                li.add(insertShuXing("法术暴", roleid, 0, status));
                li.add(insertShuXing("反震率", roleid, 15, status));
                li.add(insertShuXing("躲避率", roleid, 5, status));
                li.add(insertShuXing("抗物理", roleid, 20, status));
                li.add(insertShuXing("抗玄击", roleid, 5, status));
                li.add(insertShuXing("抗围困", roleid, 0, status));
                li.add(insertShuXing("抗扰乱", roleid, 0, status));
                li.add(insertShuXing("抗封锁", roleid, 0, status));
                li.add(insertShuXing("抗风沙", roleid, 0, status));
                li.add(insertShuXing("抗妖火", roleid, 0, status));
                li.add(insertShuXing("抗落雷", roleid, 0, status));
                li.add(insertShuXing("抗毒术", roleid, 0, status));

                break;
            }
        }
        return li;
    }

    public static RoleNewShuXing insertShuXing(String kx, Integer roleid, Integer tx, String status) throws Exception {
        RoleNewShuXing bx = new RoleNewShuXing();
        bx.setKangxing(kx);
        bx.setRoleid(roleid);
        if (bx.getKangxingtotal() == null) {
            bx.setKangxingtotal(0);
        }
        bx.setStatus(status);
        bx.setKangxingtotal(bx.getKangxingtotal() + tx);
        return bx;
    }

    public static void main(String[] args) {


        System.out.println((int) (Math.random() * 2));
    }
}

package com.hxsg.pk.websoket;


import com.google.gson.Gson;

import com.hxsg.CommonServer.CommonServer;
import com.hxsg.CommonUtil.ExpUtil;
import com.hxsg.CommonUtil.FinalMap;
import com.hxsg.CommonUtil.MathUtil;
import com.hxsg.Dao.*;
import com.hxsg.dao.FuJiangService;
import com.hxsg.dao.HxsgBattle;
import com.hxsg.dao.InitPlayData;
import com.hxsg.dao.PkVoService;
import com.hxsg.po.*;
import com.hxsg.redis.RedisDaoService;

import com.hxsg.systemdao.SystemNotification;
import com.hxsg.util.AttackUtil;
import com.hxsg.vo.*;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * PK服务器webscoket通信
 * <p>
 * <p>
 * {'type':'pkWait','money':1000,'key':{'roleA':1000,'roleB':1000,'key':'dwjdhwdg233','level':'10,20'},'pkType':'1000'}
 */
@RequestMapping("pkServer")
public class WebSocketPkServer extends TextWebSocketHandler {
    private static Log logger = LogFactory.getLog(WebSocketPkServer.class);

    public final static Map<String, Object> PKMAP = new Hashtable<String, Object>();
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
    InitPlayData initplaydata;
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

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            //初始化加载
            String msg = message.getPayload();
            System.out.println(msg);
            Gson gn = new Gson();
            Map<String, Object> dataMap = gn.fromJson(msg, Map.class);
            String type = (String) dataMap.get("type");
            List roleLi = null;
            switch (type) {
                //1.加入PK队列等待B玩家加入
                case "pkWait": {
                    Map<String, Object> jsonDataMap = (Map<String, Object>) dataMap.get("jsonData");
                    String roleA = (String) jsonDataMap.get("roleA");
                    //NewRole roleaData = (NewRole) redisdaoservice.get("role:msg:" + roleA);
                    NewRole roleaData = newrolemapper.queryTotalShuXingToRole(Integer.parseInt(roleA));
                    //test
                    roleaData.setTotalxue1(200000);

                    //加入wession维护
                    FinalMap.PKROLESESSION.put(roleA, session);

                    //验证pk类型
                    String pkType = (String) dataMap.get("pkType");
//                    if(PKROLE.equals(pkType)){
//                     //验证是否是当前玩家操作，验证B玩家是否在线，或者是否跟A玩家坐标是否在同一地点
//                        String aKey= (String) redisdaoservice.get(roleA.toString());
//                        if(key.equals(aKey)){
//                            NewRole rolebData= (NewRole) redisdaoservice.get("role:msg:"+roleB);
//                            String bstatus=rolebData.getRoleStatus();
//                            if("上线".equals(bstatus)){
//                            }else{
//                                //通知A玩家，B玩家下线，或者B玩家正在Pk本次发起结束
//                            }
//
//                        }else{
//                            //恶意行为，封锁该IP
//                        }
//                    }
                    if (PKGUAI.equals(pkType)) {
                        //野怪
                        List<RoleFujiang> yeGuaiLi = null;
                        List<RoleFujiang> yeGuaiLiTemp = null;
                        Map<String, String> guaiData = (Map<String, String>) jsonDataMap.get("guaiData");
                        //野怪等级范围
                        String level = (String) guaiData.get("level");
                        //野怪群名称
                        String name = (String) guaiData.get("name");
                        //野怪等级
                        String[] ls = level.split(",");
                        Integer start = Integer.parseInt(ls[0]);
                        Integer end = Integer.parseInt(ls[1]);
                        YeGuaiQun yq = yeguaiqunmapper.selectByName(name);
                        //数据不完整过滤
                        if (yq == null) {
                            yq = yeguaiqunmapper.selectByName("女贼");
                            logger.debug("没有找到该野怪：" + name);

                        }
                        //初始化野怪数据到List集合中
                        yeGuaiLi = getRoleFuJiangList(start, end, yq, roleaData.getId());
                        yeGuaiLiTemp = yeGuaiLi;
                        //封装返回客户端数据
                        List<PkRoleVo> rvLiTotal = new ArrayList<>();
                        List<PkRoleVo> rvLiA = new ArrayList<>();
                        List<PkRoleVo> rvLiB = new ArrayList<>();
                        List<Integer> sortLi = new ArrayList<>();
                        //uuid封装
                        List<String> uuidLi = new ArrayList<>();
                        Map<String, PkRoleVo> rvMap = new HashMap<>();
                        for (RoleFujiang r : yeGuaiLi) {
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
                        List<RoleNewShuXing> shuxili = rolenewshuxingmapper.queryRoleShuXing(roleaData.getId(), 0);

                        //test
//                        for (RoleNewShuXing r : shuxili) {
//                            if ("反震率".equals(r.getKangxing())) {
//                                r.setKangxingtotal(100);
//                            }
//                        }
                        //test
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
                        List<PkRoleVo> pvLi = pkvoservice.getPkRoleVo(rv.getId(), "休息");
                        for (PkRoleVo p : pvLi) {
                            rvLiTotal.add(p);
                            rvLiA.add(p);
                            rvMap.put(p.getId(), p);
                            uuidLi.add(p.getId());
                            sortLi.add(p.getSudu());
                        }
                        //构建玩家副
                        /*************************************测试代码 start*****************************************/
//                        PkRoleVo rv2= new PkRoleVo();
//                        //test
//                        BeanUtils.copyProperties(rv,rv2);
//                        rv2.setId("1000022");
//                        rvLiTotal.add(rv2);
//                        rvLiA.add(rv2);
//                        rvMap.put(rv2.getId(), rv2);
//                        uuidLi.add(rv2.getId());
//                        sortLi.add(rv2.getSudu());

                        //tesst
                        //rvLiA.add(rv);

                        /*************************************测试代码 end*****************************************/

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
                        //默认等待60S自动攻击
                        Calendar cal = Calendar.getInstance();//得到当前时间
                        long nowTime = cal.getTimeInMillis();
                        // out:
                        boolean key = true;
                        String uuidA = null;
                        String uuidB = null;
                        if (key) {
                            //本次PK对方标志
                            uuidA = UUID.randomUUID().toString();
                            Map<String, Object> jsonMp = new HashMap<>();
                            jsonMp.put("uuid", uuidA);
                            jsonMp.put("rvLiB", rvLiB);
                            jsonMp.put("rvLiA", rvLiA);
                            systemnotification.sendSystemMsg(new Object[]{jsonMp, "roleMsg"}, roleA);
                            PKMAP.put(uuidA, uuidA);
                            key = false;
                        }
                        //处理pk过程
                        notices(rvMap, uuidLi, roleA, roleA, uuidA, true, rvLiTotal, rvLiA, rvLiB);
                        //pk完成，数据结算，奖励发放
                        Map<String, Object> jyMp = new HashMap<>();
                        //1.主角经验
                        Integer zjJy = ExpUtil.caleExp(roleaData.getLevel(), start, yq.size());
                        boolean zjRe = commonserver.expConversionLevel(roleaData.getId(), zjJy);
                        if (zjRe) {
                            jyMp.put(roleaData.getId().toString(), roleaData.getRolename() + "获得" + zjJy + "经验," + "升至" + (roleaData.getLevel() + 1) + "级");
                        } else {
                            jyMp.put(roleaData.getId().toString(), roleaData.getRolename() + "获得" + zjJy + "经验");
                        }
                        //副将经验
                        for (PkRoleVo p : pvLi) {
                            Integer fjjy = ExpUtil.caleExp(p.getLevel(), start, yq.size());
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
                        systemnotification.sendSystemMsg(new Object[]{mpJson, "gameOver"}, roleA);
                        logger.debug("本次PK结束，进行战斗奖励发放");
                    }
                }

            }
        } catch (Exception e) {
            logger.error("连接pkServer异常:" + e.getMessage(), e);
        }
    }

    private List<Map<String, Object>> getYeGuaiAi(List<PkRoleVo> rvLiA, List<PkRoleVo> rvLiB) {
        List<Map<String, Object>> yeguaiMapLi = new ArrayList<>();
        Gson gn = new Gson();
        for (PkRoleVo p : rvLiB) {
            //气血大于0的话，才能执行攻击动作
            if (p.getQixue() > 0) {
                //验证buff
                String buff = yzBuff(p.getBuff());
                PkRoleVo pv = getRandom(rvLiA);
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

    public List<RoleFujiang> getRoleFuJiangList(Integer start, Integer end, YeGuaiQun yq, Integer roleId) throws Exception {
        List<RoleFujiang> rfLi = new ArrayList<RoleFujiang>();
        if (yq != null) {
            String g1 = yq.getGuai1();
            RoleFujiang rf1 = pkserverinitdata.CreateRoleFujiang(g1, start, end, roleId);

            String g2 = yq.getGuai2();
            RoleFujiang rf2 = pkserverinitdata.CreateRoleFujiang(g2, start, end, roleId);

            String g3 = yq.getGuai3();
            RoleFujiang rf3 = pkserverinitdata.CreateRoleFujiang(g3, start, end, roleId);

            String g4 = yq.getGuai4();
            RoleFujiang rf4 = pkserverinitdata.CreateRoleFujiang(g4, start, end, roleId);

            String g5 = yq.getGuai5();
            RoleFujiang rf5 = pkserverinitdata.CreateRoleFujiang(g5, start, end, roleId);

            String g6 = yq.getGuai6();
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

    //客户端速度很快的点击自动攻击有可能造成2次计算
    private void notices(Map<String, PkRoleVo> rvMap, List<String> uuidLi, String roleA, String roleB, String uuidA, Boolean key, List<PkRoleVo> rvLiTotal, List<PkRoleVo> rvLiA1, List<PkRoleVo> rvLiB2) throws Exception {
        //开始等待时间，最多60s
        long pkWaitTimeStart = new Date().getTime();
        boolean endKey = false;
        String winPlay = "6000";//战斗中
        int hhNUm = 0;
        out:
        while (true) {
            //  String amsg = (String) PKMAP.get(uuidA);
            Object uuidAOb = PKMAP.get(uuidA);
            String bmsg = (String) PKMAP.get("B");

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

            if (uuidAOb instanceof List && (bmsg != null || key)) {
                //重置双方玩家信息
                resetBuff(rvLiA1);
                resetBuff(rvLiB2);


//                List<PkRoleVo> rvLiB=new ArrayList<>();
//                List<PkRoleVo> rvLiA=new ArrayList<>();
                //获取到AB客户端数据
                // 根据指令计算伤害,如果有一方气血全部为0.结束
                List<Map<String, Object>> playJson = (List<Map<String, Object>>) uuidAOb;
                Iterator it = playJson.iterator();

                while (it.hasNext()) {
                    Map<String, Object> m = (Map<String, Object>) it.next();
                    String pj1 = (String) m.get("player1");
                    PkRoleVo p = rvMap.get(pj1);
                    String buff = yzBuff(p.getBuff());
                    m.put("status", buff);
                    if (p.getQixue() <= 0) {
                        it.remove();
                    }
                }

                //校验 playJson buff
//                for (Map<String, Object> m : playJson) {
//
//
//                }

                //排序-----start
                Collections.sort(rvLiTotal);

                List<Map<String, Object>> yeguaiMapLi = getYeGuaiAi(rvLiA1, rvLiB2);

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
                    PkRoleVo p1Pv = rvMap.get(player1);
                    if (p1Pv.getQixue() > 0) {
                        //每次攻击前需要验证被攻击者是否气血为0，为0的话则被攻击者为对方玩家集合中气血不为0的第一个
                        PkRoleVo p2Pv = rvMap.get(player2);
                        if (p2Pv.getQixue() <= 0) {
                            List<PkRoleVo> pvLi = null;
                            if (rvLiA1.contains(p2Pv)) {
                                pvLi = rvLiA1;
                            } else {
                                pvLi = rvLiB2;

                            }
                            for (PkRoleVo p : pvLi) {
                                if (p.getQixue() > 0) {
                                    List<String> arr = new ArrayList<>();
                                    arr.add(p.getId());
                                    pkJson.get(i).put("player2", arr);
                                    Map<String, Object> battleData = (Map<String, Object>) pkJson.get(i).get("battleData");
                                    battleData.put(p.getId(), battleData.get(player2));
                                    battleData.remove(player2);
                                    playerArr = arr;
                                    break;
                                }
                            }

                        }

                        pkJsonTemp.add(pkJson.get(i));
                        Map<String, Map<String, Object>> battleData = (Map<String, Map<String, Object>>) pkJson.get(i).get("battleData");

                        Map<String, String> attackType = (Map<String, String>) pkJson.get(i).get("attackType");

                        //战斗数据

                        //验证攻击
                        if (uuidLi.contains(player2)) {
                            //构造攻击者玩家数据
                            PkRoleVo pvb1 = rvMap.get(player1);
                            //根据技能和熟练度，为群体技能设置攻击人数
                            String skillName = attackType.get("name");
                            Skill skill = AttackUtil.getSkill(pvb1, skillName);
                            if (skill != null) {
                                switch (skillName) {
                                    case "排山倒海":
                                    case "巫蛊极毒":
                                    case "妖火燎原":
                                    case "呼风唤雨": {
                                        //模拟攻击三个
                                        if (1 == 1) {
                                            addAttacked(rvMap, yeguaiMapLi, pkJson.get(i), 2);
                                        }

                                        break;
                                    }
                                    case "画地为牢": {
                                        if (1 == 1) {
                                            addAttacked(rvMap, yeguaiMapLi, pkJson.get(i), 2);

                                        }

                                        break;
                                    }
                                    case "气冲斗牛": {
                                        addAttacked(rvMap, yeguaiMapLi, pkJson.get(i), 1);


                                        break;
                                    }
                                    //.....
                                }

                            }

                            //被攻击者详细数据
                            List<PkRoleVo> pvLi = new ArrayList<>();
                            for (String s : playerArr) {
                                PkRoleVo pv = rvMap.get(s);
                                pv.setMpBuff(new HashMap<String, Integer>());
                                pvLi.add(pv);
                            }

                            AttackA atta = hxsgbattle.skillAttack(pvb1, pvLi, skillName);

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

//                        if (atta.getAttackbLi().get(0) != null && atta.getAttackbLi().get(0).getBuffType() != null) {
//                            attackType.put("buffTypeValue", atta.getAttackbLi().get(0).getBuffTypeValue() + "");
//                            attackType.put("buffType", atta.getAttackbLi().get(0).getBuffType() + "");
//                        }
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

                            //判断是否存在一方玩家所有气血全部为0的情况
                            //1.判断玩家A是否气血全部为o
                            Boolean play1 = ganmeOver(playJson, rvMap);
                            Boolean play2 = ganmeOver(yeguaiMapLi, rvMap);
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
                            break out;
                        }


                    }


                    //发送结算数据

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
                jsonMp.put("rvLiA", rvLiA1);
                jsonMp.put("rvLiB", rvLiB2);
                PKMAP.put(uuidA, uuidA);
                countBuff(rvLiA1);
                countBuff(rvLiB2);
                //统一处理回合数buff --
                systemnotification.sendSystemMsg(new Object[]{jsonMp, "sendPkMsg"}, roleA);

                if (endKey) {
                    break out;
                }


            } else {
                Thread.sleep(2000);
                System.out.println("等待客户端指令:" + uuidA);
            }

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
        int zy = (int) (Math.random() * 3);
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
        List<String> li = new ArrayList<>();
        li.add("1");
        li.add("2");
        li.add("3");
        li.remove("1");
        li.remove("2");
        System.out.println(li.get(0));
    }

}
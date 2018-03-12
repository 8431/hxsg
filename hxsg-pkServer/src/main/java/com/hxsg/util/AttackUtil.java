package com.hxsg.util;

import com.hxsg.Dao.RoleYaoMapper;
import com.hxsg.Dao.SkillMapper;
import com.hxsg.po.RoleYao;
import com.hxsg.po.Skill;
import com.hxsg.vo.AttackA;
import com.hxsg.vo.AttackB;
import com.hxsg.vo.PkPlayData;
import com.hxsg.vo.PkRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/5/17 0017.
 * 攻击数值计算工具类
 * <p>
 * 抗性分类
 * '抗物理'
 * '命中率'
 * '暴击率'
 * '致命率'
 * '反击率'
 * '躲避率'
 * '反震率'
 * '抗玄击'
 * '法术暴'
 * '抗围困'
 * '抗扰乱'
 * '抗封锁'
 * '抗风沙'
 * '抗妖火'
 * '抗落雷'
 * '抗毒术'
 */
public class AttackUtil {
    /**
     * A-----普通攻击----B
     *
     * @param A
     * @param B
     * @return
     */
    @Autowired
    static RoleYaoMapper roleyaomapper;
    public static AttackA generalAttack(PkPlayData A, PkPlayData B) {
        //伤害值
//            AttackA av=new AttackA();
//             int sh=0;
//             Integer attackA=A.getTotalgong();
//             Integer attackB=B.getTotalgong();
//             Map<String, Integer> sxMap_A=A.getRoleMap();
//             Map<String, Integer> sxMap_B=B.getRoleMap();
//             Integer mzl_A=sxMap_A.get("命中率");
//             Integer bjl_A=sxMap_A.get("暴击率");//1.5倍伤害
//             Integer zml_A=sxMap_A.get("致命率");//当前HP百分之20
//             Integer dbl_A=sxMap_A.get("躲避率");
//             Integer fjl_A=sxMap_A.get("反击率");
//             Integer fzl_A=sxMap_A.get("反震率");
//             Integer kwl_A=sxMap_A.get("抗物理");
//
//             Integer mzl_B=sxMap_B.get("命中率");
//             Integer bjl_B=sxMap_B.get("暴击率");//1.5倍伤害
//             Integer zml_B=sxMap_B.get("致命率");//当前HP百分之20
//             Integer dbl_B=sxMap_B.get("躲避率");
//             Integer fjl_B=sxMap_B.get("反击率");
//             Integer fzl_B=sxMap_B.get("反震率");
//             Integer kwl_B=sxMap_B.get("抗物理");
//             Integer ttxue_B= B.getTotalxue1();
//             Integer ttxue_A= A.getTotalxue1();
//            //A的命中率减去B的躲避率=A的实际命中率
//             mzl_A=mzl_A-dbl_B;
//             mzl_B=mzl_B-dbl_A;
//            //B优先顺序，暴击-致命-命中
//            int fjgl_B= (int) (Math.round(Math.random()*100)+1);
//            int fzgl_B= (int) (Math.round(Math.random()*100)+1);
//        AttackA arv_a = attackType(attackA, mzl_A, bjl_A, zml_A, kwl_B, ttxue_B);
//         sh=arv_a.getShValue_A();
//         av.setAttakType_A(arv_a.getAttakType_A());
//         av.setShValue_A(sh);
//        Boolean key=true;
//        if("封".equals(B.getBuffType())){
//            key=false;
//        }
//        //检测B是否触发反击或者反震概率
//        int a_sh=0;
//        if(!"致命一击".equals(av.getAttakType_A())&&sh>0){
//            if(key){
//                if(fzl_B>fzgl_B){
//                  a_sh=sh;
//                    av.setAttakType_B("反震");
//                }else if(fjl_B>fjgl_B){
//                    AttackA arv = attackType(attackB, mzl_B, bjl_B, zml_B, kwl_A, ttxue_A);
//                    a_sh= arv.getShValue_A();
//                    av.setAttakType_B(arv.getAttakType_A());
//
//                }
//            }
//        }
//        av.setShValue_B(a_sh);
//        A.setTotalxue1(ttxue_A - a_sh > 0 ? (ttxue_A - a_sh) : 0);
//        B.setTotalxue1(ttxue_B-sh>0?(ttxue_B-sh):0);
//        av.setKouXue_A(A.getTotalxue1());
//        av.setKouXue_B(B.getTotalxue1());
//       return  av;
        return null;
    }

    public static AttackA attackType(Integer attackA, Integer mzl_A, Integer bjl_A, Integer zml_A, Integer kwl_B, Integer ttxue_b) {
        AttackA av = new AttackA();
        int sh = 0;
        //A优先顺序-命中-暴击-致命
        int mzgl = (int) (Math.round(Math.random() * 100) + 1);
        int bjgl = (int) (Math.round(Math.random() * 100) + 1);
        int zmgl = (int) (Math.round(Math.random() * 100) + 1);

        if (true) {
            //命中成功，检测暴击概率
            if (bjl_A > bjgl) {
                //暴击伤害
                av.setAttakType_A("暴击");
                sh = (int) Math.round(attackA * 1.5 * (100 - kwl_B) / 100) + 1;
                System.out.println("---------" + sh);
            } else if (zml_A > zmgl) {
                //致命伤害
                av.setAttakType_A("致命一击");
                sh = (int) Math.round(ttxue_b * 0.2) + 1;
            } else {
                //普通伤害
                av.setAttakType_A("普通攻击");
                sh = (int) Math.round(attackA * (100 - kwl_B) / 100) + 1;
            }
        } else {
            av.setAttakType_A("普通攻击");
        }
        av.setShValue_A(sh);
        return av;
    }

    /**
     * 检测反震攻击类型
     *
     * @param name
     * @return
     */
    public static boolean fanZhengData(String name) {
        boolean re = false;
        switch (name) {
            case "普通攻击":
            case "五雷轰顶":
            case "妖火燎原":
            case "呼风唤雨":
            case "舍命一击": {
                re = true;
                break;
            }
        }
        return re;
    }

    /**
     * 反震--普通攻击，暴击，五雷轰顶，妖火燎原，呼风唤雨，舍命一击
     * <p>
     * 待补充-----------
     * 伤害公式
     * 耗蓝公式
     *
     * @param skillName
     * @return
     */
    public static AttackA skillAttack(PkRoleVo p1, List<PkRoleVo> p2Li, String skillName) throws Exception {

        AttackA av = new AttackA();
        List<AttackB> attackbLi = new ArrayList<AttackB>();
        av.setAttackbLi(attackbLi);
        //A攻击者属性
        Integer attackA = p1.getTotalgong();//p1攻击力

        Map<String, Integer> mpBuff_a = p1.getMpBuff();//p1 buff状态
        int toxue_A = p1.getQixue();//血
        Map<String, Integer> sxMap_A = p1.getSxLi();// p1 属性集合
        Integer kwl_A = sxMap_A.get("抗物理");
        Integer mzl_A = sxMap_A.get("命中率");
        Integer bjl_A = sxMap_A.get("暴击率");//1.5倍伤害
        Integer zml_A = sxMap_A.get("致命率");//当前HP百分之20
        Integer dbl_A = sxMap_A.get("躲避率");
        Integer fsb_A = sxMap_A.get("法术暴");
        int fsbjgl_A = (int) (Math.round(Math.random() * 100));//法术暴击概率

        //被攻击者
        for (PkRoleVo p2 : p2Li) {
            //被攻击者返回数据包装类
            AttackB arB = new AttackB();
            //B的buff结合
            Map<String, Integer> mpBuff_b = p2.getMpBuff();
            List<Map<String, Object>> buff_b = p2.getBuff();

            /**
             *  1.判断被攻击者的BUFF状态 （buff状态:四面楚歌，画地为牢，趁火打劫，气冲斗牛，巫蛊极毒）
             *  2.除了画地为牢状态下被攻击后，buff受影响次数重置为0，其他的每回合递减1
             */
//           if(!StringUtils.isEmpty(B.getBuffType())){
//               if("画地为牢".equals(B.getBuffType())){
//                   B.setBuffTypeValue(0);
//               }else {
//                   B.setBuffTypeValue(B.getBuffTypeValue()-1>0?(B.getBuffTypeValue()-1):0);
//               }
//
//           }
            //B被攻击者属性
            Integer attackB = p2.getTotalgong();//p2攻击力
            Map<String, Integer> sxMap_B = p2.getSxLi();//p2属性集合
            int toxue_B = p2.getQixue();//p2血
            int tolan_B = p2.getJingli();//p2蓝
            Integer fzl_B = sxMap_B.get("反震率");
            Integer kwl_B = sxMap_B.get("抗物理");
            Integer mzl_B = sxMap_B.get("命中率");
            Integer dbl_B = sxMap_B.get("躲避率");
            Integer fjl_B = sxMap_B.get("反击率");
            Integer zml_B = sxMap_B.get("致命率");//当前HP百分之20
            Integer bjl_B = sxMap_B.get("暴击率");//1.5倍伤害
            /**
             * 通过技能计算伤害值和buff效果
             * 1.验证技能
             * 2.
             */
            int sh_xue = 0;
            int sh_lan = 0;
            int sh_buff = 0;
             /*
             *  1.判断技能是否具备反震资格
             *  2.判断人物反震概率是否引起反震
             *  3.统计伤害，整理战斗数据
             */
            //1.判断技能是否具备反震资格
            boolean fanZhenJnKey = fanZhengData(skillName);//技能技能是否具备反震资格
            boolean fanZhenKey = false;//反震开关

            int fzgl_B = (int) (Math.round(Math.random() * 100));
            //2.判断人物反震概率是否引起反震
            if (fanZhenJnKey && fzl_B > fzgl_B) {
                fanZhenKey = true;
            }
            //p2 buff
            List<Map<String, Object>> p2Buff = p2.getBuff();
            //p2被攻击技能名称
            p2.setAttakType(skillName);
            //根据技能计算伤害和buff效果
            if ("普通攻击".equals(skillName)) {
                //A的命中率减去B的躲避率=A的实际命中率
                mzl_A = mzl_A - dbl_B;
                mzl_B = mzl_B - dbl_A;
                AttackA arv_a = attackType(attackA, mzl_A, bjl_A, zml_A, kwl_B, toxue_B);
                sh_xue = arv_a.getShValue_A();
                av.setAttakType_A(arv_a.getAttakType_A());
                av.setShValue_A(sh_xue);


                //检测B是否触发反击或者反震概率
                int a_sh = 0;
                if (!"致命一击".equals(av.getAttakType_A()) && sh_xue > 0) {
                    //B优先顺序，暴击-致命-命中
                    int fjgl_B = (int) (Math.round(Math.random() * 100) + 1);
                    if (fanZhenKey) {
                        //反震伤害 -----普通攻击 B攻击A 技能攻击 直接扣血
                        av.setBuffTypeValue(sh_xue);
                        av.setBuffType("反震");
                        mpBuff_a.put("反震", sh_xue);
                        a_sh = sh_xue;

                    } else if (fjl_B > fjgl_B) {
                        AttackA arvB = attackType(attackB, mzl_B, bjl_B, zml_B, kwl_A, p1.getQixue());
                        a_sh = arvB.getShValue_A();
                        arB.setShValue_B(a_sh);
                        arB.setAttakType_B(arvB.getAttakType_A());
                    }
                    p1.setQixue(toxue_A - a_sh > 0 ? toxue_A - a_sh : 0);
                }
                System.out.println("伤害值：---------------------" + sh_xue);
                p2.setQixue(toxue_B - sh_xue > 0 ? toxue_B - sh_xue : 0);
                p2.setKouXue(sh_xue);

                //buff处理 围 被击中，buff消失，乱-封 buff每回合--
//                if(sh_xue>0){
//                    resetBuff(p2Buff,true);
//                }else{
//                    resetBuff(p2Buff,false);
//                }

            } else if(YaoUtil.getYaoName(skillName)){
                //使用药品
                Map<String,Object> ry=roleyaomapper.selectByYaoNameRoleId(p1.getId(),skillName);
                if(ry!=null){
                    Integer qixuezhi= (Integer) ry.get("qixuezhi");
                    Integer qx=0;
                    if(qixuezhi<=1){
                        qx= (p2.getQixue()+p2.getQixue2()*qixuezhi)>=p2.getQixue2()?p2.getQixue2():p2.getQixue()+p2.getQixue2()*qixuezhi;
                        qixuezhi=p2.getQixue2()*qixuezhi;
                    }else{

                         qx= (p2.getQixue()+qixuezhi)>=p2.getQixue2()?p2.getQixue2():p2.getQixue()+qixuezhi;
                    }
                    p2.setQixue(qx);
                    p2.setKouXue(qixuezhi);

                }



            } else{
                //p1攻击者的技能
                Skill skill = getSkill(p1, skillName);
                av.setAttakType_A(skill.getSkillname());
                //技能伤害计算
                JeNengData jnData =getJnData(skill, sxMap_B);

                sh_buff = jnData.getSh_buff();
                sh_xue = jnData.getSh_xue();
                sh_lan = jnData.getSh_lan();
                //3.统计伤害，整理战斗数据
                if (fsb_A > fsbjgl_A) {
                    sh_xue = (int) Math.round(sh_xue * 1.5);//伤害型技能才具备法术暴击
                }
                //B数据统计
                if (sh_lan > 0) {
                    p2.setJingli(tolan_B - sh_lan > 0 ? tolan_B - sh_lan : 0);
                    arB.setKouLan_B(sh_lan);
                }
                //文人技能
                if (sh_buff >= 0) {
                    //封 乱 围 只能存一
                    addBuff(skillName, buff_b);
                    Map<String, Object> mpBuff = new HashMap<>();
                    String key = getBuffKey(skillName);
                    mpBuff.put(key, sh_buff);
                    //追加buff -封-围-乱-毒-攻
                    buff_b.add(mpBuff);

                    p1.setShValue(sh_buff-1);//p1伤害值
                    p2.setKouXue(sh_buff-1);
                    arB.setBuffType(av.getAttakType_A());
                    arB.setBuffTypeValue(sh_buff);

                    av.setShValue_A(sh_buff);

                } else {

                    av.setBuffType("舍命一击");
                    av.setBuffTypeValue(sh_buff);
                    p1.setQixue(p1.getQixue() + sh_buff > 0 ? p1.getQixue() + sh_buff : 0);

                }
                if (sh_xue > 0) {
                    p2.setQixue(toxue_B - sh_xue > 0 ? toxue_B - sh_xue : 0);
                    p2.setKouXue(sh_xue);
                    //buff处理 围 被击中，buff消失，乱-封 buff每回合--
                  //  resetBuff(p2Buff,true);
                    //attack 数据
                    arB.setKouXue_B(sh_xue);
                }
                //A数据统计
                if (fanZhenKey && sh_xue > 0) {
                    //反震伤害
                    mpBuff_a.put("反震", sh_xue);
                    p1.setQixue(p1.getQixue() - sh_xue > 0 ? p1.getQixue() - sh_xue : 0);
                    //p1 buff追加
                    p1.getMpBuff().put("反震", sh_xue);

                    av.setBuffTypeValue(sh_xue);
                    av.setBuffType("反震");
                }
                av.setShValue_A(sh_xue);
            }

            attackbLi.add(arB);



        }
        return av;
    }

    public static void addBuff(String skillName, List<Map<String, Object>> buff_b) {
        switch (skillName){
            case "趁火打劫":
            case "四面楚歌":
            case "画地为牢":{
                 Iterator<Map<String, Object>> it = buff_b.iterator();
                  while(it.hasNext()) {
                      Map<String, Object> s = it.next();
                      if(s.get("wei")!=null){
                          it.remove();
                      }
                      if(s.get("feng")!=null){
                          it.remove();
                      }
                      if(s.get("luan")!=null){
                          it.remove();
                      }

                  }

                break;
            }
        }

    }

    public static void resetBuff(List<Map<String, Object>> p2Buff, Boolean re) {
        Iterator<Map<String, Object>> it = p2Buff.iterator();
        while(it.hasNext()) {
            Map<String, Object> m= it.next();
            Integer feng = (Integer) m.get("feng");
            Integer wei = (Integer) m.get("wei");
            Integer luan = (Integer) m.get("luan");

            //封
            if (feng != null && feng > 0) {
                feng--;
                if (feng == 0) {
                    it.remove();
                } else {
                    m.put("feng", feng);
                }
            }
            //乱
            if (luan != null && luan > 0) {
                luan--;
                if (luan == 0) {
                    it.remove();
                } else {
                    m.put("luan", luan);
                }
            }
            //围


                if (wei != null && wei > 0) {
                    if(re){
                        it.remove();
                    }else{
                        wei--;
                        if (wei == 0) {
                            it.remove();
                        } else {
                            m.put("wei", wei);
                        }
                    }


            }
        }

    }


    public static String getBuffKey(String name) {
        String s = null;
        switch (name) {
            case "四面楚歌": {
                s = "feng";
                break;
            }
            case "画地为牢": {
                s = "wei";
                break;
            }
            case "趁火打劫": {
                s = "luan";
                break;
            }
            case "巫蛊极毒": {
                s = "du";
                break;
            }
            case "气冲斗牛": {
                s = "gong";
                break;
            }


        }
        return s;
    }

    /**
     * 技能效果公式
     *
     * @param skill
     * @param sxMap
     * @return
     */
    public  static JeNengData getJnData(Skill skill, Map<String, Integer> sxMap) {
        JeNengData jn = new JeNengData();
        int sh_xue = 0;
        int sh_lan = 0;
        int sh_buff = 0;
        switch (skill.getSkillname()) {

            /**
             * 五雷计算公式
             * 1-2级
             */
            case "五雷轰顶": {
                Integer kll_B = sxMap.get("抗落雷");
                sh_xue = (int) Math.round((0.3 * skill.getShuliandu() + Integer.parseInt(skill.getLevel()) * 200 + 400) * (100 - kll_B) / 100);
                break;
            }
            case "呼风唤雨": {
                Integer kll_B = sxMap.get("抗风沙");
                sh_xue = (int) Math.round((0.3 * skill.getShuliandu() + Integer.parseInt(skill.getLevel()) * 200 + 400) * (100 - kll_B) / 100);
                break;
            }
            case "妖火燎原": {
                Integer kll_B = sxMap.get("抗妖火");
                sh_xue = (int) Math.round((0.3 * skill.getShuliandu() + Integer.parseInt(skill.getLevel()) * 200 + 400) * (100 - kll_B) / 100);
                break;
            }
            case "巫蛊极毒": {
                Integer kll_B = sxMap.get("抗毒术");
                sh_buff = 400;
                sh_xue = (int) Math.round((0.3 * skill.getShuliandu() + Integer.parseInt(skill.getLevel()) * 200 + 400) * (100 - kll_B) / 100);
                break;
            }

            case "力劈华山": {
                Integer kll_B = sxMap.get("抗玄击");
                sh_xue = (int) Math.round(50 + Integer.parseInt(skill.getLevel()) / 6 * 50);
                sh_lan = (int) Math.round(50 + Integer.parseInt(skill.getLevel()) / 6 * 50);
                break;
            }
            case "排山倒海": {
                Integer kll_B = sxMap.get("抗玄击");
                sh_xue = (int) Math.round(50 + Integer.parseInt(skill.getLevel()) / 6 * 50);
                sh_lan = (int) Math.round(50 + Integer.parseInt(skill.getLevel()) / 6 * 50);
                break;
            }
            //舍命一击会自身受伤
            case "舍命一击": {
                Integer kll_B = sxMap.get("抗物理");
                sh_buff = -400;
                ;//舍命一击的伤害
                sh_xue = (int) Math.round((0.3 * skill.getShuliandu() + Integer.parseInt(skill.getLevel()) * 200 + 400) * (100 - kll_B) / 100);
                break;
            }
            /**
             * 概率需要根据熟练度计算（待补充）
             * 气冲斗牛，画地为牢该技能根据熟练度随机选择多个目标增加其BUFF
             */
            case "气冲斗牛": {
                int jngl = 100;
                sh_buff = addBuff(skill, jngl);//buff存活回合数
                break;
            }
            case "四面楚歌": {
                int jngl = 100;
                sh_buff = addBuff(skill, jngl);
                break;
            }
            case "画地为牢": {
                int jngl = 100;
                sh_buff = addBuff(skill, jngl);
                break;
            }
            case "趁火打劫": {
                int jngl = 100;
                sh_buff = addBuff(skill, jngl);
                break;
            }
        }
        jn.setSh_buff(sh_buff);
        jn.setSh_lan(sh_lan);
        jn.setSh_xue(sh_xue);
        return jn;
    }

    /**
     * 围---封---乱--攻--
     * 添加BUFF效果 buff受影响回合数
     *
     * @param skill
     * @return
     */
    private static int addBuff(Skill skill, int jngl) {
        int sh_buff = 0;
        int gl = (int) Math.round(Math.random() * 99);
        if (jngl > gl) {
            //需要根据熟练度判断影响回合数
            sh_buff = (int) Math.round(Math.random() * 3) + 3;
        }
        return sh_buff;
    }

    /**初始化客户端攻击指令
     * 初始化buff数据
     * 围---封---乱--攻--毒---速---隐--固
     * @param A
     * @param bli
     */
//    private static void initBuff(PkPlayData A,List<PkPlayData> bli,String skillName)throws Exception{
//       Skill skill= getSkill(A, skillName);
//
//
//
//
//    }

    /**
     * 通过技能名获取技能函数
     *
     * @param A
     * @param skillName
     * @return
     */
    public static Skill getSkill(PkRoleVo A, String skillName) throws Exception {
        // 1.验证技能
        List<Skill> liSkill = A.getJineng();
        if (liSkill != null) {
            for (Skill s : liSkill) {
                if (s.getSkillname().equals(skillName)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * 初始化元数据
     * 1.根据客户端攻击指令，重构指令数据
     * where
     * 1.1根据攻击类型 判断A的蓝量是否足够发起技能，技能耗蓝，普通攻击不耗蓝
     * 2.根据buff效果，重构指令数据
     * where
     * 2.1【围】 画地为牢，只允许使用道具类，不能够执行攻击指令
     * 2.2【封】 四面楚歌,任何指令都无效操作
     * 2.3【乱】 只能随机攻击在场所有玩家之一，包括自身
     * 2.4【攻】 气冲斗牛，增加自身攻击力
     * 2.5【速】 凌波微步，增加移动速度
     * 2.6【固】 固若金汤 ，增加各种抗性
     * 2.7【毒】 巫蛊极毒,先扣除自身血量判断是否>0?执行该操作:取消本次操作
     * 3.执行技能攻击或者普通攻击
     * 4.重构数据返回到客户端
     *
     * @param A
     * @param bli
     */
    public static boolean onloadData(PkPlayData A, List<PkPlayData> bli, String skillName) throws Exception {
        boolean key = false;
        Map<String, Integer> mpBuff_a = A.getMpBuff();
        if (mpBuff_a != null && mpBuff_a.size() > 0) {
            Set<String> set = mpBuff_a.keySet();
            for (String s : set) {
                switch (s) {
                    case "画地为牢": {
                        if ("使用道具".equals(skillName)) {

                        }
                    }
                    case "四面楚歌": {
                        //本次动作无效
                    }

                    case "趁火打劫": {
                        //随机攻击某个目标

                    }
                    case "气冲斗牛": {
                        //增加攻击力
                        for (PkPlayData p : bli) {
                            //根据熟练度计算
                            p.setTotalgong((int) Math.round(p.getTotalgong() * 1.5));
                        }
                    }
                    case "巫蛊极毒": {
                        //扣除A的血量在去执行
                        Integer xue = A.getTotalxue1() + mpBuff_a.get(s);
                        A.setTotalxue1(xue > 0 ? xue : 0);

                    }
                }
            }

        }

        return key;
    }



}

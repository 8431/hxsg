package com.hxsg.dao.impl;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.Dao.HxsgBaseDaoMapper;
import com.hxsg.Dao.RoleYaoMapper;
import com.hxsg.dao.HxsgBattle;
import com.hxsg.po.Skill;
import com.hxsg.util.AttackUtil;
import com.hxsg.util.JeNengData;
import com.hxsg.util.YaoUtil;
import com.hxsg.vo.AttackA;
import com.hxsg.vo.AttackB;
import com.hxsg.vo.PkRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
@Service("HxsgBattle")
public class HxsgBattleImpl implements HxsgBattle {
    @Autowired
     RoleYaoMapper roleyaomapper;

    @Autowired
    HxsgBaseDaoMapper hxsgbasedaomapper;


    @Override
    public AttackA skillAttack(PkRoleVo p1, List<PkRoleVo> p2Li, String skillName) throws Exception {
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
            boolean fanZhenJnKey = AttackUtil.fanZhengData(skillName);//技能技能是否具备反震资格
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
                AttackA arv_a = AttackUtil.attackType(attackA, mzl_A, bjl_A, zml_A, kwl_B, toxue_B);
                sh_xue = arv_a.getShValue_A();
                av.setAttakType_A(arv_a.getAttakType_A());
                av.setShValue_A(sh_xue);
                //检测B是否触发反击或者反震概率
                int a_sh = 0;
                System.out.println("伤害值：---------------------" + sh_xue);
                p2.setQixue(toxue_B - sh_xue > 0 ? toxue_B - sh_xue : 0);
                p2.setKouXue(sh_xue);
                if (!"致命一击".equals(av.getAttakType_A()) && sh_xue > 0) {
                    //B优先顺序，暴击-致命-命中
                    int fjgl_B = (int) (Math.round(Math.random() * 100) + 1);
                    if (fanZhenKey) {
                        //反震伤害 -----普通攻击 B攻击A 技能攻击 直接扣血
                        if(p2.getQixue()>0){
                            av.setBuffTypeValue(sh_xue);
                            av.setBuffType("反震");
                            mpBuff_a.put("反震", sh_xue);
                            a_sh = sh_xue;
                        }


                    } else if (fjl_B > fjgl_B&&p2.getQixue()>0) {
                        AttackA arvB = AttackUtil.attackType(attackB, mzl_B, bjl_B, zml_B, kwl_A, p1.getQixue());
                        a_sh = arvB.getShValue_A();
                        arB.setShValue_B(a_sh);
                        arB.setAttakType_B(arvB.getAttakType_A());

                        av.setBuffTypeValue(a_sh);
                        av.setBuffType("反击");
                        mpBuff_a.put("反击", a_sh);
                    }
                    p1.setQixue(toxue_A - a_sh > 0 ? toxue_A - a_sh : 0);
                }
            } else if(YaoUtil.getYaoName(skillName)){
                //使用药品
                Map<String,Object> ry=roleyaomapper.selectByYaoNameRoleId(p1.getId(),skillName);
                if(ry!=null){
                    Double qixuezhi= (Double) ry.get("qixuezhi");
                    Double qx=0d;
                    if(qixuezhi<=1){
                        qx= (p2.getQixue()+p2.getQixue2()*qixuezhi)>=p2.getQixue2()?p2.getQixue2():p2.getQixue()+p2.getQixue2()*qixuezhi;
                        qixuezhi=p2.getQixue2()*qixuezhi;
                    }else{

                        qx= (p2.getQixue()+qixuezhi)>=p2.getQixue2()?p2.getQixue2():p2.getQixue()+qixuezhi;
                    }
                    p2.setQixue(qx.intValue());
                    p2.setKouXue(qixuezhi.intValue());

                }

            } else{

                //p1攻击者的技能
                Skill skill = AttackUtil.getSkill(p1, skillName);
                av.setAttakType_A(skill.getSkillname());
                //3.判断精力是否能够发动技能
                    //3.1获取耗蓝值
                int hlVal= getHlVal(skillName, skill);
                    //3.2 判断技能是否能够发动
                 Integer jlVal= p1.getJingli();
                 if(jlVal<hlVal){
                     av.setAttakType_A("精力不足");

                 }else{
                     //消耗蓝量计算
                     p1.setJingli(p1.getJingli()-hlVal);

                     //计算技能伤害

                     JeNengData jnData = this.getJnData(skill, sxMap_B,p2);
                     sh_buff = jnData.getSh_buff();
                     sh_xue = jnData.getSh_xue();
                     sh_lan = jnData.getSh_lan();
                     //3.统计伤害，整理战斗数据
                     if (fsb_A > fsbjgl_A) {
                         sh_xue = (int) Math.round(sh_xue * 1.5);//伤害型技能才具备法术暴击
                         av.setAttakType_A(skill.getSkillname()+"(暴)");
                     }
                     //B数据统计
                     if (sh_lan > 0) {
                         p2.setJingli(tolan_B - sh_lan > 0 ? tolan_B - sh_lan : 0);
                         arB.setKouLan_B(sh_lan);
                     }
                     //文人技能
                     if (sh_buff > 0) {
                         //封 乱 围 只能存一
                         AttackUtil.addBuff(skillName, buff_b);
                         Map<String, Object> mpBuff = new HashMap<>();
                         String key = AttackUtil.getBuffKey(skillName);
                         mpBuff.put(key, sh_buff);
                         //追加buff -封-围-乱-毒-攻
                         buff_b.add(mpBuff);

                         p1.setShValue(sh_buff-1>0?sh_buff-1:0);//p1伤害值
                         p2.setKouXue(sh_buff-1>0?sh_buff-1:0);
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
                     if (fanZhenKey && sh_xue > 0&&p2.getQixue()>0) {
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








        }
        return av;
    }

    private int getHlVal(String skillName, Skill skill) {
        int hlVal =0;
        String level=skill.getLevel();
        switch (skillName){
            case "舍命一击":
            case "力劈华山":
            case "气冲斗牛":
            case "五雷轰顶":
            case "四面楚歌":
            case "趁火打劫":
            {

                if("1".equals(level)){
                    hlVal=200;
                }


                if("2".equals(level)){
                    hlVal=500;

                }
                if("3".equals(level)){
                    hlVal=1000;
                }
                if("4".equals(level)){
                    hlVal=3000;
                }
                if("5".equals(level)){
                    hlVal=4500;

                }
                if("6".equals(level)){
                    hlVal=8000;

                }
                if("7".equals(level)){
                    hlVal=10000;
                }
                if("8".equals(level)){
                    hlVal=12000;
                }


                break;
            }
            case "呼风唤雨":
            case "妖火燎原":
            case "排山倒海":
            case "画地为牢":
            case "巫蛊极毒":
            case "毁天灭地":
            {
                if("1".equals(level)){
                    hlVal=300;
                }

            }
            if("2".equals(level)){
                hlVal=500;

            }
            if("3".equals(level)){
                hlVal=1000;
            }
            if("4".equals(level)){
                hlVal=3500;
            }
            if("5".equals(level)){
                hlVal=5000;

            }
            if("6".equals(level)){
                hlVal=8500;

            }
            if("7".equals(level)){
                hlVal=10500;
            }
            if("8".equals(level)){
                hlVal=12500;
            }
            break;
        }
        return hlVal;
    }

    /**
     * 技能效果公式
     *
     * @param skill
     * @param sxMap
     * @return
     */
    public   JeNengData getJnData(Skill skill, Map<String, Integer> sxMap,PkRoleVo p) throws CommonException {
        JeNengData jn = new JeNengData();
        int sh_xue = 0;
        int sh_lan = 0;
        int sh_buff = 0;
        Map<String,Object> param=new HashMap<>();

        param.put("name",skill.getSkillname());
        param.put("level",skill.getLevel());
        param.put("sql","select * from jnval j,skill_describe s  where  j.jnId=s.id and j.level=#{level} and s.name=#{name}");
        List<Map<String,Object>> jnValLi=hxsgbasedaomapper.QuerySql(param);
        Map<String,Object> mp=jnValLi.get(0);
        String max= (String) mp.get("maxVal");
        String min= (String) mp.get("minVal");
        String le=skill.getLevel();

        Integer maxVal=Integer.parseInt(max);
        Integer minVal=Integer.parseInt(min);
        Integer level=Integer.parseInt(le);
        Integer sld=skill.getShuliandu();

        switch (skill.getSkillname()) {

            /****************************************异人技能*************************************************/
            case "五雷轰顶": {
                Integer kll_B = sxMap.get("抗落雷");

                switch (level){
                    case 1:{
                        sh_xue = (int) Math.round((600.0F+sld*6000/30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 2:{
                        sh_xue = (int) Math.round((1600.0F+sld*6000/30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 3:{
                        sh_xue = (int) Math.round((2800.0F+sld*6000/30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 4:{
                        sh_xue = (int) Math.round((4200.0F+sld*6000/30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 5:{
                        sh_xue = (int) Math.round((6000.0F+sld*6000/30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 6:{
                        sh_xue = (int) Math.round((14000.0F + sld * 6000 / 20000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 7:{
                        sh_xue = (int) Math.round((20000.0F + sld * 6000 / 20000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 8:{
                        sh_xue = (int) Math.round((26000.0F + sld * 6000 / 20000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                }
                break;
            }
            case "呼风唤雨": {
                Integer kll_B = sxMap.get("抗风沙");
                Integer shVal=0;
                Integer shcsz=0;
                float sldVal=0;
                switch (level){
                    case 1:{
                        shVal=400;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 2:{
                        shVal=900;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 3:{
                        shVal=1100;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 4:{
                        shVal=2000;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 5:{
                        shVal=2500;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 6:{
                        shVal=6000;
                        shcsz=3000;
                        sldVal=20000.0F;
                        break;
                    }
                    case 7:{
                        shVal=9000;
                        shcsz=3000;
                        sldVal=20000.0F;
                        break;
                    }
                    case 8:{
                        shVal=12000;
                        shcsz=3000;
                        sldVal=20000.0F;
                        break;
                    }
                }
                sh_xue  = (int) Math.round((shVal + sld * shcsz/ sldVal)*(100 - kll_B) / 100);
                break;
            }
            case "妖火燎原": {
                Integer kll_B = sxMap.get("抗妖火");
                Integer shVal=0;
                Integer shcsz=0;
                float sldVal=0;
                switch (level){
                    case 1:{
                        shVal=400;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 2:{
                        shVal=900;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 3:{
                        shVal=1100;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 4:{
                        shVal=2000;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 5:{
                        shVal=2500;
                        shcsz=2500;
                        sldVal=30000.0F;
                        break;
                    }
                    case 6:{
                        shVal=6000;
                        shcsz=3000;
                        sldVal=20000.0F;
                        break;
                    }
                    case 7:{
                        shVal=9000;
                        shcsz=3000;
                        sldVal=20000.0F;
                        break;
                    }
                    case 8:{
                        shVal=12000;
                        shcsz=3000;
                        sldVal=20000.0F;
                        break;
                    }
                }
                sh_xue  = (int) Math.round((shVal + sld * shcsz/ sldVal)*(100 - kll_B) / 100);

                break;
            }
            //待定计算
            case "巫蛊极毒": {
                Integer kll_B = sxMap.get("抗毒术");
                sh_buff = 400;

                switch (level){
                    case 1:{
                        sh_xue = (int) Math.round((200.0F + sld * 1250 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 2:{
                        sh_xue = (int) Math.round((450.0F + sld * 1250 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 3:{
                        sh_xue = (int) Math.round((550.0F + sld * 1250 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 4:{
                        sh_xue = (int) Math.round((1000.0F + sld * 1250 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 5:{
                        sh_xue = (int) Math.round((1250.0F + sld * 1250 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 6:{
                        sh_xue = (int) Math.round((3000.0F + sld * 1500 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 7:{
                        sh_xue = (int) Math.round((4500.0F + sld * 1500 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                    case 8:{
                        sh_xue = (int) Math.round((6000.0F + sld * 1500 / 30000.0F) *((100 - kll_B) / 100));
                        break;
                    }
                }
                break;
            }
            /****************************************武士技能*************************************************/

            case "力劈华山": {
                Integer kll_B = sxMap.get("抗玄击");
                switch (level) {
                    case 1: {
                        sh_xue = (int) Math.round(( 0.17F + 0.2F * sld / 30000.0F) * ((100 - kll_B) / 100));
                        break;
                    }
                    case 2: {
                        sh_xue = (int) Math.round((( 0.19F + 0.2F * sld / 30000.0F))* ((100 - kll_B) / 100));
                        break;
                    }
                    case 3: {
                        sh_xue = (int) Math.round((( 0.21F + 0.2F * sld / 30000.0F))* ((100 - kll_B) / 100));
                        break;
                    }
                    case 4: {
                        sh_xue = (int) Math.round((( 0.23F + 0.2F * sld / 30000.0F)) * ((100 - kll_B) / 100));
                        break;
                    }
                    case 5: {
                        sh_xue = (int) Math.round((( 0.25F + 0.2F * sld / 30000.0F)) * ((100 - kll_B) / 100));
                        break;
                    }
                    case 6: {
                        sh_xue = (int) Math.round(( 0.47F + 0.03F * sld / 20000.0F) * ((100 - kll_B) / 100));
                        break;
                    }
                    case 7: {
                        sh_xue = (int) Math.round((0.5F + 0.04F * sld / 20000.0F) * ((100 - kll_B) / 100));
                        break;
                    }
                    case 8: {
                        sh_xue = (int) Math.round((0.54F + 0.04F * sld / 20000.0F) * ((100 - kll_B) / 100));
                        break;
                    }
                }

                break;
            }
            case "排山倒海": {
                Integer kll_B = sxMap.get("抗玄击");
                switch (level) {
                    case 1: {
                        sh_xue = (int) Math.round(( 0.11F + 0.15F * sld / 30000.0F) *100* ((100 - kll_B) / 100));
                        break;
                    }
                    case 2: {
                        sh_xue = (int) Math.round((( 0.12F + 0.15F * sld / 30000.0F))*100* ((100 - kll_B) / 100));
                        break;
                    }
                    case 3: {
                        sh_xue = (int) Math.round((( 0.13F + 0.15F * sld / 30000.0F))*100* ((100 - kll_B) / 100));
                        break;
                    }
                    case 4: {
                        sh_xue = (int) Math.round((( 0.14F + 0.15F * sld / 30000.0F)) *100* ((100 - kll_B) / 100));
                        break;
                    }
                    case 5: {
                        sh_xue = (int) Math.round((( 0.15F + 0.15F * sld / 30000.0F)) *100* ((100 - kll_B) / 100));
                        break;
                    }
                    case 6: {
                        sh_xue = (int) Math.round(( 0.31F + 0.02F * sld / 20000.0F) *100* ((100 - kll_B) / 100));
                        break;
                    }
                    case 7: {
                        sh_xue = (int) Math.round((0.33F + 0.02F * sld / 20000.0F) *100* ((100 - kll_B) / 100));
                        break;
                    }
                    case 8: {
                        sh_xue = (int) Math.round((0.35F + 0.02F * sld / 20000.0F) *100* ((100 - kll_B) / 100));
                        break;
                    }
                }

                break;
            }
            //舍命一击会自身受伤
            case "舍命一击": {
                Integer kll_B = sxMap.get("抗物理");
                switch (level) {
                    case 1: {
                        sh_xue = (int) Math.round(sld / 3 + 600 * ((100 - kll_B) / 100));
                        break;
                    }
                    case 2: {
                        sh_xue = (int) Math.round(sld / 3 + 1600 * ((100 - kll_B) / 100));
                        break;
                    }
                    case 3: {
                        sh_xue = (int) Math.round(sld / 3 + 2800 * ((100 - kll_B) / 100));
                        break;
                    }
                    case 4: {
                        sh_xue = (int) Math.round(sld / 3 + 4200 * ((100 - kll_B) / 100));
                        break;
                    }
                    case 5: {
                        sh_xue = (int) Math.round(sld / 3 + 6000 * ((100 - kll_B) / 100));
                        break;
                    }
                    case 6: {
                        sh_xue = (int) Math.round((18000.0F + sld * 7000 / 20000.0F) * ((100 - kll_B) / 100));
                        break;
                    }
                    case 7: {
                        sh_xue = (int) Math.round((25000.0F + sld * 7000 / 20000.0F) * ((100 - kll_B) / 100));
                        break;
                    }
                    case 8: {
                        sh_xue = (int) Math.round((32000.0F + sld * 7000 / 20000.0F) * ((100 - kll_B) / 100));
                        break;
                    }
                }

                sh_buff = (int) (sh_xue*(level*0.03));
                //舍命一击的伤害
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
            /****************************************文人技能*************************************************/
            case "四面楚歌": {
                Integer kll_B = sxMap.get("抗封锁");
                int jngl =0;
                switch (level){
                    case 6:{
                        jngl=(int) Math.round(( 100.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    case 7:{
                        jngl=(int) Math.round(( 120.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    case 8:{
                        jngl=(int) Math.round(( 130.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    default:{
                        jngl=(int) Math.round(( 60.0F + 30.0F * sld / 30000.0F - 0.0F)*(100 - kll_B) / 100);
                    }
                }
                sh_buff = addBuff(skill, jngl);
                break;
            }
            case "画地为牢": {
                Integer kll_B = sxMap.get("抗围困");
                int jngl =0;
                switch (level){
                    case 6:{
                        jngl=(int) Math.round(( 100.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    case 7:{
                        jngl=(int) Math.round(( 110.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    case 8:{
                        jngl=(int) Math.round(( 120.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    default:{
                        jngl=(int) Math.round(( 60.0F + 30.0F * sld / 30000.0F - 0.0F)*(100 - kll_B) / 100);
                    }
                }
                sh_buff = addBuff(skill, jngl);
                break;
            }
            case "趁火打劫": {
                Integer kll_B = sxMap.get("抗扰乱");
                int jngl =0;
                switch (level){
                    case 6:{
                        jngl=(int) Math.round(( 100.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    case 7:{
                        jngl=(int) Math.round(( 120.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    case 8:{
                        jngl=(int) Math.round(( 130.0F + 10.0F * sld / 20000.0F - 0.0F)*(100 - kll_B) / 100);
                        break;
                    }
                    default:{
                        jngl=(int) Math.round(( 60.0F + 30.0F * sld / 30000.0F - 0.0F)*(100 - kll_B) / 100);
                    }
                }
                sh_buff = addBuff(skill, 100);
                break;
            }
        }
        sh_lan = getXhMp(level,sld,skill.getSkillname());
        jn.setSh_buff(sh_buff);
        jn.setSh_lan(sh_lan);
        jn.setSh_xue(sh_xue);
        return jn;
    }
    public  static Integer getXhMp(Integer jnLevel,Integer sld,String JnName){
        Integer mp=0;
        switch (JnName){
            case "舍命一击":
            case "力劈华山":
            case "气冲斗牛":{
                switch (jnLevel){
                    case  1:{
                        mp=(int)Math.round((220 + sld * 110 / 6000.0F));
                        break;
                    }
                    case  2:{
                        mp=(int)Math.round((500 + sld * 250 / 12000.0F));
                        break;
                    }
                    case  3:{
                        mp=(int)Math.round((1100 + sld * 550 / 18000.0F));

                        break;
                    }
                    case  4:{
                        mp=(int)Math.round((1700 + sld * 850 / 24000.0F));

                        break;
                    }
                    case  5:{
                        mp=(int)Math.round((2800 + sld * 1400 / 30000.0F));
                        break;
                    }
                    case  6:{
                        mp=(int)Math.round((3900 + sld * 1900 / 20000.0F));
                        break;
                    }
                    case  7:{
                        mp=(int)Math.round((4600 + sld * 2400 / 20000.0F));
                        break;
                    }
                    case  8:{
                        mp=(int)Math.round((5600 + sld * 2800 / 20000.0F));
                        break;
                    }

                }
                break;
            }
            case "呼风唤雨":
            case "妖火燎原":
            case "巫蛊极毒":
            case "妙手回春":
            case "画地为牢":{
                switch (jnLevel){
                    case  1:{
                        mp=(int)Math.round((300 + sld * 150 / 6000.0F));
                        break;
                    }
                    case  2:{
                        mp=(int)Math.round((700 + sld * 350 / 12000.0F));
                        break;
                    }
                    case  3:{
                        mp=(int)Math.round((1400 + sld * 700 / 18000.0F));

                        break;
                    }
                    case  4:{
                        mp=(int)Math.round((2200 + sld * 1100 / 24000.0F));

                        break;
                    }
                    case  5:{
                        mp=(int)Math.round((3600 + sld * 1800 / 30000.0F));
                        break;
                    }
                    case  6:{
                        mp=(int)Math.round((5000 + sld * 2600 / 20000.0F));
                        break;
                    }
                    case  7:{
                        mp=(int)Math.round((6000 + sld * 3100 / 20000.0F));
                        break;
                    }
                    case  8:{
                        mp=(int)Math.round((7200 + sld * 3600 / 20000.0F));
                        break;
                    }

                }

                break;
            }
            case "四面楚歌":
            case "趁火打劫":
            case "五雷轰顶":{


                switch (jnLevel){
                    case  1:{
                        mp=(int)Math.round((260 + sld * 130 / 6000.0F));
                        break;
                    }
                    case  2:{
                        mp=(int)Math.round((600 + sld * 300 / 12000.0F));
                        break;
                    }
                    case  3:{
                        mp=(int)Math.round((1200 + sld * 600 / 18000.0F));

                        break;
                    }
                    case  4:{
                        mp=(int)Math.round((2000 + sld * 1000 / 24000.0F));

                        break;
                    }
                    case  5:{
                        mp=(int)Math.round((3200 + sld * 1600 / 30000.0F));
                        break;
                    }
                    case  6:{
                        mp=(int)Math.round((4500 + sld * 2200 / 20000.0F));
                        break;
                    }
                    case  7:{
                        mp=(int)Math.round((5400 + sld * 2600 / 20000.0F));
                        break;
                    }
                    case  8:{
                        mp=(int)Math.round((6400 + sld * 3200 / 20000.0F));
                        break;
                    }

                }
                break;
            }
        }
        return  mp;

    }
    /**
     * 围---封---乱--攻--
     * 添加BUFF效果 buff受影响回合数
     *
     * @param skill
     * @return
     */
    private  int addBuff(Skill skill, int jngl) {
        int sh_buff = 0;
        int gl = (int) Math.round(Math.random() * 99);
        if (jngl > gl) {
            //需要根据熟练度判断影响回合数
            sh_buff = (int) Math.round(Math.random() * 3) + 3;
        }
        return sh_buff;
    }

    public static void main(String[] args) {

        int jngl = (int) Math.round(( 60.0F + 30.0F * 24453 / 30000.0F - 0.0F));
        System.out.println(jngl);

    }
}

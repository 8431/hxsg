package com.hxsg.vo;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 * 伤害计算返回数据VO类
 */
public class AttackA {
    //A伤害值
    private Integer shValue_A;
    //A当前血量
    private Integer kouXue_A;
    //A攻击名称
    //attakType_A所有的指令【普通攻击】【使用道具】【五雷轰顶】【妖火燎原】【呼风唤雨】【舍命一击】【力劈华山】【排山倒海】【画地为牢】【四面楚歌】【趁火打劫】
    private String attakType_A;
    //负面状态
    private String buffType;//围，乱，封，攻，毒
    //负面状态值 毒 舍命
    private Integer buffTypeValue;
    //群体技能
    private List<AttackB> attackbLi;


    @Override
    public String toString() {
        System.out.println("a攻击名称："+attakType_A);
        System.out.println("a伤害值："+shValue_A);
        System.out.println("a被扣除血量："+kouXue_A);
        System.out.println("a的buff："+buffType);
        System.out.println("a的buff值："+buffTypeValue);
        for(AttackB b:attackbLi){
            b.toString();
        }
      return super.toString();
    }

    public Integer getShValue_A() {
        return shValue_A;
    }

    public void setShValue_A(Integer shValue_A) {
        this.shValue_A = shValue_A;
    }

    public Integer getKouXue_A() {
        return kouXue_A;
    }

    public void setKouXue_A(Integer kouXue_A) {
        this.kouXue_A = kouXue_A;
    }

    public String getAttakType_A() {
        return attakType_A;
    }

    public void setAttakType_A(String attakType_A) {
        this.attakType_A = attakType_A;
    }

    public String getBuffType() {
        return buffType;
    }

    public void setBuffType(String buffType) {
        this.buffType = buffType;
    }

    public Integer getBuffTypeValue() {
        return buffTypeValue;
    }

    public void setBuffTypeValue(Integer buffTypeValue) {
        this.buffTypeValue = buffTypeValue;
    }

    public List<AttackB> getAttackbLi() {
        return attackbLi;
    }

    public void setAttackbLi(List<AttackB> attackbLi) {
        this.attackbLi = attackbLi;
    }
}

package com.hxsg.vo;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class AttackB {
    //B伤害值
    private int shValue_B;
    //B被扣除血量
    private int kouXue_B;
    //B被扣除蓝量
    private int kouLan_B;
    //B反击类型
    private String attakType_B;
    private String buffType;//围，乱，封，攻，毒
    //负面状态值 毒 舍命
    private Integer buffTypeValue;
    @Override
    public String toString() {
        System.out.println("B攻击名称反击类型："+attakType_B);
        System.out.println("B伤害值："+shValue_B);
        System.out.println("B被扣除血量："+kouXue_B);
        System.out.println("B被扣除蓝量："+kouLan_B);
        System.out.println("B的buff："+buffType);
        System.out.println("B的buff值："+buffTypeValue);
        return null;

    }

    public int getShValue_B() {
        return shValue_B;
    }

    public void setShValue_B(int shValue_B) {
        this.shValue_B = shValue_B;
    }

    public int getKouXue_B() {
        return kouXue_B;
    }

    public void setKouXue_B(int kouXue_B) {
        this.kouXue_B = kouXue_B;
    }

    public int getKouLan_B() {
        return kouLan_B;
    }

    public void setKouLan_B(int kouLan_B) {
        this.kouLan_B = kouLan_B;
    }

    public String getAttakType_B() {
        return attakType_B;
    }

    public void setAttakType_B(String attakType_B) {
        this.attakType_B = attakType_B;
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
}

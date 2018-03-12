package com.hxsg.vo;

import com.hxsg.po.Skill;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/12/16.
 * 玩家信息详情
 */
public class PkPlayData {
    private Integer id;//角色ID
    private String rolename;//角色名
    private Integer level;//等级

    private Integer totalxue1;//当前生命值

    private Integer totalxue2;//生命值最大上限

    private Integer totaljing1;//当前精力值

    private Integer totaljing2;//精力值最大上限

    private Integer totalgong;//攻击力

    private Integer totalsudu;//速度

    private String img;//人物头像
    //人物抗性情况
    private Map<String, Integer> roleMap;
    //技能情况
    private List<Skill> skill=null;
    //伤害值
    private int shValue;
    //被扣除血量
    private int kouXue;
    //攻击名称
    private String attakType;
    //负面状态
    Map<String,Integer> mpBuff=new HashMap<String,Integer>();
    public Map<String, Integer> getRoleMap() {
        return roleMap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getTotalxue1() {
        return totalxue1;
    }

    public void setTotalxue1(Integer totalxue1) {
        this.totalxue1 = totalxue1;
    }

    public Integer getTotalxue2() {
        return totalxue2;
    }

    public void setTotalxue2(Integer totalxue2) {
        this.totalxue2 = totalxue2;
    }

    public Integer getTotaljing1() {
        return totaljing1;
    }

    public void setTotaljing1(Integer totaljing1) {
        this.totaljing1 = totaljing1;
    }

    public Integer getTotaljing2() {
        return totaljing2;
    }

    public void setTotaljing2(Integer totaljing2) {
        this.totaljing2 = totaljing2;
    }

    public Integer getTotalgong() {
        return totalgong;
    }

    public void setTotalgong(Integer totalgong) {
        this.totalgong = totalgong;
    }

    public Integer getTotalsudu() {
        return totalsudu;
    }

    public void setTotalsudu(Integer totalsudu) {
        this.totalsudu = totalsudu;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setRoleMap(Map<String, Integer> roleMap) {
        this.roleMap = roleMap;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    public int getShValue() {
        return shValue;
    }

    public void setShValue(int shValue) {
        this.shValue = shValue;
    }

    public int getKouXue() {
        return kouXue;
    }

    public void setKouXue(int kouXue) {
        this.kouXue = kouXue;
    }

    public String getAttakType() {
        return attakType;
    }

    public void setAttakType(String attakType) {
        this.attakType = attakType;
    }

    public Map<String, Integer> getMpBuff() {
        return mpBuff;
    }

    public void setMpBuff(Map<String, Integer> mpBuff) {
        this.mpBuff = mpBuff;
    }

}

package com.hxsg.vo;

import com.hxsg.po.Skill;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/10/31.
 * 副将界面详情加载VO
 */
public class FuJiangDetailVo implements Serializable{
    private String name;//副将:藤甲兵
    private String imgUrl;//副将图片URL地址
    private Integer id;//副将ID:10086
    private String touXian;//头衔:常人
    private String zhiYe;//职业:125级武士
    private String shengJiJingYan;//升级:升级还需要12313经验
    private Integer moQiDu;//默契度:1000  【宴请】
    private String zhongChengDu;//忠诚度:80/100
    private Integer shuXingDian;//属性点:0  【查看】
    //气血:21212/31311
    private Integer qiXue1;//21212
    private Integer qiXue2;//31311
    //精力:31404/41404
    private Integer jingLi1;//31404
    private Integer jingLi2;//41404
    private Integer gongJi;//攻击:21113
    private Integer fangYu;//防御:123
    private Integer suDu;//速度:432
    private Integer jinglids;//精力点数
    private Integer gongJids;//攻击点数
    private Integer qixueds;//气血点数
    private Integer sududs;//速度点数
    private List<Skill> jiNeng;
    private String jiNeng1;//技能1:(1级)呼风唤雨
    private String shuLianDu1;//熟练度:6000 【提高熟练度】
    private String jiNeng2;//技能2:(5级)五雷轰顶
    private String shuLianDu2;//熟练度:6000 【提高熟练度】
    private String jiNeng3;//技能3:(化3)妖火燎原
    private String shuLianDu3;//熟练度:30000 【提高熟练度】
    private Integer keyongds;//可用点数
    private Float chengzhang;//成长
    private Integer chuxue;//初血
    private Integer chujing;//初精
    private Integer chugong;//初攻
    private Integer chusu;//初速
    private Integer level;//等级
    private Integer roleid;//角色id

    public List<Skill> getJiNeng() {
        return jiNeng;
    }

    public void setJiNeng(List<Skill> jiNeng) {
        this.jiNeng = jiNeng;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTouXian() {
        return touXian;
    }

    public void setTouXian(String touXian) {
        this.touXian = touXian;
    }

    public String getZhiYe() {
        return zhiYe;
    }

    public void setZhiYe(String zhiYe) {
        this.zhiYe = zhiYe;
    }

    public String getShengJiJingYan() {
        return shengJiJingYan;
    }

    public void setShengJiJingYan(String shengJiJingYan) {
        this.shengJiJingYan = shengJiJingYan;
    }

    public Integer getMoQiDu() {
        return moQiDu;
    }

    public void setMoQiDu(Integer moQiDu) {
        this.moQiDu = moQiDu;
    }

    public String getZhongChengDu() {
        return zhongChengDu;
    }

    public void setZhongChengDu(String zhongChengDu) {
        this.zhongChengDu = zhongChengDu;
    }

    public Integer getShuXingDian() {
        return shuXingDian;
    }

    public void setShuXingDian(Integer shuXingDian) {
        this.shuXingDian = shuXingDian;
    }

    public Integer getQiXue1() {
        return qiXue1;
    }

    public void setQiXue1(Integer qiXue1) {
        this.qiXue1 = qiXue1;
    }

    public Integer getQiXue2() {
        return qiXue2;
    }

    public void setQiXue2(Integer qiXue2) {
        this.qiXue2 = qiXue2;
    }

    public Integer getJingLi1() {
        return jingLi1;
    }

    public void setJingLi1(Integer jingLi1) {
        this.jingLi1 = jingLi1;
    }

    public Integer getJingLi2() {
        return jingLi2;
    }

    public void setJingLi2(Integer jingLi2) {
        this.jingLi2 = jingLi2;
    }

    public Integer getGongJi() {
        return gongJi;
    }

    public void setGongJi(Integer gongJi) {
        this.gongJi = gongJi;
    }

    public Integer getFangYu() {
        return fangYu;
    }

    public void setFangYu(Integer fangYu) {
        this.fangYu = fangYu;
    }

    public Integer getSuDu() {
        return suDu;
    }

    public void setSuDu(Integer suDu) {
        this.suDu = suDu;
    }

    public String getJiNeng1() {
        return jiNeng1;
    }

    public void setJiNeng1(String jiNeng1) {
        this.jiNeng1 = jiNeng1;
    }

    public String getShuLianDu1() {
        return shuLianDu1;
    }

    public void setShuLianDu1(String shuLianDu1) {
        this.shuLianDu1 = shuLianDu1;
    }

    public String getJiNeng2() {
        return jiNeng2;
    }

    public void setJiNeng2(String jiNeng2) {
        this.jiNeng2 = jiNeng2;
    }

    public String getShuLianDu2() {
        return shuLianDu2;
    }

    public void setShuLianDu2(String shuLianDu2) {
        this.shuLianDu2 = shuLianDu2;
    }

    public String getJiNeng3() {
        return jiNeng3;
    }

    public void setJiNeng3(String jiNeng3) {
        this.jiNeng3 = jiNeng3;
    }

    public String getShuLianDu3() {
        return shuLianDu3;
    }

    public void setShuLianDu3(String shuLianDu3) {
        this.shuLianDu3 = shuLianDu3;
    }

    public Integer getJinglids() {
        return jinglids;
    }

    public void setJinglids(Integer jinglids) {
        this.jinglids = jinglids;
    }

    public Integer getGongJids() {
        return gongJids;
    }

    public void setGongJids(Integer gongJids) {
        this.gongJids = gongJids;
    }

    public Integer getQixueds() {
        return qixueds;
    }

    public void setQixueds(Integer qixueds) {
        this.qixueds = qixueds;
    }

    public Integer getSududs() {
        return sududs;
    }

    public void setSududs(Integer sududs) {
        this.sududs = sududs;
    }

    public Integer getKeyongds() {
        return keyongds;
    }

    public void setKeyongds(Integer keyongds) {
        this.keyongds = keyongds;
    }

    public Float getChengzhang() {
        return chengzhang;
    }

    public void setChengzhang(Float chengzhang) {
        this.chengzhang = chengzhang;
    }

    public Integer getChuxue() {
        return chuxue;
    }

    public void setChuxue(Integer chuxue) {
        this.chuxue = chuxue;
    }

    public Integer getChujing() {
        return chujing;
    }

    public void setChujing(Integer chujing) {
        this.chujing = chujing;
    }

    public Integer getChugong() {
        return chugong;
    }

    public void setChugong(Integer chugong) {
        this.chugong = chugong;
    }

    public Integer getChusu() {
        return chusu;
    }

    public void setChusu(Integer chusu) {
        this.chusu = chusu;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}

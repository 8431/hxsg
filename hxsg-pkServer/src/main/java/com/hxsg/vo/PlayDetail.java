package com.hxsg.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/12/16.
 * 玩家信息详情
 */
public class PlayDetail {
    private Integer id;//角色ID
    private Integer account;//所属账号
    private String rolename;//角色名
    private String status;//禁用标志，0正常，1封号
    private Integer level;//等级
    private Integer jingyan;//经验

    private Integer shengjijingyan;//升级经验

    private Integer sumds;//总点数

    private Integer keyongds;//可用点数

    private Integer totaljy;//总共经验

    private Integer qixueds;//气血点数

    private Integer jinglids;//精力点数

    private Integer gongjids;//攻击点数

    private Integer sududs;//速度点数

    private Integer totalxue1;//当前生命值

    private Integer totalxue2;//生命值最大上限

    private Integer totaljing1;//当前精力值

    private Integer totaljing2;//精力值最大上限

    private Integer totalgong;//攻击力

    private Integer totalsudu;//速度

    private Date createdata;//角色创建日期

    private String zhiye;//职业

    private String chenghao;//称号

    private Integer shengjixiaolv;//升级效率

    private Integer tilizhi;//体力值

    private Integer jin;//随身携带金

    private Integer yin;//随身携带银

    private String sex;//性别

    private String img;//人物头像
    private String zuobiao;//人物坐标
    private String roleStatus;//离线,上线
    //人物抗性
    private Map<String, Integer> roleMap = new HashMap<String, Integer>();
    private List<FuJiangDetailVo> fjLi;










    public Map<String, Integer> getRoleMap() {
        return roleMap;
    }



    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getJingyan() {
        return jingyan;
    }

    public void setJingyan(Integer jingyan) {
        this.jingyan = jingyan;
    }

    public Integer getShengjijingyan() {
        return shengjijingyan;
    }

    public void setShengjijingyan(Integer shengjijingyan) {
        this.shengjijingyan = shengjijingyan;
    }

    public Integer getSumds() {
        return sumds;
    }

    public void setSumds(Integer sumds) {
        this.sumds = sumds;
    }

    public Integer getKeyongds() {
        return keyongds;
    }

    public void setKeyongds(Integer keyongds) {
        this.keyongds = keyongds;
    }

    public Integer getTotaljy() {
        return totaljy;
    }

    public void setTotaljy(Integer totaljy) {
        this.totaljy = totaljy;
    }

    public Integer getQixueds() {
        return qixueds;
    }

    public void setQixueds(Integer qixueds) {
        this.qixueds = qixueds;
    }

    public Integer getJinglids() {
        return jinglids;
    }

    public void setJinglids(Integer jinglids) {
        this.jinglids = jinglids;
    }

    public Integer getGongjids() {
        return gongjids;
    }

    public void setGongjids(Integer gongjids) {
        this.gongjids = gongjids;
    }

    public Integer getSududs() {
        return sududs;
    }

    public void setSududs(Integer sududs) {
        this.sududs = sududs;
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

    public Date getCreatedata() {
        return createdata;
    }

    public void setCreatedata(Date createdata) {
        this.createdata = createdata;
    }

    public String getZhiye() {
        return zhiye;
    }

    public void setZhiye(String zhiye) {
        this.zhiye = zhiye == null ? null : zhiye.trim();
    }

    public String getChenghao() {
        return chenghao;
    }

    public void setChenghao(String chenghao) {
        this.chenghao = chenghao == null ? null : chenghao.trim();
    }

    public Integer getShengjixiaolv() {
        return shengjixiaolv;
    }

    public void setShengjixiaolv(Integer shengjixiaolv) {
        this.shengjixiaolv = shengjixiaolv;
    }

    public Integer getTilizhi() {
        return tilizhi;
    }

    public void setTilizhi(Integer tilizhi) {
        this.tilizhi = tilizhi;
    }

    public Integer getJin() {
        return jin;
    }

    public void setJin(Integer jin) {
        this.jin = jin;
    }

    public Integer getYin() {
        return yin;
    }

    public void setYin(Integer yin) {
        this.yin = yin;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getZuobiao() {
        return zuobiao;
    }

    public void setZuobiao(String zuobiao) {
        this.zuobiao = zuobiao == null ? null : zuobiao.trim();
    }


    public void setRoleMap(Map<String, Integer> roleMap) {
        this.roleMap = roleMap;
    }

    public List<FuJiangDetailVo> getFjLi() {
        return fjLi;
    }

    public void setFjLi(List<FuJiangDetailVo> fjLi) {
        this.fjLi = fjLi;
    }
}

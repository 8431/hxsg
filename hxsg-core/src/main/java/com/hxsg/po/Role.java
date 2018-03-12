package com.hxsg.po;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String supperpass;

    private String zhiye;

    private String chenghao;

    private Integer jingyan;

    private Integer sjxiaolv;

    private Integer tilizhi;

    private Integer jin;

    private Integer yin;

    private String tanwei;

    private String juzhudi;

    private String house;

    private String jiaopai;

    private Integer killsum;

    private String peiou;

    private Integer shuxing;

    private String fujiang;

    private String zuoji;

    private Integer qixue1;

    private Integer qixue2;

    private Integer jingli1;

    private Integer jingli2;

    private Integer gongji;

    private Integer sudu;

    private Integer fangyu;

    private Integer fuzhong1;

    private String touxiang;

    private Integer dengji;

    private Integer huilizhi;

    private String juesename;

    private String sex;

    private Integer status;
    private List<rolePass> rp;

    public void setRp(List<rolePass> rp) {
        this.rp = rp;
    }

    public List<rolePass> getRp() {
        return rp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSupperpass() {
        return supperpass;
    }

    public void setSupperpass(String supperpass) {
        this.supperpass = supperpass == null ? null : supperpass.trim();
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

    public Integer getJingyan() {
        return jingyan;
    }

    public void setJingyan(Integer jingyan) {
        this.jingyan = jingyan;
    }

    public Integer getSjxiaolv() {
        return sjxiaolv;
    }

    public void setSjxiaolv(Integer sjxiaolv) {
        this.sjxiaolv = sjxiaolv;
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

    public String getTanwei() {
        return tanwei;
    }

    public void setTanwei(String tanwei) {
        this.tanwei = tanwei == null ? null : tanwei.trim();
    }

    public String getJuzhudi() {
        return juzhudi;
    }

    public void setJuzhudi(String juzhudi) {
        this.juzhudi = juzhudi == null ? null : juzhudi.trim();
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house == null ? null : house.trim();
    }

    public String getJiaopai() {
        return jiaopai;
    }

    public void setJiaopai(String jiaopai) {
        this.jiaopai = jiaopai == null ? null : jiaopai.trim();
    }

    public Integer getKillsum() {
        return killsum;
    }

    public void setKillsum(Integer killsum) {
        this.killsum = killsum;
    }

    public String getPeiou() {
        return peiou;
    }

    public void setPeiou(String peiou) {
        this.peiou = peiou == null ? null : peiou.trim();
    }

    public Integer getShuxing() {
        return shuxing;
    }

    public void setShuxing(Integer shuxing) {
        this.shuxing = shuxing;
    }

    public String getFujiang() {
        return fujiang;
    }

    public void setFujiang(String fujiang) {
        this.fujiang = fujiang == null ? null : fujiang.trim();
    }

    public String getZuoji() {
        return zuoji;
    }

    public void setZuoji(String zuoji) {
        this.zuoji = zuoji == null ? null : zuoji.trim();
    }

    public Integer getQixue1() {
        return qixue1;
    }

    public void setQixue1(Integer qixue1) {
        this.qixue1 = qixue1;
    }

    public Integer getQixue2() {
        return qixue2;
    }

    public void setQixue2(Integer qixue2) {
        this.qixue2 = qixue2;
    }

    public Integer getJingli1() {
        return jingli1;
    }

    public void setJingli1(Integer jingli1) {
        this.jingli1 = jingli1;
    }

    public Integer getJingli2() {
        return jingli2;
    }

    public void setJingli2(Integer jingli2) {
        this.jingli2 = jingli2;
    }

    public Integer getGongji() {
        return gongji;
    }

    public void setGongji(Integer gongji) {
        this.gongji = gongji;
    }

    public Integer getSudu() {
        return sudu;
    }

    public void setSudu(Integer sudu) {
        this.sudu = sudu;
    }

    public Integer getFangyu() {
        return fangyu;
    }

    public void setFangyu(Integer fangyu) {
        this.fangyu = fangyu;
    }

    public Integer getFuzhong1() {
        return fuzhong1;
    }

    public void setFuzhong1(Integer fuzhong1) {
        this.fuzhong1 = fuzhong1;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang == null ? null : touxiang.trim();
    }

    public Integer getDengji() {
        return dengji;
    }

    public void setDengji(Integer dengji) {
        this.dengji = dengji;
    }

    public Integer getHuilizhi() {
        return huilizhi;
    }

    public void setHuilizhi(Integer huilizhi) {
        this.huilizhi = huilizhi;
    }

    public String getJuesename() {
        return juesename;
    }

    public void setJuesename(String juesename) {
        this.juesename = juesename == null ? null : juesename.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
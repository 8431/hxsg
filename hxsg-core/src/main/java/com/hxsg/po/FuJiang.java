package com.hxsg.po;

import java.io.Serializable;

public class FuJiang implements Serializable {
    private Integer id;

    private Float chengzhang;

    private Integer chuxue;

    private Integer chujing;

    private Integer chusu;

    private Integer chufang;

    private Integer chugong;

    private String touxiang;

    private String fujiangname;

    private String sex;

    private String touxian;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getChusu() {
        return chusu;
    }

    public void setChusu(Integer chusu) {
        this.chusu = chusu;
    }

    public Integer getChufang() {
        return chufang;
    }

    public void setChufang(Integer chufang) {
        this.chufang = chufang;
    }

    public Integer getChugong() {
        return chugong;
    }

    public void setChugong(Integer chugong) {
        this.chugong = chugong;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang == null ? null : touxiang.trim();
    }

    public String getFujiangname() {
        return fujiangname;
    }

    public void setFujiangname(String fujiangname) {
        this.fujiangname = fujiangname == null ? null : fujiangname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTouxian() {
        return touxian;
    }

    public void setTouxian(String touxian) {
        this.touxian = touxian == null ? null : touxian.trim();
    }
}
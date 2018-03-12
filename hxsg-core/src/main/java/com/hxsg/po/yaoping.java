package com.hxsg.po;

import java.io.Serializable;

public class yaoping implements Serializable {
    private Integer yaoid;

    private String yaoname;

    private Integer yaoprice;

    private Double qixuezhi;

    private String dengji;

    private Integer sx;

    private String touxiang;

    public Integer getYaoid() {
        return yaoid;
    }

    public void setYaoid(Integer yaoid) {
        this.yaoid = yaoid;
    }

    public String getYaoname() {
        return yaoname;
    }

    public void setYaoname(String yaoname) {
        this.yaoname = yaoname == null ? null : yaoname.trim();
    }

    public Integer getYaoprice() {
        return yaoprice;
    }

    public void setYaoprice(Integer yaoprice) {
        this.yaoprice = yaoprice;
    }

    public Double getQixuezhi() {
        return qixuezhi;
    }

    public void setQixuezhi(Double qixuezhi) {
        this.qixuezhi = qixuezhi;
    }

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji == null ? null : dengji.trim();
    }

    public Integer getSx() {
        return sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang == null ? null : touxiang.trim();
    }
}
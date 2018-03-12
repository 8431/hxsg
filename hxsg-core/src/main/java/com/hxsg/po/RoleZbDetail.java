package com.hxsg.po;

import java.io.Serializable;

public class RoleZbDetail implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer toukui;

    private Integer wuqi;

    private Integer huwan;

    private Integer xiezi;

    private Integer xl;

    private Integer yifu;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getToukui() {
        return toukui;
    }

    public void setToukui(Integer toukui) {
        this.toukui = toukui;
    }

    public Integer getWuqi() {
        return wuqi;
    }

    public void setWuqi(Integer wuqi) {
        this.wuqi = wuqi;
    }

    public Integer getHuwan() {
        return huwan;
    }

    public void setHuwan(Integer huwan) {
        this.huwan = huwan;
    }

    public Integer getXiezi() {
        return xiezi;
    }

    public void setXiezi(Integer xiezi) {
        this.xiezi = xiezi;
    }

    public Integer getXl() {
        return xl;
    }

    public void setXl(Integer xl) {
        this.xl = xl;
    }

    public Integer getYifu() {
        return yifu;
    }

    public void setYifu(Integer yifu) {
        this.yifu = yifu;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
package com.hxsg.po;

import java.io.Serializable;

public class wupin implements Serializable {
    private Integer roleid;

    private Integer yaopinid;

    private Integer yaopinnum;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getYaopinid() {
        return yaopinid;
    }

    public void setYaopinid(Integer yaopinid) {
        this.yaopinid = yaopinid;
    }

    public Integer getYaopinnum() {
        return yaopinnum;
    }

    public void setYaopinnum(Integer yaopinnum) {
        this.yaopinnum = yaopinnum;
    }
}
package com.hxsg.po;

import java.io.Serializable;

public class RoleYao implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer yaoid;

    private Integer yaonum;

    private String yaoname;

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

    public Integer getYaoid() {
        return yaoid;
    }

    public void setYaoid(Integer yaoid) {
        this.yaoid = yaoid;
    }

    public Integer getYaonum() {
        return yaonum;
    }

    public void setYaonum(Integer yaonum) {
        this.yaonum = yaonum;
    }

    public String getYaoname() {
        return yaoname;
    }

    public void setYaoname(String yaoname) {
        this.yaoname = yaoname == null ? null : yaoname.trim();
    }
}
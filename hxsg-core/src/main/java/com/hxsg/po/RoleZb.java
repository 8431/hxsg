package com.hxsg.po;

import java.io.Serializable;

public class RoleZb implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer wqid;

    private String name;

    private Integer xiaoguo;

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

    public Integer getWqid() {
        return wqid;
    }

    public void setWqid(Integer wqid) {
        this.wqid = wqid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getXiaoguo() {
        return xiaoguo;
    }

    public void setXiaoguo(Integer xiaoguo) {
        this.xiaoguo = xiaoguo;
    }
}
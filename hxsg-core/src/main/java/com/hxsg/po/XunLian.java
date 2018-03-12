package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class XunLian implements Serializable {
    private Integer id;

    private Integer roleid;

    private Date date;

    private String status;

    private String type;

    private Integer jingyan;
    private String rolename;

    public String getRoleName() {
        return rolename;
    }

    public void setRoleName(String roleName) {
        this.rolename = roleName;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getJingyan() {
        return jingyan;
    }

    public void setJingyan(Integer jingyan) {
        this.jingyan = jingyan;
    }
}
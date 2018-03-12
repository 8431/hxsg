package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class RoleMoney implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer jin;

    private Integer yin;

    private String message;

    private Date date;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
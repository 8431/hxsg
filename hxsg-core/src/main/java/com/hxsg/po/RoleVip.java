package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class RoleVip implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer level;

    private Date data;

    private Integer money;

    private String status;

    private String supervip;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSupervip() {
        return supervip;
    }

    public void setSupervip(String supervip) {
        this.supervip = supervip == null ? null : supervip.trim();
    }
}
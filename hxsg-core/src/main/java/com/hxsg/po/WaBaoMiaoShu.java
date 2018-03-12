package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class WaBaoMiaoShu implements Serializable {
    private Integer id;

    private Integer roleid;

    private String rolename;

    private Integer baoid;

    private String baoname;

    private String status;

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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getBaoid() {
        return baoid;
    }

    public void setBaoid(Integer baoid) {
        this.baoid = baoid;
    }

    public String getBaoname() {
        return baoname;
    }

    public void setBaoname(String baoname) {
        this.baoname = baoname == null ? null : baoname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class RoleNewShuXing implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer kangxingtotal;

    private String kangxing;

    private Date date;

    private String status;

    private String custom1;

    private String custom2;

    private String custom3;

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

    public Integer getKangxingtotal() {
        return kangxingtotal;
    }

    public void setKangxingtotal(Integer kangxingtotal) {
        this.kangxingtotal = kangxingtotal;
    }

    public String getKangxing() {
        return kangxing;
    }

    public void setKangxing(String kangxing) {
        this.kangxing = kangxing == null ? null : kangxing.trim();
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

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1 == null ? null : custom1.trim();
    }

    public String getCustom2() {
        return custom2;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2 == null ? null : custom2.trim();
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3 == null ? null : custom3.trim();
    }
}
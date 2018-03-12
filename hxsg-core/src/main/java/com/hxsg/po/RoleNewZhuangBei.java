package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class RoleNewZhuangBei implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer wqid;

    private String name;

    private Integer wqxiaoguo;

    private String status;

    private String type;

    private Date date;

    private Integer baoshi1;

    private Integer baoshi2;

    private Integer baoshi3;

    private Integer zb;

    private String kangxing;

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

    public Integer getWqxiaoguo() {
        return wqxiaoguo;
    }

    public void setWqxiaoguo(Integer wqxiaoguo) {
        this.wqxiaoguo = wqxiaoguo;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getBaoshi1() {
        return baoshi1;
    }

    public void setBaoshi1(Integer baoshi1) {
        this.baoshi1 = baoshi1;
    }

    public Integer getBaoshi2() {
        return baoshi2;
    }

    public void setBaoshi2(Integer baoshi2) {
        this.baoshi2 = baoshi2;
    }

    public Integer getBaoshi3() {
        return baoshi3;
    }

    public void setBaoshi3(Integer baoshi3) {
        this.baoshi3 = baoshi3;
    }

    public Integer getZb() {
        return zb;
    }

    public void setZb(Integer zb) {
        this.zb = zb;
    }

    public String getKangxing() {
        return kangxing;
    }

    public void setKangxing(String kangxing) {
        this.kangxing = kangxing == null ? null : kangxing.trim();
    }
}
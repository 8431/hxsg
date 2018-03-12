package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class YouJianWuPin implements Serializable {
    private Integer id;

    private Integer wupinid;

    private String wupinnname;

    private Integer num;

    private Date date;

    private String status;

    private Integer typeid;

    private Integer roleid;

    private String rolename;

    private Integer receiveid;

    private String receivedname;

    private Integer yin;

    private Integer youjianid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWupinid() {
        return wupinid;
    }

    public void setWupinid(Integer wupinid) {
        this.wupinid = wupinid;
    }

    public String getWupinnname() {
        return wupinnname;
    }

    public void setWupinnname(String wupinnname) {
        this.wupinnname = wupinnname == null ? null : wupinnname.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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

    public Integer getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(Integer receiveid) {
        this.receiveid = receiveid;
    }

    public String getReceivedname() {
        return receivedname;
    }

    public void setReceivedname(String receivedname) {
        this.receivedname = receivedname == null ? null : receivedname.trim();
    }

    public Integer getYin() {
        return yin;
    }

    public void setYin(Integer yin) {
        this.yin = yin;
    }

    public Integer getYoujianid() {
        return youjianid;
    }

    public void setYoujianid(Integer youjianid) {
        this.youjianid = youjianid;
    }
}
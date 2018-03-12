package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class YouJian implements Serializable {
    private Integer id;

    private Integer roleid;

    private String rolename;

    private Integer receiveid;

    private String receivedname;

    private String status;

    private Date data;

    private Date data2;

    private Integer yin;

    private String message;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData2() {
        return data2;
    }

    public void setData2(Date data2) {
        this.data2 = data2;
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
}
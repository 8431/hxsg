package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class jiangjun implements Serializable {
    private Integer id;

    private Integer roleid;

    private String jjname;

    private Integer num;

    private String type;

    private String status;

    private Date data;

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

    public String getJjname() {
        return jjname;
    }

    public void setJjname(String jjname) {
        this.jjname = jjname == null ? null : jjname.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
}
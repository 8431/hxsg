package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class TaskDetail implements Serializable {
    private Integer id;

    private Integer roleid;

    private String guainame;

    private String status;

    private String type;

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

    public String getGuainame() {
        return guainame;
    }

    public void setGuainame(String guainame) {
        this.guainame = guainame == null ? null : guainame.trim();
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
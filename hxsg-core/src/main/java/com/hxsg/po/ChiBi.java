package com.hxsg.po;

import java.util.Date;

public class ChiBi {
    private Integer id;

    private Integer num;

    private String resut;

    private String status;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getResut() {
        return resut;
    }

    public void setResut(String resut) {
        this.resut = resut == null ? null : resut.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
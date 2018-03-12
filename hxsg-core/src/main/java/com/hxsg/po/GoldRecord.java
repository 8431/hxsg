package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class GoldRecord implements Serializable {
    private Integer id;

    private Integer sellroleid;

    private String sellrolename;

    private Integer sellnum;

    private Integer buyroleid;

    private String buyrolename;

    private Date date;

    private String status;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellroleid() {
        return sellroleid;
    }

    public void setSellroleid(Integer sellroleid) {
        this.sellroleid = sellroleid;
    }

    public String getSellrolename() {
        return sellrolename;
    }

    public void setSellrolename(String sellrolename) {
        this.sellrolename = sellrolename == null ? null : sellrolename.trim();
    }

    public Integer getSellnum() {
        return sellnum;
    }

    public void setSellnum(Integer sellnum) {
        this.sellnum = sellnum;
    }

    public Integer getBuyroleid() {
        return buyroleid;
    }

    public void setBuyroleid(Integer buyroleid) {
        this.buyroleid = buyroleid;
    }

    public String getBuyrolename() {
        return buyrolename;
    }

    public void setBuyrolename(String buyrolename) {
        this.buyrolename = buyrolename == null ? null : buyrolename.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}
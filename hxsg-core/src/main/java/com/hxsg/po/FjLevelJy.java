package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class FjLevelJy implements Serializable {
    private Integer id;

    private Integer level;

    private Integer yingcai;

    private Integer jiangcai;

    private Integer yuanshuai;

    private Integer status;

    private String type;

    private Date data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getYingcai() {
        return yingcai;
    }

    public void setYingcai(Integer yingcai) {
        this.yingcai = yingcai;
    }

    public Integer getJiangcai() {
        return jiangcai;
    }

    public void setJiangcai(Integer jiangcai) {
        this.jiangcai = jiangcai;
    }

    public Integer getYuanshuai() {
        return yuanshuai;
    }

    public void setYuanshuai(Integer yuanshuai) {
        this.yuanshuai = yuanshuai;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
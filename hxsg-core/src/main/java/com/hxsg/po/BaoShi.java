package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class BaoShi implements Serializable {
    private Integer id;

    private String name;

    private Integer xiaoguo;

    private String kangxing;

    private Date data;

    private String status;

    private String miaoshuid;

    private String zbtype;

    private String img;

    private Integer roleid;

    private Integer wqid;

    private Integer sell;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getXiaoguo() {
        return xiaoguo;
    }

    public void setXiaoguo(Integer xiaoguo) {
        this.xiaoguo = xiaoguo;
    }

    public String getKangxing() {
        return kangxing;
    }

    public void setKangxing(String kangxing) {
        this.kangxing = kangxing == null ? null : kangxing.trim();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMiaoshuid() {
        return miaoshuid;
    }

    public void setMiaoshuid(String miaoshuid) {
        this.miaoshuid = miaoshuid == null ? null : miaoshuid.trim();
    }

    public String getZbtype() {
        return zbtype;
    }

    public void setZbtype(String zbtype) {
        this.zbtype = zbtype == null ? null : zbtype.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
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

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }
}
package com.hxsg.po;

import java.io.Serializable;

public class WuqiDescribe implements Serializable {
    private Integer id;

    private Integer level;

    private String miaoshu;

    private Integer xiaoguo;

    private Integer powernum;

    private String wuqiname;

    private Integer rolelevel;

    private Integer price;

    private String imgurl;

    private String status;

    private String custom1;

    private String custom2;

    private String custom3;

    private String type;

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

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu == null ? null : miaoshu.trim();
    }

    public Integer getXiaoguo() {
        return xiaoguo;
    }

    public void setXiaoguo(Integer xiaoguo) {
        this.xiaoguo = xiaoguo;
    }

    public Integer getPowernum() {
        return powernum;
    }

    public void setPowernum(Integer powernum) {
        this.powernum = powernum;
    }

    public String getWuqiname() {
        return wuqiname;
    }

    public void setWuqiname(String wuqiname) {
        this.wuqiname = wuqiname == null ? null : wuqiname.trim();
    }

    public Integer getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(Integer rolelevel) {
        this.rolelevel = rolelevel;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}
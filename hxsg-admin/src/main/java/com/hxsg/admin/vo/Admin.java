package com.hxsg.admin.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * vo类字典详情接受
 * Created by dlf on 2016/6/27.
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 6997784220972833508L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private BigDecimal pk;//0代表常数字典，1代表非常数字典
    private String type;//0代表常数字典，1代表非常数字典
    private String name;//说明
    private String costom1;//预留字段1
    private String costom2;//预留字段2
    private String costom3;//预留字段3
    private String code;//荷载类型
    private String user;//说明
    private String password;//说明

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPk() {
        return pk;
    }

    public void setPk(BigDecimal pk) {
        this.pk = pk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCostom1() {
        return costom1;
    }

    public void setCostom1(String costom1) {
        this.costom1 = costom1;
    }

    public String getCostom2() {
        return costom2;
    }

    public void setCostom2(String costom2) {
        this.costom2 = costom2;
    }

    public String getCostom3() {
        return costom3;
    }

    public void setCostom3(String costom3) {
        this.costom3 = costom3;
    }
}

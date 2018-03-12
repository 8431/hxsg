package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class Acount implements Serializable {
    @Override
    public boolean equals(Object o) {
        Acount at= (Acount) o;
        if(id== at.getId()){
            return true;
        }else{
            return false;
        }
    }
    private Integer id;

    private String name;

    private String password;

    private String supperpass;

    private String telphone;

    private String status;

    private String email;

    private String lock;

    private Date logintime;

    private String custom1;

    private String custom2;

    private String custom3;

    private String custom4;

    private String custom5;

    private Integer roleid;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSupperpass() {
        return supperpass;
    }

    public void setSupperpass(String supperpass) {
        this.supperpass = supperpass == null ? null : supperpass.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock == null ? null : lock.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
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

    public String getCustom4() {
        return custom4;
    }

    public void setCustom4(String custom4) {
        this.custom4 = custom4 == null ? null : custom4.trim();
    }

    public String getCustom5() {
        return custom5;
    }

    public void setCustom5(String custom5) {
        this.custom5 = custom5 == null ? null : custom5.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}
package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class YlDxXq implements Serializable {
    private Integer id;

    private Integer roleid;

    private String rolename;

    private Integer jin;

    private Integer yin;

    private String result;

    private Integer num;

    private Integer status;

    private String jieguo;

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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getJin() {
        return jin;
    }

    public void setJin(Integer jin) {
        this.jin = jin;
    }

    public Integer getYin() {
        return yin;
    }

    public void setYin(Integer yin) {
        this.yin = yin;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getJieguo() {
        return jieguo;
    }

    public void setJieguo(String jieguo) {
        this.jieguo = jieguo == null ? null : jieguo.trim();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
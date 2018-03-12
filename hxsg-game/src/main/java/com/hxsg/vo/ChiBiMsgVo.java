package com.hxsg.vo;

import com.hxsg.po.ChibiYazhuDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dlf on 2017/3/9.
 */
public class ChiBiMsgVo implements Serializable{
    public List<ChibiYazhuDetail> cbLi;
    public String result;
    public Integer num;
    public Integer roleJin;
    public Integer roleYin;
    public Integer sumJin;
    public Integer sumYin;

    public Integer getRoleJin() {
        return roleJin;
    }

    public void setRoleJin(Integer roleJin) {
        this.roleJin = roleJin;
    }

    public Integer getRoleYin() {
        return roleYin;
    }

    public void setRoleYin(Integer roleYin) {
        this.roleYin = roleYin;
    }

    public Integer getSumJin() {
        return sumJin;
    }

    public void setSumJin(Integer sumJin) {
        this.sumJin = sumJin;
    }

    public Integer getSumYin() {
        return sumYin;
    }

    public void setSumYin(Integer sumYin) {
        this.sumYin = sumYin;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<ChibiYazhuDetail> getCbLi() {
        return cbLi;
    }

    public void setCbLi(List<ChibiYazhuDetail> cbLi) {
        this.cbLi = cbLi;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

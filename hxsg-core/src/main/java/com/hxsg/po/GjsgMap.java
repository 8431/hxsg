package com.hxsg.po;

import java.io.Serializable;

public class GjsgMap implements Serializable {
    private Integer id;

    private String centerCity;

    private String nCity;

    private String sCity;

    private String wCity;

    private String eCity;

    private String quyu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCenterCity() {
        return centerCity;
    }

    public void setCenterCity(String centerCity) {
        this.centerCity = centerCity == null ? null : centerCity.trim();
    }

    public String getnCity() {
        return nCity;
    }

    public void setnCity(String nCity) {
        this.nCity = nCity == null ? null : nCity.trim();
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity == null ? null : sCity.trim();
    }

    public String getwCity() {
        return wCity;
    }

    public void setwCity(String wCity) {
        this.wCity = wCity == null ? null : wCity.trim();
    }

    public String geteCity() {
        return eCity;
    }

    public void seteCity(String eCity) {
        this.eCity = eCity == null ? null : eCity.trim();
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu == null ? null : quyu.trim();
    }
}
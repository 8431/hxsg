package com.hxsg.po;

import java.io.Serializable;

public class MapGuai implements Serializable {
    private Integer id;

    private String city;

    private String guaiid1;

    private String guaiid2;

    private String guaiid3;

    private String guaiid4;

    private String status;

    private String level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getGuaiid1() {
        return guaiid1;
    }

    public void setGuaiid1(String guaiid1) {
        this.guaiid1 = guaiid1 == null ? null : guaiid1.trim();
    }

    public String getGuaiid2() {
        return guaiid2;
    }

    public void setGuaiid2(String guaiid2) {
        this.guaiid2 = guaiid2 == null ? null : guaiid2.trim();
    }

    public String getGuaiid3() {
        return guaiid3;
    }

    public void setGuaiid3(String guaiid3) {
        this.guaiid3 = guaiid3 == null ? null : guaiid3.trim();
    }

    public String getGuaiid4() {
        return guaiid4;
    }

    public void setGuaiid4(String guaiid4) {
        this.guaiid4 = guaiid4 == null ? null : guaiid4.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }
}
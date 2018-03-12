package com.hxsg.po;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public class YeGuaiQun implements Serializable {
    private Integer id;

    private String name;

    private String guai1;

    private String guai2;

    private String guai3;

    private String guai4;

    private String guai5;

    private String guai6;
    public int size(){
        int i=0;
        if(!StringUtils.isEmpty(guai1)){
            i++;
        }
        if(!StringUtils.isEmpty(guai2)){
            i++;
        }
        if(!StringUtils.isEmpty(guai3)){
            i++;
        }
        if(!StringUtils.isEmpty(guai4)){
            i++;
        }
        if(!StringUtils.isEmpty(guai5)){
            i++;
        }
        if(!StringUtils.isEmpty(guai6)){
            i++;
        }



        return i;
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

    public String getGuai1() {
        return guai1;
    }

    public void setGuai1(String guai1) {
        this.guai1 = guai1 == null ? null : guai1.trim();
    }

    public String getGuai2() {
        return guai2;
    }

    public void setGuai2(String guai2) {
        this.guai2 = guai2 == null ? null : guai2.trim();
    }

    public String getGuai3() {
        return guai3;
    }

    public void setGuai3(String guai3) {
        this.guai3 = guai3 == null ? null : guai3.trim();
    }

    public String getGuai4() {
        return guai4;
    }

    public void setGuai4(String guai4) {
        this.guai4 = guai4 == null ? null : guai4.trim();
    }

    public String getGuai5() {
        return guai5;
    }

    public void setGuai5(String guai5) {
        this.guai5 = guai5 == null ? null : guai5.trim();
    }

    public String getGuai6() {
        return guai6;
    }

    public void setGuai6(String guai6) {
        this.guai6 = guai6 == null ? null : guai6.trim();
    }
}
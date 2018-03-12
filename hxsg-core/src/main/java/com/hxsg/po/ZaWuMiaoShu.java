package com.hxsg.po;

import java.io.Serializable;

public class ZaWuMiaoShu implements Serializable {
    private Integer id;

    private String name;

    private Integer level;

    private String jieshao;

    private String status;

    private String type;

    private Integer xiaoguo;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao == null ? null : jieshao.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getXiaoguo() {
        return xiaoguo;
    }

    public void setXiaoguo(Integer xiaoguo) {
        this.xiaoguo = xiaoguo;
    }




}
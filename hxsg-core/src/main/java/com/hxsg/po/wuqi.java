package com.hxsg.po;

import java.io.Serializable;

public class wuqi implements Serializable {
    private Integer id;

    private Integer dengji;

    private String jiehsao;

    private Integer gongji;

    private Integer liliang;

    private String name;

    private Integer roleDengji;

    private String shuxing;

    private Integer price;

    private String img;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDengji() {
        return dengji;
    }

    public void setDengji(Integer dengji) {
        this.dengji = dengji;
    }

    public String getJiehsao() {
        return jiehsao;
    }

    public void setJiehsao(String jiehsao) {
        this.jiehsao = jiehsao == null ? null : jiehsao.trim();
    }

    public Integer getGongji() {
        return gongji;
    }

    public void setGongji(Integer gongji) {
        this.gongji = gongji;
    }

    public Integer getLiliang() {
        return liliang;
    }

    public void setLiliang(Integer liliang) {
        this.liliang = liliang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRoleDengji() {
        return roleDengji;
    }

    public void setRoleDengji(Integer roleDengji) {
        this.roleDengji = roleDengji;
    }

    public String getShuxing() {
        return shuxing;
    }

    public void setShuxing(String shuxing) {
        this.shuxing = shuxing == null ? null : shuxing.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
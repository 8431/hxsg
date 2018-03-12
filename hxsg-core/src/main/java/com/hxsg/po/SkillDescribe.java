package com.hxsg.po;

import java.io.Serializable;

public class SkillDescribe implements Serializable {
    private Integer id;

    private String name;

    private String decccribe;

    private String zhiye;

    private String xingbie;

    private String level;

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

    public String getDecccribe() {
        return decccribe;
    }

    public void setDecccribe(String decccribe) {
        this.decccribe = decccribe == null ? null : decccribe.trim();
    }

    public String getZhiye() {
        return zhiye;
    }

    public void setZhiye(String zhiye) {
        this.zhiye = zhiye == null ? null : zhiye.trim();
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie == null ? null : xingbie.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }
}
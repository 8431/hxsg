package com.hxsg.vo;

import java.io.Serializable;

/**
 * Created by dlf on 2016/10/21.
 * 杂物详情封装VO类
 */
public class ZaWuDetailVo implements Serializable{
    private Integer id;//物品ID
    private Integer roleId;//拥有角色ID
    private String roleName;//拥有角色名称
    private String zwName;//名称:将军令
    private String zwLevel;//等级：--
    private String zwXiaoGuo;//效果：抗封+10，抗扰乱+12，抗封印+24
    private String zwMiaoShu;//介绍：可用来招募英才，系统随机抽取一名英才为你的副将。快来参加战斗吧！
    private String zwUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getZwName() {
        return zwName;
    }

    public void setZwName(String zwName) {
        this.zwName = zwName;
    }

    public String getZwLevel() {
        return zwLevel;
    }

    public void setZwLevel(String zwLevel) {
        this.zwLevel = zwLevel;
    }

    public String getZwXiaoGuo() {
        return zwXiaoGuo;
    }

    public void setZwXiaoGuo(String zwXiaoGuo) {
        this.zwXiaoGuo = zwXiaoGuo;
    }

    public String getZwMiaoShu() {
        return zwMiaoShu;
    }

    public void setZwMiaoShu(String zwMiaoShu) {
        this.zwMiaoShu = zwMiaoShu;
    }

    public String getZwUrl() {
        return zwUrl;
    }

    public void setZwUrl(String zwUrl) {
        this.zwUrl = zwUrl;
    }
}

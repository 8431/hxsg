package com.hxsg.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dlf on 2016/10/21.
 * 装备详情描述分类
 */
public class ZhuangBeiDetailVo implements Serializable{
    private String zbName;//装备名称:凤翅鎏金盔
    private String zbFuZhong;//负重：20
    private String zbXiaoGuo;//效果：抗封+10，抗扰乱+12，抗封印+24
    private String zbMiaoShu;//介绍：上古神兵之一，来自虚空，遗落凡尘。随机一项文人抗计策属性加10。
    private String zbTiaoJian;//佩戴所需条件：等级大于90级，体质大于200点
    private String zbBaoShiKong;//宝石孔:已满--------宝石孔:3
    private List<Integer> baoshiId;//宝石孔:已满--------宝石孔:3
    private Integer id;//物品ID
    private Integer roleId;//拥有角色ID
    private String roleName;//拥有角色名称
    private String imgUrl;//道具图片
    private String level;//等级

    public String getZbName() {
        return zbName;
    }

    public void setZbName(String zbName) {
        this.zbName = zbName;
    }

    public String getZbFuZhong() {
        return zbFuZhong;
    }

    public void setZbFuZhong(String zbFuZhong) {
        this.zbFuZhong = zbFuZhong;
    }

    public String getZbXiaoGuo() {
        return zbXiaoGuo;
    }

    public void setZbXiaoGuo(String zbXiaoGuo) {
        this.zbXiaoGuo = zbXiaoGuo;
    }

    public String getZbMiaoShu() {
        return zbMiaoShu;
    }

    public void setZbMiaoShu(String zbMiaoShu) {
        this.zbMiaoShu = zbMiaoShu;
    }

    public String getZbTiaoJian() {
        return zbTiaoJian;
    }

    public void setZbTiaoJian(String zbTiaoJian) {
        this.zbTiaoJian = zbTiaoJian;
    }

    public String getZbBaoShiKong() {
        return zbBaoShiKong;
    }

    public void setZbBaoShiKong(String zbBaoShiKong) {
        this.zbBaoShiKong = zbBaoShiKong;
    }

    public List<Integer> getBaoshiId() {
        return baoshiId;
    }

    public void setBaoshiId(List<Integer> baoshiId) {
        this.baoshiId = baoshiId;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

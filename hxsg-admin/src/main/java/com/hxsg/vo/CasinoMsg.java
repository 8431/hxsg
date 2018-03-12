package com.hxsg.vo;

import com.hxsg.po.YlDaXiao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dlf on 2016/10/13.
 * 赌场页面数据展示刷新信息类
 */
public class CasinoMsg implements Serializable {
    private Map mp=new HashMap();
    private Integer danYin;
    private Integer danJin;
    private Integer shuangYin;
    private Integer shuangJin;
    private Integer daYin;
    private Integer daJin;
    private Integer xiaoYin;
    private Integer xiaoJin;
    private Integer baoZiYin;
    private Integer baoZiJin;
    private Integer totalSumYin;
    private Integer totalSumJin;
    private YlDaXiao yldaxiao;

    public Map getMp() {
        return mp;
    }

    public YlDaXiao getYldaxiao() {
        return yldaxiao;
    }

    public void setYldaxiao(YlDaXiao yldaxiao) {
        this.yldaxiao = yldaxiao;
    }

    public Integer getDanYin() {
        return danYin;
    }

    public void setDanYin(Integer danYin) {
        this.danYin = danYin;
    }

    public Integer getDanJin() {
        return danJin;
    }

    public void setDanJin(Integer danJin) {
        this.danJin = danJin;
    }

    public Integer getShuangYin() {
        return shuangYin;
    }

    public void setShuangYin(Integer shuangYin) {
        this.shuangYin = shuangYin;
    }

    public Integer getShuangJin() {
        return shuangJin;
    }

    public void setShuangJin(Integer shuangJin) {
        this.shuangJin = shuangJin;
    }

    public Integer getDaYin() {
        return daYin;
    }

    public void setDaYin(Integer daYin) {
        this.daYin = daYin;
    }

    public Integer getDaJin() {
        return daJin;
    }

    public void setDaJin(Integer daJin) {
        this.daJin = daJin;
    }

    public Integer getXiaoYin() {
        return xiaoYin;
    }

    public void setXiaoYin(Integer xiaoYin) {
        this.xiaoYin = xiaoYin;
    }

    public Integer getXiaoJin() {
        return xiaoJin;
    }

    public void setXiaoJin(Integer xiaoJin) {
        this.xiaoJin = xiaoJin;
    }

    public Integer getBaoZiYin() {
        return baoZiYin;
    }

    public void setBaoZiYin(Integer baoZiYin) {
        this.baoZiYin = baoZiYin;
    }

    public Integer getBaoZiJin() {
        return baoZiJin;
    }

    public void setBaoZiJin(Integer baoZiJin) {
        this.baoZiJin = baoZiJin;
    }

    public Integer getTotalSumYin() {
        return totalSumYin;
    }

    public void setTotalSumYin(Integer totalSumYin) {
        this.totalSumYin = totalSumYin;
    }

    public Integer getTotalSumJin() {
        return totalSumJin;
    }

    public void setTotalSumJin(Integer totalSumJin) {
        this.totalSumJin = totalSumJin;
    }
}

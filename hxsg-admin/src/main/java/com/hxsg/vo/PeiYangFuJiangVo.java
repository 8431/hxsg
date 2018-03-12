package com.hxsg.vo;

import java.io.Serializable;

/**
 * Created by dlf on 2016/10/26.
 * 副将培养界面VO
 */
public class PeiYangFuJiangVo implements Serializable{
    private Integer fuId;//副将Id
    private String jjMsg;//您现在有0枚将军令
    private String fuName;//副将:藤甲兵
    private String czl;//成长率:0.9
    private String qx;//气血:2132
    private String jl;//精力:2132
    private String gj;//攻击:2132
    private String mj;//敏捷:2132
    private String tiShiMsg;//无法培养参战设置下的副将！


    public Integer getFuId() {
        return fuId;
    }

    public void setFuId(Integer fuId) {
        this.fuId = fuId;
    }

    public String getJjMsg() {
        return jjMsg;
    }

    public void setJjMsg(String jjMsg) {
        this.jjMsg = jjMsg;
    }

    public String getFuName() {
        return fuName;
    }

    public void setFuName(String fuName) {
        this.fuName = fuName;
    }

    public String getCzl() {
        return czl;
    }

    public void setCzl(String czl) {
        this.czl = czl;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getJl() {
        return jl;
    }

    public void setJl(String jl) {
        this.jl = jl;
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }

    public String getTiShiMsg() {
        return tiShiMsg;
    }

    public void setTiShiMsg(String tiShiMsg) {
        this.tiShiMsg = tiShiMsg;
    }
}

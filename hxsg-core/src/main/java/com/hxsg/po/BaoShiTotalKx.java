package com.hxsg.po;

import java.io.Serializable;

/**
 * Created by dlf on 2016/10/21.
 */
public class BaoShiTotalKx implements Serializable {
    private String kangXing;
    private Integer num;//数量
    private Integer kangXingTotal;

    public Integer getKangXingTotal() {
        return kangXingTotal;
    }

    public void setKangXingTotal(Integer kangXingTotal) {
        this.kangXingTotal = kangXingTotal;
    }

    public String getKangXing() {
        return kangXing;
    }

    public void setKangXing(String kangXing) {
        this.kangXing = kangXing;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


}

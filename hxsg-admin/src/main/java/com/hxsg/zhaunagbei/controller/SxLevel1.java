package com.hxsg.zhaunagbei.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.ZbDetail.ZbShuXing;
import com.hxsg.Dao.*;
import com.hxsg.po.WuQiDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dlf on 2016/3/12.
 */

public class SxLevel1 {
    @Autowired
    RoleMapper rm;
    @Autowired
    jiangjunMapper jjm;
    @Autowired
    ZaWuMiaoShuMapper zwmsm;
    @Autowired
    WuQiDetailMapper wqdm;
    @Autowired
    RoleZbDetailMapper rzdm;
    @Autowired
    wuqiMapper wqm;
        public void level1(Integer wuqiid,HttpServletRequest request,HttpServletResponse response)throws Exception {
            WuQiDetail wqd = new WuQiDetail();
            wqd.setZbid(wuqiid);
            WuQiDetail wl = wqdm.selectAll(wqd).get(0);
            WuQiDetail wadl = wqdm.selectAll(wqd).get(0);
            Integer levels = Integer.parseInt(wadl.getLevel());
            String pinzhi2 =wqdm.selectAll(wqd).get(0).getPinzhi();
            Integer pinzhi =Integer.parseInt(pinzhi2);
            if(levels==1)
            switch (pinzhi) {
                //1.紫色武士武器
                case 1:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_WS_ZSWQ_GONGJI);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_WS_ZSWQ_GONGJI);

                        if (wadl.getBaoji() > 0) {
                            wadl.setBaoji(wadl.getBaoji() +ZbShuXing.LEVEL2_ZSSX);
                        }
                        if (wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() +ZbShuXing.LEVEL2_ZSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() +ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;

                }
                // 2.金色武士武器
                case 2:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_WS_JSWQ_GONGJI);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_WS_JSWQ_GONGJI);

                        if (wadl.getBaoji() > 0) {
                            wadl.setBaoji(wadl.getBaoji() + ZbShuXing.LEVEL2_JSSX);
                        }
                        if (wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() + ZbShuXing.LEVEL2_JSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() + ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
                //3.紫色法师武器
                case 3:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_FS_ZSWQ_GONGJI);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_FS_ZSWQ_GONGJI);

                        if (wadl.getFashubao() > 0) {
                            wadl.setBaoji(wadl.getBaoji() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        if (wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
                // 4.金色法师武器
                case 4:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_FS_JSWQ_GONGJI);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_FS_JSWQ_GONGJI);

                        if (wadl.getFashubao() > 0) {
                            wadl.setBaoji(wadl.getBaoji() + ZbShuXing.LEVEL2_JSSX);
                        }
                        if (wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() +ZbShuXing.LEVEL2_JSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() +ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
                //5.紫色文人武器
                case 5:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_WR_ZSWQ_GONGJI);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_WR_ZSWQ_GONGJI);

                        if (wadl.getQixue() > 0) {
                            wadl.setBaoji(wadl.getBaoji() +ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }

                    break;
                }
                // 6.金色文人武器
                case 6:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_WR_JSWQ_GONGJI);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_WR_JSWQ_GONGJI);
                        if (wadl.getQixue() > 0) {
                            wadl.setBaoji(wadl.getBaoji() + ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() + ZbShuXing.LEVEL2_JSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() + ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }

                    break;
                }
                //7.紫色铠甲
                case 7:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        //wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL1_WR_JSWQ_GONGJI);
                        //wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL1_WR_JSWQ_GONGJI);
                        // wadl.setWuli(1);
                        // wadl.setWuli(1);
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_ZSKJ);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_ZSKJ);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }

                    break;
                }
                //8.金色铠甲
                case 8:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_JSKJ);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_JSKJ);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
                //9.紫色护腕
                case 9:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_ZSHW);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_ZSHW);

                        if(wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        if (wadl.getBaoji() > 0) {
                            wadl.setBaoji(wadl.getBaoji() + ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }

                    break;
                }
                //10.金色护腕
                case 10:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_JSHW);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_JSHW);
                        wadl.setQixue(wadl.getQixue()+500);
                        if(wadl.getZhiming() > 0) {
                            wadl.setZhiming(wadl.getZhiming() + ZbShuXing.LEVEL2_JSSX);
                        }
                        if (wadl.getMingzhong() > 0) {
                            wadl.setMingzhong(wadl.getMingzhong() + ZbShuXing.LEVEL2_JSSX);
                        }
                        if (wadl.getBaoji() > 0) {
                            wadl.setBaoji(wadl.getBaoji() + ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
                //11.紫色项链
                case 11:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_ZSXL);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_ZSXL);
                        //wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getSudu()>0){//速度
                            wadl.setSudu(wadl.getSudu()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
                //12.金色项链
                case 12:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_JSXL);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_JSXL);
                        wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getSudu()>0){//速度
                            wadl.setSudu(wadl.getSudu()+ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }

                //13.紫色战靴
                case 13:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_ZSZX);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_ZSZX);

                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getSudu()>0){//速度
                            wadl.setSudu(wadl.getSudu()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }

                    break;
                }
                //14.金色战靴
                case 14:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_JSZX);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_JSZX);

                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getSudu()>0){//速度
                            wadl.setSudu(wadl.getSudu()+ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }

                    break;
                }

                //15.紫色力盔
                case 15:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_ZSTK);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_ZSTK);
                        //  wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//反震
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;

                }
                //16.金色力盔
                case 16:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_JSTK);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_JSTK);
                        // wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//反震
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }

                //17.紫色血盔
                case 17:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_ZSTK);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_ZSTK);
                        wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//反震
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
                //18.金色血盔
                case 18:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_JSTK);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_JSTK);
                        wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//反震
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }

                //19.紫色速盔
                case 19:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_ZSTK);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_ZSTK);
                        wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getFanzhen()>0){//反震
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_ZSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;

                }
                //20.金色速盔
                case 20:{
                    int gl = (int) (Math.random() * 99);
                    //升星概率
                    if (gl > 60) {
                        wadl.setLevel("2");
                        wadl.setGongji(wadl.getGongji() + ZbShuXing.LEVEL2_JSTK);
                        wadl.setAddgongji(wadl.getAddgongji() + ZbShuXing.LEVEL2_JSTK);
                        // wadl.setJingli(wadl.getJingli()+500);
                        if(wadl.getYaohuo()>0){//抗妖火
                            wadl.setYaohuo(wadl.getYaohuo()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWuli()>0){//抗物理
                            wadl.setWuli(wadl.getWuli()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDu()>0){//抗毒
                            wadl.setDu(wadl.getDu()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanji()>0){//反击
                            wadl.setFanji(wadl.getFanji()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLei()>0){//雷
                            wadl.setLei(wadl.getLei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getHufeng()>0){//呼风
                            wadl.setHufeng(wadl.getHufeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFeng()>0){//封
                            wadl.setFeng(wadl.getFeng()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getLuan()>0){//乱
                            wadl.setLuan(wadl.getLuan()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getWei()>0){//围
                            wadl.setWei(wadl.getWei()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//围
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getFanzhen()>0){//反震
                            wadl.setFanzhen(wadl.getFanzhen()+ZbShuXing.LEVEL2_JSSX);
                        }
                        if(wadl.getDuobi()>0){//躲避
                            wadl.setDuobi(wadl.getDuobi()+ZbShuXing.LEVEL2_JSSX);
                        }
                        wqdm.updateByPrimaryKeySelective(wadl);
                        CommonUtilAjax.sendAjaxList("msg", "恭喜你装备升级为2星", request, response);
                    }else{
                        CommonUtilAjax.sendAjaxList("msg", "很遗憾装备升星失败！", request, response);
                    }
                    break;
                }
            }



        }

}

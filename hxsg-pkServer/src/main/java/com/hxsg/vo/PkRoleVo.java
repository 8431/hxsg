package com.hxsg.vo;

import com.hxsg.po.RoleNewShuXing;
import com.hxsg.po.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class PkRoleVo  implements Serializable , Comparable{
    private String name;//副将名
    private String id;//副将ID
    private Integer qixue;//气血
    private Integer qixue2;//总气血
    private Integer jingli;//精力
    private Integer jingli2;//总精力
    private Integer sudu;//速度
    private Integer totalgong;//攻击力
    private Integer level;//等级
    //伤害值
    private int shValue;
    //被扣除血量
    private int kouXue;
    //攻击名称
    private String attakType;

    private List<RoleNewShuXing> shuXingLi;//属性集合
    private List<Skill> jineng=null;//技能集合
    private List<Map<String,Object>> buff=new ArrayList<>();    //BUFF
    //属性格式化
    private Map<String, Integer> sxLi=new HashMap<>();
    //负面状态
    Map<String,Integer> mpBuff=new HashMap<String,Integer>();
    @Override
    public int compareTo(Object o) {
        PkRoleVo prv= (PkRoleVo) o;
        if(this.getSudu()>=prv.getSudu()){
            return 1;
        }else{
            return -1;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQixue() {
        return qixue;
    }

    public void setQixue(Integer qixue) {
        try{

            System.out.println("-------------------------------------------------------");

            System.out.println("上次气血："+this.qixue+".目前气血："+qixue+"被扣除气血："+(this.qixue-qixue));
            System.out.println("-------------------------------------------------------");
        }catch (Exception e){

        }


        this.qixue = qixue;
    }

    public List<Skill> getJineng() {
        return jineng;
    }

    public void setJineng(List<Skill> jineng) {
        this.jineng = jineng;

    }

    public Integer getQixue2() {
        return qixue2;
    }

    public void setQixue2(Integer qixue2) {
        this.qixue2 = qixue2;
    }

    public Integer getJingli() {
        return jingli;
    }

    public void setJingli(Integer jingli) {
        this.jingli = jingli;
    }

    public Integer getJingli2() {
        return jingli2;
    }

    public void setJingli2(Integer jingli2) {
        this.jingli2 = jingli2;
    }

    public Integer getSudu() {
        return sudu;
    }

    public void setSudu(Integer sudu) {
        this.sudu = sudu;
    }

    public List<RoleNewShuXing> getShuXingLi() {
        return shuXingLi;
    }

    /**
     * 玩家属性格式化
     * @param shuXingLi
     */
    public void setShuXingLi(List<RoleNewShuXing> shuXingLi) {
        if(shuXingLi!=null){
            for(RoleNewShuXing r:shuXingLi){
                this.sxLi.put(r.getKangxing(),r.getKangxingtotal());
            }
        }

        this.shuXingLi = shuXingLi;
    }

    public Integer getTotalgong() {
        return totalgong;
    }

    public void setTotalgong(Integer totalgong) {
        this.totalgong = totalgong;
    }

    public List<Map<String, Object>> getBuff() {
        return buff;
    }

    public void setBuff(List<Map<String, Object>> buff) {
        this.buff = buff;
    }

    public Map<String, Integer> getSxLi() {
        return sxLi;
    }

    public Map<String, Integer> getMpBuff() {
        return mpBuff;
    }

    public void setMpBuff(Map<String, Integer> mpBuff) {
        this.mpBuff = mpBuff;
    }

    public int getShValue() {
        return shValue;
    }

    public void setShValue(int shValue) {
        this.shValue = shValue;
    }

    public int getKouXue() {
        return kouXue;
    }

    public void setKouXue(int kouXue) {
        this.kouXue = kouXue;
    }

    public String getAttakType() {
        return attakType;
    }

    public void setAttakType(String attakType) {
        this.attakType = attakType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setSxLi(Map<String, Integer> sxLi) {
        this.sxLi = sxLi;
    }
}

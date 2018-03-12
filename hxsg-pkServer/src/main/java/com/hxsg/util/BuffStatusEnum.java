package com.hxsg.util;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public enum  BuffStatusEnum {
    feng("四面楚歌",1),luan("趁火打劫",2),wei("画地为牢",3),gong("气冲斗牛",4),
    du("巫蛊极毒",5);
    private String name;
    private  int index;
    BuffStatusEnum(String s, int i) {
     this.name=s;
     this.index=i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

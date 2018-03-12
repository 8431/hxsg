package com.hxsg.pk.websoket;


import com.google.gson.Gson;




/**
 * PK服务器
 */
public interface PkServerDao{
    /**
     *   1.A发起pk初始化话A数据并等待B加入
     *   2.B加入队列，根据B的类型（玩家，野怪）初始化B数据
     *   3.下发AB数据到AB客户端
     *   4.等待AB客户端发送攻击指令（默认指令）
     *   5.AB发送指令到服务器，服务器对指令进行计算，如果有一方阵亡 PK结束并进行奖励发放，以及结束信息推送
     *   否则继续执行第3步 一直循环
     */
    void loading();
    void proA();
    void proB();
    void proData();
    void  excute();





}
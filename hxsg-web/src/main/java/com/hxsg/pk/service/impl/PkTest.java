package com.hxsg.pk.service.impl;

/**
 * Created by Administrator on 2017/4/13.
 * /**
 * 1. A加入PK队列等待B加入
 *      1.1 初始化一个Pk线程，并初始化A数据后，进入挂起状态等待B进入后唤醒
 * 2. B加入队列，初始化数据传递到客户端
 *      2.1 B唤醒Pk线程，并初始化B数据
 * 3. 加载战斗数据
 *      3.1 将数据下发到AB客户端
 * 4. AB客户端传递攻击指令
 *      4.14. 服务端对比校验数据，根据攻击指令计算结果，返回计算后的数据到客户端
 * 5. 客户端根据数据演示相应动画，完毕后通知服务器
 * 6. 服务器接收到AB客户端指令后，开始下一回合
 */
public class PkTest {
    public static void mian(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().isAlive());




    }
}

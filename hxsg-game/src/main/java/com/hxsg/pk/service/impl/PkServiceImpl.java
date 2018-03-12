package com.hxsg.pk.service.impl;

import com.hxsg.CommonUtil.util.PkMap;
import com.hxsg.pk.service.PkService;
import com.hxsg.system.dao.SystemNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by dlf on 2016/12/28.
 */
@Service("PkService")
/**
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
public class PkServiceImpl implements PkService {
    @Autowired
    SystemNotification systemnotification;
    @Override
    public void pushTime(Integer roleId, Integer pkId) {
        Boolean key = true;
        Boolean round = true;
        try {
            int num = 1;
            do {
                times:
                for (int i = 30; i >= 0; i--) {
                    if (i == 0) {
                        Boolean zeroKey = true;
                        //最多等待5秒，如果数据还没推过来，那么按照默认处理
                        int five = 0;
                        do {
                            Object roleObj2 = PkMap.PKMAP.get(roleId);
                            Object pkObj2 = PkMap.PKMAP.get(pkId);
                            if (roleObj2 != null && pkObj2 != null) {
                                System.out.println("i=0.等待双方玩家出招");
                                //当有一方所有副将和主将血量为0的时候
                                int a = (int) (Math.random() * 100);
                                if (a > 50) {
                                    key = false;
                                } else {
                                    //继续执行倒计时

                                }
                                PkMap.PKMAP.remove(roleId);
                                PkMap.PKMAP.remove(pkId);
                                break times;
                            } else {
                                five++;
                                if (five == 5) {
                                    //最多等待5秒，如果数据还没推过来，那么按照默认处理
                                    System.out.println("最多等待5秒，如果数据还没推过来，那么按照默认处理");
                                }

                            }
                            System.out.println("时间为0，正在查询集合是否为空，为空则没1秒查询一次；直到不为空为止");
                            Thread.sleep(1000);

                        } while (zeroKey);
                    } else if (i > 0) {
                        Object roleObj = PkMap.PKMAP.get(roleId);
                        Object pkObj = PkMap.PKMAP.get(pkId);
                        if (roleObj != null && pkObj != null) {
                            //当有一方所有副将和主将血量为0的时候
                            int a = (int) (Math.random() * 100);
                            if (a > 70) {
                                System.out.println("战斗结束。。。");
                                key = false;
                            } else {
                                //继续执行倒计时

                            }
                            PkMap.PKMAP.remove(roleId);
                            PkMap.PKMAP.remove(pkId);
                            break times;
                        } else {
                            System.out.println(i);
//                            systemnotification.sendSystemMsg(new Object[]{"119", i}, roleId.toString());
//                            systemnotification.sendSystemMsg(new Object[]{"119", i}, pkId.toString());
                            Thread.sleep(1000);
                        }
                    }
                }
                System.out.println("第" + num + "回合结束。。。");
                num++;
            } while (key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] ars) {
        Boolean key = true;
        Boolean round = true;
        try {


            do {
                times:
                for (int i = 5; i >= 0; i--) {
                    if (i == 0) {
                        Boolean zeroKey = true;
                        do {
                            Object roleObj2 = PkMap.PKMAP.get(1000);
                            Object pkObj2 = PkMap.PKMAP.get(1002);
                            if (!StringUtils.isEmpty(roleObj2) && !StringUtils.isEmpty(pkObj2)) {
                                //当有一方所有副将和主将血量为0的时候
                                int a = (int) (Math.random() * 100);
                                if (a > 70) {
                                    key = false;
                                } else {
                                    //继续执行倒计时

                                }
                                break times;
                            }
                            System.out.println("时间为0，正在查询集合是否为空，为空则没1秒查询一次；直到不为空为止");
                            Thread.sleep(1000);

                        } while (zeroKey);
                    } else if (i > 0) {

                        Object roleObj = PkMap.PKMAP.get(1000);
                        Object pkObj = PkMap.PKMAP.get(1002);
                        if (!StringUtils.isEmpty(roleObj) && !StringUtils.isEmpty(pkObj)) {
                            //当有一方所有副将和主将血量为0的时候
                            int a = (int) (Math.random() * 100);
                            if (a > 70) {
                                key = false;
                            } else {
                                //继续执行倒计时
                                break times;
                            }
                        } else {
                            System.out.println(i);
                            Thread.sleep(1000);
                        }
                    }
                }

            } while (key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

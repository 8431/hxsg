package com.hxsg.gchang.controller.yule.service.impl;

import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.*;
import com.hxsg.gchang.controller.yule.service.Cocos2dGcYuLeChiBiGameService;
import com.hxsg.po.*;
import com.hxsg.system.dao.SystemNotification;
import com.hxsg.vo.ChiBiMsgVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2017/3/8.
 */
@Service("Cocos2dGcYuLeChiBiGameService")
public class Cocos2dGcYuLeChiBiGameServiceImpl implements Cocos2dGcYuLeChiBiGameService {
    private static final String CHIBIRESULT = "魏蜀吴";
    public static long CHIBITIME = 0;
    //赤壁的期数
    public static Integer num = 1;
    //钱庄的金银比，由钱庄类动态推送过来。
    private static Integer bili = 0;
    //上期玩家赢得金银总数
    private static Integer totalJin = 0;
    private static Integer totalYin = 0;
    private static String chiBiKey = "1000";
    private Logger logger = Logger.getLogger(Cocos2dGcYuLeChiBiGameServiceImpl.class);
    @Autowired
    ChibiYazhuDetailMapper chibiyazhudetailmapper;
    @Autowired
    ChiBiMapper chibimapper;
    @Autowired
    NewRoleMapper newrolemapper;
    @Autowired
    RoleFriendsMsgMapper rfmm;
    @Autowired
    SystemNotification systemnotification;
    @Autowired
    WorldMsgMapper worldmsgmapper;

    @Override
    public String roleStakeResult(Integer roleId, ChibiYazhuDetail cd) throws Exception {
        String msg = StatusNum.FAIL;
        try {
            //1。验证是否是同一个人
            if (cd.getRoleid() != null) {
                if (cd.getYin() == null) {
                    cd.setYin(0);
                }else if(cd.getYin()>100000){
                    return StatusNum.CCXZ;
                }
                if (cd.getJin() == null) {
                    cd.setJin(0);
                }else if(cd.getJin()>1000){
                    return StatusNum.CCXZ;
                }
                if(cd.getJin()>0&&cd.getYin()>0){
                    return StatusNum.FAIL;
                }
                ChibiYazhuDetail cdl = chibiyazhudetailmapper.queryPlayerTotal(cd.getRoleid(), num);
                if (cdl != null) {
                    int cldJin=0;
                    int cldYin=0;
                    if(cdl.getJin()!=null){
                         cldJin = cdl.getJin();
                        if (cldJin >=1000&&cd.getJin()>0 ) {
                             return StatusNum.CCXZ;

                        }
                    }
                    if(cdl.getYin()!=null){
                         cldYin = cdl.getYin();
                        if (cldYin >=100000&&cd.getYin()>0) {
                            return StatusNum.CCXZ;

                        }

                    }




                }

                if (!StringUtils.isEmpty(cd.getResult()) && CHIBIRESULT.indexOf(cd.getResult()) != -1) {

                    NewRole role = newrolemapper.selectByPrimaryKey(cd.getRoleid());
                    if (role != null) {
                        int roleJin = role.getJin();
                        int roleYin = role.getYin();
                        if (cd.getJin() > 0) {
                            if (roleJin >= cd.getJin()) {

                                role.setJin(role.getJin() - cd.getJin());
                            }
                        }
                        if (cd.getYin() > 0) {
                            if (roleJin >= cd.getJin()) {
                                role.setYin(role.getYin() - cd.getYin());

                            }
                        }
                        if ((cd.getJin() > 0 && roleJin >= cd.getJin()) || (cd.getYin() > 0 && roleYin >= cd.getYin())) {
                            newrolemapper.updateByPrimaryKeySelective(role);
                            cd.setData(new Date());
                            cd.setStatus("0");
                            cd.setNum(num);
                            chibiyazhudetailmapper.insertSelective(cd);
                            //将信息推送到页面上，页面重新获取押注数据
                            systemnotification.sendSystemMsg(new Object[]{StatusNum.CHIBIYAZHU, null});
                            msg = StatusNum.SUCCES;

                        }

                    }
                }
            }
        } catch (Exception e) {
            msg = StatusNum.ERROR;
            logger.error("赤壁押注失败异常：" + e.getMessage(), e);
        }

        return msg;
    }

    @Override
    public ChiBiMsgVo loadChiBiDetail(Integer num) throws Exception {
        ChiBiMsgVo cv = new ChiBiMsgVo();
        try {
            List<ChibiYazhuDetail> cdLi = chibiyazhudetailmapper.queryYzZhuDetail(num);
            cv.setCbLi(cdLi);
            ChiBi cb = chibimapper.selectByPrimaryKey(num - 1);
            cv.setNum(num);
            cv.setResult(cb.getResut());
        } catch (Exception e) {
            logger.error("获取赤壁页面初始化数据异常：" + e.getMessage(), e);
        }
        return cv;
    }

    @Override
    public ChiBiMsgVo loadChiBiYaZhuMsg(Integer roleId, Integer num) throws Exception {
        ChiBiMsgVo cv = new ChiBiMsgVo();
        try {
            if (roleId != null && num != null) {
                ChibiYazhuDetail cdl = chibiyazhudetailmapper.queryPlayerTotal(roleId, num);
                if (cdl != null) {
                    if(cdl.getJin()!=null){
                        int cldJin = cdl.getJin();
                        cv.setSumJin(cldJin);
                    }else{
                        cv.setSumJin(0);
                    }
                    if(cdl.getYin()!=null){
                        int cldYin = cdl.getYin();
                        cv.setSumYin(cldYin);
                    }else{
                        cv.setSumYin(0);
                    }

                } else {
                    cv.setSumJin(0);
                    cv.setSumYin(0);
                }
                NewRole role = newrolemapper.selectByPrimaryKey(roleId);
                cv.setRoleJin(role.getJin());
                cv.setRoleYin(role.getYin());
            }


        } catch (Exception e) {
            logger.error("获取赤壁加载页面异常：" + e.getMessage(), e);

        }


        return cv;
    }

    /**
     * 赤壁游戏玩法实现
     * 1.查询该期押注的所有信息，针对金砖进行分析，银两可以根据钱庄实时动态比，转化为相应金钻
     * 进行跌算，如果钱庄没有金银求购信息，默认金银比为1000:1
     * 2.开盘操控方式
     * 2.1按照押 魏-蜀-吴  ex: 魏 1000金 蜀 2000 金 吴500金 开吴。系统统计最利于系统赔
     * 出的金额进行开盘数据判定
     * 2.2根据系统表预定好的开盘顺序开盘
     * 说明：提供支持以上两种开盘方式的热部署切换操作
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object GameAI() throws Exception {
        try {
            //赤壁以第1种库表随机方式运行
            if ("1001".equals(chiBiKey)) {


            } else {
                //赤壁以第2种系统根据押注情况自行判定
                //2.1查询改期数魏蜀吴分类的金银押注情况
                ChiBi cb = new ChiBi();
                cb.setStatus("0");
                cb.setTime(new Date());
                chibimapper.insertSelective(cb);

                List<ChibiYazhuDetail> cdLi = chibiyazhudetailmapper.queryYzZhuDetail(num);
                //2.2获取魏蜀吴押注最少的结果
                String result = getResult(cdLi);
                ChiBi cbi = chibimapper.selectByPrimaryKey(num);
                if (!StringUtils.isEmpty(result) && cbi != null) {
                    //2.3将开盘结果插入到表中
                    cbi.setResut(result);
                    chibimapper.updateByPrimaryKeySelective(cbi);
                    //2.4发放玩家奖励
                    List<ChibiYazhuDetail> sumYazhu = chibiyazhudetailmapper.queryPlayerYaZhuDetail(num);
                    ChibiYazhuDetail cd=new ChibiYazhuDetail();
                    if (sumYazhu != null && sumYazhu.size() > 0) {
                        for (ChibiYazhuDetail d : sumYazhu) {
                            cd.setId(d.getId());
                            RoleFriendsMsg rg = new RoleFriendsMsg();
                            rg.setRoleid(d.getRoleid());
                            rg.setFriendid(1000);
                            rg.setData(new Date());
                            rg.setType("通知");

                            int jin = d.getJin();
                            int yin = d.getYin();
                            //待处理，岁率
                            int sui = cbi.getNum();
                            //输赢判定
                            if (result.equals(d.getResult())) {
                                Double Jind = (100 - sui) / 100d * jin * 3;
                                Double Yind = (100 - sui) / 100d * yin * 3;

                                BigDecimal winJin = new BigDecimal(Jind.toString()).setScale(0, BigDecimal.ROUND_HALF_UP);
                                BigDecimal winYin = new BigDecimal(Yind.toString()).setScale(0, BigDecimal.ROUND_HALF_UP);

                                totalJin += winJin.intValue();
                                totalYin += winYin.intValue();
                                //发送奖励给玩家
                                NewRole role = newrolemapper.selectByPrimaryKey(d.getRoleid());
                                if (role != null) {
                                    role.setJin(role.getJin() + winJin.intValue());
                                    role.setYin(role.getYin() + winYin.intValue());
                                    newrolemapper.updateByPrimaryKeySelective(role);
                                }
                                cd.setJieguo("赢");
                                rg.setMessage("本期赤壁您支持的【" + result + "】军胜利,恭喜您赢了" + winJin + "金" + winYin + "银");
                            } else {
                                cd.setJieguo("输");
                                rg.setMessage("本期赤壁您支持的【" + d.getResult() + "】军惨败,很遗憾您输了" + jin + "金" + yin + "银");

                            }

                            chibiyazhudetailmapper.updateByPrimaryKeySelective(cd);
                            rfmm.insertSelective(rg);
                            systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rg}, rg.getRoleid().toString());

                        }
                        String wordMsg = "本期赤壁【" + result + "】军胜利!玩家共赢的" + totalJin + "金" + totalYin + "银";
                        sendWordMsg(wordMsg);
                        cbi.setStatus("1");
                        chibimapper.updateByPrimaryKeySelective(cbi);

                    }

                }

                    cbi.setResut(result);
                    chibimapper.updateByPrimaryKeySelective(cbi);
                    String wordMsg = "本期赤壁【" + result + "】军胜利!玩家共赢的" + totalJin + "金" + totalYin + "银";
                    sendWordMsg(wordMsg);
                    cbi.setStatus("1");
                    chibimapper.updateByPrimaryKeySelective(cbi);

                //下一期赤壁期号
                num = cb.getId();

            }


        } catch (Exception e) {
            logger.error("赤壁结算异常：" + e.getMessage(), e);

        } finally {
            CHIBITIME = (9 * 60 * 1000) - (30 * 1000);
        }
        return null;
    }

    public void sendWordMsg(String wordMsg) throws Exception {
        WorldMsg wg = new WorldMsg();
        wg.setData(new Date());
        wg.setRoleid(999);
        wg.setType("0");
        wg.setRolename("系统");
        wg.setMessage(wordMsg);
        worldmsgmapper.insertSelective(wg);
        List<WorldMsg> worldli = worldmsgmapper.queryWorldMsg();//世界
        systemnotification.sendSystemMsg(new Object[]{"4", worldli});
    }

    public String getResult(List<ChibiYazhuDetail> cdLi) {
        String result = null;
        int sum = Integer.MAX_VALUE;
        List<String> li = null;
        if (cdLi.size() == 2) {
            li = new ArrayList<String>();
            li.add("魏");
            li.add("蜀");
            li.add("吴");

        }
        if (cdLi != null && cdLi.size() > 1) {
            for (int i = 0; i < cdLi.size(); i++) {
                String sg = cdLi.get(i).getResult();
                if (li != null && li.contains(sg)) {
                    li.remove(sg);
                    result = li.get(0);
                } else {
                    int jin = cdLi.get(i).getJin();
                    int yin = cdLi.get(i).getYin();
                    if (bili.equals(0)) {
                        //如果钱庄没有比例产生默认比例为1000
                        bili = 1000;
                    }
                    int total = jin * bili + yin;
                    if (total <= sum) {
                        sum = total;
                        result = sg;
                    }
                }

            }

        }
        if (cdLi == null || cdLi.size() == 1 || cdLi.size() == 0) {
            int de = (int) (Math.random() * 3);
            switch (de) {
                case 0: {
                    result = "魏";
                    break;
                }
                case 1: {
                    result = "蜀";
                    break;
                }
                case 2: {
                    result = "吴";
                    break;
                }

            }
        }
        return result;
    }

    /**
     * 查询赤壁历史记录
     * @return
     * @throws Exception
     */
    @Override
    public Object queryHistoryRecord() throws Exception {
        return chibimapper.queryHistory();
    }

    /**
     * 赤壁战利品排行
     * @return
     * @throws Exception
     */
    @Override
    public Object queryWinRecord() throws Exception {
        return chibiyazhudetailmapper.queryTotalRecord();
    }

    /**
     * 赤壁投注记录
     * @return
     * @throws Exception
     */
    @Override
    public Object queryMySelfRecord(Integer roleid) throws Exception {
        return chibiyazhudetailmapper.queryYaZhuRecord(roleid);
    }

    /**
     * 赤壁战利品排行
     * @return
     * @throws Exception
     */
    @Override
    public Object queryWinRecordforNum(Integer num) throws Exception {
        return chibiyazhudetailmapper.queryRecordforNum(num);
    }

    public static void main(String[] args) {
      for(int i=0;i<10;i++){
          int de = (int) (Math.random() * 3);
          System.out.println(de);

      }
        //Double d=Math.round(2.7d);
        //String aa=d.toString();

        //Integer dd= Integer.valueOf(aa);
//        System.out.println((new BigDecimal("1000.0").setScale(0, BigDecimal.ROUND_HALF_UP)));


    }
}

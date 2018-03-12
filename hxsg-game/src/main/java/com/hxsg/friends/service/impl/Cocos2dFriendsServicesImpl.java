package com.hxsg.friends.service.impl;

import com.hxsg.CommonUtil.listener.Cocos2dHttpSessionListener;
import com.hxsg.CommonUtil.util.StatusNum;
import com.hxsg.Dao.NewRoleMapper;
import com.hxsg.Dao.RoleFriendsMapper;
import com.hxsg.Dao.RoleFriendsMsgMapper;
import com.hxsg.Dao.SystemNotisMapper;
import com.hxsg.friends.service.Cocos2dFriendsServices;
import com.hxsg.po.NewRole;
import com.hxsg.po.Role;
import com.hxsg.po.RoleFriends;
import com.hxsg.po.RoleFriendsMsg;
import com.hxsg.system.dao.SystemNotification;
import com.hxsg.vo.FriendsVo;
import com.hxsg.vo.PlayDetail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2016/12/12.
 * 好友聊天系统
 */
@Service("Cocos2dFriendsServices")
public class Cocos2dFriendsServicesImpl implements Cocos2dFriendsServices {
    private Logger logger = Logger.getLogger(Cocos2dFriendsServicesImpl.class);

    @Autowired
    RoleFriendsMsgMapper rolefriendsmsgmapper;
    @Autowired
    SystemNotification systemnotification;
    @Autowired
    RoleFriendsMapper rolefriendsmapper;
    @Autowired
    NewRoleMapper newrolemapper;

    private Boolean init(RoleFriendsMsg msg) {
        Boolean result = false;
        try {
            if (msg.getRoleid() != null && msg.getFriendid() != null) {
                NewRole role = newrolemapper.selectByPrimaryKey(msg.getFriendid());
                if (role != null && role.getRolename().equals(msg.getFriendname())) {
                    RoleFriends rf = new RoleFriends();
                    rf.setRoleid(msg.getFriendid());
                    rf.setFriendid(msg.getRoleid());
                    List<RoleFriends> li = rolefriendsmapper.selectAll(rf);
                    if (li != null && li.size() > 0) {
                        if ("正常".equals(li.get(0).getStatus())) {
                            result = true;
                        }
                    } else {
                        //从未拉黑，加好友，禁言，直接返回true
                        result = true;
                    }
                }

            }
        } catch (Exception e) {
            logger.error("初始化被发送信息的玩家，是否被拉黑或者禁言");
        }
        return result;
    }

    @Override
    public RoleFriendsMsg sendMsgForFriends(RoleFriendsMsg msg) {
        try {
            if (init(msg)) {
//                systemnotification.sendSystemMsg(new Object[]{"200", msg}, msg.getFriendid().toString());
                msg.setType("私聊");
                msg.setData(new Date());
                //将信息插入到聊天记录表中
                rolefriendsmsgmapper.insertSelective(msg);
                //把信息推送到好友的聊天界面中
                systemnotification.sendSystemMsg(new Object[]{StatusNum.FRIENDSMSG203, msg}, msg.getFriendid().toString());
            }else{
                msg.setMessage("您被该玩家拉黑，无法对其发言");
                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, msg}, msg.getRoleid().toString());
                 return null;
            }
        } catch (Exception e) {
            logger.error("sendMsgForFriends给玩家发送信息失败:" + e.getMessage());
        }
        return msg;
    }

    @Override
    public String addFriends(RoleFriends rf) {
        String msg=StatusNum.SUCCES;
        try {
            if ( rf.getRoleid()!= null && rf.getFriendid() != null) {

                rf.setStatus(null);
                RoleFriendsMsg rg = new RoleFriendsMsg();
                if(rf.getRoleid().equals(rf.getFriendid())){
                    rg.setMessage("无法添加自己为好友");
                    systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rg}, rg.getFriendid().toString());
                }else{
                    NewRole role = newrolemapper.selectByPrimaryKey(rf.getFriendid());
                    if (role != null && role.getRolename().equals(rf.getFriendname())) {

                        List<RoleFriends> li = rolefriendsmapper.selectAll(rf);
                        if (li != null && li.size() > 0) {
                            RoleFriends r = li.get(0);
                            if ("好友".equals(r.getType())) {
                                rg.setMessage("你们已是好友,不要重复添加");
                                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rg}, rg.getRoleid().toString());
                                return msg;
                            } else {
                                r.setType("好友");
                                rg.setMessage("玩家【" + rf.getFriendname() + "】同意你添加好友");
                            }
                            rolefriendsmapper.updateByPrimaryKeySelective(r);
                        } else {

                            rf.setData(new Date());
                            rf.setType("好友");
                            rf.setStatus("正常");
                            rolefriendsmapper.insertSelective(rf);
                            rg.setMessage("玩家【" + rf.getFriendname() + "】同意你添加好友");
                        }
                        rg.setFriendid(rf.getRoleid());
                        systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rg}, rg.getFriendid().toString());
                    }
                }

            }
        } catch (Exception e) {
            msg=StatusNum.ERROR;
            logger.error("添加好友异常:" + e.getMessage());
        }
       return msg;
    }

    /**
     * 发送加好友请求
     * *******提示信息***********
     * 1.玩家【咕叽】请求加你为好友
     * 2.玩家【咕叽】拒绝你添加为好友
     * 上面的提示信息由客户端发送而来
     *
     * @param rf
     * @return
     */
    @Override
    public void sendAddFriendsMsg(RoleFriendsMsg rf) {
        try {
            if (rf.getRoleid() != null&&rf.getFriendid()!=null) {
                if(rf.getRoleid().equals(rf.getFriendid())){
                    rf.setMessage("无法添加自己为好友");
                    systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rf},rf.getRoleid().toString());

                }else{
                    RoleFriends rfs=new RoleFriends();
                    rfs.setRoleid(rf.getRoleid());
                    rfs.setFriendid(rf.getFriendid());
                    List<RoleFriends>liRfs=rolefriendsmapper.selectAll(rfs);
                    if(liRfs!=null&&liRfs.size()>0){
                        rf.setMessage("你们已是好友请不要重复添加");
                        systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rf},rf.getRoleid().toString());
                    }else{
                        if ("202".equals(rf.getStatus())) {
                            if("yes".equals(rf.getType())){
                                rf.setMessage("玩家【"+rf.getRolename()+"】请求添加你为好友");
                                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG201, rf}, rf.getFriendid().toString());
                            }
                            if("no".equals(rf.getType())){
                                rf.setMessage("玩家【"+rf.getRolename()+"】拒绝添加你为好友");
                                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rf}, rf.getFriendid().toString());
                            }
                        } else {
                            systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG201, rf}, rf.getFriendid().toString());
                        }
                    }
                }

            }
            rf.setMessage("请求发送成功");
            systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rf},rf.getRoleid().toString());
        } catch (Exception e) {
            logger.error("添加或者拒绝好友申请异常:" + e.getMessage());
            rf.setMessage("服务器异常");
            systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rf}, rf.getRoleid().toString());
        }
    }

    /**
     * 禁言，拉黑，正常
     *
     * @param rf
     * @return
     * @throws Exception
     */
    @Override
    public String speakStatus(RoleFriends rf) {
        String msg = StatusNum.SUCCES;
        String status = null;
        try {
            if (rf.getRoleid() != null && rf.getFriendid() != null) {
                status = rf.getStatus();
                rf.setStatus(null);
                rf.setType(null);
                List<RoleFriends> li  = rolefriendsmapper.selectAll(rf);
                if (li != null && li.size() > 0) {
                    RoleFriends r = li.get(0);
                    r.setStatus(status);
                    rolefriendsmapper.updateByPrimaryKeySelective(r);
                } else {
                    rf.setData(new Date());
                    rolefriendsmapper.insertSelective(rf);
                }
            }
        } catch (Exception e) {
            msg = StatusNum.ERROR;
            logger.error("禁言，拉黑异常:" + e.getMessage());
        }
        return msg;
    }

    @Override
    public List<FriendsVo> queryRoleFriends(RoleFriends rf) {
        List<FriendsVo> li = new ArrayList<FriendsVo>();
        int num = 0;
        try {
            switch (rf.getType()) {
                case "最近": {
                    List<RoleFriendsMsg> limsg = null;

                    limsg = rolefriendsmsgmapper.queryFriendsByTime(rf.getRoleid());

                    for (RoleFriendsMsg r : limsg) {
                        FriendsVo f = new FriendsVo();
                        f.setFriendId(r.getFriendid());
                        f.setFriendName(r.getFriendname());
                        if (Cocos2dHttpSessionListener.HTTPSESSIONMAP.containsKey(r.getFriendid().toString())) {
                            f.setStatus("在线");
                            ++num;
                        } else {
                            f.setStatus("离线");
                        }
                        li.add(f);
                    }
                    break;
                }
                case "亲人": {
                    List<RoleFriends> lifd = rolefriendsmapper.queryQinRen(rf.getRoleid());
                    for (RoleFriends r : lifd) {
                        FriendsVo f = new FriendsVo();
                        f.setFriendId(r.getFriendid());
                        f.setFriendName(r.getFriendname());
                        if (Cocos2dHttpSessionListener.HTTPSESSIONMAP.containsKey(r.getFriendid().toString())) {
                            f.setStatus("在线");
                            ++num;
                        } else {
                            f.setStatus("离线");
                        }
                        li.add(f);
                    }
                    break;
                }
                default: {
                    List<RoleFriends> lifd = rolefriendsmapper.selectAll(rf);
                    for (RoleFriends r : lifd) {
                        FriendsVo f = new FriendsVo();
                        f.setFriendId(r.getFriendid());
                        f.setFriendName(r.getFriendname());
                        if (Cocos2dHttpSessionListener.HTTPSESSIONMAP.containsKey(r.getFriendid().toString())) {
                            f.setStatus("在线");
                            ++num;
                        } else {
                            f.setStatus("离线");
                        }
                        li.add(f);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }

    @Override
    public String relieveStatus(RoleFriends rf) {
        String msg = StatusNum.SUCCES;
        try {
            if (rf.getRoleid() != null && rf.getFriendid() != null) {
                List<RoleFriends> li = rolefriendsmapper.selectAll(rf);
                if (li != null && li.size() > 0) {
                    RoleFriends r = li.get(0);
                    r.setType("断交");
                    rolefriendsmapper.updateByPrimaryKeySelective(r);
                }
                //将断交信息推送给在线玩家
                RoleFriendsMsg rg = new RoleFriendsMsg();
                rg.setMessage("玩家【" + rf.getRolename() + "】与你割袍断交,江湖路远从此互不相认");
                rg.setFriendid(rf.getFriendid());
                systemnotification.sendSystemMsg(new Object[]{StatusNum.SYSTEMMSG202, rg}, rg.getFriendid().toString());
            }
        } catch (Exception e) {
            msg = StatusNum.ERROR;
            logger.error("relieveStatus割袍断交异常:" + e.getMessage());
        }
        return msg;
    }

    @Override
    public List<RoleFriendsMsg> loadFrinedsMsg(RoleFriendsMsg msg) {
        List<RoleFriendsMsg> li = null;
        try {
            if (msg.getRoleid() != null && msg.getFriendid() != null) {
                li = rolefriendsmsgmapper.queryChatRecord(msg.getRoleid(), msg.getFriendid());
            }
        } catch (Exception e) {
            logger.error("loadFrinedsMsg查询聊天记录异常:" + e.getMessage());
        }
        return li;
    }

    @Override
    public List<NewRole> queryFriends(String msg,String type) throws Exception {
        List<NewRole> li=new ArrayList<NewRole>();
        if("ID".equals(type)){
            NewRole fd=newrolemapper.selectByPrimaryKey(Integer.parseInt(msg));
            li.add(fd);
        }else{
            if(!StringUtils.isEmpty(msg)){
                msg="%"+msg+"%";
                li=newrolemapper.queryFriends(msg);
            }

        }
        return li;
    }
}

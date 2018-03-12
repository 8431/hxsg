package com.hxsg.index.service.impl;

import com.hxsg.CommonUtil.listener.Cocos2dHttpSessionListener;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.login.Login;
import com.hxsg.Dao.*;
import com.hxsg.Interceptor.HxsgUrlInterceptor;
import com.hxsg.index.service.cocos2dIndexService;
import com.hxsg.po.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.*;

/**
 * Created by dlf on 2016/9/29.
 */
@Service("cocos2dIndexService")
public class cocos2dIndexServiceImpl implements cocos2dIndexService {
    private Logger logger =Logger.getLogger(cocos2dIndexServiceImpl.class);
   // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    @Autowired
    NewRoleMapper rolemapper;
    @Autowired
    GjsgMapMapper gjsgmapmapper;
    @Autowired
    MapGuaiMapper mapguaimapper;
//    @Autowired
//    JiaoPaiMapper jiaopaimapper;
    @Autowired
    WorldMsgMapper worldmsgmapper;
    @Autowired
    roleMessageMapper rolemessagemapper;
    @Autowired
    YeGuaiQunMapper yeguaiqunmapper;
    @Autowired
    RoleFriendsMapper rolefriendsmapper;

    @Override
    public NewRole queryRoleMsg(Integer  id) {
        NewRole role=null;
        try {
            if(id!=null){
                role=rolemapper.queryTotalShuXingToRole(id);
                if (Cocos2dHttpSessionListener.HTTPSESSIONMAP.containsKey(role.getId().toString())) {
                    role.setRoleStatus("在线");
                }else{
                    role.setRoleStatus("离线");
                }
                RoleFriends rf=new RoleFriends();

                rolefriendsmapper.selectAll(rf);
            }

        } catch (Exception e) {
            logger.error("cocos2dIndexService，queryRoleMsg获取角色详情失败"+e.getMessage());
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public NewRole queryRoleById(Integer id) throws Exception {
        return rolemapper.selectByPrimaryKey(id);
    }

    @Override
    public NewRole queryRoleMsgQx(Integer id) throws Exception {

        return rolemapper.queryRoleMsgQx(id);
    }

    @Override
    public NewRole queryRoleMsg(Integer  id,Integer friendId) {
        NewRole role=null;
        try {
            if(friendId!=null&&id!=null){
                role=rolemapper.queryTotalShuXingToRole(friendId);
                role.setHeiStatus("拉黑");
                role.setJinStatus("禁言");
                if (Cocos2dHttpSessionListener.HTTPSESSIONMAP.containsKey(role.getId().toString())) {
                    role.setRoleStatus("在线");
                }else{
                    role.setRoleStatus("离线");
                }
                RoleFriends rf=new RoleFriends();
                rf.setRoleid(id);
                rf.setFriendid(friendId);
                List<RoleFriends> liRf=rolefriendsmapper.selectAll(rf);
                if(liRf!=null&&liRf.size()>0){
                    if("拉黑".equals(liRf.get(0).getStatus())){
                        role.setHeiStatus("解黑");
                    }
                    if("禁言".equals(liRf.get(0).getStatus())){
                        role.setHeiStatus("解禁");
                    }
                }
            }

        } catch (Exception e) {
            logger.error("cocos2dIndexService，queryRoleMsg获取角色详情失败"+e.getMessage());
            e.printStackTrace();
        }
        return role;
    }
    @Override
    public Object[] chatMsg(){
        Object[] ot=null;
        try {
            List<WorldMsg> worldli=worldmsgmapper.queryWorldMsg();//世界
            List<roleMessage> quli=rolemessagemapper.getMsgType("1");//区
            List<roleMessage> paili=rolemessagemapper.getMsgType("3");//商
           // List<roleMessage> paili=rolemessagemapper.getMsgType("2");//派
            ot=new Object[]{new Object[]{4,worldli},new Object[]{3,paili},new Object[]{1,quli},new Object[]{2,new ArrayList<roleMessage>()}};
        } catch (Exception e) {
            logger.error("cocos2dIndexService，加载主界面聊天信息失败："+e.getMessage());
            e.printStackTrace();
        }
        return ot;
    }

    @Override
    public GjsgMap moveCity(String city,HttpSession session) throws Exception {
        GjsgMap gp=null;
        if(!StringUtils.isEmpty(city)){
            Integer roleId= (Integer) session.getAttribute("roleId");
            NewRole role=rolemapper.selectByPrimaryKey(roleId);
            //查询地图坐标
            gp=gjsgmapmapper.selectByCenterCity(city);
            String c=gp.getCenterCity();
            role.setZuobiao(c);
            //更新人物坐标
            rolemapper.updateByPrimaryKeySelective(role);
            //通知更新附近玩家

        }
        return gp;
    }

    @Override
    public MapGuai queryMapGuai(String city) throws RuntimeException {
        MapGuai mg=null;
        if(!StringUtils.isEmpty(city)){
            mg=mapguaimapper.selectByCity(city);
        }
        return mg;
    }

    @Override
    public YeGuaiQun queryYeGuaiQun(String name) throws RuntimeException {
        YeGuaiQun yn=null;
        if(!StringUtils.isEmpty(name)){
            yn=yeguaiqunmapper.selectByName(name);
        }
        return yn;
    }

    @Override
    public  List<NewRole>  nearbyRole(String city,String key) throws Exception {
        List<NewRole> liRole=null;
        if(!StringUtils.isEmpty(city)){
            NewRole r=new NewRole();
            r.setZuobiao(city);
            liRole=rolemapper.selectAll(r);
        }
        if(liRole!=null&&liRole.size()>0){
            Iterator<NewRole>  it=liRole.iterator();
            while(it.hasNext()){
                System.out.println(liRole.size());
                NewRole role=it.next();
                Integer roleId=role.getId();
                Login ln= new Login();
                ln.setRoleId(roleId);
                if(!Constants.loginMap.containsValue(ln)){
                    it.remove();
                }

            }
        }
        return liRole;
    }
}

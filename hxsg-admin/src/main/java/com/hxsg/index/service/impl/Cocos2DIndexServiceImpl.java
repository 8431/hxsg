package com.hxsg.index.service.impl;

import com.google.gson.Gson;
import com.hxsg.CommonUtil.listener.Cocos2dHttpSessionListener;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.login.Login;
import com.hxsg.Dao.*;
import com.hxsg.index.RoleMsgUtil;
import com.hxsg.index.service.Cocos2dIndexService;
import com.hxsg.po.*;
import com.hxsg.redis.RedisDaoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by dlf on 2016/9/29.
 */
@Service("cocos2dIndexService")
public class Cocos2DIndexServiceImpl implements Cocos2dIndexService {
    private Logger logger = Logger.getLogger(Cocos2DIndexServiceImpl.class);
    // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    @Autowired
    RedisDaoService redisdaoservice;
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
    RoleMessageMapper rolemessagemapper;
    @Autowired
    YeGuaiQunMapper yeguaiqunmapper;
    @Autowired
    RoleFriendsMapper rolefriendsmapper;
    @Autowired
    RoleNewShuXingMapper rolenewshuxingmapper;
    @Autowired
    RoleFujiangMapper rolefujiangmapper;
    @Autowired
    HxsgBaseDaoMapper hxsgbasedaomapper;

    @Override
    public NewRole queryRoleMsg(Integer id) {
        NewRole role = null;
        try {
            if (id != null) {
                role = rolemapper.queryTotalShuXingToRole(id);
                if (Cocos2dHttpSessionListener.HTTPSESSIONMAP.containsKey(role.getId().toString())) {
                    role.setRoleStatus("在线");
                } else {
                    role.setRoleStatus("离线");
                }
                String sql="select fujiangname,id from role_fujiang where roleid=#{id} and shuxing='1' and status='休息'";
                Map<String,Object> param=new HashMap<>();
                param.put("id",id);
                param.put("sql",sql);
                List<Map<String,Object>> reFjLi=hxsgbasedaomapper.QuerySql(param);
                Gson gn=new Gson();
                List<RoleFujiang> rfLi= gn.fromJson(gn.toJson(reFjLi),List.class);
                role.setFuLi(rfLi);

            }

        } catch (Exception e) {
            logger.error("Cocos2dIndexService，queryRoleMsg获取角色详情失败" + e.getMessage());
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
        NewRole role = null;
        Object ob = redisdaoservice.get(RoleMsgUtil.ROLEMSG + id);
        if (ob == null) {
            role = rolemapper.queryTotalShuXingToRole(id);
            //查询角色携带副将详情
            if (role != null) {
                RoleFujiang rf = new RoleFujiang();
                rf.setStatus("休息");
                rf.setShuxing(1);
                rf.setRoleid(id);
                List<RoleFujiang> rfLi = rolefujiangmapper.selectAll(rf);
                if (rfLi != null && rfLi.size() > 0) {
                    for (RoleFujiang f : rfLi) {
                        List<RoleNewShuXing> kxLi = rolenewshuxingmapper.queryRoleShuXing(f.getId(), 1);
                        f.setShuXingLi(kxLi);
                    }
                }
                role.setFuLi(rfLi);
            }
            redisdaoservice.set(RoleMsgUtil.ROLEMSG + id, role, null);
        } else {
            role = (NewRole) ob;
        }
        return role;
    }

    @Override
    public NewRole queryRoleMsg(Integer id, Integer friendId) {
        NewRole role = null;
        try {
            if (friendId != null && id != null) {
                role = queryRoleMsgQx(friendId);
                role.setHeiStatus("拉黑");
                role.setJinStatus("禁言");
                if (Cocos2dHttpSessionListener.HTTPSESSIONMAP.containsKey(role.getId().toString())) {
                    role.setRoleStatus("在线");
                } else {
                    role.setRoleStatus("离线");
                }
                RoleFriends rf = new RoleFriends();
                rf.setRoleid(id);
                rf.setFriendid(friendId);
                List<RoleFriends> liRf = rolefriendsmapper.selectAll(rf);
                if (liRf != null && liRf.size() > 0) {
                    if ("拉黑".equals(liRf.get(0).getStatus())) {
                        role.setHeiStatus("解黑");
                    }
                    if ("禁言".equals(liRf.get(0).getStatus())) {
                        role.setHeiStatus("解禁");
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Cocos2dIndexService，queryRoleMsg获取角色详情失败" + e.getMessage());
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Object[] chatMsg() {
        Object[] ot = null;
        try {
            //test
            long t3= new Date().getTime();
            List<WorldMsg> worldli = worldmsgmapper.queryWorldMsg();//世界
            long t4= new Date().getTime();
            logger.debug("chatMsg:"+(t4-t3));
            List<roleMessage> quli = (List<roleMessage>) redisdaoservice.lrange("hxsg:chatMsg:1", 0L, 19L);
            List<roleMessage> paili = (List<roleMessage>) redisdaoservice.lrange("hxsg:chatMsg:3", 0L, 19L);
            long t5= new Date().getTime();
            logger.debug("chatMsg:"+(t5-t4));

            ot = new Object[]{new Object[]{4, null}, new Object[]{3, paili}, new Object[]{1, quli}, new Object[]{2, new ArrayList<roleMessage>()}};
        } catch (Exception e) {
            logger.error("Cocos2dIndexService，加载主界面聊天信息失败：" + e.getMessage());
            e.printStackTrace();
        }
        return ot;
    }

    @Override
    public GjsgMap moveCity(String city, HttpSession session) throws Exception {
        GjsgMap gp = null;
        if (!StringUtils.isEmpty(city)) {
            Integer roleId = (Integer) session.getAttribute("roleId");
            NewRole role = rolemapper.selectByPrimaryKey(roleId);
            //查询地图坐标
            gp = gjsgmapmapper.selectByCenterCity(city);
            String c = gp.getCenterCity();
            role.setZuobiao(c);
            //更新人物坐标
            rolemapper.updateByPrimaryKeySelective(role);
            //通知更新附近玩家

        }
        return gp;
    }

    @Override
    public MapGuai queryMapGuai(String city) throws RuntimeException {
        MapGuai mg = null;
        if (!StringUtils.isEmpty(city)) {
            mg = mapguaimapper.selectByCity(city);
        }
        return mg;
    }

    @Override
    public YeGuaiQun queryYeGuaiQun(String name) throws RuntimeException {
        YeGuaiQun yn = null;
        if (!StringUtils.isEmpty(name)) {
            yn = yeguaiqunmapper.selectByName(name);
        }
        return yn;
    }

    @Override
    public List<NewRole> nearbyRole(String city, String key) throws Exception {
        List<NewRole> liRole = null;
        if (!StringUtils.isEmpty(city)) {
            NewRole r = new NewRole();
            r.setZuobiao(city);
            liRole = rolemapper.selectAll(r);
        }
        if (liRole != null && liRole.size() > 0) {
            Iterator<NewRole> it = liRole.iterator();
            while (it.hasNext()) {
                //System.out.println(liRole.size());
                NewRole role = it.next();
                Integer roleId = role.getId();
                Login ln = new Login();
                ln.setRoleId(roleId);
//                if (!Constants.loginMap.containsValue(ln)) {
//                    it.remove();
//                }

            }
        }
        return liRole;
    }
}

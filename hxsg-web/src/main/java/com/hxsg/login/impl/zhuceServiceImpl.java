package com.hxsg.login.impl;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.util.MapUtil;
import com.hxsg.Dao.RoleMapper;
import com.hxsg.login.websoket.loginWebScoketController;
import com.hxsg.login.zhuceService;
import com.hxsg.po.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2015/12/31.
 */
@Service("zhuceService")
public class zhuceServiceImpl implements zhuceService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    Map<String,Object> mp= Constants.SESSION_NAME;
    @Autowired
    RoleMapper rolemapper;
    @Override
    public boolean getZhuCe(Role re) {
        boolean result=false;
        int a=rolemapper.insertSelective(re);
        if(a>0){
            result=true;
        }
        return result;
    }

    @Override
    public boolean checkRole(Role re) {
        boolean result=true;
        try{
            List<Role> liRole=rolemapper.selectAll(re);
            if(liRole.size()>0){
                result=false;
            }
        }catch (Exception e){
            result=false;
           logger.error("service层--验证角色名或者账号名是否重复异常checkRole："+e.getMessage());
        }
        return  result;
    }

    @Override
    public Boolean creatRole(Role re) throws Exception {
        return null;
    }


    @Override
    public Boolean appCreatrole(Role re,WebSocketSession session) throws Exception {
        Boolean result=false;
        try{
            String id=MapUtil.getRoleId(mp,session);
            Integer roleid = Integer.parseInt(id);//获取用户IDsession
            Role role = rolemapper.selectByPrimaryKey(roleid);
            if (roleid != null && role.getDengji() == null) {
                Role rolelist = new Role();
                rolelist.setId(roleid);
                rolelist.setZhiye(re.getZhiye());// 职业
                rolelist.setDengji(1);// 等级
                rolelist.setJin(8888);// 金
                rolelist.setYin(10000);// 银
                rolelist.setJuesename(re.getJuesename());// 角色名
                rolelist.setJuzhudi("许昌");
                rolelist.setSex(re.getSex());// 性别
                rolelist.setTouxiang(re.getTouxiang());// 头像
                //根据职业设置
                rolelist.setQixue1(120);
                rolelist.setQixue2(120);
                rolelist.setJingli1(100);
                rolelist.setJingli2(100);
                rolelist.setJingli1(100);
                rolelist.setGongji(130);
                rolelist.setSudu(20);
                rolelist.setFangyu(100);
                int a = rolemapper.updateByPrimaryKeySelective(rolelist);
                if (a > 0) {
                    result=true;
                }
            }
        }catch (Exception e){
            logger.error("创建角色异常"+e.getMessage());
        }
       return result;
        }
    }

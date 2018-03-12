package com.hxsg.wupin.service.impl;

import com.hxsg.Dao.NewRoleMapper;
import com.hxsg.Dao.RoleMapper;
import com.hxsg.Dao.RoleNewZhuangBeiMapper;
import com.hxsg.Dao.RoleZhuangbeiMsMapper;
import com.hxsg.po.NewRole;
import com.hxsg.po.Role;
import com.hxsg.po.RoleNewZhuangBei;
import com.hxsg.po.RoleZhuangbeiMs;
import com.hxsg.wupin.service.Cocos2dZhuangBeiToRole;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dlf on 2016/10/24.
 */
@Service("Cocos2dZhuangBeiToRole")
public class Cocos2dZhuangBeiToRoleImpl implements Cocos2dZhuangBeiToRole {
    private Logger logger = Logger.getLogger(Cocos2dZhuangBeiToRoleImpl.class);
    @Autowired
    RoleNewZhuangBeiMapper rolenewzhuangbeimapper;
    @Autowired
    RoleZhuangbeiMsMapper rolezhuangbeimsmapper;
    @Autowired
    NewRoleMapper newrolemapper;

    /**
     * * 将装备给人物装备上去
     *战斗模式下不能换装------（未加入）
     * @param id 道具Id
     * @param roleid 副将或者角色ID
     * @param type 副将---角色
     * @param re  true战斗中fasle未战斗
     * @return
     */
    @Override
    public String zhuangBeiToRole(Integer roleid,Integer id,String type,Boolean re) {
        String msg="";
        if(re){
            try {
            RoleZhuangbeiMs rb = rolezhuangbeimsmapper.selectByPrimaryKey(id);
            if (rb.getZb()==0) {
                //该角色还未装备任何装备,验证装备是否能被装备
                //---副将可以佩戴三件相同装备，人物可以佩戴5件不同类型装备
                if(type.equals("role")){
                    NewRole   role = newrolemapper.selectByPrimaryKey(roleid);
                    //1.等级限制
                    Integer roleLevel=rb.getRolelevel();
                    //2.点数限制
                    Integer powerNum=rb.getPowernum();
                    Boolean key=false;
                    if(powerNum.equals(0)){
                        key=true;
                    }

                    if(rb.getWuqitype().contains("力")&&powerNum>0&&role.getGongjids()>=powerNum){
                        key=true;

                    }
                    if(rb.getWuqitype().contains("血")&&powerNum>0&&role.getQixueds()>=powerNum){
                        key=true;
                    }
                    if(rb.getWuqitype().contains("速")&&powerNum>0&&role.getSududs()>=powerNum){
                        key=true;
                    }
                    if(role.getLevel()>roleLevel&&key){
                        //查询角色是否装备了同种类型装备，是的话就替换
                        List<RoleZhuangbeiMs> lizb=rolezhuangbeimsmapper.queryRoleZhuangBei(roleid, 1);
                        if(lizb.size()>0&&lizb!=null){
                            String wuqiType=rb.getWuqitype();
                            for(RoleZhuangbeiMs r:lizb){
                                RoleNewZhuangBei zb=new RoleNewZhuangBei();
                                int a=0;
                                if(wuqiType.substring(1,2).equals(r.getWuqitype().substring(1,2))){
                                    //替换
                                    zb.setZb(0);
                                    zb.setId(r.getId());
                                    zb.setType("0");
                                     a=rolenewzhuangbeimapper.updateByPrimaryKeySelective(zb);
                                }
                                zb.setZb(1);
                                zb.setType("1");
                                zb.setId(id);
                                int b=rolenewzhuangbeimapper.updateByPrimaryKeySelective(zb);
                                if(a+b>0){
                                    msg="装备成功!";
                                }else{
                                    msg="服务器异常，请联系管理员";
                                    logger.error("装备ID"+id+"异常");
                                }
                            }
                        }else{
                            RoleNewZhuangBei rbn=rolenewzhuangbeimapper.selectByPrimaryKey(id);
                            rbn.setZb(1);
                            rbn.setType("1");//1代表角色2副将
                            int a=rolenewzhuangbeimapper.updateByPrimaryKey(rbn);
                            if(a>0){
                                msg="装备成功!";
                            }else{
                                msg="服务器异常，请联系管理员";
                                logger.error("装备ID"+id+"异常");
                            }
                        }

                    }else{
                        msg="等级或者点数不满足条件，无法佩戴";
                    }

                }else{
                    //副将装备道具
                    //查询角色是否装备了同种类型装备，是的话就替换
                    List<RoleZhuangbeiMs> lizb=rolezhuangbeimsmapper.queryRoleZhuangBei(roleid, 1);
                    if(lizb.size()>0&&lizb!=null){
                        RoleNewZhuangBei zb=new RoleNewZhuangBei();
                        if(lizb.size()<3){
                            String wuqiType=rb.getWuqitype();
                                zb.setZb(1);
                                zb.setType("1");
                                zb.setId(id);
                                int b=rolenewzhuangbeimapper.updateByPrimaryKeySelective(zb);
                                if(b>1){
                                    msg="装备成功!";
                                }else{
                                    msg="服务器异常，请联系管理员";
                                    logger.error("装备ID"+id+"异常");
                                }

                        }else{
                            zb.setId(lizb.get(0).getId());
                            zb.setType("0");
                            zb.setZb(0);
                            int a=rolenewzhuangbeimapper.updateByPrimaryKeySelective(zb);
                            zb.setZb(1);
                            zb.setType("1");
                            zb.setId(id);
                            int b=rolenewzhuangbeimapper.updateByPrimaryKeySelective(zb);
                            if(a+b>0){
                                msg="装备成功!";
                            }else{
                                msg="服务器异常，请联系管理员";
                                logger.error("装备ID"+id+"异常");
                            }
                        }


                    }else{
                        RoleNewZhuangBei rbn=rolenewzhuangbeimapper.selectByPrimaryKey(id);
                        rbn.setZb(1);
                        rbn.setType("2");//1代表角色2副将
                        int a=rolenewzhuangbeimapper.updateByPrimaryKey(rbn);
                        if(a>0){
                            msg="装备成功!";
                        }else{
                            msg="服务器异常，请联系管理员";
                            logger.error("装备ID"+id+"异常");
                        }
                    }

                }
        }else{
            msg="你处于战斗模式，无法换装备!";
        }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return msg;
    }
}
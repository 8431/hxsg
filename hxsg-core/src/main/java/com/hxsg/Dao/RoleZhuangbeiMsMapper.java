package com.hxsg.Dao;

import com.hxsg.po.BaoShiTotalKx;
import com.hxsg.po.RoleNewZhuangBei;
import com.hxsg.po.RoleZhuangbeiMs;

import java.util.List;

public interface RoleZhuangbeiMsMapper {
    int insert(RoleZhuangbeiMs record);

    int insertSelective(RoleZhuangbeiMs record);
    List<RoleZhuangbeiMs> selectAll(RoleZhuangbeiMs record);
    RoleZhuangbeiMs selectByPrimaryKey(Integer id);

    /**
     * 查询角色拥有的装备
     * @param roleid
     * @return
     */
    List<RoleZhuangbeiMs> queryRoleZhuangbeiMsByRoleid(Integer roleid);

    /*select * from hxsg.role_zhuangbei_ms re where  id not in
    (SELECT id FROM hxsg.role_zhuangbei_ms r where roleid=1000 and r.baoShi1 is not null and  r.baoShi2 is not  null and  r.baoShi3 is  not null);
     * 查询可以被宝石镶嵌的装备
     * @param roleid
     * @return
     */
    List<RoleZhuangbeiMs> queryRoleZhuangbeiForBaoShi(Integer roleid);
    /** /**查询装备镶嵌的宝石的总抗性（抗封24+抗物理+12）
     * SELECT kangXing,count(kangXing) num,sum(xiaoGuo)totalKx  FROM hxsg.baoshi b where id in(312,320,321) group by kangXing;
     * @param bsid1
     * @param bsid2
     * @param bsid3
     * @return
     */
    List<BaoShiTotalKx> queryZhuangBeiKx(Integer bsid1,Integer bsid2,Integer bsid3);

    /**
     * 查询角色装备的武器
     * @param roleid
     * @return
     */
    List<RoleZhuangbeiMs> queryRoleZhuangBei(Integer roleid,Integer zb);
}
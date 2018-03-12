package com.hxsg.pk.websoket.impl;

import com.hxsg.Dao.*;
import com.hxsg.po.NewRole;
import com.hxsg.po.RoleNewShuXing;
import com.hxsg.po.Skill;
import com.hxsg.redis.RedisDaoService;
import com.hxsg.vo.AttackA;
import com.hxsg.vo.PkPlayData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hxsg.util.AttackUtil.skillAttack;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-redis.xml"})

public class LoadGuaiServiceImplTest {
    @Autowired
    RedisDaoService redisdaoservice;
    @Autowired
    NewRoleMapper rolemapper;
    @Autowired
    GjsgMapMapper gjsgmapmapper;
    @Autowired
    MapGuaiMapper mapguaimapper;
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
    SkillMapper skillmapper;
    @Test
    public void pkTest() throws Exception {

        PkPlayData B=new PkPlayData();
        PkPlayData A=new PkPlayData();
        NewRole role= (NewRole) redisdaoservice.get("role:msg:1000");
        BeanUtils.copyProperties(role,B);
        BeanUtils.copyProperties(role,A);
        Skill sk=new Skill();
        sk.setRfid(B.getId());
        sk.setType(0);
        List<Skill> skLi=skillmapper.selectAll(sk);
        B.setSkill(skLi);
        A.setSkill(skLi);
        //查询抗性
        List<RoleNewShuXing> roleLi=rolenewshuxingmapper.queryRoleShuXing(10844,1);
        Map<String, Integer> sxMap = getSxMapByList(roleLi);
        B.setRoleMap(sxMap);
        A.setRoleMap(sxMap);

//        Map<String, Integer> sxMap_A=new HashMap<>();
//        Map<String, Integer> sxMap_B=new HashMap<>();
//        sxMap_A.put("命中率",100);
//        sxMap_A.put("暴击率",100);
//        sxMap_A.put("致命率",0);
//        sxMap_A.put("反震率",15);
//        sxMap_A.put("躲避率",15);
//        sxMap_A.put("抗物理",15);
//        sxMap_A.put("反击率",15);
//        sxMap_A.put("法术暴",15);
//
//        sxMap_B.put("命中率",85);
//        sxMap_B.put("暴击率",15);
//        sxMap_B.put("致命率",100);
//        sxMap_B.put("反震率",10);
//        sxMap_B.put("躲避率",15);
//        sxMap_B.put("抗物理",20);
//        sxMap_B.put("反击率",100);
//        sxMap_B.put("抗风沙",10);
//        sxMap_B.put("抗毒术",10);
//        A.setRoleMap(sxMap_A);
//        B.setRoleMap(sxMap_B);

        List<PkPlayData> bli=new ArrayList<>();
//        bli.add(B);
//        AttackA atta=skillAttack(A,bli,"舍命一击");
//        atta.toString();
    }

    public static Map<String, Integer> getSxMapByList(List<RoleNewShuXing> roleLi) throws Exception{
        Map<String, Integer> sxMap=new HashMap<String, Integer>();
        for(RoleNewShuXing r:roleLi){
            try {
                sxMap.put(r.getKangxing(),r.getKangxingtotal());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sxMap;
    }

    @Test
    public void queryGuaiData() throws Exception {

    }

}
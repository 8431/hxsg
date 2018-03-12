package com.hxsg.util;

import com.hxsg.Dao.RoleYaoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-*.xml"})
public class AttackUtilTest {
    @Autowired
     RoleYaoMapper roleyaomapper;
    @Test
    public void skillAttack() throws Exception {
       Map<String,Object> mp= roleyaomapper.selectByYaoNameRoleId("1000","九转丹");
    }

}
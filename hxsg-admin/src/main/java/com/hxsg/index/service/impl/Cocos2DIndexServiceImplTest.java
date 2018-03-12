package com.hxsg.index.service.impl;

import com.hxsg.index.service.Cocos2dIndexService;
import com.hxsg.po.NewRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-redis.xml"})

public class Cocos2DIndexServiceImplTest {
    @Autowired
    Cocos2dIndexService cocos2dindexservice;
    @Test
    public void queryRoleMsgQx() throws Exception {
        NewRole role=cocos2dindexservice.queryRoleMsgQx(1000);
    }

}
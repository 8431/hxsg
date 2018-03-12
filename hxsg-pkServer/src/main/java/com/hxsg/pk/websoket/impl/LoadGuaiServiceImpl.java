package com.hxsg.pk.websoket.impl;

import com.hxsg.Dao.FuJiangMapper;
import com.hxsg.Dao.YeGuaiQunMapper;
import com.hxsg.pk.websoket.LoadGuaiService;
import com.hxsg.po.FuJiang;
import com.hxsg.po.YeGuaiQun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Service("LoadGuaiService")
public class LoadGuaiServiceImpl implements LoadGuaiService {

    @Autowired
    YeGuaiQunMapper  yeguaiqunmapper;
    @Autowired
    FuJiangMapper fujiangmapper;
    @Override
    public List<FuJiang> queryGuaiData(String name) throws Exception{
        YeGuaiQun  ygq=yeguaiqunmapper.selectByName(name);
        List<FuJiang> li=fujiangmapper.queryFuJiangByName(ygq.getGuai1(),ygq.getGuai2(),ygq.getGuai3(),ygq.getGuai4(),ygq.getGuai5(),ygq.getGuai6());
        return li;
    }

    @Override
    public void pkTest() throws Exception {

    }
}

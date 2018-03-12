package com.hxsg.pk.websoket;

import com.hxsg.po.FuJiang;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public interface LoadGuaiService {
    List<FuJiang> queryGuaiData(String name) throws Exception;
    void pkTest() throws Exception;
}

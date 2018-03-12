package com.hxsg.pk.websoket.impl;

import com.hxsg.Dao.FuJiangMapper;
import com.hxsg.Dao.YeGuaiQunMapper;
import com.hxsg.pk.websoket.PkServerInitData;
import com.hxsg.pk.websoket.WebSocketPkServer;
import com.hxsg.po.FuJiang;
import com.hxsg.po.RoleFujiang;
import com.hxsg.po.YeGuaiQun;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
@Service("PkServerInitData")
public class PkServerInitDataImpl implements PkServerInitData {
    private static Log logger = LogFactory.getLog(PkServerInitDataImpl.class);

    @Autowired
    FuJiangMapper fujiangmapper;

    @Override
    public RoleFujiang CreateRoleFujiang(String name,Integer level1,Integer level2,Integer roleId) throws Exception{
        RoleFujiang rf=null;
        if(!StringUtils.isEmpty(name)){
           FuJiang fg=new FuJiang();
            fg.setFujiangname(name);
            List<FuJiang> fjLi=fujiangmapper.selectAll(fg);
            if(fjLi!=null&&fjLi.size()==1){
                rf=WebSocketPkServer.CreateRoleFujiang(fjLi.get(0),level1,level2,true,roleId);
            }else{
                logger.info("CreateRoleFujiang传输数据有误");
            }
        }
        return rf;
    }
}

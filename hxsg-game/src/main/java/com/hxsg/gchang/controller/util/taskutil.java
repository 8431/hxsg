package com.hxsg.gchang.controller.util;


import com.hxsg.Dao.YlDaXiaoMapper;
import com.hxsg.gchang.controller.GcController;
import com.hxsg.po.YlDaXiao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2016/1/20.
 */
@Service
public class taskutil {

    public  void tasktime(){

    if(GcController.times>0){
        GcController.times-=1000;
    }




    }
}

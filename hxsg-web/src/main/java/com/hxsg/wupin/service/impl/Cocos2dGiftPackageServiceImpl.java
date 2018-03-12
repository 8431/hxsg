package com.hxsg.wupin.service.impl;

import com.hxsg.Dao.RoleWuPinMapper;
import com.hxsg.Dao.TaskDetailMapper;
import com.hxsg.po.RoleWuPin;
import com.hxsg.po.TaskDetail;
import com.hxsg.wupin.service.Cocos2dGiftPackageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by dlf on 2017/1/7.
 */
@Service("Cocos2dGiftPackageService")
public class Cocos2dGiftPackageServiceImpl implements Cocos2dGiftPackageService {
    private Logger logger =Logger.getLogger(Cocos2dGiftPackageServiceImpl.class);
    @Autowired
    RoleWuPinMapper rolewupinmapper;
    @Autowired
    TaskDetailMapper taskdetailmapper;
    @Override
    public String giftPackage(Integer roleId,String name)  {
        String msg=null;
        try {

        if(roleId!=null&&!StringUtils.isEmpty(name)) {
            TaskDetail tk=taskdetailmapper.selectByPrimaryKey(1);
            if("激活".equals(tk.getType())){
                TaskDetail t = new TaskDetail();
                t.setRoleid(roleId);
                t.setGuainame(name);
                List<TaskDetail> lit= taskdetailmapper.selectAll(t);
                boolean key=true;
                if(lit!=null&&lit.size()>0){
                    TaskDetail tdl= lit.get(0);
                    int d1=new Date().getDay();
                    int d2= tdl.getData().getDay();
                    if(d1==d2){
                        key=false;
                    }
                }else{
                    t.setData(new Date());
                    t.setStatus("已领取");
                    t.setType("激活");
                    taskdetailmapper.insertSelective(t);
                }
                if(key){

                    RoleWuPin rw=new RoleWuPin();
                    rw.setName("藏宝图");
                    rw.setRoleid(roleId);
                    List<RoleWuPin> li=rolewupinmapper.selectAll(rw);
                    if(li!=null&&li.size()>0){
                        int num=li.get(0).getNum()+10;
                        li.get(0).setNum(num);
                        rolewupinmapper.updateByPrimaryKeySelective( li.get(0));
                    }else{
                        rw.setWupinid(34);
                        rw.setNum(10);
                        rw.setType1("图");
                        rw.setType2("杂物");
                        rolewupinmapper.insertSelective(rw);
                    }


                    msg="领取成功，请前往物品栏查看";
                }else{
                    msg="你已领取过了，请明天再来！";
                }

            }else{
                msg="活动已过期，请下次再来";
            }
        }
            } catch (Exception e) {
            msg="领取失败，服务器异常";
            }
        return msg;
    }
}

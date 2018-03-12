package com.hxsg.controller;

import com.google.gson.Gson;
import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.SimpleCode;
import com.hxsg.CommonUtil.SimpleResult;
import com.hxsg.dao.HandlePkService;
import com.hxsg.dao.PkVoService;
import com.hxsg.dao.impl.HandlePkServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
@RestController
@RequestMapping("Cocos2dAddPk")
public class AddPkController {
    private Logger logger = Logger.getLogger(AddPkController.class);
    @Autowired
    PkVoService pkvoservice;
    @Autowired
    Gson gsonBean;
    @Autowired
    HandlePkService handlepkservice;

    //    @RequestMapping("/attackData")
//    public String attackData(@RequestParam(value="jsonData",required = true) String jsonData) {
//        String re="0000";
//        Gson gn=new Gson();
//        try {
//            Map<String,Object> dataMap=gn.fromJson(jsonData,Map.class);
//            String uuid = (String) dataMap.get("uuid");
//            List<Map<String,Object>> jsonDataMp = (List<Map<String, Object>>) dataMap.get("jsonData");
//            String systemUUid= (String) PKMAP.get(uuid);
//            if(systemUUid.equals(uuid)&&jsonDataMp!=null&&jsonDataMp.size()>0){
//                //标识吻合
//                PKMAP.put(uuid,jsonDataMp);
//                re="1000";
//
//
//
//            }else{
//                //标志不吻合，恶意植入数据
//                re="2000";
//            }
//
//        } catch (Exception e) {
//            logger.error("attackData控制层异常:" + e.getMessage(),e);
//        }
//        return re;
//    }
//
    //攻击提交
    @RequestMapping(value = "/attackData", method = RequestMethod.POST)
    public String attackData(@RequestParam(value = "jsonData", required = true) String jsonData,
                            HttpServletRequest request) {
        String re = "";
        try {
            Map<String, Object> dataMap = gsonBean.fromJson(jsonData, Map.class);
            String uuid = (String) dataMap.get("uuid");
            List<Map<String, Object>> jsonDataMp = (List<Map<String, Object>>) dataMap.get("jsonData");
           //ff String systemUUid = (String) HandlePkServiceImpl.HANDLEPKSERVICEPKMAP.get(uuid);
            //标识吻合
            HandlePkServiceImpl.HANDLEPKSERVICEPKMAP.put(uuid, jsonDataMp);
            String key= request.getHeader("key");
            handlepkservice.handlePk(key,uuid);
//            if (uuid.equals(systemUUid) && jsonDataMp != null && jsonDataMp.size() > 0) {
//                //标识吻合
//                HandlePkServiceImpl.HANDLEPKSERVICEPKMAP.put(uuid, jsonDataMp);
//                re = "1000";
//                handlepkservice.handlePk("1000",uuid);
//            } else {
//                //标志不吻合，恶意植入数据
//                re = "2000";
//            }
        } catch (CommonException e) {
            return SimpleCode.ERROR.getCode();
        }
        return re;
    }
//    @RequestMapping("/test")
//    public String attackData() {
//        List<PkRoleVo> pvLi=null;
//        try {
//           pvLi=pkvoservice.getPkRoleVo("1000","休息");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    @RequestMapping("/getYeGuaiData")
//
//    public SimpleResult getYeGuaiData(@RequestParam(value="level",required = true) String level,
//                                      @RequestParam(value="name",required = true) String name
//                                      ) {
//        SimpleResult sr=null;
//        Map<String,Object> mp=new HashMap();
//        try {
//            mp=pkvoservice.getYeGuaiData(level,name,1000);
//        } catch (CommonException e) {
//            logger.error("getYeGuaiData:" + e.getMessage(),e);
//            return SimpleResult.error(e.getCode(), e.getMessage());
//        }
//        sr=SimpleResult.success();
//        sr.put("data", gsonBean.toJson(mp));
//        return sr;
//    }


    //初始化野怪数据
    @RequestMapping(value = "/getYeGuaiData", method = RequestMethod.POST)
    public SimpleResult queryVedio(@RequestParam(value = "level", required = true) String level,
                                             @RequestParam(value = "name", required = true) String name,
                                   HttpServletRequest request) {
        SimpleResult sr = null;
        Map<String, Object> mp = new HashMap();
        try {
            String key= request.getHeader("key");
            mp = pkvoservice.getYeGuaiData(level, name, key);
        } catch (Exception e) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data", gsonBean.toJson(mp));
        return sr;
    }
    //pk使用药品
    @RequestMapping(value = "/usrYao", method = RequestMethod.POST)
    public SimpleResult usrYao(@RequestParam(value = "id", required = true) String id,
                                    HttpServletRequest request
                                  ) {
        SimpleResult sr = null;
        String key= request.getHeader("key");

        try {
            pkvoservice.usrYao(id,key);
        } catch (Exception e) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getMessage());
        }
        sr = SimpleResult.success();
        return sr;
    }
    //判断是否有PK在进行中
    @RequestMapping(value = "/queryPk", method = RequestMethod.POST)
    public SimpleResult queryPk( HttpServletRequest request
    ) {
        SimpleResult sr = null;
        String key= request.getHeader("key");

        String re="";
        try {
            re=pkvoservice.queryPk(key);
        } catch (Exception e) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data",re);
        return sr;
    }

}

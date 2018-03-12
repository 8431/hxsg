package com.hxsg.zhuangbei.impl;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.Dao.HxsgBaseDaoMapper;
import com.hxsg.Dao.yaopingMapper;
import com.hxsg.po.yaoping;
import com.hxsg.yiguan.Cocos2dYaoPinService;
import com.hxsg.zhuangbei.ZhuangBeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/1/4.
 */
@Service("ZhuangBeiService")
public class ZhuangBeiServiceImpl implements ZhuangBeiService {
    @Autowired
    HxsgBaseDaoMapper hxsgbasedaomapper;
    @Autowired
    Cocos2dYaoPinService cocos2dyaopinservice;

    @Override
    public List<Map<String, Object>> queryZhuangBeiShop(String type) throws CommonException {
        List<Map<String, Object>> li = new ArrayList<>();
        try {
            Map<String, Object> param = new HashMap();
            param.put("sql", "select * from wuqi_describe  where wuqiType LIKE CONCAT('%',#{type},'%') order by level");
            param.put("type", type);
            li = hxsgbasedaomapper.QuerySql(param);
        } catch (Exception e) {
            throw new CommonException("CM001", "查询武器商店：" + e.getMessage());
        }
        return li;
    }

    @Override
    public String buyZhuangBei(Integer roleid, String id) throws CommonException {
        String re = "购买失败";
        try {
            List<Map<String, Object>> li = new ArrayList<>();
            Map<String, Object> param = new HashMap();
            param.put("sql", "select * from wuqi_describe  where id=#{id} ");
            param.put("id", id);
            li = hxsgbasedaomapper.QuerySql(param);
            if (li != null && li.size() == 1) {
                Long price = (Long) li.get(0).get("price");
                //扣除金额
                String result = cocos2dyaopinservice.xiaofei(price.intValue(), roleid.toString(), "1");
                if ("1".equals(result)) {
                    //插入武器到玩家武器库
                    param.put("sql", "insert into role_new_zhuangbei(roleid,wqid,name,wqxiaoguo,type,date,zb,kangxing) values(#{roleid},#{wqid},#{name},#{xiaoguo},'0',now(),'0',#{kangxing}) ");
                    param.put("roleid", roleid);
                    param.put("wqid", id);
                    param.put("name", li.get(0).get("wuQiName"));
                    param.put("xiaoguo", li.get(0).get("xiaoGuo"));
                    param.put("kangxing", li.get(0).get("kangXing"));
                    hxsgbasedaomapper.InsertSql(param);
                    re = "购买成功，您花费了" + price + "银两购买了(" + li.get(0).get("wuQiName") + ")";
                } else if ("2".equals(result)) {
                    re = "余额不足，购买失败!";
                }
            } else {
                throw new CommonException("没有查询到该武器，id无效");

            }
        } catch (Exception e) {
            throw new CommonException("CM001", "查询武器商店：" + e.getMessage());
        }
        return re;
    }
}

package com.hxsg.yiguan.impl;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.CommonUtil.StringUtil;
import com.hxsg.Dao.HxsgBaseDaoMapper;
import com.hxsg.yiguan.Cocos2dYaoPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2018/1/17 0017.
 * Email 1429264916@qq.com
 */
@Service("Cocos2dYaoPinService")
public class Cocos2dYaoPinServiceImpl implements Cocos2dYaoPinService {
    @Autowired
    HxsgBaseDaoMapper hxsgbasedaomapper;

    @Override
    public Map<String, Object> queryYaoPinDetail(String id) throws CommonException {
        if (StringUtil.isEmpty(id)) {
            throw new CommonException("药品id不能为空");
        }
        Map<String, Object> result = null;
        try {
            Map<String, Object> param = new HashMap();
            param.put("id", id);
            param.put("sql", "select * from yaoping where yaoid=#{id} ");
            List<Map<String, Object>> re = hxsgbasedaomapper.QuerySql(param);
            if (re != null && re.size() > 0) {
                result = re.get(0);
            }
        } catch (Exception e) {
            throw new CommonException("查询药品详情异常：" + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> buyYao(String id, String num, String roleid) throws CommonException {
        if (StringUtil.isEmpty(id) || StringUtil.isEmpty(num)) {
            throw new CommonException("药品id,购买数量不能为空");
        }

        Map<String, Object> result = new HashMap<>();
        if (Integer.parseInt(num) > 10) {
            result.put("message", "最多只能一次购买10个");
            return result;
        }
        try {
            Map<String, Object> param = new HashMap();
            param.put("id", id);
            param.put("sql", "select * from yaoping where yaoid=#{id} ");
            List<Map<String, Object>> remp = hxsgbasedaomapper.QuerySql(param);
            if (remp != null && remp.size() > 0) {
                Map<String, Object> re = remp.get(0);
                Long price = (Long) re.get("yaoprice");
                Long totalPrrice = Integer.parseInt(num) * price;
                //扣除金额
                String xfResult = this.xiaofei(totalPrrice.intValue(), roleid, "1");
                if ("1".equals(xfResult)) {
                    //将药品放至角色药品表
                    String insertSql = "insert into roleyao (yaoid,yaonum,roleid,createdata)values" +
                            "(#{yaoid},#{yaonum},#{roleid},now())";
                    param.put("yaoid", id);
                    param.put("yaonum", num);
                    param.put("roleid", roleid);
                    param.put("sql", insertSql);
                    hxsgbasedaomapper.InsertSql(param);
                    result.put("message", "您本次消费" + totalPrrice + "，购买了" + num + "个" + (String) re.get("yaoname"));
                }
                if ("2".equals(xfResult)) {
                    result.put("message", "余额不足，购买失败");
                }

            } else {
                throw new CommonException("药品不存在，输入id有误");
            }
        } catch (Exception e) {
            throw new CommonException("查询药品详情异常：" + e.getMessage());
        }
        return result;
    }

    /**
     * 购买物品
     *
     * @param money
     * @param roleid
     * @return
     * @throws CommonException 1 购买成功
     *                         2 余额不足
     *                         3 购买失败
     */
    @Override
    public String xiaofei(Integer money, String roleid, String type) throws CommonException {
        String result = "3";
        if (StringUtil.isEmpty(roleid) || money == null) {
            throw new CommonException("roleid,金钱不能为空");
        }
        try {
            if ("1".equals(type)) {
                type = "yin";
            } else {
                type = "jin";
            }
            Map<String, Object> param = new HashMap();
            param.put("roleid", roleid);
            String sql = "select " + type + " from new_role where id=#{roleid}";
            param.put("sql", sql);
            List<Map<String, Object>> remp = hxsgbasedaomapper.QuerySql(param);
            if (remp != null && remp.size() > 0) {
                Long jin = (Long) remp.get(0).get(type);
                if (jin >= money) {
                    result = "1";
                    sql = "update new_role set " + type + "=#{money}  where id=#{roleid}";
                    param.put("money", jin - money);
                    param.put("sql", sql);
                    hxsgbasedaomapper.UpdateSql(param);
                } else {
                    result = "2";
                }
            } else {
                throw new CommonException("没有查到该条记录");
            }
        } catch (Exception e) {
            throw new CommonException("查询药品详情异常：" + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> queryRoleYao(String roleid) throws CommonException {
        if (StringUtil.isEmpty(roleid)) {
            throw new CommonException("roleid,不能为空");
        }
        List<Map<String, Object>> result=null;
        try {
            Map<String, Object> param = new HashMap();
            param.put("roleid",roleid);
            param.put("sql","select m.yaoid,sum(m.yaonum) as sum,m.yaoname from (select r.yaoid,r.yaonum,y.yaoname from roleyao r,yaoping y  where r.yaoid=y.yaoid and r.roleid=#{roleid}) m group by m.yaoid having sum(yaonum)>0\n");
            result= hxsgbasedaomapper.QuerySql(param);
        } catch (Exception e) {
            throw new CommonException("查询角色药品异常：" + e.getMessage());
        }

        return result;
    }

}

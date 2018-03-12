package com.hxsg.YG.service.impl;

import com.hxsg.Dao.RoleMapper;
import com.hxsg.Dao.RoleFujiangMapper;
import com.hxsg.YG.service.Cocos2dYgService;
import com.hxsg.po.RoleFujiang;
import com.hxsg.vo.IndexFuJiangVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlf on 2016/10/31.
 */
@Service("Cocos2dYgService")
public class Cocos2dYgServiceImpl implements Cocos2dYgService {
    @Autowired
    RoleFujiangMapper RoleFujiangmapper;
    @Autowired
    RoleMapper rolemapper;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Cocos2dYgServiceImpl.class);
    /**
     * 查询角色副将
     * @param roleId 角色ID
     * @param shuxing 1，不在驿馆，0在驿馆
     * @return
     * @throws Exception
     */
    @Override
    public List<IndexFuJiangVo> queryRoleFuJiang(Integer roleId, Integer shuxing){
        List<IndexFuJiangVo> livo=null;
        try {
            livo=new ArrayList<IndexFuJiangVo>();
            List<RoleFujiang> lifj=RoleFujiangmapper.getByRoleIdShux(roleId, shuxing);
            for(RoleFujiang r:lifj){
                IndexFuJiangVo fv=new IndexFuJiangVo();
                fv.setRoleId(r.getRoleid());
                fv.setFuId(r.getId());
                fv.setStatus(r.getStatus());
                StringBuffer sb=new StringBuffer("(");
                sb.append(r.getLeve()+"级)");
                sb.append(r.getFujiangname());
                //sb.append(".");
                //sb.append();//职业
                fv.setName(sb.toString());
                livo.add(fv);
            }
        } catch (Exception e) {
            logger.equals("查询角色副将数据库异常："+e.getMessage());
            e.printStackTrace();
        }
        return livo;
    }



    /**
     * 招领-留守副将
     * @param roleId
     * @param fuId
     * @return
     */
    @Override
    public String fuJiangZhaoLing(Integer roleId, Integer fuId){
        String msg="";
        try {
            List<RoleFujiang> lifj=RoleFujiangmapper.getByRoleIdShux(roleId,1);
            if(lifj!=null&&lifj.size()<=10){
                RoleFujiang rg=RoleFujiangmapper.selectByPrimaryKey(fuId);
                if(roleId.equals(rg.getRoleid())){
                    if(rg.getShuxing()==1){
                        rg.setShuxing(0);
                        int a= RoleFujiangmapper.updateByPrimaryKeySelective(rg);
                        if(a>0){
                            msg="留守副将成功！";
                        }
                    }else{
                        rg.setShuxing(1);
                        int a= RoleFujiangmapper.updateByPrimaryKeySelective(rg);
                        if(a>0){
                            msg="招领副将成功！";
                        }
                    }
                }else{
                    msg="该副将不属于你！";
                }
            }else{
                msg="您的随行副将已经有10个，无法招领!";
            }
        } catch (Exception e) {
            logger.error("招领，留守副将数据库异常："+e.getMessage());
            msg="服务器异常";
            e.printStackTrace();
        }
        return msg;
    }
}

package com.hxsg.wupin.controller;

import com.hxsg.CommonUtil.CommonUtilAjax;
import com.hxsg.po.*;
import com.hxsg.vo.IndexFuJiangVo;
import com.hxsg.vo.ZaWuDetailVo;
import com.hxsg.vo.ZhuangBeiDetailVo;
import com.hxsg.wupin.service.Cocos2dUserWuPinService;
import com.hxsg.wupin.service.Cocos2dZhuangBeiToRole;
import com.hxsg.wupin.service.cocos2dWuPinService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dlf on 2016/9/29.
 */
@Controller
@RequestMapping("cocos2dWuPin")
public class Cocos2dWuPinController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(Cocos2dWuPinController.class);
    @Autowired
    cocos2dWuPinService cocos2dwupinservice;
    @Autowired
    Cocos2dUserWuPinService cocos2duserwupinservice;
    @Autowired
    Cocos2dZhuangBeiToRole cocos2dzhuangbeitorole;
    //加载物品分类
    @RequestMapping(value = "/wupin", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String wupin(RoleWuPin re,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            //过滤掉数量为0的数据。。
            Integer roleId=(Integer)session.getAttribute("roleId");
            re.setRoleid(roleId);
            //test
            re.setRoleid(roleId);
            re.setType2("杂物");
            List<RoleWuPin> rn=cocos2dwupinservice.queryRoleWupin(re);
            Object[][] obj=new  Object[][]{{"type",3},{"msg",rn}};
            CommonUtilAjax.sendAjaxArray(obj,request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/wupin获取角色物品："+e.getMessage());
            CommonUtilAjax.sendAjaxList("msg", new RoleWuPin(), request, response);
        }
        return null;
    }
    //物品-杂物分类
    @RequestMapping(value = "/wupinDescribe", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String wupinDescribe( @RequestParam(value = "id", required = false) Integer id,
                                 HttpServletRequest request,HttpServletResponse response){
        try{
            ZaWuMiaoShu zu=cocos2dwupinservice.queryWuPinDescribe(id);
            CommonUtilAjax.sendAjaxList("msg", zu, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/wupinDescribe获取物品详情："+e.getMessage());
            CommonUtilAjax.sendAjaxList("msg", new ZaWuMiaoShu(), request, response);
        }
        return null;
    }
    //物品-宝石分类
    @RequestMapping(value = "/baoshi", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String baoshi(
            HttpSession session,
            HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            String roleName=(String)session.getAttribute("roleName");
            BaoShi bc=new BaoShi();
            bc.setRoleid(roleId);
            List<ZaWuDetailVo> libaoshi=cocos2dwupinservice.queryWuPinByBaoShi(roleId, roleName);
            Object[][] obj=new  Object[][]{{"type",2},{"msg",libaoshi}};
            CommonUtilAjax.sendAjaxArray(obj,request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/baoshi获取物品详情："+e.getMessage());
        }
        return null;
    }
    //物品-宝石详情
    @RequestMapping(value = "/baoshiDescribe", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String baoshiDescribe(@RequestParam(value = "id", required = false) Integer id,
                                 HttpSession session,
                                 HttpServletRequest request,HttpServletResponse response){
        try{
            ZaWuDetailVo baoshi=cocos2dwupinservice.queryBaoShiDescribe(id);
            CommonUtilAjax.sendAjaxList("msg", baoshi, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/baoshiDescribe获取物品详情："+e.getMessage());
        }
        return null;
    }
    //物品-装备详情
    @RequestMapping(value = "/queryZhuangBeiDetail", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryZhuangBeiDetail(ZhuangBeiDetailVo zb,
                                       HttpSession session,
                                       HttpServletRequest request,HttpServletResponse response){
        try{
            String roleName=(String)session.getAttribute("roleName");
            ZhuangBeiDetailVo zv=cocos2dwupinservice.queryZhuangBeiDetail(zb,roleName);
            CommonUtilAjax.sendAjaxList("msg", zv, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/queryZhuangBeiDetail装备详情："+e.getMessage());
        }
        return null;
    }
    //加载物品-装备
    @RequestMapping(value = "/zhaungbei", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String zhaungbei(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            List<RoleNewZhuangBei> rn=cocos2dwupinservice.queryWuPinZhuangBei(roleId,0);
            Object[][] obj=new  Object[][]{{"type",1},{"msg",rn}};
            CommonUtilAjax.sendAjaxArray(obj,request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/zhaungbei获取物品-装备："+e.getMessage());
        }
        return null;
    }
    /****************************************cocos2d物品使用模块代码*****************************************/
    //物品-宝石分类--矿石的使用
    @RequestMapping(value = "/userMineral ", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String userMineral(RoleWuPin rn,
                              HttpSession session,
                              HttpServletRequest request,HttpServletResponse response){
        try{
            String juseName=(String)session.getAttribute("roleName");
            Integer roleId=(Integer)session.getAttribute("roleId");
            //test
            rn.setRoleid(roleId);
            System.out.println(rn.getRoleid());
            String baoshi=cocos2dwupinservice.userMineral(rn,juseName);
            CommonUtilAjax.sendAjaxList("msg", baoshi, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/userMineral砸宝石："+e.getMessage());
        }
        return null;
    }
    //物品-获取可以被该宝石镶嵌的装备
    @RequestMapping(value = "/getBaoShiByZhaungbei ", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getBaoShiByZhaungbei(@RequestParam(value = "id", required = false) Integer id,
                                       HttpSession session,
                                       HttpServletRequest request,HttpServletResponse response){
        try{
            String juseName=(String)session.getAttribute("roleName");
            Integer roleId=(Integer)session.getAttribute("roleId");
            List<RoleZhuangbeiMs> lir= cocos2dwupinservice.getBaoShiByZhaungbei(id,roleId);
            CommonUtilAjax.sendAjaxList("msg", lir, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/getBaoShiByZhaungbei获取可以被该宝石镶嵌的装备："+e.getMessage());
        }
        return null;
    }
    //物品-将宝石镶嵌到装备上
    @RequestMapping(value = "/mosaicGemtoZhuangBei ", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String mosaicGemtoZhuangBei(@RequestParam(value = "bsId", required = false) Integer bsId,
                                       @RequestParam(value = "zbId", required = false) Integer zbId,
                                       HttpSession session,
                                       HttpServletRequest request,HttpServletResponse response){
        try{
            String juseName=(String)session.getAttribute("roleName");
            Integer roleId=(Integer)session.getAttribute("roleId");
            String result= cocos2dwupinservice.mosaicGemtoZhuangBei(roleId,bsId,zbId);
            CommonUtilAjax.sendAjaxList("msg",result, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/getBaoShiByZhaungbei获取可以被该宝石镶嵌的装备："+e.getMessage());
        }
        return null;
    }
    //物品-使用物品
    @RequestMapping(value = "/userWuPin", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String userDaoJu(@RequestParam(value = "wupinId", required = false) Integer wupinId,
                            @RequestParam(value = "num", required = false) Integer num,
                            @RequestParam(value = "jnId", required = false) Integer jnId,
                            @RequestParam(value = "fuId", required = false) Integer fuId,
                            HttpSession session,
                            HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            String result= cocos2duserwupinservice.userDaoJu(wupinId,roleId,num,jnId,fuId);
            CommonUtilAjax.sendAjaxList("msg",result, request, response);
        }catch (RuntimeException e){
            CommonUtilAjax.sendAjaxList("msg","已经学习过该技能了，不需要再次学习", request, response);
        }
        catch (Exception e){
            logger.error("控制层--cocos2dWuPin/userWuPin使用物品异常："+e.getMessage());
        }
        return null;
    }
    //物品-培养副将
    @RequestMapping(value = "/peiYangFuJiang ", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String peiYangFuJiang(@RequestParam(value = "fuId", required = false) Integer fuId,
                                 @RequestParam(value = "type", required = false) String type,
                                 HttpSession session,
                                 HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            Object ot= cocos2duserwupinservice.peiYangFuJiang(fuId,roleId,type);
            CommonUtilAjax.sendAjaxList("msg",ot, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/peiYangFuJiang培养副将初值："+e.getMessage());
        }
        return null;
    }
    //物品-技能
    @RequestMapping(value = "/queryFuJiangJiNengBook ", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String queryFuJiangJiNengBook(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "type", required = false) String type,
                                         HttpSession session,
                                         HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            List<IndexFuJiangVo> ot= cocos2duserwupinservice.queryFuJiangJiNengBook(id, roleId);
            CommonUtilAjax.sendAjaxList("msg",ot, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/queryFuJiangJiNengBook："+e.getMessage());
        }
        return null;
    }
    //物品-获取使用技能书的列表
    @RequestMapping(value = "/getJiNengShuList", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getJiNengShuList(@RequestParam(value = "id", required = false) Integer id,
                                   @RequestParam(value = "status", required = false) Integer status,
                                   HttpSession session,
                                   HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            List<Skill> lisk= cocos2duserwupinservice.querySkillForRole(roleId,id,status);
            CommonUtilAjax.sendAjaxList("msg",lisk, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/pgetJiNengShuList获取使用技能书的列表："+e.getMessage());
        }
        return null;
    }
    //物品-获取使用心法书列表
    @RequestMapping(value = "/getXinFaFuJiang", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getXinFaFuJiangs(@RequestParam(value = "wupinId", required = false) Integer wupinId,
                                   HttpSession session,
                                   HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            List<RoleFujiang> lifu= cocos2duserwupinservice.getRoleFuforUserXinFaShu(roleId,wupinId);
            CommonUtilAjax.sendAjaxList("msg",lifu, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/getXinFaFuJiang取使用心法书列表："+e.getMessage());
        }
        return null;
    }
    //物品-装备-为副将或者角色装备
    @RequestMapping(value = "/zbWuQi", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String zbWuQi(@RequestParam(value = "id", required = false) Integer id,
                         @RequestParam(value = "type", required = false) String type,
                         HttpSession session,
                         HttpServletRequest request,HttpServletResponse response){
        try{
            Integer roleId=(Integer)session.getAttribute("roleId");
            String msg=cocos2dzhuangbeitorole.zhuangBeiToRole(roleId,id,type,true);
            CommonUtilAjax.sendAjaxList("msg",msg, request, response);
        }catch (Exception e){
            logger.error("控制层--cocos2dWuPin/getXinFaFuJiang取使用心法书列表："+e.getMessage());
        }
        return null;
    }
}

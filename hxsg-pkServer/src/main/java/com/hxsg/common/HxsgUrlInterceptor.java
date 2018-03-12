package com.hxsg.common;

import com.hxsg.CommonUtil.StringUtil;
import com.hxsg.Dao.NewRoleMapper;
import com.hxsg.po.NewRole;
import com.hxsg.redis.RedisDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dlf on 2016/11/8.
 */
@Service
public class HxsgUrlInterceptor implements HandlerInterceptor {
    protected  HttpSession session=null;
    @Autowired
    RedisDaoService redisdaoservice;
    @Autowired
    NewRoleMapper  newrolemapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean result=false;
        try {
            String key=request.getHeader("key");
            System.out.println("-----------------"+key);
            session = request.getSession();
            //test
            if(!StringUtil.isEmpty(key)){
                Integer roleId= (Integer) redisdaoservice.get(key);
                if(roleId!=null){
                  NewRole role=  newrolemapper.queryRoleMsgQx(roleId);
                    session.setAttribute("roleId",roleId);
                    session.setAttribute("roleName",role.getRolename());
                }
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}

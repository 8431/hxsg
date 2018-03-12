package com.hxsg.Interceptor;

import com.hxsg.CommonUtil.StringUtil;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.login.Login;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dlf on 2016/11/8.
 */
public class HxsgUrlInterceptor implements HandlerInterceptor {
    protected  HttpSession session=null;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean result=false;
        try {
            String key=request.getHeader("key");
            System.out.println("-----------------"+key);
            session = request.getSession(true);
            if(!StringUtil.isEmpty(key)){
                Login ln= (Login) Constants.loginMap.get(key);
                if(ln!=null){
                    session.setAttribute("roleId",ln.getRoleId());
                    session.setAttribute("roleName",ln.getRoleName());
                    session.setAttribute("acount",ln.getAcount().getId());
                }
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

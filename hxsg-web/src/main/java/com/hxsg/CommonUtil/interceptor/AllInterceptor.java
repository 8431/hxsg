package com.hxsg.CommonUtil.interceptor;

import com.hxsg.CommonUtil.login.Constants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dlf on 2016/3/17.
 */
public class AllInterceptor extends HandlerInterceptorAdapter {
    public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        boolean falg=true;
        Integer roleid=(Integer)session.getAttribute("roleid");
        String rolekey=(String)session.getAttribute("rolekey");
        String a=null;
        if(roleid!=null) {
            a= (String) Constants.SESSION_NAME.get(roleid.toString());
        }
        if(a==null){//说明用户还未上线，正在登陆
            //拦截不符合要求的请求
            String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
            if(null != allowUrls && allowUrls.length>=1){
                for(int i=0;i<allowUrls.length;i++){
                    if(allowUrls[i].equals(requestUrl)){
                        falg=true;
                    }else{
                        falg=false;
                       // request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
                    }

                }

            }
        }else{//用户已经登陆
            if(rolekey!=null){
                if(rolekey.equals(a)){//判断用户登录时候的KEY是否和目前的Session保存的KEY相同。true表示同一用户
                    falg=true;

                }else{//该账号在其他地方登陆
                    //拦截不符合要求的请求
                    String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
                    if(null != allowUrls && allowUrls.length>=1){
                        if(allowUrls[0].equals(requestUrl)){
                            falg=true;
                        }else{
                            falg=false;
                           // response.sendRedirect("http://www.baidu.com");
                            //request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
                        }
                    }
                }

        }




        }



        return falg;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}

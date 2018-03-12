package com.hxsg.CommonUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/16 0016.
 */
public class CORSFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        // response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        //by dlf 增加json/application 跨域支持
        response.addHeader("Access-Control-Allow-Headers", "key,Origin, X-Requested-With, Content-Type, Accept");
        filterChain.doFilter(new XSSRequestWrapper((HttpServletRequest) servletRequest), servletResponse);

    }

    public void destroy() {

    }

}

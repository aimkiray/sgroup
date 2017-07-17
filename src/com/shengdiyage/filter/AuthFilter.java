package com.shengdiyage.filter;

import com.shengdiyage.entity.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Akari on 2017/7/12.
 */
public class AuthFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();
        if (session.getAttribute("admin") != null || uri.indexOf("login") > 0 || uri.endsWith(".css") || uri.endsWith(".js")) {
            // 按已登录处理
            filterChain.doFilter(request, response);
            return;
        } else {
            // 未登录
            response.sendRedirect("/admin/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}

package com.sgroup.Listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class NewSessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        String sessionId = httpSessionEvent.getSession().getId();
        System.out.println("当前创建的sessionId = " + sessionId);
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Object userCount = servletContext.getAttribute("userCount");
        if (userCount == null || "0".equals(userCount)) {
            servletContext.setAttribute("userCount", 1);
        } else {
            servletContext.setAttribute("userCount", Integer.parseInt(userCount.toString()) + 1);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String sessionId = httpSessionEvent.getSession().getId();
        System.out.println("当前销毁的sessionId = " + sessionId);
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Object userCount = servletContext.getAttribute("userCount");
        if (userCount == null) {
            servletContext.setAttribute("userCount", 0);
        } else {
            servletContext.setAttribute("userCount", Integer.parseInt(userCount.toString()) - 1);
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        ServletContext servletContext = httpSessionBindingEvent.getSession().getServletContext();
        Object loginCount = httpSessionBindingEvent.getSession().getAttribute("loginCount");
        if (loginCount == null || "0".equals(loginCount)) {
            servletContext.setAttribute("loginCount", 1);
        } else {
            servletContext.setAttribute("loginCount", Integer.parseInt(loginCount.toString()) + 1);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        ServletContext servletContext = httpSessionBindingEvent.getSession().getServletContext();
        Object loginCount = httpSessionBindingEvent.getSession().getAttribute("loginCount");
        if (loginCount == null || "0".equals(loginCount)) {
            servletContext.setAttribute("loginCount", 0);
        } else {
            servletContext.setAttribute("loginCount", Integer.parseInt(loginCount.toString()) - 1);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}

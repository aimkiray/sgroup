package com.shengdiyage.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

public class NewServletContextListener implements ServletContextListener {

    Timer timer = null;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        timer = new Timer();
        timer.schedule(new NewTimerTask(), 0,10000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        timer.cancel();
        System.out.println("cancel");

    }
}

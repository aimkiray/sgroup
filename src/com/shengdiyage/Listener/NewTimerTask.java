package com.shengdiyage.Listener;

import java.util.Date;
import java.util.TimerTask;

public class NewTimerTask extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        System.out.println(new Date() + " 北京时间");
    }
}

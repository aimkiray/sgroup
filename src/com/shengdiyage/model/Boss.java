package com.shengdiyage.model;

/**
 * Created by Akari on 2017/6/29.
 */
public class Boss {
    private int bossId;
    private String bossName;
    private String bossPassword;

    public Boss(String bossName, String bossPassword) {
        this.bossName = bossName;
        this.bossPassword = bossPassword;
    }

    public Boss(int bossId, String bossName, String bossPassword) {
        this.bossId = bossId;
        this.bossName = bossName;
        this.bossPassword = bossPassword;
    }

    public int getBossId() {
        return bossId;
    }

    public void setBossId(int bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getBossPassword() {
        return bossPassword;
    }

    public void setBossPassword(String bossPassword) {
        this.bossPassword = bossPassword;
    }
}

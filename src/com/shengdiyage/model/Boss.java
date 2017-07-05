package com.shengdiyage.model;

/**
 * Created by Akari on 2017/6/29.
 */
public class Boss {
    private int bid;
    private String bname;
    private String bpassword;

    public Boss(String bname, String bpassword) {
        this.bname = bname;
        this.bpassword = bpassword;
    }

    public Boss(int bid, String bname, String bpassword) {
        this.bid = bid;
        this.bname = bname;
        this.bpassword = bpassword;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBpassword() {
        return bpassword;
    }

    public void setBpassword(String bpassword) {
        this.bpassword = bpassword;
    }
}

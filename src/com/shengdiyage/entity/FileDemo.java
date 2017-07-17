package com.shengdiyage.entity;

import java.util.Date;

/**
 * Created by nekuata on 2017/7/16.
 */
public class FileDemo {
//    文件类的私有属性，与mariaDB相对应
    private int id;
    private String name;
    private Date date;

    public FileDemo() {
    }

    public FileDemo(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public FileDemo(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.shengdiyage.model;

/**
 * Created by Akari on 2017/6/27.
 */
public class Product {
    private int pid;
    private String pname;
    private int pprice;
    private int number;
    private int ptype;
    private int id;

    public Product(int pid, String pname, int pprice, int number, int ptype, int id) {
        this.pid = pid;
        this.pname = pname;
        this.pprice = pprice;
        this.number = number;
        this.ptype = ptype;
        this.id = id;
    }

    public Product() {};

    public Product(String pname, int pprice, int number, int ptype) {
        this.pname = pname;
        this.pprice = pprice;
        this.number = number;
        this.ptype = ptype;
    }

    public Product(int pid, String pname, int pprice, int number, int ptype) {
        this.pid = pid;
        this.pname = pname;
        this.pprice = pprice;
        this.number = number;
        this.ptype = ptype;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  ", 名称='" + pname + '\'' +
                ", 价格=" + pprice +
                ", 库存=" + number;
    }
}

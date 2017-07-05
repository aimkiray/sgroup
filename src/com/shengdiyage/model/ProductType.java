package com.shengdiyage.model;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductType {

    private int typeid;
    private String typename;

    public ProductType(String typename) {
        this.typename = typename;
    }

    public ProductType(int typeid, String typename) {
        this.typeid = typeid;
        this.typename = typename;
    }

    public ProductType() {

    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}

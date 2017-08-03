package com.sgroup.entity;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductType {

    private int typeId;
    private String typeName;

    public ProductType(String typeName) {
        this.typeName = typeName;
    }

    public ProductType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public ProductType() {

    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

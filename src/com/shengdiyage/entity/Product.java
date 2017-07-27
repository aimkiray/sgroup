package com.shengdiyage.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Akari on 2017/6/27.
 */
public class Product implements Serializable {

    // 当前类的版本，修改此类的同时也要修改版本号。
    private static final long serialVersionUID = 1L;

    private int productId;
    private String productName;
    private int productPrice;
    private int number;
    private ProductType productType;
    @JSONField(format = "yyyy-MM-dd")
    private Date productTime;
    private String fileName;
    private int id = 0;
    private int quantity;

    public Product(int productId, String productName, int productPrice, int number, ProductType productType, Date productTime) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.number = number;
        this.productType = productType;
        this.productTime = productTime;
    }

    public Product(String productName, int productPrice, int number, ProductType productType, Date productTime) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.number = number;
        this.productType = productType;
        this.productTime = productTime;
    }

    public Product(String productName, int productPrice, int number, Date productTime) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.number = number;
        this.productTime = productTime;
    }

    public Product(int productId, String productName, int productPrice, int number, ProductType productType, Date productTime, String fileName) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.number = number;
        this.productType = productType;
        this.productTime = productTime;
        this.fileName = fileName;
    }

    public Product(String productName, int productPrice, int number, ProductType productType, Date productTime, String fileName) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.number = number;
        this.productType = productType;
        this.productTime = productTime;
        this.fileName = fileName;
    }

    public Product() {
    }

    //    public Product(int productId, String productName, int productPrice, int number, ProductType productType, int id) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.number = number;
//        this.productType = productType;
//        this.id = id;
//    }
//
//    public Product() {};
//
//    public Product(String productName, int productPrice, int number, ProductType productType) {
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.number = number;
//        this.productType = productType;
//    }
//
//    public Product(int productId, String productName, int productPrice, int number, ProductType productType) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.number = number;
//        this.productType = productType;
//    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  "名称=" + productName +
                ", 价格=" + productPrice +
                ", 库存=" + number +
                ", 类别=" + productType.getTypeName();
    }

    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }
}

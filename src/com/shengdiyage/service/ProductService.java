package com.shengdiyage.service;

import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public interface ProductService {
    /**
     * 增加产品
     * @param product
     * @return
     */
    int addProduct(Product product);

    /**
     * 删除产品
     * @param pid
     * @return
     */
    int deleteProduct(int pid);

    /**
     * 修改产品
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 查询产品
     * @param pid
     * @return
     */
    Product getProduct(int pid);

    /**
     * 通过typeid获得typename
     * @param typeid
     * @return ProductType
     */
    ProductType getTypeNameByTypeId(int typeid);

    /**
     * 查询全部产品
     * @return
     */
    List<Product> queryProduct();

    /**
     * 分页查询
     * @param start
     * @param conut
     * @return
     */
    List<Product> queryProduct(int start, int conut);
}

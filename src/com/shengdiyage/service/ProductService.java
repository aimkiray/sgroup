package com.shengdiyage.service;

import com.shengdiyage.entity.Product;

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
     * 批量删除产品
     * @param pids
     * @return
     */
    int delMulProduct(Integer[] pids);

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
    Product queryProduct(int pid);

    /**
     * 查询全部产品
     * @return
     */
    List<Product> queryProduct();

    /**
     * 分页查询
     * @param curPage
     * @param conut
     * @return
     */
    List<Product> queryProduct(Product product, int curPage, int conut);

    /**
     * (dataTables)分页查询
     * @param start
     * @param length
     * @return
     */
    List<Product> queryProductToTable(Product product, int start, int length);

    /**
     * 查询产品总数
     * @return 产品总数
     */
    int queryProductNum();

    /**
     * 查询匹配的产品总数
     * @param product 匹配用的product类
     * @return 产品总数
     */
    int queryProductNum(Product product);

    /**
     * 通过产品名称查找产品
     * @param productName 产品名称
     * @return 找到的产品，没找到返回null
     */
    boolean queryProductByName(String productName);
}

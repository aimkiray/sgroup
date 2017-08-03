package com.sgroup.dao;

import com.sgroup.entity.Product;

import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public interface ProductDao {
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
     * @param pids 要删除的产品id数组
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
     * 查询产品总数
     * @return 产品总数
     */
    int queryProductNum();

    /**
     * 查询搜素目标产品的总数
     * @return 产品总数
     */
    int queryProductNum(Product product);

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

    /**
     * 按类别模糊搜索查询
     * @param product 要查询的产品
     * @param start 从第几条开始查询
     * @param conut 每次查询的数量
     * @return
     */
    List<Product> queryProduct(Product product, int start, int conut);

    /**
     * 根据名字查询产品
     * @param productName 产品名称
     * @return 查询到的产品，没有则返回null
     */
    Product queryProductByName(String productName);

}

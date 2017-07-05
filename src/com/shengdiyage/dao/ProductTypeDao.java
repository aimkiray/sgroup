package com.shengdiyage.dao;

import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public interface ProductTypeDao {
    /**
     * 增加产品类别
     * @param productType
     * @return
     */
    int addProductTypeByType(ProductType productType);

    /**
     * 删除产品类别
     * @param typeid
     * @return
     */
    int deleteProductTypeByTypeId(int typeid);

    /**
     * 输入productType修改产品
     * @param productType
     * @return
     */
    int updateProductTypeByTypeId(ProductType productType);

    /**
     * 查询该类别下的所有产品
     * @param typeid
     * @return
     */
    List<Product> queryProductByTypeId(int typeid);

    /**
     * 通过typeid查询ProductType类
     * @param typeid
     * @return
     */
    ProductType queryProductTypeByTypeId(int typeid);

    /**
     * 显示所有类别
     * @return
     */
    List<ProductType> queryAllProductType();

    /**
     * 通过名字查询类别
     * @param typename
     * @return
     */
    ProductType queryProductTypeByTypeName(String typename);


//    /**
//     * 分页查询
//     * @param start
//     * @param conut
//     * @return
//     */
//    List<Product> queryProductByTypeId(int start, int conut);
}

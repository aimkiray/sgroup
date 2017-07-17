package com.shengdiyage.service;

import com.shengdiyage.entity.Product;
import com.shengdiyage.entity.ProductType;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public interface ProductTypeService {
    /**
     * 增加产品类别
     * @param productType
     * @return
     */
    int addProductTypeByType(ProductType productType);

    /**
     * 删除产品类别
     * @param typeId
     * @return
     */
    int deleteProductTypeByTypeId(int typeId);

    /**
     * 通过typeid查询产品
     * @param productType
     * @return
     */
    int updateProductTypeByType(ProductType productType);

    /**
     * 通过typeid查询type类
     * @param typeId
     * @return
     */
    ProductType queryTypeByTypeId(int typeId);

    /**
     * 通过typeid获得typename
     * @param typeId
     * @return ProductType
     */
    ProductType queryTypeNameByTypeId(int typeId);

    /**
     * 查询该类别下的所有产品
     * @param typeId
     * @return
     */
    List<Product> queryProductsByTypeId(int typeId);

    /**
     * 显示所有类别
     * @return
     */
    List<ProductType> queryAllProductType();

    /**
     * 查询该类别名是否存在
     * @param typeName 要查询的产品名
     * @return 未存在返回true，已存在返回false
     */
    boolean queryTypeByTypeName(String typeName);

//    /**
//     * 通过名字查询类别
//     * @param typename
//     * @return
//     */
//    int queryProductByTypeName(String typename);
}

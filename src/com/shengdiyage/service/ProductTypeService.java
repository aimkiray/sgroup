package com.shengdiyage.service;

import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;

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
     * @param typeid
     * @return
     */
    int deleteProductTypeByTypeId(int typeid);

    /**
     * 通过typeid查询产品
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
     * 显示所有类别
     * @return
     */
    List<ProductType> queryAllProductType();

//    /**
//     * 通过名字查询类别
//     * @param typename
//     * @return
//     */
//    int queryProductByTypeName(String typename);
}

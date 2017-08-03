package com.sgroup.dao;

import com.sgroup.entity.Product;
import com.sgroup.entity.ProductType;

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
     * @param typeId
     * @return
     */
    int deleteProductTypeByTypeId(int typeId);

    /**
     * 输入productType修改产品
     * @param productType
     * @return
     */
    int updateProductTypeByTypeId(ProductType productType);

    /**
     * 查询该类别下的所有产品
     * @param typeId
     * @return
     */
    List<Product> queryProductsByTypeId(int typeId);

    /**
     * 通过typeid查询ProductType类
     * @param typeId
     * @return
     */
    ProductType queryProductTypeByTypeId(int typeId);

    /**
     * 显示所有类别
     * @return
     */
    List<ProductType> queryAllProductType();

    /**
     * 通过名字查询类别
     * @param typeName
     * @return
     */
    ProductType queryProductTypeByTypeName(String typeName);


//    /**
//     * 分页查询
//     * @param start
//     * @param conut
//     * @return
//     */
//    List<Product> queryProductByTypeId(int start, int conut);
}

package com.shengdiyage.service.serviceImplement;

import com.shengdiyage.dao.ProductTypeDao;
import com.shengdiyage.dao.implement.ProductTypeDaoImpl;
import com.shengdiyage.entity.Product;
import com.shengdiyage.entity.ProductType;
import com.shengdiyage.service.ProductTypeService;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductTypeServiceImpl implements ProductTypeService {

    ProductTypeDao productTypeDao = new ProductTypeDaoImpl();

    /**
     * 添加产品类别
     *
     * @param productType 传入一个类别类
     * @return 返回数据库修改的条目数，0表示失败
     */
    @Override
    public int addProductTypeByType(ProductType productType) {
        int result = 0;
//        查询数据库是否有同名类别
        ProductType realType = productTypeDao.queryProductTypeByTypeName(productType.getTypeName());
//        如果没有则添加该类别
        if (realType == null) {
//            返回修改的条目数
            result = productTypeDao.addProductTypeByType(productType);
        }
        return result;
    }

    @Override
    public int deleteProductTypeByTypeId(int typeId) {
        int result = 0;
//        如果该类别下没有产品
        if(productTypeDao.queryProductsByTypeId(typeId) == null) {
            result = productTypeDao.deleteProductTypeByTypeId(typeId);
        }
        return result;
    }

    @Override
    public int updateProductTypeByType(ProductType productType) {
        int result = 0;
        ProductType realType = productTypeDao.queryProductTypeByTypeName(productType.getTypeName());
        if (realType == null) {
            result = productTypeDao.updateProductTypeByTypeId(productType);
        } else if (productType.getTypeId() == realType.getTypeId()) {
            result = productTypeDao.updateProductTypeByTypeId(productType);
        }
        return result;
    }

    @Override
    public ProductType queryTypeByTypeId(int typeId) {
        return productTypeDao.queryProductTypeByTypeId(typeId);
    }

    @Override
    public ProductType queryTypeNameByTypeId(int typeId) {
        return productTypeDao.queryProductTypeByTypeId(typeId);
    }

    @Override
    public List<Product> queryProductsByTypeId(int typeId) {
        return productTypeDao.queryProductsByTypeId(typeId);
    }

    @Override
    public List<ProductType> queryAllProductType() {
        return productTypeDao.queryAllProductType();
    }

    @Override
    public boolean queryTypeByTypeName(String typeName) {
        boolean result = false;
        ProductType productType = productTypeDao.queryProductTypeByTypeName(typeName);
        if (productType == null) {
            result = true;
        }
        return result;
    }

//    @Override
//    public int queryProductByTypeName(String typename) {
//        return 0;
//    }
}

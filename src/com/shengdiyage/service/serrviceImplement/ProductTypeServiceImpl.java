package com.shengdiyage.service.serrviceImplement;

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
        productTypeDao.queryProductByTypeId(typeId);
        return productTypeDao.deleteProductTypeByTypeId(typeId);
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
    public List<Product> queryProductByTypeId(int typeId) {
        return productTypeDao.queryProductByTypeId(typeId);
    }

    @Override
    public List<ProductType> queryAllProductType() {
        return productTypeDao.queryAllProductType();
    }

//    @Override
//    public int queryProductByTypeName(String typename) {
//        return 0;
//    }
}

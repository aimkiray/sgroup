package com.shengdiyage.service.serrviceImplement;

import com.shengdiyage.dao.ProductTypeDao;
import com.shengdiyage.dao.implement.ProductTypeDaoImpl;
import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;
import com.shengdiyage.service.ProductTypeService;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductTypeServiceImpl implements ProductTypeService {

    ProductTypeDao productTypeDao = new ProductTypeDaoImpl();

    @Override
    public int addProductTypeByType(ProductType productType) {
        int result = 0;
        if(!productType.getTypeName().equals(productTypeDao.queryProductTypeByTypeName(productType.getTypeName()).getTypeName())) {
            result = productTypeDao.addProductTypeByType(productType);
        }
        return result;
    }

    @Override
    public int deleteProductTypeByTypeId(int typeid) {
        return productTypeDao.deleteProductTypeByTypeId(typeid);
    }

    @Override
    public int updateProductTypeByTypeId(ProductType productType) {
        return productTypeDao.updateProductTypeByTypeId(productType);
    }

    @Override
    public ProductType queryTypeByTypeId(int typeid) {
        return productTypeDao.queryProductTypeByTypeId(typeid);
    }

    @Override
    public ProductType queryTypeNameByTypeId(int typeid) {
        return productTypeDao.queryProductTypeByTypeId(typeid);
    }

    @Override
    public List<Product> queryProductByTypeId(int typeid) {
        return productTypeDao.queryProductByTypeId(typeid);
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

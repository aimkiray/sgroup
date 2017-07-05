package com.shengdiyage.service.serrviceImplement;

import com.shengdiyage.dao.ProductDao;
import com.shengdiyage.dao.ProductTypeDao;
import com.shengdiyage.dao.implement.ProductDaoImplement;
import com.shengdiyage.dao.implement.ProductTypeDaoImplement;
import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;
import com.shengdiyage.service.ProductService;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductServiceImplement implements ProductService {

    ProductDao productDao = new ProductDaoImplement();
    ProductTypeDao productTypeDao = new ProductTypeDaoImplement();

    @Override
    public int addProduct(Product product) {
        int result = 0;
        if(!product.getPname().equals(productDao.queryProductByName(product.getPname()).getPname())) {
            result = productDao.addProduct(product);
        }
        return result;
   }

    @Override
    public int deleteProduct(int pid) {
        return productDao.deleteProduct(pid);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public Product getProduct(int pid) {
        return productDao.getProduct(pid);
    }

    @Override
    public ProductType getTypeNameByTypeId(int typeid) {
        return productTypeDao.queryProductTypeByTypeId(typeid);
    }

    @Override
    public List<Product> queryProduct() {
        return productDao.queryProduct();
    }

    @Override
    public List<Product> queryProduct(int start, int conut) {
        return productDao.queryProduct(start, conut);
    }
}

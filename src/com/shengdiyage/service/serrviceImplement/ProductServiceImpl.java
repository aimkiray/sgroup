package com.shengdiyage.service.serrviceImplement;

import com.shengdiyage.dao.ProductDao;
import com.shengdiyage.dao.ProductTypeDao;
import com.shengdiyage.dao.implement.ProductDaoImpl;
import com.shengdiyage.dao.implement.ProductTypeDaoImpl;
import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;
import com.shengdiyage.service.ProductService;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDaoImpl();

    /**
     * 调用Dao层添加产品
     * @param product 传入一个商品类，不要用带productId的构造函数
     * @return 返回添加的表单数量，返回0表示添加失败
     */
    @Override
    public int addProduct(Product product) {
        int result = 0;
        Product realProduct = productDao.queryProductByName(product.getProductName());
        if(realProduct == null) {
            List<Product> products = productDao.queryProduct();
            for (int i = 0; i < products.size(); i++) {
                if ((i+1) != products.get(i).getId()){
                    product.setId(i+1);
                    break;
                }
            }
            if (product.getId() == 0) {
                product.setId(products.size()+1);
            }
            result = productDao.addProduct(product);
        }
        return result;
   }

    /**
     * 删除产品
     * @param pid 传入商品的productId
     * @return 返回修改的表单数量，返回0表示修改失败
     */
    @Override
    public int deleteProduct(int pid) {
        return productDao.deleteProduct(pid);
    }

    /**
     * 删除多个产品
     * @param pid 传入多个产品的productId数组
     * @return 返回修改的表单数量，返回0表示修改失败
     */
    @Override
    public int delMulProduct(Integer[] pid) {
        return 0;
    }

    @Override
    public int updateProduct(Product product) {
        int result = 0;
        Product realProduct = productDao.queryProductByName(product.getProductName());
        if (realProduct == null) {
            result = productDao.updateProduct(product);
        } else {
            if (product.getProductId() == realProduct.getProductId()) {
                result = productDao.updateProduct(product);
            } else {
                result = -1;
            }
        }
        return result;
    }

    @Override
    public Product queryProduct(int pid) {
        return productDao.queryProduct(pid);
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

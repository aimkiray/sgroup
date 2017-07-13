package com.shengdiyage.service.serrviceImplement;

import com.shengdiyage.dao.ProductDao;
import com.shengdiyage.dao.implement.ProductDaoImpl;
import com.shengdiyage.entity.Product;
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
            // 按id从小到大输入，如有空缺则填补空缺（不完善，应改成删除某元素，之后的元素id依次递增一位，不过id一行暂且无用）。
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
     * @param pids 传入多个产品的productId数组
     * @return 返回修改的表单数量，返回0表示修改失败
     */
    @Override
    public int delMulProduct(Integer[] pids) {
        return productDao.delMulProduct(pids);
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
    public int queryProductNum(Product product) {
        return productDao.queryProductNum(product);
    }

    @Override
    public List<Product> queryProduct(Product product, int curPage, int pageSize) {
        return productDao.queryProduct(product,(curPage-1)*pageSize, pageSize);
    }

    @Override
    public int queryProductNum() {
        return productDao.queryProductNum();
    }
}

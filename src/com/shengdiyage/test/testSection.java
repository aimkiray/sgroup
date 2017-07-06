package com.shengdiyage.test;

import com.shengdiyage.dao.implement.ProductDaoImplement;
import com.shengdiyage.model.Product;

import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public class testSection {

    public static void main(String[] args) {
        ProductDaoImplement product = new ProductDaoImplement();
//        int result = 0;
//        Product product1 = new Product("路西法",100,1,"勇者");
//        result = product.addProductTypeByType(product1);
//        System.out.println(result);
//        Product product2 = product.queryProduct(3);
//        System.out.println(product2);
//        System.out.println("---------------------");
        List<Product> products = product.queryProduct();
        for(int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }
}

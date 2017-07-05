package com.shengdiyage.view;

import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;
import com.shengdiyage.service.serrviceImplement.ProductTypeServiceImplement;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductTypeUI {

    ProductTypeServiceImplement productTypeServiceImplement = new ProductTypeServiceImplement();

    public void addProductType() {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入要添加的类别：");
        ProductType productType = new ProductType(scan.next());
        if(productTypeServiceImplement.addProductTypeByType(productType) > 0) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败，请检查类别是否存在！");
        }
    }

    public void deleteProduct() {
        Scanner scan = new Scanner(System.in);
        System.out.println("产品类别列表：");
        List<ProductType> productTypes = productTypeServiceImplement.queryAllProductType();
        for(int i = 0; i < productTypes.size(); i++) {
            System.out.println("序号："+productTypes.get(i).getTypeid()+"名称："+productTypes.get(i).getTypename());
        }
        System.out.println("请选择要删除的类别序号：");
        int input = Integer.parseInt(scan.next());
        if(productTypeServiceImplement.deleteProductTypeByTypeId(input) > 0) {
            System.out.println("删除成功！");
        } else {
            System.out.println("删除失败，请检查该类别是否被使用或者该类别是否存在。");
        }
    }

    public void updateProductTypeByTypeId() {
        Scanner scan = new Scanner(System.in);
        System.out.println("产品类别列表：");
        List<ProductType> productTypes = productTypeServiceImplement.queryAllProductType();
        for(int i = 0; i < productTypes.size(); i++) {
            System.out.println("序号："+productTypes.get(i).getTypeid()+"名称："+productTypes.get(i).getTypename());
        }
        System.out.println("请选择要修改的类别序号：");
        int typeid = Integer.parseInt(scan.next());
        System.out.println("请输入修改后的名称：");
        String typename = scan.next();
        ProductType productType = new ProductType(typeid, typename);
        if(productTypeServiceImplement.updateProductTypeByTypeId(productType) > 0) {
            System.out.println("修改成功！");
        } else {
            System.out.println("修改失败，请检查该类别id是否存在。");
        }
    }

    public void queryProductByTypeId() {
        Scanner scan = new Scanner(System.in);
        System.out.println("产品类别列表：");
        List<ProductType> productTypes = productTypeServiceImplement.queryAllProductType();
        for(int i = 0; i < productTypes.size(); i++) {
            System.out.println("序号："+productTypes.get(i).getTypeid()+" 名称："+productTypes.get(i).getTypename());
        }
        System.out.println("请输入要查询的类别序号：");
        int input = Integer.parseInt(scan.next());
        List<Product> products = productTypeServiceImplement.queryProductByTypeId(input);
        for(int i = 0; i < products.size(); i++) {
            System.out.println("序号："+products.get(i).getPid()+" "+products.get(i));
        }
    }

    public void queryAllProduct() {
        System.out.println("产品类别列表：");
        List<ProductType> productTypes = productTypeServiceImplement.queryAllProductType();
        for(int i = 0; i < productTypes.size(); i++) {
            System.out.println("序号："+productTypes.get(i).getTypeid()+" 名称："+productTypes.get(i).getTypename());
        }
    }
}

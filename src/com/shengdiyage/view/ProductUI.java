package com.shengdiyage.view;

import com.shengdiyage.model.Product;
import com.shengdiyage.model.ProductType;
import com.shengdiyage.service.serrviceImplement.ProductServiceImplement;
import com.shengdiyage.service.serrviceImplement.ProductTypeServiceImplement;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Akari on 2017/6/28.
 */
public class ProductUI {

    ProductServiceImplement productserviceimplement = new ProductServiceImplement();
    ProductTypeServiceImplement productTypeServiceImplement = new ProductTypeServiceImplement();

    public void addProduct() {
        Scanner scan = new Scanner(System.in);
        System.out.println("产品类别表：");
        List<ProductType> productTypes = productTypeServiceImplement.queryAllProductType();
        for(int i = 0; i < productTypes.size(); i++) {
            System.out.println("序号："+productTypes.get(i).getTypeid()+"名字："+productTypes.get(i).getTypename());
        }
        System.out.println("请输入要添加的产品信息：");
        System.out.println("名称 价格 数量 所属类别（以空格隔开）");
        Product product = new Product(scan.next(),scan.nextInt(),scan.nextInt(),scan.nextInt());
        if(productserviceimplement.addProduct(product) > 0) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！请检查产品是否存在。");
        }
    }

    public void deleteProduct() {
        Scanner scan = new Scanner(System.in);
        System.out.println("产品列表：");
        List<Product> products = productserviceimplement.queryProduct();
        for(int i = 0; i < products.size(); i++) {
            System.out.println("序号："+products.get(i).getPid()+products.get(i)+", 类别："+productserviceimplement.queryTypeNameByTypeId(products.get(i).getPtype()).getTypename());
        }
        System.out.println("请选择要删除的产品序号：");
        int input = Integer.parseInt(scan.next());
        if(productserviceimplement.deleteProduct(input) > 0) {
            System.out.println("删除成功！");
        }
    }

    public void updateProduct() {
        Scanner scan = new Scanner(System.in);
        System.out.println("产品列表：");
        List<Product> products = productserviceimplement.queryProduct();
        for(int i = 0; i < products.size(); i++) {
            System.out.println("序号："+products.get(i).getPid()+products.get(i)+", 类别："+productserviceimplement.queryTypeNameByTypeId(products.get(i).getPtype()).getTypename());
        }
        System.out.println("请输入要修改的产品序号：");
        int input = Integer.parseInt(scan.next());
        System.out.println("产品类别表：");
        List<ProductType> productTypes = productTypeServiceImplement.queryAllProductType();
        for(int i = 0; i < productTypes.size(); i++) {
            System.out.println("序号："+productTypes.get(i).getTypeid()+"名字："+productTypes.get(i).getTypename());
        }
        System.out.println("请输入要修改的产品信息：");
        System.out.println("名称 价格 数量 所属类别（以空格隔开）");
        Product product = new Product(input, scan.next(),scan.nextInt(),scan.nextInt(),scan.nextInt());
        if(productserviceimplement.updateProduct(product) > 0) {
            System.out.println("修改成功！");
        }
    }

    public void queryProduct() {
        int start = 0;
        int count;
        int flag = 1;
        List<Product> allproducts = productserviceimplement.queryProduct();
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入每页显示的数目：");
        count = Integer.parseInt(scan.next());
        while (true) {
            List<Product> products = productserviceimplement.queryProduct(start, count);
            System.out.println("-------------------------------------");
            for(int i = 0; i < products.size(); i++) {
                System.out.println("序号："+products.get(i).getPid()+products.get(i)+", 类别："+productserviceimplement.queryTypeNameByTypeId(products.get(i).getPtype()).getTypename());
            }
            System.out.println("-------------------------------------");
            System.out.println("当前是第"+flag+"页");
            System.out.println("1.第一页 2.上一页 3.下一页 4.尾页 5.返回");
            int input = Integer.parseInt(scan.next());
            switch (input) {
                case 1:
                    start = 0;
                    flag = 1;
                    break;
                case 2:
                    if(start > count) {
                        start -= count;
                        flag--;
                    } else {
                        start = 0;
                        flag = 1;
                    }
                    break;
                case 3:
                    if (start < allproducts.size()-count) {
                        start += count;
                        flag++;
                    }
                    break;
                case 4:
                    while (start < allproducts.size()-count) {
                        start +=count;
                        flag++;
                    }
                    break;
                default:
                    break;
            }
            if (input == 5) {
                break;
            }
        }
    }
}

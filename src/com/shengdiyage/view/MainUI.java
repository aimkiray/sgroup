package com.shengdiyage.view;

import com.shengdiyage.model.Admin;
import com.shengdiyage.model.Boss;
import com.shengdiyage.service.serrviceImplement.AdminServiceImplement;
import com.shengdiyage.service.serrviceImplement.BossServiceImplement;
import com.shengdiyage.service.serrviceImplement.ProductServiceImplement;

import java.util.Scanner;

/**
 * Created by Akari on 2017/6/28.
 */
public class MainUI {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AdminServiceImplement adminserviceimplement = new AdminServiceImplement();
        BossServiceImplement bossServiceImplement = new BossServiceImplement();
        System.out.println("--------------------------------------------------------");
        System.out.println("----------欢迎来到美国圣地亚戈农资集团仓库管理系统！---------");
        System.out.println("--------------------------------------------------------");
        while (true) {
            System.out.println("1.管理员登陆 2.管理员注册 3.BOSS登陆 4.退出系统");
            System.out.print("请选择：");
            int input = Integer.parseInt(scan.next());
            if (input == 1) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("请输入用户名：");
                String aname = scan.next();
                System.out.println("请输入密码：");
                String apassword = scan.next();
                if(adminserviceimplement.verifyAdmin(new Admin(aname, apassword))) {
                    System.out.println("登陆成功！");
                    AdminOperate(aname);
                } else {
                    System.out.println("登陆失败，请检查用户名和密码！");
                }
            } else if (input == 2) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("请输入用户名：");
                String aname = scan.next();
                System.out.println("请输入密码：");
                String apassword = scan.next();
                Admin admin = new Admin(aname, apassword);
                if(adminserviceimplement.verifyAdmin(admin)) {
                    System.out.println("注册失败，该用户已存在！");
                } else {
                    if(adminserviceimplement.addAdmin(admin) > 0) {
                        System.out.println("注册成功！");
                        AdminOperate(aname);
                    } else {
                        System.out.println("注册失败，请重试！");
                    }
                }
            } else if (input == 3) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("请输入ID：");
                String bname = scan.next();
                System.out.println("请输入密码：");
                String bpassword = scan.next();
                if(bossServiceImplement.verifyBoss(new Boss(bname, bpassword))) {
                    System.out.println("登陆成功！");
                    BossOperate(bname);
                } else {
                    System.out.println("登陆失败，请检查ID和密码！");
                }
            } else if (input == 4) {
                System.out.println("Bye~");
                System.exit(0);
            } else {
                System.out.println("非法输入！");
            }
        }
    }

    public static void BossOperate(String bname) {
        AdminUI adminUI = new AdminUI();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println(bname+",Master,请指示~");
            System.out.println("1.显示所有管理员 2.删除管理员 3.退出登陆 4.删库");
            System.out.print("请选择：");
            String input = scan.next();
            switch (input) {
                case "1":
                    adminUI.queryAllAdmin();
                    break;
                case "2":
                    adminUI.deleteAdminById();
                    break;
                default:
                    break;
            }
            if("3".equals(input)) {
                break;
            }
        }
    }

    public static void AdminOperate(String aname) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("无比尊贵的"+aname+"，请问，你是我的Master吗？（滑稽.jpg）");
            System.out.println("1.产品操作 2.产品类别操作 3.退出登陆");
            System.out.print("请选择：");
            String input = scan.next();
            switch (input) {
                case "1":
                    ProductOperate();
                    break;
                case "2":
                    productTypeOperate();
                    break;
                default:
                    break;
            }
            if("3".equals(input)) {
                break;
            }
        }
    }

    private static void productTypeOperate() {
        Scanner scan = new Scanner(System.in);
        ProductTypeUI productTypeUI = new ProductTypeUI();
        while (true) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("1.增加类别 2.删除类别 3.修改类别 4.显示全部类别 5.按类别查询产品 6.返回上层");
            System.out.print("请选择：");
            String input = scan.next();
            switch (input) {
                case "1":
                    productTypeUI.addProductType();
                    break;
                case "2":
                    productTypeUI.deleteProduct();
                    break;
                case "3":
                    productTypeUI.updateProductTypeByTypeId();
                    break;
                case "4":
                    productTypeUI.queryAllProduct();
                    break;
                case "5":
                    productTypeUI.queryProductByTypeId();
                    break;
                default:
                    break;
            }
            if("6".equals(input)) {
                break;
            }
        }
    }

    public static void ProductOperate() {
        Scanner scan = new Scanner(System.in);
        ProductUI productUI = new ProductUI();
        while (true) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("1.增加产品 2.删除产品 3.修改产品 4.查询产品 5.返回上层");
            System.out.print("请选择：");
            String input = scan.next();
            switch (input) {
                case "1":
                    productUI.addProduct();
                    break;
                case "2":
                    productUI.deleteProduct();
                    break;
                case "3":
                    productUI.updateProduct();
                    break;
                case "4":
                    productUI.queryProduct();
                    break;
                default:
                    break;
            }
            if("5".equals(input)) {
                break;
            }
        }
    }
}

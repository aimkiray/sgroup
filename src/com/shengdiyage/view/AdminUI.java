package com.shengdiyage.view;

import com.shengdiyage.model.Admin;
import com.shengdiyage.model.ProductType;
import com.shengdiyage.service.serrviceImplement.AdminServiceImplement;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Akari on 2017/6/28.
 */
public class AdminUI {

    AdminServiceImplement adminServiceImplement = new AdminServiceImplement();

    public void deleteAdminById() {
        Scanner scan = new Scanner(System.in);
        System.out.println("管理员列表：");
        List<Admin> admins = adminServiceImplement.queryAdmin();
        for(int i = 0; i < admins.size(); i++) {
            System.out.println("序号："+admins.get(i).getAid()+" 名称："+admins.get(i).getAname()+" 密码："+admins.get(i).getApassword());
        }
        System.out.println("请选择要删除的权限狗序号：");
        int input = Integer.parseInt(scan.next());
        if(adminServiceImplement.deleteAdmin(input) > 0) {
            System.out.println("删除成功！");
        }
    }

    public void queryAllAdmin() {
        System.out.println("管理员列表：");
        List<Admin> admins = adminServiceImplement.queryAdmin();
        for(int i = 0; i < admins.size(); i++) {
            System.out.println("序号："+admins.get(i).getAid()+" 名称："+admins.get(i).getAname()+" 密码："+admins.get(i).getApassword());
        }
    }
}

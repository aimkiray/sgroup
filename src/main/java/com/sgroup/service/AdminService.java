package com.sgroup.service;

import com.sgroup.entity.Admin;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public interface AdminService {
    /**
     * 增加管理员
     * @param admin
     * @return
     */
    int addAdmin(Admin admin);

    /**
     * 删除鹳狸猿
     * @param adminId
     * @return
     */
    int deleteAdmin(int adminId);

    /**
     * 通过名字查询管理员
     * @param adminName
     * @return
     */
    Admin queryAdminByName(String adminName);

    /**
     * 管理员列表
     * @return
     */
    List<Admin> queryAdmin();

    /**
     * 管理员登陆验证
     * @return
     */
    boolean verifyAdmin(Admin admin);
}

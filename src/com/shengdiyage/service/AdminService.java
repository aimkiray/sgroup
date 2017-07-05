package com.shengdiyage.service;

import com.shengdiyage.model.Admin;

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
     * @param aid
     * @return
     */
    int deleteAdmin(int aid);

    /**
     * 查询管理员
     * @param aid
     * @return
     */
    List<Admin> queryAdmin(int aid);

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

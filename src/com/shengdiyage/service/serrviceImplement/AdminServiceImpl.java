package com.shengdiyage.service.serrviceImplement;

import com.shengdiyage.dao.AdminDao;
import com.shengdiyage.dao.implement.AdminDaoImpl;
import com.shengdiyage.entity.Admin;
import com.shengdiyage.service.AdminService;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = new AdminDaoImpl();

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @Override
    public int addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    /**
     * 通过id删除管理员
     * @param adminId
     * @return
     */
    @Override
    public int deleteAdmin(int adminId) {
        return adminDao.deleteAdmin(adminId);
    }

    /**
     * 通过名字获得管理员
     * @param adminName
     * @return
     */
    @Override
    public Admin queryAdminByName(String adminName) {
        return adminDao.queryAdminByName(adminName);
    }

    /**
     * 查询全部管理员
     * @return
     */
    @Override
    public List<Admin> queryAdmin() {
        return adminDao.queryAdmin();
    }

    /**
     * 查询管理员是否存在，用于登陆验证。
     * @param admin
     * @return
     */
    @Override
    public boolean verifyAdmin(Admin admin) {
        return adminDao.verifyAdmin(admin);
    }
}

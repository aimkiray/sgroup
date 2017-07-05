package com.shengdiyage.service.serrviceImplement;

import com.shengdiyage.dao.AdminDao;
import com.shengdiyage.dao.implement.AdminDaoImplement;
import com.shengdiyage.model.Admin;
import com.shengdiyage.service.AdminService;

import java.util.List;

/**
 * Created by Akari on 2017/6/28.
 */
public class AdminServiceImplement implements AdminService {

    AdminDao adminDao = new AdminDaoImplement();

    @Override
    public int addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    @Override
    public int deleteAdmin(int aid) {
        return adminDao.deleteAdmin(aid);
    }

    @Override
    public List<Admin> queryAdmin(int aid) {
        return null;
    }

    @Override
    public List<Admin> queryAdmin() {
        return adminDao.queryAdmin();
    }

    @Override
    public boolean verifyAdmin(Admin admin) {
        return adminDao.verifyAdmin(admin);
    }
}

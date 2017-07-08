package com.shengdiyage.service.serrviceImplement;

import com.shengdiyage.dao.AdminDao;
import com.shengdiyage.dao.implement.AdminDaoImpl;
import com.shengdiyage.model.Admin;
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
     * @param aid
     * @return
     */
    @Override
    public int deleteAdmin(int aid) {
        return adminDao.deleteAdmin(aid);
    }

    /**
     * 通过名字获得管理员
     * @param aname
     * @return
     */
    @Override
    public Admin queryAdminByName(String aname) {
        return adminDao.queryAdminByName(aname);
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

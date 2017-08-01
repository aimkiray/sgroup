package com.shengdiyage.dao.implement;

import com.shengdiyage.dao.AdminDao;
import com.shengdiyage.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public class AdminDaoImpl extends BaseDao implements AdminDao{
    /**
     * 增加管理员
     * @param admin
     * @return
     */
    @Override
    public int addAdmin(Admin admin) {
        int result = 0;
        String sql = "INSERT INTO admin(aname, apassword) VALUES (?,?)";
        Object[] objects = {admin.getAdminName(),admin.getAdminPassword()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 修改密码
     * @param admin
     * @return
     */
    @Override
    public int UpdateAdmin(Admin admin) {
        int result = 0;
        String sql = "UPDATE admin set aname = ?, apassword = ? WHERE aid = ?";
        Object[] objects = {admin.getAdminName(),admin.getAdminPassword()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 删除管理员
     * @param adminId
     * @return
     */
    @Override
    public int deleteAdmin(int adminId) {
        int result = 0;
        String sql = "DELETE FROM admin WHERE aid = ?";
        Object[] objects = {adminId};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 管理员列表
     * @return
     */
    @Override
    public List<Admin> queryAdmin() {
        List<Admin> admins = new ArrayList<Admin>();
        String sql = "SELECT * FROM admin ORDER BY aid ASC";
        Object[] objects = {};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            while (rs.next()) {
                admins.add(new Admin(rs.getInt("aid"), rs.getString("aname"), rs.getString("apassword")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return admins;
    }

    /**
     * 通过名字查找管理员
     * @param adminName
     * @return
     */
    @Override
    public Admin queryAdminByName(String adminName) {
        String sql = "SELECT * FROM admin WHERE aname = ?";
        Object[] objects = {adminName};
        ResultSet rs = super.executeQuery(sql, objects);
        Admin admin = null;
        try {
            if (rs.next()) {
                admin = new Admin(rs.getInt("aid"), rs.getString("aname"), rs.getString("apassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return admin;
    }

    /**
     * 管理员登陆验证
     * @return
     */
    @Override
    public boolean verifyAdmin(Admin admin) {
        boolean result = false;
        String sql = "SELECT aname FROM admin WHERE aname = ? AND apassword = ?";
        Object[] objects = {admin.getAdminName(), admin.getAdminPassword()};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            if(rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }
}

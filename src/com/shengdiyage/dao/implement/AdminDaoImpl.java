package com.shengdiyage.dao.implement;

import com.shengdiyage.dao.AdminDao;
import com.shengdiyage.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public class AdminDaoImpl extends BaseDao implements AdminDao{
    @Override
    public int addAdmin(Admin admin) {
        int result = 0;
        String sql = "INSERT INTO admin(aname, apassword) VALUES (?,?)";
        Object[] objects = {admin.getAname(),admin.getApassword()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public int UpdateAdmin(Admin admin) {
        int result = 0;
        String sql = "UPDATE admin set aname = ?, apassword = ? WHERE aid = ?";
        Object[] objects = {admin.getAname(),admin.getApassword()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public int deleteAdmin(int aid) {
        int result = 0;
        String sql = "DELETE FROM admin WHERE aid = ?";
        Object[] objects = {aid};
        result = super.executeUpdate(sql,objects);
        return result;
    }

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
            super.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public Admin queryAdminByName(String aname) {
        String sql = "SELECT * FROM admin WHERE aname = ?";
        Object[] objects = {aname};
        ResultSet rs = super.executeQuery(sql, objects);
        Admin admin = null;
        try {
            if (rs.next()) {
                admin = new Admin(rs.getInt("aid"), rs.getString("aname"), rs.getString("apassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean verifyAdmin(Admin admin) {
        boolean result = false;
        String sql = "SELECT aname FROM admin WHERE aname = ? AND apassword = ?";
        Object[] objects = {admin.getAname(), admin.getApassword()};
        ResultSet rs = super.executeQuery(sql, objects);
        try {
            if(rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

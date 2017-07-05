package com.shengdiyage.dao.implement;

import com.shengdiyage.dao.BossDao;
import com.shengdiyage.model.Boss;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Akari on 2017/6/29.
 */
public class BossDaoImplement extends BaseDao implements BossDao {
    @Override
    public int addBoss(Boss boss) {
        int result = 0;
        String sql = "INSERT INTO boss(bname, bpassword) VALUES (?,?)";
        Object[] objects = {boss.getBname(),boss.getBpassword()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public int UpdateBoss(Boss boss) {
        return 0;
    }

    @Override
    public List<Boss> queryBoss() {
        return null;
    }

    @Override
    public boolean verifyBoss(Boss boss) {
        boolean result = false;
        String sql = "SELECT bname FROM boss WHERE bname = ? AND bpassword = ?";
        Object[] objects = {boss.getBname(), boss.getBpassword()};
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

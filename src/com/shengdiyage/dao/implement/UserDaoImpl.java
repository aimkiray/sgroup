package com.shengdiyage.dao.implement;

import com.shengdiyage.dao.UserDao;
import com.shengdiyage.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public class UserDaoImpl extends BaseDao implements UserDao{
    /**
     * 添加用户
     * @param user 要添加的用户
     * @return
     */
    @Override
    public int addUser(User user) {
        int result = 0;
        String sql = "INSERT INTO user(user_name, user_password, realName, email, gender, date) VALUES (?,?,?,?,?,?)";
        Object[] objects = {user.getUserName(), user.getUserPassword(), user.getRealName(), user.getEmail(), user.getGender(), user.getSignDate()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public int UpdateUser(User user) {
        int result = 0;
        String sql = "UPDATE user set user_name = ?, user_password = ?, realName = ?, email = ?, gender = ?, date = ? WHERE userId = ?";
        Object[] objects = {user.getUserName(), user.getUserPassword(), user.getRealName(), user.getEmail(), user.getGender(), user.getSignDate(), user.getUserId()};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public int deleteUser(String userId) {
        int result = 0;
        String sql = "DELETE FROM user WHERE userId = ?";
        Object[] objects = {userId};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    @Override
    public List<User> queryUser() {
        String sql = "SELECT * FROM user ORDER BY aid ASC";
        Object[] objects = {};
        ResultSet rs = super.executeQuery(sql, objects);
        return getAllUser(rs);
    }

    @Override
    public User queryUserByName(String userName) {
        String sql = "SELECT * FROM user WHERE user_name = ?";
        Object[] objects = {userName};
        ResultSet rs = super.executeQuery(sql, objects);

        return getOneUser(rs);
    }

    @Override
    public boolean verifyUser(User user) {
        boolean result = false;
        String sql = "SELECT user_name FROM user WHERE user_name = ? AND user_password = ?";
        Object[] objects = {user.getUserName(), user.getUserPassword()};
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

    public List<User> getAllUser(ResultSet resultSet) {
        List<User> users = new ArrayList<User>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getString("userId"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserPassword(resultSet.getString("user_password"));
                user.setRealName(resultSet.getString("realName"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getInt("gender"));
                user.setSignDate(resultSet.getDate("date"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return users;
    }

    public User getOneUser(ResultSet resultSet) {
        User user = null;
        try {
            while (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getString("userId"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserPassword(resultSet.getString("user_password"));
                user.setRealName(resultSet.getString("realName"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getInt("gender"));
                user.setSignDate(resultSet.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return user;
    }
}

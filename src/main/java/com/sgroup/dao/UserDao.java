package com.sgroup.dao;

import com.sgroup.entity.User;

import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public interface UserDao {
    /**
     * 增加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    int UpdateUser(User user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUser(String userId);

    /**
     * 用户列表
     * @return
     */
    List<User> queryUser();

    /**
     * 通过名字查找用户
     * @param userName
     * @return
     */
    User queryUserByName(String userName);

    /**
     * 用户登陆验证
     * @return
     */
    boolean verifyUser(User user);
}

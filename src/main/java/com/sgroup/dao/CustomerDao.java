package com.sgroup.dao;

import com.sgroup.entity.Customer;

import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public interface CustomerDao {
    /**
     * 添加客户
     * @param customer 要添加的客户
     * @return 返回添加执行的条目数，0为失败
     */
    int addCustomer(Customer customer);

    /**
     * 删除客户
     * @param customerId 客户id
     * @return 返回修改的条目数，0为失败
     */
    int delCustomer(int customerId);

    /**
     * 修改客户
     * @param customer 要修改的客户
     * @return 返回修改执行的条目数，0为失败
     */
    int editCustomer(Customer customer);

    /**
     * 分页查询客户
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的客户列表
     */
    List<Customer> queryCustomer(int start, int limit);

    /**
     * 查询客户总数，用于分页
     * @return 客户总数
     */
    int queryCustomerNum();

    /**
     * 通过Id查询客户
     * @param customerId
     * @return
     */
    Customer queryCustomer(int customerId);
}

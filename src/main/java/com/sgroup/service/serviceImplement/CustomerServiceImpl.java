package com.sgroup.service.serviceImplement;

import com.sgroup.dao.CustomerDao;
import com.sgroup.dao.implement.CustomerDaoImpl;
import com.sgroup.entity.Customer;
import com.sgroup.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpl();

    /**
     * 添加客户
     *
     * @param customer 要添加的客户
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    /**
     * 删除客户
     *
     * @param customerId 客户id
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int delCustomer(int customerId) {
        return customerDao.delCustomer(customerId);
    }

    /**
     * 修改客户
     *
     * @param customer 要修改的客户
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int editCustomer(Customer customer) {
        return customerDao.editCustomer(customer);
    }

    /**
     * 分页查询客户
     *
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的客户列表
     */
    @Override
    public List<Customer> queryCustomer(int start, int limit) {
        return customerDao.queryCustomer(start, limit);
    }

    /**
     * 查询客户总数，用于分页
     *
     * @return 客户总数
     */
    @Override
    public int queryCustomerNum() {
        return customerDao.queryCustomerNum();
    }

    /**
     * 通过Id查询客户
     *
     * @param customerId
     * @return
     */
    @Override
    public Customer queryCustomer(int customerId) {
        return customerDao.queryCustomer(customerId);
    }
}

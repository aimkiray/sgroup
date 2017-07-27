package com.shengdiyage.dao.implement;

import com.shengdiyage.dao.CustomerDao;
import com.shengdiyage.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    /**
     * 添加客户
     * @param customer 要添加的客户
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addCustomer(Customer customer) {
        int result = 0;
        String sql = "INSERT INTO customer(customerID, customer_name) VALUES(?, ?)";
        Object[] objects = {customer.getCustomerId(), customer.getCustomerName()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 删除客户
     * @param customerId 客户id
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int delCustomer(int customerId) {
        int result = 0;
        String sql = "DELETE FROM customer WHERE customerID = ?";
        Object[] objects = {customerId};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 修改客户
     * @param customer 要修改的客户
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int editCustomer(Customer customer) {
        int result = 0;
        String sql = "UPDATE customer SET customer_name = ? WHERE customerID = ?";
        Object[] objects = {customer.getCustomerName(), customer.getCustomerId()};
        result = super.executeUpdate(sql, objects);
        return result;
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
        String sql = "SELECT * FROM customer LIMIT ?,?";
        Object[] objects = {start, limit};
        ResultSet resultSet = super.executeQuery(sql, objects);
        return getCustomerList(resultSet);
    }

    /**
     * 查询客户总数，用于分页
     *
     * @return 客户总数
     */
    @Override
    public int queryCustomerNum() {
        int result = 0;
        String sql = "SELECT COUNT(customerID) FROM customer";
        Object[] objects = {};
        ResultSet resultSet = super.executeQuery(sql, objects);
        try {
            if(resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return result;
    }

    /**
     * 通过Id查询客户
     *
     * @param customerId
     * @return
     */
    @Override
    public Customer queryCustomer(int customerId) {
        String sql = "SELECT * FROM customer WHERE customerID = ?";
        Object[] objects = {customerId};
        ResultSet rs = super.executeQuery(sql,objects);
        return getOneCustomer(rs);
    }

    /**
     * 从结果集中获取客户列表
     * @param resultSet
     * @return
     */
    private List<Customer> getCustomerList(ResultSet resultSet) {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customerID"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return customers;
    }

    /**
     * 获取客户
     * @param resultSet
     * @return
     */
    private Customer getOneCustomer(ResultSet resultSet) {
        Customer customer = null;
        try {
            if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customerID"));
                customer.setCustomerName(resultSet.getString("customer_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return customer;
    }
}

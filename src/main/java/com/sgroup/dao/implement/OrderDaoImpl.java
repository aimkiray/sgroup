package com.sgroup.dao.implement;

import com.sgroup.dao.CustomerDao;
import com.sgroup.dao.EmployeeDao;
import com.sgroup.dao.OrderDao;
import com.sgroup.entity.Customer;
import com.sgroup.entity.Employee;
import com.sgroup.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    private CustomerDao customerDao = new CustomerDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    /**
     * 添加订单
     * @param order 要添加的订单
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addOrder(Order order) {
        int result = 0;
        String sql = "INSERT INTO order_info(orderID, order_date, customerID, empID) VALUES(?, ?, ?, ?)";
        Object[] objects = {order.getOrderId(), order.getOrderDate(), order.getCustomer().getCustomerId(), order.getEmployee().getEmpId()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 删除订单
     * @param orderId 订单id
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int delOrder(String orderId) {
        int result = 0;
        String sql = "DELETE FROM order_info WHERE orderID = ?";
        Object[] objects = {orderId};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 修改订单
     * @param order 要修改的订单
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int editOrder(Order order) {
        int result = 0;
        String sql = "UPDATE order_info SET order_date = ?, customerID = ?, empID = ? WHERE orderID = ?";
        Object[] objects = {order.getOrderDate(), order.getCustomer().getCustomerId(), order.getEmployee().getEmpId(), order.getOrderId()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 分页查询订单
     *
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的订单列表
     */
    @Override
    public List<Order> queryOrder(int start, int limit) {
        String sql = "SELECT * FROM order_info LIMIT ?,?";
        Object[] objects = {start, limit};
        ResultSet resultSet = super.executeQuery(sql, objects);
        return getOrderList(resultSet);
    }

    /**
     * 通过orderId查询Order
     *
     * @param orderId
     * @return
     */
    @Override
    public Order queryOrderById(String orderId) {
        String sql = "SELECT * FROM order_info WHERE orderID = ?";
        Object[] objects = {orderId};
        ResultSet rs = super.executeQuery(sql,objects);
        return getOneOrder(rs);
    }

    /**
     * 查询订单总数，用于分页
     *
     * @return 订单总数
     */
    @Override
    public int queryOrderNum() {
        int result = 0;
        String sql = "SELECT COUNT(orderID) FROM order_info";
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
     * 从结果集中获取订单列表
     * @param resultSet
     * @return
     */
    private List<Order> getOrderList(ResultSet resultSet) {
        List<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                Customer customer = customerDao.queryCustomer(resultSet.getInt("customerID"));
                Employee employee = employeeDao.queryEmployee(resultSet.getInt("empID"));
                order.setCustomer(customer);
                order.setEmployee(employee);
                order.setOrderDate(resultSet.getDate("order_date"));
                order.setOrderId(resultSet.getString("orderID"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return orders;
    }

    /**
     * 从结果中获取订单
     * @param resultSet
     * @return
     */
    private Order getOneOrder(ResultSet resultSet) {
        Order order = null;
        try {
            if (resultSet.next()) {
                order = new Order();
                Customer customer = customerDao.queryCustomer(resultSet.getInt("customerID"));
                Employee employee = employeeDao.queryEmployee(resultSet.getInt("empID"));
                order.setCustomer(customer);
                order.setEmployee(employee);
                order.setOrderDate(resultSet.getDate("order_date"));
                order.setOrderId(resultSet.getString("orderID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return order;
    }
}

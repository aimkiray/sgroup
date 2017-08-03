package com.sgroup.service.serviceImplement;

import com.sgroup.dao.OrderDao;
import com.sgroup.dao.implement.OrderDaoImpl;
import com.sgroup.entity.Order;
import com.sgroup.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    // 初始化Dao层

    OrderDao orderDao = new OrderDaoImpl();

    /**
     * 添加订单
     *
     * @param order 要添加的订单
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单id
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int delOrder(String orderId) {
        return orderDao.delOrder(orderId);
    }

    /**
     * 修改订单
     *
     * @param order 要修改的订单
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int editOrder(Order order) {
        return orderDao.editOrder(order);
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
        return orderDao.queryOrder(start, limit);
    }

    /**
     * 查询订单总数，用于分页
     *
     * @return 订单总数
     */
    @Override
    public int queryOrderNum() {
        return orderDao.queryOrderNum();
    }

    /**
     * 通过orderId查询Order
     *
     * @param orderId
     * @return
     */
    @Override
    public Order queryOrderById(String orderId) {
        return orderDao.queryOrderById(orderId);
    }
}

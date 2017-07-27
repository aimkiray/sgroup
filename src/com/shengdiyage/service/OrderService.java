package com.shengdiyage.service;

import com.shengdiyage.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * 添加订单
     * @param order 要添加的订单
     * @return 返回添加执行的条目数，0为失败
     */
    int addOrder(Order order);

    /**
     * 删除订单
     * @param orderId 订单id
     * @return 返回修改的条目数，0为失败
     */
    int delOrder(String orderId);

    /**
     * 修改订单
     * @param order 要修改的订单
     * @return 返回修改执行的条目数，0为失败
     */
    int editOrder(Order order);

    /**
     * 分页查询订单
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的订单列表
     */
    List<Order> queryOrder(int start, int limit);

    /**
     * 查询订单总数，用于分页
     * @return 订单总数
     */
    int queryOrderNum();

    /**
     * 通过orderId查询Order
     * @param orderId
     * @return
     */
    Order queryOrderById (String orderId);
}

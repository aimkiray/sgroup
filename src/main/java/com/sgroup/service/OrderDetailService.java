package com.sgroup.service;

import com.sgroup.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    /**
     * 添加订单详情
     * @param orderDetail 要添加的订单详情
     * @return 返回添加执行的条目数，0为失败
     */
    int addOrderDetail(OrderDetail orderDetail);

    /**
     * 删除订单详情
     * @param orderDetailId 订单详情id
     * @return 返回修改的条目数，0为失败
     */
    int delOrderDetail(int orderDetailId);

    /**
     * 修改订单详情
     * @param orderDetail 要修改的订单详情
     * @return 返回修改执行的条目数，0为失败
     */
    int editOrderDetail(OrderDetail orderDetail);

    /**
     * 分页查询订单详情
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的订单详情列表
     */
    List<OrderDetail> queryOrderDetail(int start, int limit);

    /**
     * 查询订单详情总数，用于分页
     * @return 订单详情总数
     */
    int queryOrderDetailNum();

    /**
     * 查询产品id下的所有订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> queryOrderDetailByOrderId(String orderId);
}

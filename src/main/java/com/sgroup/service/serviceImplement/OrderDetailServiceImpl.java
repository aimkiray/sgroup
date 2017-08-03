package com.sgroup.service.serviceImplement;

import com.sgroup.dao.OrderDetailDao;
import com.sgroup.dao.implement.OrderDetailDaoImpl;
import com.sgroup.entity.OrderDetail;
import com.sgroup.service.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    /**
     * 添加订单详情
     *
     * @param orderDetail 要添加的订单详情
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addOrderDetail(OrderDetail orderDetail) {
        return orderDetailDao.addOrderDetail(orderDetail);
    }

    /**
     * 删除订单详情
     *
     * @param orderDetailId 订单详情id
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int delOrderDetail(int orderDetailId) {
        return orderDetailDao.delOrderDetail(orderDetailId);
    }

    /**
     * 修改订单详情
     *
     * @param orderDetail 要修改的订单详情
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int editOrderDetail(OrderDetail orderDetail) {
        return orderDetailDao.editOrderDetail(orderDetail);
    }

    /**
     * 分页查询订单详情
     *
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的订单详情列表
     */
    @Override
    public List<OrderDetail> queryOrderDetail(int start, int limit) {
        return orderDetailDao.queryOrderDetail(start, limit);
    }

    /**
     * 查询订单详情总数，用于分页
     *
     * @return 订单详情总数
     */
    @Override
    public int queryOrderDetailNum() {
        return orderDetailDao.queryOrderDetailNum();
    }

    /**
     * 查询产品id下的所有订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderDetail> queryOrderDetailByOrderId(String orderId) {
        return orderDetailDao.queryOrderDetailByOrderId(orderId);
    }
}

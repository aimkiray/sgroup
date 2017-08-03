package com.sgroup.dao.implement;

import com.sgroup.dao.OrderDetailDao;
import com.sgroup.dao.ProductDao;
import com.sgroup.entity.OrderDetail;
import com.sgroup.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl extends BaseDao implements OrderDetailDao {

    ProductDao productDao = new ProductDaoImpl();
    /**
     * 添加订单
     * @param orderDetail 要添加的订单
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addOrderDetail(OrderDetail orderDetail) {
        int result = 0;
        String sql = "INSERT INTO order_detail(orderID, productID, quantity, discount) VALUES(?, ?, ?, ?)";
        Object[] objects = {orderDetail.getOrderId(), orderDetail.getProduct().getProductId(), orderDetail.getQuantity(), orderDetail.getDiscount()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 删除订单
     * @param orderDetailId 订单id
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int delOrderDetail(int orderDetailId) {
        int result = 0;
        String sql = "DELETE FROM order_detail WHERE id = ?";
        Object[] objects = {orderDetailId};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 修改订单
     * @param orderDetail 要修改的订单
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int editOrderDetail(OrderDetail orderDetail) {
        int result = 0;
        String sql = "UPDATE order SET orderID = ?, productID = ?, quantity = ?, discount = ? WHERE id = ?";
        Object[] objects = {orderDetail.getOrderId(), orderDetail.getProduct().getProductId(), orderDetail.getQuantity(), orderDetail.getDiscount(), orderDetail.getId()};
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
    public List<OrderDetail> queryOrderDetail(int start, int limit) {
        String sql = "SELECT * FROM order_detail LIMIT ?,?";
        Object[] objects = {start, limit};
        ResultSet resultSet = super.executeQuery(sql, objects);
        return getOrderDetailList(resultSet);
    }

    /**
     * 查询订单总数，用于分页
     *
     * @return 订单总数
     */
    @Override
    public int queryOrderDetailNum() {
        int result = 0;
        String sql = "SELECT COUNT(id) FROM order_detail";
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
     * 查询订单id下的所有订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderDetail> queryOrderDetailByOrderId(String orderId) {
        String sql = "SELECT * FROM order_detail WHERE orderID = ?";
        Object[] objects = {orderId};
        ResultSet rs = super.executeQuery(sql, objects);
        return getOrderDetailList(rs);
    }

    /**
     * 从结果集中获取订单列表
     * @param resultSet
     * @return
     */
    private List<OrderDetail> getOrderDetailList(ResultSet resultSet) {
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        try {
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(resultSet.getInt("id"));
                orderDetail.setOrderId(resultSet.getString("orderID"));
                Product product = productDao.queryProduct(resultSet.getInt("productID"));
                orderDetail.setProduct(product);
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setDiscount(resultSet.getInt("discount"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return orderDetails;
    }
}

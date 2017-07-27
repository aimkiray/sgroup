package com.shengdiyage.entity;

import java.util.Date;

public class Order {
    private String orderId;
    private Date orderDate;
    private Customer customer;
    private Employee employee;

    public Order() {
    }

    public Order(String orderId, Date orderDate, Customer customer, Employee employee) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

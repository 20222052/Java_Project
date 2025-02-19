package com.example.demo.service;

import com.example.demo.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAllOrderDetail();
    List<OrderDetail> findByOrderId(Integer orderId);
    List<OrderDetail> findByCustomerId(Integer customerId);
    void insert(OrderDetail orderDetail);
    void update(OrderDetail orderDetail);
    void delete(OrderDetail orderDetail);
}

package com.example.demo.service.Impl;

import com.example.demo.model.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository repo;

    @Override
    public List<OrderDetail> findAllOrderDetail() {
        return List.of();
    }

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        return repo.findOrderDetailsByOrderId(orderId);
    }

    @Override
    public List<OrderDetail> findByCustomerId(Integer customerId) {
        return null;
    }

    @Override
    public void insert(OrderDetail orderDetail) {
        repo.save(orderDetail);
    }

    @Override
    public void update(OrderDetail orderDetail) {

    }

    @Override
    public void delete(OrderDetail orderDetail) {

    }
}

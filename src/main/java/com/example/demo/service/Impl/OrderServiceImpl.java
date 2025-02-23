package com.example.demo.service.Impl;


import com.example.demo.model.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.getById(id);
    }

    @Override
    public void deleteById(Integer order_id) {
        orderRepository.deleteById(order_id);
    }

    @Override
    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateById(Integer id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }
}

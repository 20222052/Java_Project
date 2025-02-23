package com.example.demo.service;


import com.example.demo.model.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Integer id);
    void deleteById(Integer order_id);
    Order insert(Order order);
    Order updateById(Integer id, Order order);

}

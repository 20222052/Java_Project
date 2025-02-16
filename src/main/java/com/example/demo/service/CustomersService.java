package com.example.demo.service;

import com.example.demo.model.entity.customers;

import java.util.List;

public interface CustomersService {
    List<customers> findAll();
    customers getById(Integer id);
    void save(customers customer);
    void update(customers customer);
    void delete(customers customer);
    void deleteById(Integer id);

}

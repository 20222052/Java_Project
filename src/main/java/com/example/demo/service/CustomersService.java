package com.example.demo.service;

import com.example.demo.model.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomersService {
    List<Customer> findAll();
    Customer getById(Integer id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    void deleteById(Integer id);
    Customer getByEmail(String email);
    Customer getByPhone(String phone);
    Page<Customer> getCustomer(String name,int page, int size, String sortField, String sortDirection);
}

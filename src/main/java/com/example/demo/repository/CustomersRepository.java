package com.example.demo.repository;

import com.example.demo.model.entity.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
    Customer findByPhone(String phone);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByNameContainingIgnoreCase(String name, Pageable pageable);
}


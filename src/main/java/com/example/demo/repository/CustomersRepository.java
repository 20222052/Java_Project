package com.example.demo.repository;

import com.example.demo.model.entity.customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<customers, Integer> {
}

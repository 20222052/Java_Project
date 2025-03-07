package com.example.demo.service.Impl;

import com.example.demo.model.entity.Customer;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomersServiceImpl  implements CustomersService {
    @Autowired
    private CustomersRepository customersRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public List<Customer> findAll() {
        return customersRepository.findAll();
    }

    @Override
    public Customer getById(Integer id) {
        return customersRepository.getById(id);
    }

    @Override
    public void save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customersRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customersRepository.delete(customer);
    }

    @Override
    public void deleteById(Integer id) {
        customersRepository.deleteById(id);
    }

    @Override
    public Customer getByEmail(String email) {
       return customersRepository.findByEmail(email);
    }

    @Override
    public void update(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customersRepository.save(customer);
    }
}

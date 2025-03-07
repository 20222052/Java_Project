package com.example.demo.repository;

import com.example.demo.model.entity.Blog;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Favorite;
import com.example.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByCustomerId(Integer cusId);
    boolean existsByCustomerAndProduct(Customer customer, Product product);
    Favorite findByCustomerAndProduct(Customer customer, Product product);
    Favorite findTopByCustomerAndProduct(Customer customer, Product product);

}


package com.example.demo.repository;

import com.example.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findTop4ByOrderByCreatedAtDesc();
    List<Product> findTop4ByOrderByCreatedAtAsc();
    List<Product> findTop6ByOrderByCreatedAtAsc();

    @Query(value = "SELECT * FROM products WHERE sale_price < price ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Product> findProductsSale();

    List<Product> findByCategoryIdOrderByCreatedAtDesc(Integer categoryId);
    List<Product> findByCategoryIdOrderByIdAsc(Integer categoryId);
    List<Product> findByCategoryIdOrderByIdDesc(Integer categoryId);
    List<Product> findByCategoryIdOrderByNameAsc(Integer categoryId);
    List<Product> findByCategoryIdOrderByNameDesc(Integer categoryId);
}

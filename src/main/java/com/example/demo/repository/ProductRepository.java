package com.example.demo.repository;

import com.example.demo.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByCategoryId(int categoryId);
    List<Product> findTop4ByOrderByCreatedAtDesc();
    List<Product> findTop4ByOrderByCreatedAtAsc();
    List<Product> findTop6ByOrderByCreatedAtAsc();
    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    @Query(value = "SELECT * FROM products WHERE sale_price < price ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Product> findProductsSale();

    List<Product> findByCategoryIdOrderByCreatedAtDesc(Integer categoryId);
    List<Product> findByCategoryIdOrderByIdAsc(Integer categoryId);
    List<Product> findByCategoryIdOrderByIdDesc(Integer categoryId);
    List<Product> findByCategoryIdOrderByNameAsc(Integer categoryId);
    List<Product> findByCategoryIdOrderByNameDesc(Integer categoryId);
    List<Product> findProductsByName(String name);
    List<Product> findByNameContainingIgnoreCase(String keyword);

}

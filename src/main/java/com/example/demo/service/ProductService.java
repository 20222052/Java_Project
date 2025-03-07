package com.example.demo.service;

import com.example.demo.model.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProductsByCategorySorted(Integer categoryId, String order);
    List<Product> getLatestProducts();
    List<Product> getAllProducts();
    List<Product> getProductsSortedBy(String sort);
    List<Product> getProductsSale();
    List<Product> getTop6Products();
    Product getProductById(Integer id);
    Product insertProduct(Product product);
    Product  updateProduct(Product product);
    void deleteProductById(Integer id);
    Page<Product> getProductsByCategory(Integer categoryId, int page, int size);
    Optional<Product> getById(Integer id);
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    void deleteById(Integer id);
    List<Product> getProductsByName(String name);
}

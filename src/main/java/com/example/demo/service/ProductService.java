package com.example.demo.service;

import com.example.demo.model.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsSortedBy(String sort);
    List<Product> getProductsSale();
    List<Product> getTop6Products();
    Product getProductById(Integer id);
    Product insertProduct(Product product);
    Product  updateProduct(Product product);
    void deleteProductById(Integer id);
}

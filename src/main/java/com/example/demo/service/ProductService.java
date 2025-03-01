package com.example.demo.service;

import com.example.demo.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product insertProduct(Product product);
    Product  updateProduct(Product product);
    void deleteProductById(Integer id);
}

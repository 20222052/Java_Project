package com.example.demo.service;

import com.example.demo.model.entity.Product;

import java.util.List;

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
}

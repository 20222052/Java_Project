package com.example.demo.service.Impl;

import com.example.demo.model.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.getById(id);
    }

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {


        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findProductsByname(name);
    }
}

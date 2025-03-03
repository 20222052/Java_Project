package com.example.demo.service.Impl;

import com.example.demo.model.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Product> getProductsSortedBy(String sort) {
        if ("desc".equalsIgnoreCase(sort)) {
            return productRepository.findTop4ByOrderByCreatedAtDesc();
        } else {
            return productRepository.findTop4ByOrderByCreatedAtAsc();
        }
    }

    @Override
    public List<Product> getProductsSale() {
        return productRepository.findProductsSale();
    }

    @Override
    public List<Product> getTop6Products() {
        return productRepository.findTop6ByOrderByCreatedAtAsc();
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
    public void deleteProductById(Integer proid) {
        productRepository.deleteById(proid);
    }


}

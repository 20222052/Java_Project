package com.example.demo.service.Impl;

import com.example.demo.model.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductsByCategorySorted(Integer categoryId, String order) {
        switch (order) {
            case "id-asc":
                return productRepository.findByCategoryIdOrderByIdAsc(categoryId);
            case "id-desc":
                return productRepository.findByCategoryIdOrderByIdDesc(categoryId);
            case "name-asc":
                return productRepository.findByCategoryIdOrderByNameAsc(categoryId);
            case "name-desc":
                return productRepository.findByCategoryIdOrderByNameDesc(categoryId);
            default:
                return productRepository.findByCategoryIdOrderByCreatedAtDesc(categoryId);
        }
    }

    public List<Product> getLatestProducts() {
        return productRepository.findTop6ByOrderByCreatedAtAsc();
    }

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

    @Override
    public Page<Product> getProductsByCategory(Integer categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (categoryId == 0){
            return productRepository.findAll(pageable);
        }
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }


}

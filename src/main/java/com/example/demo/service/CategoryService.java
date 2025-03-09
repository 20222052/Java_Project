package com.example.demo.service;

import com.example.demo.model.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    Category save(Category category);
    Category update(Category category);
    void deleteById(int id);
}

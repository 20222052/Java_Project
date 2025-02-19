package com.example.demo.service;

import com.example.demo.model.entity.Blog;
import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Blog getById(int id);
    void save(Blog blog);
    void deleteById(int id);
}

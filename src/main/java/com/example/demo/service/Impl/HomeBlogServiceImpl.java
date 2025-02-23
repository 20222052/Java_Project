package com.example.demo.service.Impl;

import com.example.demo.model.entity.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.HomeBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("homeBlogService") // Định danh để tránh conflict với AdminBlogServiceImpl
public class HomeBlogServiceImpl implements HomeBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getById(int id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        throw new UnsupportedOperationException("HomeBlogService không hỗ trợ save!");
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("HomeBlogService không hỗ trợ delete!");
    }
}

package com.example.demo.service;

import com.example.demo.model.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> getAllBlogs();
    Page<Blog> getBlogsPaginated(Pageable pageable);
    Optional<Blog> getBlogById(Integer id);
    Blog saveBlog(Blog blog);
    void deleteBlog(Integer id);
}

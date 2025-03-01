package com.example.demo.service.Impl;

import com.example.demo.model.entity.Blog;
import com.example.demo.model.entity.Comment;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.BlogDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogDetailsServiceImpl implements BlogDetailsService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Blog getBlogById(int id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getCommentsByBlogId(int blogId) {
        return commentRepository.findByBlogId(blogId);
    }
}

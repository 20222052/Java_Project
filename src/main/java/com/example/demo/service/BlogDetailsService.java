package com.example.demo.service;

import com.example.demo.model.entity.Blog;
import com.example.demo.model.entity.Comment;
import java.util.List;

public interface BlogDetailsService {
    Blog getBlogById(int id);
    List<Comment> getCommentsByBlogId(int blogId);
}

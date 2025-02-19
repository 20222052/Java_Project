package com.example.demo.service;

import com.example.demo.model.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment getById(Integer id);
    void save(Comment comment);
    void update(Comment comment);
    void delete(Comment comment);
    void deleteById(Integer id);

}


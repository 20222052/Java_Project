package com.example.demo.service.Impl;

import com.example.demo.model.entity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentsRepository;
    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public List<Comment> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public Comment getById(Integer id) {
        return commentsRepository.getById(id);
    }

    @Override
    public void save(Comment comment) {
        commentsRepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentsRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentsRepository.delete(comment);
    }

    @Override
    public void deleteById(Integer id) {
        commentsRepository.deleteById(id);
    }
}

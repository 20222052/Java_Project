package com.example.demo.service;

import com.example.demo.model.entity.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);
    List<User> findAllUsers();
    User insertUser(User user);
    User updateUser(User user);
    void deleteUser(Integer id);
}

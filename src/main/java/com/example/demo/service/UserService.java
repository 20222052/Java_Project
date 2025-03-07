package com.example.demo.service;

import com.example.demo.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);
    List<User> findAllUsers();
    User insertUser(User user);
    User updateUser(User user);
    void deleteUser(Integer id);
    UserDetails loadUserByUsername(String username);
    User findUserById(Integer id);
}

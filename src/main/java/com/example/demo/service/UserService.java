package com.example.demo.service;

import com.example.demo.model.entity.User;

public interface UserService {
    User findUserByEmail(String email);
}

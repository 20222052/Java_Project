package com.example.demo.service;

import com.example.demo.model.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    Contact getById(Integer id);
    void save(Contact contact);
    void update(Contact contact);
    void delete(Contact contact);
    void deleteById(Integer id);
}

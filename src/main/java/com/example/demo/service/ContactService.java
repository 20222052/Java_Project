package com.example.demo.service;

import com.example.demo.model.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> getFilteredContacts(String keyword, String order);
    Optional<Contact> findById(Integer id);
    void save(Contact contact);
    void update(Integer id, Contact contact);
    void delete(Integer id);
    void updateStatus(Integer id, Integer status);
}

package com.example.demo.service.Impl;


import com.example.demo.model.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getFilteredContacts(String keyword, String order) {
        List<Contact> contacts;

        if (keyword != null && !keyword.isEmpty()) {
            contacts = contactRepository.findByNameContaining(keyword);
        } else {
            contacts = contactRepository.findAll();
        }

        if (order != null) {
            switch (order) {
                case "id-asc":
                    contacts.sort(Comparator.comparing(Contact::getId));
                    break;
                case "id-desc":
                    contacts.sort(Comparator.comparing(Contact::getId).reversed());
                    break;
                case "name-asc":
                    contacts.sort(Comparator.comparing(Contact::getName));
                    break;
                case "name-desc":
                    contacts.sort(Comparator.comparing(Contact::getName).reversed());
                    break;
                case "created_at-asc":
                    contacts.sort(Comparator.comparing(Contact::getCreatedAt));
                    break;
                case "created_at-desc":
                    contacts.sort(Comparator.comparing(Contact::getCreatedAt).reversed());
                    break;
            }
        }

        return contacts;
    }

    @Override
    public Optional<Contact> findById(Integer id) {
        return contactRepository.findById(id);
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void update(Integer id, Contact contact) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            Contact updatedContact = existingContact.get();
            updatedContact.setName(contact.getName());
            updatedContact.setEmail(contact.getEmail());
            updatedContact.setSubject(contact.getSubject());
            updatedContact.setMessage(contact.getMessage());
            updatedContact.setStatus(contact.getStatus());
            contactRepository.save(updatedContact);
        }
    }

    @Override
    public void delete(Integer id) {
        contactRepository.deleteById(id);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(c -> {
            c.setStatus(status);
            contactRepository.save(c);
        });
    }
}

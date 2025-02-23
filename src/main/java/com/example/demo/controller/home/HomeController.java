package com.example.demo.controller.home;

import com.example.demo.model.entity.Contact;
import com.example.demo.service.ContactService;
import com.example.demo.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private CustomersService customersService;
    private ContactService contactService;
    @GetMapping
    public String index() {

        return "master/main_home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("page", "contact");
        return "master/main_home";
    }

    @PostMapping()
    public String addContact(@ModelAttribute("contact") Contact contact, Model model) {
        contactService.save(contact);
        return "redirect:/";
    }
}

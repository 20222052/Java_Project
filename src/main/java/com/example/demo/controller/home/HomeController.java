package com.example.demo.controller.home;

import com.example.demo.model.entity.Contact;
import com.example.demo.service.ContactService;
import com.example.demo.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ContactService contactService;
    @GetMapping
    public String index() {

        return "master/main_home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("page", "contact");
        model.addAttribute("pass", passwordEncoder.encode("12345"));
        passwordEncoder.encode("12345");

        return "master/main_home";
    }

    @PostMapping("/contact")
    public String addContact(@ModelAttribute("contact") Contact contact) {
        contactService.save(contact);
        return "redirect:/";
    }
}

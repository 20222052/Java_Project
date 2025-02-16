package com.example.demo.controller.admin;


import com.example.demo.model.entity.customers;
import com.example.demo.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {
    @Autowired
    private CustomersService customersService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("content" , "customer");
    }

    @GetMapping("/")
    public String index(Model model) {
        List<customers> customers = customersService.findAll();
        if (customers.isEmpty()) {
            model.addAttribute("content2" , "okokokok");
        }else {
            model.addAttribute("content2" , "nononon");
        }
        model.addAttribute("customers", customers);
        model.addAttribute("customer", customersService.getById(2));
        model.addAttribute("content1" , "List Customer");
        return "master/main_admin";
    }
}

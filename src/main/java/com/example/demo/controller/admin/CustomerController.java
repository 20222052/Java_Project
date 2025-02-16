package com.example.demo.controller.admin;


import com.example.demo.model.entity.customers;
import com.example.demo.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {
    @Autowired
    private CustomersService customersService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "customer");
    }

    @GetMapping
    public String index(Model model) {
        List<customers> customers = customersService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("content1" , "List Customer");
        return "master/main_admin";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("content1" , "Add Customer");
        model.addAttribute("content" , "create");
        return "master/main_admin";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") customers customer,BindingResult result ,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/admin/customer/create";
        }

        customersService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Customer added successfully!");

        return "redirect:/admin/customer";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id , RedirectAttributes redirectAttributes) {
        try {
            customersService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Customer deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting customer!");
        }

        return "redirect:/admin/customer";
    }
}

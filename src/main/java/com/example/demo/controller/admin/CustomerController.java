package com.example.demo.controller.admin;

import com.example.demo.model.entity.Customer;
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
        List<Customer> customers = customersService.findAll();
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
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model, BindingResult result , RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content" , "create");
            return "master/main_admin";
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("content1" , "Edit Customer");
        model.addAttribute("content" , "edit");
        model.addAttribute("customer" , customersService.getById(id));
        return "master/main_admin";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model, BindingResult result , RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content" , "edit");
            return "master/main_admin";
        }

        customersService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Customer edit successfully!");

        return "redirect:/admin/customer";
    }
}

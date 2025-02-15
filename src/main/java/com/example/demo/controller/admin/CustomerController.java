package com.example.demo.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("content" , "customer");
    }
    @GetMapping("/")
    public String index() {

        return "master/main_admin";
    }
}

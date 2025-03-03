package com.example.demo.controller.admin;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Customer;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "category");
    }
    @GetMapping
    public String category(Model model) {
        List<Category> category = categoryService.findAll();
        model.addAttribute("category", category);
        model.addAttribute("content1", "List Category");
        return "master/main_admin";
    }
}

package com.example.demo.controller.admin;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Product;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category" ,new Category());
        model.addAttribute("content1" , "Add category");
        model.addAttribute("content" , "create");
        return "master/main_admin";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("content" , "create");
            return "master/main_admin";
        }
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("success", " Added successfully!");

        return "redirect:/admin/category";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id , RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting!");
        }

        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("category" ,new Category());
        model.addAttribute("content1" , "Edit Product");
        model.addAttribute("content" , "edit");
        model.addAttribute("category" , categoryService.findById(id));
        return "master/main_admin";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content" , "edit");
            return "master/main_admin";
        }

        categoryService.save(category);
        redirectAttributes.addFlashAttribute("success", " Edit successfully!");

        return "redirect:/admin/category";
    }

}

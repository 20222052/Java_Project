package com.example.demo.controller.admin;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Controller
@RequestMapping("admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "product");
    }

    @GetMapping
    private String index(Model model) {
        List<Product> list_prd = productService.getAllProducts();
        List<Category> list_cat = categoryService.findAll();
        model.addAttribute("list", list_prd);
        model.addAttribute("cats", list_cat);
        model.addAttribute("content1" , "List Products");
        return "master/main_admin";
    }

}

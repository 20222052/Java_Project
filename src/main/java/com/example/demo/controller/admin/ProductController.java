package com.example.demo.controller.admin;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "product");
    }

    @GetMapping
    private String index(Model model) {
        List<Product> list_prd= productService.getAllProducts();
        model.addAttribute("list", list_prd);
        model.addAttribute("content1" , "List Products");
        return "master/main_admin";
    }
}

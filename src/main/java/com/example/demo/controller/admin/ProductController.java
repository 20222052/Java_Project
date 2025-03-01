//package com.example.demo.controller.admin;
//
//import com.example.demo.model.entity.Category;
//import com.example.demo.model.entity.Product;
//import com.example.demo.service.CategoryService;
//import com.example.demo.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/products")
//public class ProductController {
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping
//    private String index(Model model) {
//        List<Product> list = productService.getProducts();
//        model.addAttribute("list", list);
//        return "product/index";
//    }
//
//    @GetMapping("/add")
//    public String add(Model model) {
//        List<Category> listCate = categoryService.getAll();
//        Product product = new Product();
//        model.addAttribute("product", product);
//        model.addAttribute("listCate", listCate);
//        return "product/add";
//    }
//
//
//}

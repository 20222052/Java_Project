package com.example.demo.controller.home;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.PrdImages;
import com.example.demo.model.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PrdImagesService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class HomeProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PrdImagesService prdImagesService;

    @ModelAttribute
    public void addAtributes(Model model) {
        List lst_category = categoryService.findAll();
        model.addAttribute("cats_home" , lst_category);
    }

    @GetMapping
    public String home(Model model) {
        List<Category> catsHome = categoryService.findAll();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("cats_home", catsHome);
        model.addAttribute("page", "product");

        return "master/main_home";
    }

    @GetMapping("/{id}")
    public String products(@PathVariable("id") Integer categoryId, Model model,
                          @RequestParam(name = "order", required = false) String order) {

            Category category = categoryService.findById(categoryId);
            List<Product> products = productService.getProductsByCategorySorted(categoryId, order);
            List<Category> catsHome = categoryService.findAll();
            List<Product> latestProducts = productService.getLatestProducts();
            model.addAttribute("cat", category);
            model.addAttribute("products", products);
            model.addAttribute("cats_home", catsHome);
            model.addAttribute("news_products", latestProducts);
            model.addAttribute("page", "product");
            return "master/main_home";
    }

    @GetMapping("detail/{id}")
    public String product(@PathVariable("id") Integer productId, Model model) {
        Product product = productService.getProductById(productId);
        List<PrdImages> images = prdImagesService.getAllPrdImageById(product.getId());
        List<Product> latestProducts = productService.getLatestProducts();
        model.addAttribute("latestProducts", latestProducts);
        model.addAttribute("images", images);
        model.addAttribute("product", product);
        model.addAttribute("page", "productDetail");
        return "master/main_home";
    }

}

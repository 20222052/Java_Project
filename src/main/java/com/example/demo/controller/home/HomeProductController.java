package com.example.demo.controller.home;

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

@Controller
@RequestMapping("/product")
public class HomeProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute
    public void addAtributes(Model model) {
        List lst_category = categoryService.findAll();
        model.addAttribute("cats_home", lst_category);
        model.addAttribute("page", "product");
    }

    @GetMapping
    public String home(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "6") int size,Model model) {
        Page<Product> products = productService.getProductsByCategory(0, page, size);
        List<Product> lst_Nprd = productService.getProductsSortedBy("desc");
        model.addAttribute("news_products", lst_Nprd);
        model.addAttribute("products", products);

        return "master/main_home";
    }

    @GetMapping("/{categoryId}")
    public String getProducts(
            @PathVariable Integer categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size, Model model
    ) {
        Page<Product> products = productService.getProductsByCategory(categoryId, page, size);
        model.addAttribute("products", products);
        model.addAttribute("cat", categoryService.findById(categoryId));
        return "master/main_home";
    }

//    @GetMapping("/{id}")
//    public String product(@PathVariable("id") Integer categoryId, Model model,
//                          @RequestParam(name = "order", required = false) String order) {
//        try {
//            // Fetch category by ID
//            Category category = categoryService.findById(categoryId);
//
//            if (category == null) {
//                model.addAttribute("errorMessage", "Category not found.");
//                return "error";  // Redirect to a custom error page
//            }
//
//            // Fetch products by category and order
//            List<Product> products = productService.getProductsByCategorySorted(categoryId, order);
//
//            // Handle case when no products are found
//            if (products.isEmpty()) {
//                model.addAttribute("errorMessage", "No products found for this category.");
//                return "error";  // Redirect to a custom error page
//            }
//
//            // Fetch all categories and latest products
//            List<Category> catsHome = categoryService.findAll();
//            List<Product> latestProducts = productService.getLatestProducts();
//
//            model.addAttribute("cat", category);
//            model.addAttribute("products", products);
//            model.addAttribute("cats_home", catsHome);
//            model.addAttribute("news_products", latestProducts);
//            model.addAttribute("page", "product");
//
//            return "master/main_home";
//
//        }
//        catch (Exception e) {
//            // Log the exception (you can use a logger for production)
//            System.err.println("Error while fetching category or products: " + e.getMessage());
//
//            // Add a user-friendly error message
//            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
//
//            // Return to a general error page or a fallback view
//            return "error";  // You can create a custom error page called "error.html"
//        }
//    }
}




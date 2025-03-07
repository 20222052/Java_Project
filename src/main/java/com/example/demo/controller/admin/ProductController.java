package com.example.demo.controller.admin;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
//@RestController
@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "product");
    }
    @GetMapping({"","/"})
    private String index(Model model) {
        List<Product> list_prd = productService.getAllProducts();
        List<Category> list_cat = categoryService.findAll();
        model.addAttribute("list", list_prd);
        model.addAttribute("cats", list_cat);
        model.addAttribute("content1" , "List Products");
        return "master/main_admin";
    }
    @GetMapping("/create")
    public String create(Model model) {
        List<Category> list_cat = categoryService.findAll();
        model.addAttribute("categories" , list_cat);
        model.addAttribute("product" ,new Product());
        model.addAttribute("content1" , "Add product");
        model.addAttribute("content" , "create");
        return "master/main_admin";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("content" , "create");
            return "master/main_admin";
        }
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", " Added successfully!");

        return "redirect:/admin/customer";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id , RedirectAttributes redirectAttributes) {
        try {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting!");
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        List<Category> list_cat = categoryService.findAll();
        model.addAttribute("categories" , list_cat);
        model.addAttribute("products" ,new Product());
        model.addAttribute("content1" , "Edit Product");
        model.addAttribute("content" , "edit");
        model.addAttribute("product" , productService.getProductById(id));
        return "master/main_admin";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("product") Product product,@RequestParam("category_id") Integer categoryId, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content" , "edit");
            return "master/main_admin";
        }
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            result.rejectValue("category", "error.product", "Category không hợp lệ!");
            model.addAttribute("content", "edit");
            return "master/main_admin";
        }

        product.setCategory(category);
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", " Edit successfully!");

        return "redirect:/admin/product";
    }

}

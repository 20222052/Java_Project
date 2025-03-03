package com.example.demo.controller.home;

import com.example.demo.model.entity.Contact;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Blob;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private CustomersService customersService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ProductService productService;
    @Qualifier("blogServiceImpl")
    @Autowired
    private BlogService blogService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute
    public void addAtributes(Model model) {
        List lst_category = categoryService.findAll();
        model.addAttribute("cats_home" , lst_category);
    }

    @GetMapping
    public String index(Model model) {
        List lst_prd = productService.getAllProducts();
        List lst_Nprd = productService.getProductsSortedBy("desc");
        List lst_prd_sale = productService.getProductsSale();
        List lst_prd_feature = productService.getTop6Products();
        List lst_blog = blogService.findAll();
        List lst_banner = bannerService.getBanner();
        model.addAttribute("page", "");
        model.addAttribute("lst_banner", lst_banner);
        model.addAttribute("lst_blog", lst_blog);
        model.addAttribute("lst_prd_sale", lst_prd_sale);
        model.addAttribute("lst_prd_feature", lst_prd_feature);
        model.addAttribute("lst_prd", lst_prd);
        model.addAttribute("newsProducts", lst_Nprd);
        return "master/main_home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("page", "contact");
        return "master/main_home";
    }

    @PostMapping("/contact")
    public String addContact(@ModelAttribute("contact") Contact contact) {
        contactService.save(contact);
        return "redirect:/";
    }
}

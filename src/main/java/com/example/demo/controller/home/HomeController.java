package com.example.demo.controller.home;

import com.example.demo.model.entity.*;
//import com.example.demo.model.entity.Favorite;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
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
    private FavoriteService favoriteService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute
    public void addAtributes(Model model) {
        List lst_category = categoryService.findAll();
        model.addAttribute("cats_home" , lst_category);
    }

    @GetMapping
    public String index(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("cus");
        List<Product> lst_prd = productService.getAllProducts();
        List<Product> products = favoriteService.getProductsForFavorite(lst_prd, customer);

        List<Product> lst_Nprd = productService.getProductsSortedBy("desc");
        List<Product> Nproducts = favoriteService.getProductsForFavorite(lst_Nprd, customer);

        List<Product> lst_prd_sale = productService.getProductsSale();
        List<Product> saleproducts = favoriteService.getProductsForFavorite(lst_prd_sale, customer);

        List<Product> lst_prd_feature = productService.getTop6Products();
        List<Product> featureproducts = favoriteService.getProductsForFavorite(lst_prd_feature, customer);

        List<Blog> lst_blog = blogService.findAll();
        List<Banner> lst_banner = bannerService.getBanner();
        model.addAttribute("page", "");
        model.addAttribute("lst_banner", lst_banner);
        model.addAttribute("lst_blog", lst_blog);
        model.addAttribute("lst_prd_sale", saleproducts);
        model.addAttribute("lst_prd_feature", featureproducts);
        model.addAttribute("lst_prd", products);
        model.addAttribute("newsProducts", Nproducts);
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

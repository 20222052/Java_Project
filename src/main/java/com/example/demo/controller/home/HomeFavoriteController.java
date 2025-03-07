package com.example.demo.controller.home;

import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Favorite;
import com.example.demo.model.entity.Product;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/favorite")
public class HomeFavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private ProductService productService;

    @GetMapping()
    public String favorite(HttpSession session, Model model) {
        if(session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }
        Customer cus = (Customer) session.getAttribute("cus");
        List<Favorite> lst_favorite = favoriteService.getListFavoriteByCustomerId(cus.getId());
        model.addAttribute("lst_favorite", lst_favorite);
        model.addAttribute("page", "favorites");
        return "master/main_home";
    }

    @GetMapping("/delete/{id}")
    public String deleteFavorite(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }

        if (id <= 0) {
            return "redirect:/favorite?error=invalid"; // Tránh lỗi với ID không hợp lệ
        }

        favoriteService.deleteFavorite(id);
        return "redirect:/favorite";
    }


    @GetMapping("/insert/{prdId}")
    public String insertFavorite(@PathVariable("prdId") int prdId, HttpSession session) {
        if(session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }

        Customer cus = (Customer) session.getAttribute("cus");
        Product product = productService.getProductById(prdId);

        if (product == null) {
            return "redirect:/favorite?error=notfound"; // Tránh lỗi khi product không tồn tại
        }

        Favorite favorite = new Favorite();
        favorite.setProduct(product);
        favorite.setCustomer(cus);

        favoriteService.insertFavorite(favorite);
        return "redirect:/";
    }

}

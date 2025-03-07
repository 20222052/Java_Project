package com.example.demo.controller.home;

import com.example.demo.model.entity.Cart;
import com.example.demo.model.entity.Customer;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/acc")
public class HomeAccountController {
    @Autowired
    private CustomersService customerservice;
    @Autowired
    private CartService cartservice;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Integer countPrd(Customer customer) {
        List<Cart> carts = cartservice.findCartByCustomerId(customer.getId());
        Integer count = 0;
        if (carts != null) {
            for (Cart cart : carts) {
                count += 1*cart.getQuantity();
            }
        }
        return count;
    }

    @GetMapping({"/", "/login"})
    public String login() {
        return "account/login";
    }

    @GetMapping("/profilemain")
    public String viewProfile(HttpSession session, Model model) {
        // Lấy thông tin khách hàng từ session
        Customer auth = (Customer) session.getAttribute("cus");

        // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
        if (auth == null) {
            return "redirect:/acc/login";
        }

        // Gửi thông tin khách hàng đến Thymeleaf template
        model.addAttribute("auth", auth);
        model.addAttribute("page", "profileMain");
        return "master/main_home";
    }

    @GetMapping("/profile")
    public String showUpdateProfile(HttpSession session, Model model) {
        // Lấy thông tin khách hàng từ session
        Customer auth = (Customer) session.getAttribute("cus");

        // Kiểm tra nếu chưa đăng nhập thì chuyển hướng về trang đăng nhập
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }

        // Gửi thông tin khách hàng đến Thymeleaf để hiển thị
        model.addAttribute("auth", auth);
        model.addAttribute("page", "updateProfile");
        return "master/main_home";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("auth") Customer auth,
                                BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("page", "updateProfile");
            return "master/main_home";
        }
        customerservice.update(auth);
        // Cập nhật session
        session.setAttribute("cus", auth);
        // Quay lại trang hồ sơ sau khi cập nhật
        return "redirect:/acc/profilemain";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Customer cus = customerservice.getByEmail(email);
        if (cus != null && encoder.matches(password, cus.getPassword())) {
            cus.setQuantityPrdCart(countPrd(cus));
            session.setAttribute("cus", cus);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "acc/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String addAccount(@ModelAttribute("customer") Customer customer) {
        customerservice.save(customer);
        return "redirect:/";
    }
}


package com.example.demo.controller.home;

import com.example.demo.model.entity.Customer;
import com.example.demo.service.CustomersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/acc")
public class AccountController {
    @Autowired
    private CustomersService customerservice;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping({"/", "/login"})
    public String login() {
        return "account/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Customer cus = customerservice.getByEmail(email);

        if (cus != null && encoder.matches(password, cus.getPassword())) {
            session.setAttribute("cus", cus);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "account/login";
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


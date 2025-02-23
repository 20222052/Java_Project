package com.example.demo.controller.admin;

import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {

        User user = userService.findUserByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Sai Email");
            return "admin/login";
        }

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Sai Mật Khẩu");
            return "admin/login";
        }

        if ("admin".equals(user.getRole())) {
            session.setAttribute("user", user);
            return "redirect:/admin/customer";
        }

        model.addAttribute("error", "Bạn không có quyền truy cập.");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}

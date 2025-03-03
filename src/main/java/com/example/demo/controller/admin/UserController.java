package com.example.demo.controller.admin;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "user");
    }
    @GetMapping
    private String index(Model model) {
        List<User> list = userService.findAllUsers();
        model.addAttribute("list", list);
        model.addAttribute("content1" , "List Users");
        return "master/main_admin";
    }
}

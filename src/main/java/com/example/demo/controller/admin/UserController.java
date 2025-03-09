package com.example.demo.controller.admin;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category" ,new Category());
        model.addAttribute("content1" , "Add category");
        model.addAttribute("content" , "create");
        return "master/main_admin";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("content" , "create");
            return "master/main_admin";
        }
        userService.insertUser(user);
        redirectAttributes.addFlashAttribute("success", " Added successfully!");

        return "redirect:/admin/user";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id , RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting!");
        }

        return "redirect:/admin/user";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("user" ,new User());
        model.addAttribute("content1" , "Edit Product");
        model.addAttribute("content" , "edit");
        model.addAttribute("user" , userService.findUserById(id));
        return "master/main_admin";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content" , "edit");
            return "master/main_admin";
        }

        userService.insertUser(user);
        redirectAttributes.addFlashAttribute("success", " Edit successfully!");

        return "redirect:/admin/user";
    }
}

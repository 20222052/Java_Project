package com.example.demo.controller.admin;

import com.example.demo.model.entity.Comment;
import com.example.demo.model.entity.Customer;
import com.example.demo.service.CommentService;
import com.example.demo.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/comment")
public class CommentController {
        @Autowired
        private CommentService commentService;

        @ModelAttribute
        public void addAtributes(Model model) {
            model.addAttribute("page", "comment");
        }

        @GetMapping
        public String index(Model model) {
            List<Comment> comments = commentService.findAll();
            model.addAttribute("comments", comments);
            model.addAttribute("content1", "List Comments");
            return "master/main_admin";
        }

        @GetMapping("/create")
        public String create(Model model) {
            model.addAttribute("content1", "Add Comments");
            model.addAttribute("content", "create");
            return "master/main_admin";
        }

        @PostMapping("/save")
        public String saveCustomer(@ModelAttribute("comment") Comment comment, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
            if (result.hasErrors()) {
                model.addAttribute("content", "create");
                return "master/main_admin";
            }

            commentService.save(comment);
            redirectAttributes.addFlashAttribute("success", "comments added successfully!");

            return "redirect:/admin/comment";
        }

        @PostMapping("/delete/{id}")
        public String deleteCustomer(@PathVariable int id, RedirectAttributes redirectAttributes) {
            try {
                commentService.deleteById(id);
                redirectAttributes.addFlashAttribute("success", "comments deleted successfully!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Error deleting comments!");
            }

            return "redirect:/admin/comment";
        }

        @GetMapping("/edit/{id}")
        public String edit(@PathVariable int id, Model model) {
            model.addAttribute("content1", "Edit comments");
            model.addAttribute("content", "edit");
            model.addAttribute("customer", commentService.getById(id));
            return "master/main_admin";
        }

        @PostMapping("/update")
        public String updateCustomer(@ModelAttribute("comment") Comment comment, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
            if (result.hasErrors()) {
                model.addAttribute("content", "edit");
                return "master/main_admin";
            }

            commentService.save(comment);
            redirectAttributes.addFlashAttribute("success", "comments edit successfully!");

            return "redirect:/admin/comment";
        }
}

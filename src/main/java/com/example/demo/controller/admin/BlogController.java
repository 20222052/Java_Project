package com.example.demo.controller.admin;

import com.example.demo.model.entity.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("page", "blog");
    }

    @GetMapping
    public String index(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        model.addAttribute("content1", "List Blog");
        return "master/main_admin";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("content1", "Add Blog");
        model.addAttribute("content", "create");
        model.addAttribute("blog", new Blog());
        return "master/main_admin";
    }

    @PostMapping("/save")
    public String saveBlog(@ModelAttribute("blog") Blog blog, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content", "create");
            return "master/main_admin";
        }

        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "Blog added successfully!");
        return "redirect:/admin/blog";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            blogService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Blog deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting blog!");
        }

        return "redirect:/admin/blog";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("content1", "Edit Blog");
        model.addAttribute("content", "edit");
        model.addAttribute("blog", blogService.getById(id));
        return "master/main_admin";
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute("blog") Blog blog, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content", "edit");
            return "master/main_admin";
        }

        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "Blog updated successfully!");
        return "redirect:/admin/blog";
    }
}

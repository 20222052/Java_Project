package com.example.demo.controller.home;

import com.example.demo.model.entity.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class HomeBlogController {
    @Autowired
    @Qualifier("homeBlogService")
    private BlogService homeBlogService;

    @GetMapping
    public String listBlogs(Model model) {
        List<Blog> blogs = homeBlogService.findAll();
        model.addAttribute("blogs", blogs);
        model.addAttribute("page", "blog");
        return "master/main_home";
    }

    @GetMapping("details/{id}")
    public String blogDetail(@PathVariable int id, Model model) {
        Blog blog = homeBlogService.getById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("page", "blog_details");
        model.addAttribute("blog", blog);
        return "master/main_home";
    }
}


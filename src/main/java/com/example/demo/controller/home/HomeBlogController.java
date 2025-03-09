package com.example.demo.controller.home;

import com.example.demo.model.entity.Blog;
import com.example.demo.model.entity.Comment;
import com.example.demo.service.BlogService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @ModelAttribute
    public void addAtributes(Model model) {
        List lst_category = categoryService.findAll();
        model.addAttribute("cats_home" , lst_category);
    }

    @GetMapping
    public String listBlogs(Model model) {
        List<Blog> blogs = homeBlogService.findAll();
        model.addAttribute("blogs", blogs);
        model.addAttribute("page", "blog");
        return "master/main_home";
    }

    @GetMapping("details/{id}")
    public String blogDetail(@PathVariable int id, Model model) {
        List<Comment> cmm = commentService.findAll();
        model.addAttribute("comments", cmm);
        Blog blog = homeBlogService.getById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("page", "blog_details");
        model.addAttribute("blog", blog);
        return "master/main_home";
    }
}


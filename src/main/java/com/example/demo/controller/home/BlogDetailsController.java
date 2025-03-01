package com.example.demo.controller.home;

import com.example.demo.model.entity.Blog;
import com.example.demo.model.entity.Comment;
import com.example.demo.service.BlogDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/details")
public class BlogDetailsController {

    @Autowired
    private BlogDetailsService blogDetailsService;

    @GetMapping("/{id}")
    public String getBlogDetails(@PathVariable("id") int id, Model model) {
        Blog blog = blogDetailsService.getBlogById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }

        List<Comment> comments = blogDetailsService.getCommentsByBlogId(id);

        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);

        return "home/blog_details";
    }
}

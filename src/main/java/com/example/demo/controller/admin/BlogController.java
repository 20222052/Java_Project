package com.example.demo.controller.admin;

import com.example.demo.model.entity.Blog;
import com.example.demo.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // Hiển thị danh sách blog
    @GetMapping
    public String listBlogs(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5); // 5 blog mỗi trang
        Page<Blog> blogPage = blogService.getBlogsPaginated(pageable);
        model.addAttribute("blogs", blogPage);
        return "blog/index";
    }

    // Form tạo mới blog
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    // Xử lý tạo blog mới
    @PostMapping("/create")
    public String createBlog(@Valid @ModelAttribute Blog blog) {
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    // Form chỉnh sửa blog
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog/edit";
        }
        return "redirect:/blogs";
    }

    // Xử lý cập nhật blog
    @PostMapping("/edit/{id}")
    public String updateBlog(@PathVariable Integer id, @Valid @ModelAttribute Blog blog) {
        blog.setId(id);
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    // Xóa blog
    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }
}

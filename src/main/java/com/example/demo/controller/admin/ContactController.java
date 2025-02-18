package com.example.demo.controller.admin;

import com.example.demo.model.entity.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Hiển thị danh sách contact
    @GetMapping
    public String index(Model model,
                        @RequestParam(required = false) String keyword,
                        @RequestParam(required = false) String order) {
        List<Contact> contacts = contactService.getFilteredContacts(keyword, order);
        model.addAttribute("data", contacts);
        return "admin.contact.index";
    }

    // Hiển thị form tạo mới contact
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("contact", new Contact());
        return "admin.contact.create";
    }

    // Xử lý thêm mới contact
    @PostMapping("/store")
    public String store(@ModelAttribute Contact contact) {
        contactService.save(contact);
        return "redirect:/admin/contact";
    }

    // Hiển thị form chỉnh sửa contact
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Contact> contact = contactService.findById(id);
        if (contact.isPresent()) {
            model.addAttribute("contact", contact.get());
            return "admin.contact.edit";
        }
        return "redirect:/admin/contact";
    }

    // Xử lý cập nhật contact
    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Contact contact) {
        contactService.update(id, contact);
        return "redirect:/admin/contact";
    }

    // Xử lý xóa contact
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        contactService.delete(id);
        return "redirect:/admin/contact";
    }

    // Đánh dấu trạng thái đã đọc
    @PostMapping("/update-status/{id}")
    public String updateStatus(@PathVariable Integer id, @RequestParam Integer status) {
        contactService.updateStatus(id, status);
        return "redirect:/admin/contact";
    }
}


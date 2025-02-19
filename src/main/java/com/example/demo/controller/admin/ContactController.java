package com.example.demo.controller.admin;

import com.example.demo.model.entity.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("page", "contact");
    }

    @GetMapping
    public String index(Model model) {
        List<Contact> contacts = contactService.findAll();
        model.addAttribute("contacts", contacts);
        model.addAttribute("content1", "List Contact");
        return "master/main_admin";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("content1", "Add Contact");
        model.addAttribute("content", "create");
        return "master/main_admin";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute("contact") Contact contact, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content", "create");
            return "master/main_admin";
        }

        contactService.save(contact);
        redirectAttributes.addFlashAttribute("success", "Contact added successfully!");

        return "redirect:/admin/contact";
    }

    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            contactService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Contact deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting contact!");
        }

        return "redirect:/admin/contact";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("content1", "Edit Contact");
        model.addAttribute("content", "edit");
        model.addAttribute("contact", contactService.getById(id));
        return "master/main_admin";
    }

    @PostMapping("/update")
    public String updateContact(@ModelAttribute("contact") Contact contact, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content", "edit");
            return "master/main_admin";
        }

        contactService.save(contact);
        redirectAttributes.addFlashAttribute("success", "Contact edited successfully!");

        return "redirect:/admin/contact";
    }
}

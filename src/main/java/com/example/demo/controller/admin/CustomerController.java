package com.example.demo.controller.admin;

import com.example.demo.model.entity.Customer;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.service.CustomersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private CustomersService customersService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "customer");
    }

    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(defaultValue = "id-asc") String order,
                            Model model) {
        String[] orderParts = order.split("-");
        String sortField = orderParts[0];
        String sortDirection = orderParts[1];
        Page<Customer> customerPage = customersService.getCustomer(name,page,size,sortField,sortDirection);
        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("content1", "List Customer");
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("order", order);
        model.addAttribute("totalPages", customerPage.getTotalPages());

        return "master/main_admin";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer" ,new Customer());
        model.addAttribute("content1" , "Add Customer");
        model.addAttribute("content" , "create");
        return "master/main_admin";
    }

    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customer);
            model.addAttribute("content" , "create");
            return "master/main_admin";
        }


        if (customersService.getByPhone(customer.getPhone()) != null) {
            model.addAttribute("customers", customer);
            model.addAttribute("content" , "create");
            result.addError(new FieldError("customer", "phone", "SĐT đã được sử dụng"));
            return "master/main_admin";
        }

        if (customersService.getByEmail(customer.getEmail()) != null) {
            model.addAttribute("customers", customer);
            model.addAttribute("content" , "create");
            result.addError(new FieldError("customer", "email", "Email đã được sử dụng"));
            return "master/main_admin";
        }

        if (!customer.getPassword().equals(customer.getConfirmPassword())) {
            model.addAttribute("customers", customer);
            model.addAttribute("content" , "create");
            result.addError(new FieldError("customer", "confirmPassword", "Mật khẩu xác nhận không khớp!"));
            return "master/main_admin";
        }
        customersService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Customer added successfully!");

        return "redirect:/admin/customer";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id , RedirectAttributes redirectAttributes) {
        try {
            customersService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Customer deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting customer!");
        }

        return "redirect:/admin/customer";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer" ,new Customer());
        model.addAttribute("content1" , "Edit Customer");
        model.addAttribute("content" , "edit");
        model.addAttribute("customer" , customersService.getById(id));
        return "master/main_admin";
    }

    @PostMapping("/update")
    public String updateCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("content" , "edit");
            return "master/main_admin";
        }

        customersService.update(customer);
        redirectAttributes.addFlashAttribute("success", "Customer edit successfully!");

        return "redirect:/admin/customer";
    }
}

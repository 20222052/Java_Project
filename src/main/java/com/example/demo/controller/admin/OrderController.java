package com.example.demo.controller.admin;

import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.OrderDetail;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @ModelAttribute
    public void addAtributes(Model model) {
        model.addAttribute("page" , "order");
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("content1" , "List Order");
        return "master/main_admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        List<OrderDetail> orderDetaillst = orderDetailService.findByOrderId(id);
        model.addAttribute("orderDetails", orderDetaillst);
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("content1" , "List OrderDetail");
        model.addAttribute("content" , "edit");
        return "master/main_admin";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, @RequestParam("status") Integer status, Model model) {
        Order order = orderService.findById(id);
        order.setStatus(status);
        orderService.updateById(id, order);
        return "redirect:/admin/order";
    }

}

package com.example.demo.controller.home;

import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.OrderDetail;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class HomeOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("history")
    public String order(HttpSession session, Model model) {
        if(session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }
        float totalPrice = 0;
        Customer customer = (Customer) session.getAttribute("cus");
        List<Order> lstOrder = orderService.findByCustomerId(customer.getId());

        model.addAttribute("orders", lstOrder);
        model.addAttribute("page", "myOrder");
        return "master/main_home";
    }

    @GetMapping("/detail/{id}")
    public String detail(HttpSession session, @PathVariable int id, Model model) {
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }
        Customer customer = (Customer) session.getAttribute("cus");
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        List<OrderDetail> lstOrderDetail = orderDetailService.findByOrderId(id);
        // Tính tổng giá trị đơn hàng
//        double totalPrice = 0;
//        for (OrderDetail orderDetail : lstOrderDetail) {
//            totalPrice += orderDetail.getPrice() * orderDetail.getQuantity();
//        }
        model.addAttribute("customer", customer);
//        model.addAttribute("totalPrice", totalPrice);  // Truyền vào model
        model.addAttribute("orderDetails", lstOrderDetail);
        model.addAttribute("page", "orderDetail");
        return "master/main_home";
    }
}

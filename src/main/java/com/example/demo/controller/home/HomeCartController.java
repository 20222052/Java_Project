package com.example.demo.controller.home;

import com.example.demo.model.entity.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class HomeCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomersService customersService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ProductService productService;


    public Integer countPrd(Customer customer) {
        List<Cart> carts = cartService.findCartByCustomerId(customer.getId());
        Integer count = 0;
        if (carts != null) {
            for (Cart cart : carts) {
                count += 1*cart.getQuantity();
            }
        }
        return count;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int STRING_LENGTH = 9; // Độ dài chuỗi

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        // Kiểm tra nếu customer là null (người dùng chưa đăng nhập)
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";  // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        Customer customer = (Customer) session.getAttribute("cus");
        Integer cusId = customer.getId();
        // Lấy đối tượng customer từ session
        Customer cus = customersService.getById(cusId);

        // Kiểm tra lại giá trị của customer
        System.out.println("view carrt Customer has been added to session: " + cus.getEmail() + ", ID: " + cus.getId());

        // Nếu người dùng đã đăng nhập, lấy các sản phẩm trong giỏ hàng của khách hàng
        List<Cart> lst_prd = cartService.findCartByCustomerId(cus.getId());
        float totalPrice = 0;
        for (Cart cart : lst_prd) {
            System.out.println("Price:" + cart.getPrice());
            System.out.println("Quantity:" + cart.getQuantity());
            totalPrice += cart.getPrice() * cart.getQuantity();
        }

        // Thêm giỏ hàng và thông tin khách hàng vào model để hiển thị
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("lst_prd", lst_prd);
        model.addAttribute("customer", cus);
        model.addAttribute("page", "cart");

        return "master/main_home";  // Trả về trang giỏ hàng
    }

    @GetMapping("/clear")
    public String clearCart(HttpSession session, Model model) {
        Integer cusId = (Integer) session.getAttribute("cusId");
        // Kiểm tra nếu customer là null (người dùng chưa đăng nhập)
        if (cusId == null) {
            return "redirect:/acc/login";  // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        //        cartService.clearCart();
        return "redirect:/cart/view";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";  // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        Customer customer = (Customer) session.getAttribute("cus");
        List<Cart> carts = cartService.findCartByCustomerId(customer.getId());

        float totalPrice = 0;
        for (Cart cart : carts) {
            System.out.println("Price:" + cart.getPrice());
            System.out.println("Quantity:" + cart.getQuantity());
            totalPrice += cart.getPrice() * cart.getQuantity();
        }

        model.addAttribute("auth", customer);
        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("page", "checkout");
        return "master/main_home";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String phone,
                             @RequestParam String address,
                             HttpSession session) {

        Customer auth = (Customer) session.getAttribute("cus");
        if (auth == null) {
            return "redirect:/acc/login"; // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
        }
        String token = generateRandomString();
        Order order = new Order(name, email, phone, address, token, auth.getId(), 0);
        Order order1 = orderService.insert(order);
        List<Cart> carts = cartService.findCartByCustomerId(auth.getId());
        for (Cart cart:carts){
            System.out.println("Values:" + cart.getPrice()+" + "+cart.getQuantity()+" + "+cart.getProductId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order1);
            orderDetail.setProduct(cart.getProduct());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(cart.getPrice());
            orderDetailService.insert(orderDetail);
        }
        cartService.deleteCartByCusId(auth.getId());

        return "redirect:/";
    }

    @GetMapping("/insert/{prdId}")
    public String insert(@PathVariable("prdId") Integer id, HttpSession session) {
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }

        Customer customer = (Customer) session.getAttribute("cus");
        Product product = productService.getProductById(id);

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Cart existingCart = cartService.getCartByCustomerAndProduct(customer.getId(), id);

        if (existingCart != null) {
            // Nếu sản phẩm đã tồn tại, tăng số lượng lên 1
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            cartService.updateCart(existingCart);
        } else {
            // Nếu chưa có, thêm sản phẩm mới vào giỏ hàng
            Cart cart = new Cart();
            cart.setCustomerId(customer.getId());
            cart.setProductId(id);
            cart.setQuantity(1);
            cart.setPrice(product.getSalePrice());
            cartService.addCart(cart);
        }

        // Cập nhật số lượng sản phẩm trong giỏ hàng của khách hàng
        customer.setQuantityPrdCart(countPrd(customer));
        session.setAttribute("cus", customer);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession session) {
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }
        Customer customer = (Customer) session.getAttribute("cus");
        cartService.deleteCartByCusIdAndPrdId(customer.getId(), id);
        // Cập nhật số lượng sản phẩm trong giỏ hàng của khách hàng
        customer.setQuantityPrdCart(countPrd(customer));
        session.setAttribute("cus", customer);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam("productId") Integer productId,
                             @RequestParam("quantity") Integer quantity,
                             HttpSession session) {
        // Kiểm tra đăng nhập
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }

        Customer customer = (Customer) session.getAttribute("cus");

        // Nếu số lượng bằng 0 thì xóa sản phẩm khỏi giỏ hàng
        if (quantity == 0) {
            cartService.deleteCartByCusIdAndPrdId(customer.getId(), productId);
        } else {
            // Cập nhật số lượng sản phẩm trong giỏ hàng
            cartService.updateCartQuantity(customer.getId(), productId, quantity);
        }

        // Cập nhật lại số lượng sản phẩm trong session
        customer.setQuantityPrdCart(countPrd(customer));
        session.setAttribute("cus", customer);

        return "redirect:/cart";  // Quay lại trang giỏ hàng
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        // Kiểm tra đăng nhập
        if (session.getAttribute("cus") == null) {
            return "redirect:/acc/login";
        }
        Customer customer = (Customer) session.getAttribute("cus");
        cartService.deleteCartByCusId(customer.getId());
        // Cập nhật lại số lượng sản phẩm trong session
        customer.setQuantityPrdCart(countPrd(customer));
        session.setAttribute("cus", customer);

        return "redirect:/cart";  // Quay lại trang giỏ hàng
    }
}

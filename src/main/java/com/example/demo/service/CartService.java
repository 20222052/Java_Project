package com.example.demo.service;

import com.example.demo.model.entity.Cart;
import com.example.demo.model.entity.User;

import java.util.List;

public interface CartService {
    List<Cart> findCartByCustomerId(int id);
    void deleteCartByCusId(Integer customerId);
    void deleteCartByCusIdAndPrdId(Integer customerId, Integer productId);
    void addCart(Cart cart);
    public void updateCart(Cart cart);
    public Cart getCartByCustomerAndProduct(Integer customerId, Integer productId);
    public void updateCartQuantity(Integer customerId, Integer productId, Integer quantity);
}

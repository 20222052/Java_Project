package com.example.demo.service.Impl;

import com.example.demo.model.entity.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findCartByCustomerId(int id) {
        return cartRepository.findByCustomerId(id);
    }

    @Transactional
    @Override
    public void deleteCartByCusId(Integer customerId) {
        cartRepository.deleteByCustomerId(customerId);
    }

    @Transactional
    @Override
    public void deleteCartByCusIdAndPrdId(Integer customerId, Integer productId) {
        cartRepository.deleteByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart getCartByCustomerAndProduct(Integer customerId, Integer productId) {
        return cartRepository.findByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    @Transactional
    public void updateCartQuantity(Integer customerId, Integer productId, Integer quantity) {
        Cart cart = cartRepository.findByCustomerIdAndProductId(customerId, productId);
        if (cart != null) {
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

}

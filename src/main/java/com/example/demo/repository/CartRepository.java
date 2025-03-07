package com.example.demo.repository;

import com.example.demo.model.entity.Cart;
import com.example.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomerId(Integer customerId);
    @Modifying
    @Query(value = "DELETE FROM carts WHERE customer_id = ?", nativeQuery = true)
    void deleteByCustomerId(int customerId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM carts WHERE customer_id = ? AND product_id = ?", nativeQuery = true)
    void deleteByCustomerIdAndProductId(Integer customerId, Integer productId);
    Cart findByCustomerIdAndProductId(Integer customerId, Integer productId);
}

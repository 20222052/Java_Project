package com.example.demo.service;

import com.example.demo.model.entity.Banner;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Favorite;
import com.example.demo.model.entity.Product;

import java.util.List;

public interface FavoriteService {
    List<Favorite> getListFavoriteByCustomerId(Integer customerId);
    void insertFavorite(Favorite favorite);
    void deleteFavorite(Integer id);
    public List<Product> getProductsForFavorite(List<Product> products, Customer customer);
    Product existsByCustomerAndProduct(Customer customer, Product product);
}

package com.example.demo.service.Impl;

import com.example.demo.model.entity.Banner;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Favorite;
import com.example.demo.model.entity.Product;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Favorite> getListFavoriteByCustomerId(Integer customerId) {
        List<Favorite> lst_favorite = favoriteRepository.findByCustomerId(customerId);
        return lst_favorite;
    }

    @Override
    public void insertFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Integer id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsForFavorite(List<Product> products, Customer customer) {
        for (Product product : products) {
            boolean isFavorited = favoriteRepository.existsByCustomerAndProduct(customer, product);
            Favorite favorite = favoriteRepository.findByCustomerAndProduct(customer, product);
            product.setFavorited(isFavorited); // Gán giá trị vào sản phẩm
            product.setFavoriteId(favorite != null ? favorite.getId() : null);
        }
        return products;
    }

    @Override
    public Product existsByCustomerAndProduct(Customer customer, Product product) {
        boolean isFavorited = favoriteRepository.existsByCustomerAndProduct(customer, product);
        Favorite favorite = favoriteRepository.findTopByCustomerAndProduct(customer, product);
        product.setFavorited(isFavorited); // Gán giá trị vào sản phẩm
        product.setFavoriteId(favorite != null ? favorite.getId() : null);
        return product;
    }
}

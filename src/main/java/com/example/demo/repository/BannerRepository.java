package com.example.demo.repository;

import com.example.demo.model.entity.Banner;
import com.example.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
    @Query(value = "SELECT * FROM banners WHERE status = 1", nativeQuery = true)
    List<Banner> findBanner();
}

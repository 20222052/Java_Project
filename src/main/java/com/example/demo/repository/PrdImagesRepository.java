package com.example.demo.repository;

import com.example.demo.model.entity.PrdImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrdImagesRepository extends JpaRepository<PrdImages, Integer> {
    List<PrdImages> findByProductId(Integer productId);
}

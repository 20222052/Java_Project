package com.example.demo.service;

import com.example.demo.model.entity.Banner;
import com.example.demo.model.entity.Category;

import java.util.List;

public interface BannerService {
    List<Banner> getBanner();
    Banner getBannerById(int id);
    Banner findById(int id);
    Banner save(Banner banner);
    Banner update(Banner banner);
    void delete(Banner banner);
}

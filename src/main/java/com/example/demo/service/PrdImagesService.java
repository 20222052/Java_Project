package com.example.demo.service;

import com.example.demo.model.entity.PrdImages;
import com.example.demo.model.entity.User;

import java.util.List;

public interface PrdImagesService {
    List<PrdImages> getAllPrdImages();
    List<PrdImages> getAllPrdImageById(Integer id);
}

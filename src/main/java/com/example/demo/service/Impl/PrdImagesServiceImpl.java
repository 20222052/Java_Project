package com.example.demo.service.Impl;

import com.example.demo.model.entity.PrdImages;
import com.example.demo.repository.PrdImagesRepository;
import com.example.demo.service.PrdImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrdImagesServiceImpl implements PrdImagesService {

    @Autowired
    private PrdImagesRepository prdImagesRepository;

    @Override
    public List<PrdImages> getAllPrdImages() {
        return prdImagesRepository.findAll();
    }

    @Override
    public List<PrdImages> getAllPrdImageById(Integer id) {
        return prdImagesRepository.findByProductId(id);
    }
}

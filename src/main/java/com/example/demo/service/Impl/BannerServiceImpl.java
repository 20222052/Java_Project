package com.example.demo.service.Impl;

import com.example.demo.model.entity.Banner;
import com.example.demo.repository.BannerRepository;
import com.example.demo.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BannerServiceImpl  implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;
    @Override
    public List<Banner> getBanner() {
        return bannerRepository.findBanner();
    }
}

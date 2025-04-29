package com.project.ProjectApp.service.impl;

import com.project.ProjectApp.entities.Banner;
import com.project.ProjectApp.repository.BannerRepository;
import com.project.ProjectApp.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner getBanner() {
        // Assuming only one record exists
        return bannerRepository.findAll().stream().findFirst().orElse(new Banner());
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerRepository.save(banner);
    }
}

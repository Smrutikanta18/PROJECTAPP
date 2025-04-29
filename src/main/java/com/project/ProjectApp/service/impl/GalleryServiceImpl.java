package com.project.ProjectApp.service.impl;

import com.project.ProjectApp.entities.Gallery;
import com.project.ProjectApp.repository.GalleryRepository;
import com.project.ProjectApp.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    @Override
    public List<Gallery> getAllImages() {
        return galleryRepository.findAll();
    }

    @Override
    public void saveImage(Gallery gallery) {
        galleryRepository.save(gallery);
    }

    @Override
    public void deleteImageById(int id) {
        galleryRepository.deleteById(id);
    }
}

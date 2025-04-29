package com.project.ProjectApp.service;

import com.project.ProjectApp.entities.Gallery;

import java.util.List;

public interface GalleryService {
    List<Gallery> getAllImages();
    void saveImage(Gallery gallery);
    void deleteImageById(int id);
}

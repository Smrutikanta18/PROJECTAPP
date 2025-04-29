package com.project.ProjectApp.controller;

import com.project.ProjectApp.entities.Gallery;
import com.project.ProjectApp.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @GetMapping("/galleryImage")
    public String viewGallery(Model model) {
        model.addAttribute("images", galleryService.getAllImages());
        model.addAttribute("newImage", new Gallery()); // For the add form
        return "admin/gallery"; // gallery.html
    }

    @PostMapping("/add")
    public String addImage(@ModelAttribute("newImage") Gallery gallery) {
        galleryService.saveImage(gallery);
        return "redirect:/galleryImage";
    }

    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable("id") int id) {
        galleryService.deleteImageById(id);
        return "redirect:/galleryImage";
    }
}

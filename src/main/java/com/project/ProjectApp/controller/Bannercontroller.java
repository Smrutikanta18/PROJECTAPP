package com.project.ProjectApp.controller;

import com.project.ProjectApp.entities.Admin;
import com.project.ProjectApp.entities.Banner;
import com.project.ProjectApp.repository.BannerRepository;
import com.project.ProjectApp.service.BannerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class Bannercontroller {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private BannerRepository bannerRepository;

    private static final String uploadDir = "videos/";

    @GetMapping("/banners")
    public String getBanner(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
        Banner banner = bannerService.getBanner(); // Assume you only have 1
        model.addAttribute("banner", banner);
        return "admin/bannerImage"; // your HTML/JSP file
        }
        return "redirect:/adminLogin";
    }

    @PostMapping("/banner/update")
    public String updateBanner(@RequestParam("id") int id,
                               @RequestParam("vedio") MultipartFile file) throws IOException {

        Banner banner = bannerRepository.findById(id).orElse(null);
        if (banner != null && !file.isEmpty()) {
            // Delete old video
            if (banner.getVedio() != null) {
                Path oldVideoPath = Paths.get(uploadDir + banner.getVedio());
                Files.deleteIfExists(oldVideoPath);
            }

            // Save new video
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + filename);
            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Update DB
            banner.setVedio(filename);
            bannerRepository.save(banner);
        }
        return "redirect:/banners";
    }

}

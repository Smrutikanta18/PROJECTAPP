package com.project.ProjectApp.service.impl;

import com.project.ProjectApp.entities.Services;
import com.project.ProjectApp.repository.ServicesRepository;
import com.project.ProjectApp.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    private static final String UPLOAD_DIR = "images/";

    @Override
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Services getById(int id) {
        return servicesRepository.findById(id).orElse(null);

    }

    @Override
    public void saveService(String name, String description, MultipartFile imageFile) throws IOException {
        Services service = null;
        if (!imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            File uploadDir = new ClassPathResource("static/images").getFile();
            Path path = Paths.get(uploadDir.getAbsolutePath(), fileName);

            if (!uploadDir.exists()) uploadDir.mkdirs();
            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            service = new Services();
            service.setImage(fileName);
            service.setName(name);
            service.setDescription(description);
        }
        servicesRepository.save(service);
    }

    @Override
    public void updateService(int id,String name, String description, MultipartFile imageFile) throws IOException {
        Services service = servicesRepository.findById(id).orElse(null);;
        if (service == null) return;

        service.setName(name);
        service.setDescription(description);

        if (!imageFile.isEmpty()) {
            // Delete old image
            if (service.getImage() != null) {
                File uploadDir = new ClassPathResource("static/images").getFile();
                Path oldPath = Paths.get(uploadDir.getAbsolutePath(), service.getImage());
                Files.deleteIfExists(oldPath);
            }

            // Save new image
            String fileName = imageFile.getOriginalFilename();
            File uploadDir = new ClassPathResource("static/images").getFile();
            Path newPath = Paths.get(uploadDir.getAbsolutePath(), fileName);

            if (!uploadDir.exists()) uploadDir.mkdirs();
            Files.copy(imageFile.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

            service.setImage(fileName);
        }

        servicesRepository.save(service);
    }

    @Override
    public void deleteServiceById(int id) throws IOException {
        Services service = servicesRepository.findById(id).orElse(null);
        if (service != null && service.getImage() != null) {
            File uploadDir = new ClassPathResource("static/images").getFile();
            Path imagePath = Paths.get(uploadDir.getAbsolutePath(), service.getImage());
            Files.deleteIfExists(imagePath);
        }

        servicesRepository.deleteById(id);
    }
}

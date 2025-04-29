package com.project.ProjectApp.service;

import com.project.ProjectApp.entities.Services;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServicesService {
    List<Services> getAllServices();
    void saveService(String name, String description , MultipartFile imageFile) throws IOException;
    void updateService(int id, String name, String description , MultipartFile imageFile) throws IOException;
    void deleteServiceById(int id) throws IOException;
    Services getById(int id);
}

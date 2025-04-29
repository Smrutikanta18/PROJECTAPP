package com.project.ProjectApp.controller;

import com.project.ProjectApp.entities.Admin;
import com.project.ProjectApp.entities.Services;
import com.project.ProjectApp.service.ServicesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class SeviceController {

    @Autowired
    private ServicesService servicesService;


    @GetMapping("/adminServices")
    public String getService(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            model.addAttribute("services", servicesService.getAllServices());
            return "admin/Service";
        }
        return "redirect:/adminLogin";
    }

    @PostMapping("/services/add")
    public String addService(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("image") MultipartFile imageFile) throws IOException {
        servicesService.saveService(name,description, imageFile);
        return "redirect:/adminServices";
    }

    @PostMapping("/services/edit")
    public String editService(@RequestParam("id") int id,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("image") MultipartFile imageFile) throws IOException {
        servicesService.updateService(id,name, description, imageFile);
        return "redirect:/adminServices";
    }

    @PostMapping("/services/delete")
    public String deleteService(@RequestParam("id") int id) throws IOException {
        servicesService.deleteServiceById(id);
        return "redirect:/adminServices";
    }
}

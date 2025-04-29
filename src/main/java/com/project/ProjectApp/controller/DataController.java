package com.project.ProjectApp.controller;

import com.project.ProjectApp.entities.Data;
import com.project.ProjectApp.service.DataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/data")
    public String viewData(HttpSession session, Model model) {
        if (session.getAttribute("loggedInAdmin") != null) {
            model.addAttribute("data", dataService.getDataById(1).orElse(null));
            return "admin/data";
        }
        return "redirect:/adminLogin";
    }

    @PostMapping("/updateData")
    public String updateData(@RequestParam("id") int id,
                             @RequestParam("projects") int projects,
                             @RequestParam("customers") int customers) {
        dataService.updateData(id, projects, customers);
        return "redirect:/data";
    }
}

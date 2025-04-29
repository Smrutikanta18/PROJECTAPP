package com.project.ProjectApp.controller;

import com.project.ProjectApp.entities.Admin;
import com.project.ProjectApp.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/adminLogin")
    public String adminLogin() {
        return "admin/login";
    }

    @RequestMapping("/adminRegistration")
    public String adminRegistration() {
        return "admin/signup";
    }

    @RequestMapping("/adminIndex")
    public String adminIndex(HttpSession session,Model model){
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            model.addAttribute("admin", admin);
            return "admin/index";
        }
        return "redirect:/adminLogin";
    }

    @PostMapping("/processAdminRegistration")
    public String processAdminRegistration(@ModelAttribute Admin admin, Model model) {
        if (adminService.emailExists(admin.getEmail())) {
            model.addAttribute("error", "Email already registered.");
            return "admin/signup";
        }

        adminService.saveAdmin(admin);
        model.addAttribute("error", "Registration successful! You can now log in.");
        return "admin/login";
    }

    @PostMapping("/processAdminLogin")
    public String processAdminLogin(@ModelAttribute Admin admin, Model model, HttpSession session) {
        Admin loggedInAdmin = adminService.validateAdminLogin(admin.getEmail(), admin.getPassword());

        if (loggedInAdmin != null) {
            session.setAttribute("loggedInAdmin", loggedInAdmin); // ✅ Store admin in session
            return "redirect:/adminIndex"; // ✅ Redirect to profile page
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "admin/login";
        }
    }

    // ✅ Profile method to show admin data from session
    @RequestMapping("/adminProfile")
    public String adminProfile(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            model.addAttribute("admin", admin);
            return "admin/profile";
        }
        return "redirect:/adminLogin";
    }

    @PostMapping("/updateAdminDetails")
    public String updateAdminDetails(
            @RequestParam("id") Integer id,
            @RequestParam("email") String email,
            HttpSession session) {

        Admin updatedAdmin = adminService.updateAdminDetails(id, email);
        if (updatedAdmin != null) {
            session.setAttribute("loggedInAdmin", updatedAdmin);
        }

        return "redirect:/adminProfile";
    }

    @PostMapping("/changeAdminPassword")
    public String changeAdminPassword(
            @RequestParam("id") Integer id,
            @RequestParam("newPassword") String newPassword,
            HttpSession session) {

        Admin admin = adminService.changeAdminPassword(id,newPassword);
        if (admin != null) {
            session.setAttribute("loggedInAdmin", admin);
        }

        return "redirect:/adminProfile";
    }

    @RequestMapping("/adminLogout")
    public String adminLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/adminLogin";
    }
}

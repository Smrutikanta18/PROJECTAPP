package com.project.ProjectApp.controller;

import com.project.ProjectApp.entities.Admin;
import com.project.ProjectApp.entities.Project;
import com.project.ProjectApp.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private static final String UPLOAD_DIR = "images/";

    @GetMapping("/projects")
    public String viewInstagram(@RequestParam(defaultValue = "0") int page,
                                HttpSession session,
                                Model model) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            int pageSize = 9;
            Page<Project> projectPage = projectService.getProjectsPaginated(page, pageSize);
            model.addAttribute("projectPage", projectPage);
            model.addAttribute("currentPage", page);
            return "admin/ProjectData";
        }
        return "redirect:/adminLogin";
    }


    @PostMapping("/addProject")
    public String addProject(@RequestParam("image") MultipartFile imageFile,
                             @RequestParam("name") String name,
                             @RequestParam("work") String work,
                             @RequestParam("location") String location,
                             @RequestParam("status") String status,
                             HttpSession session) {

        if (imageFile.isEmpty()) {
            session.setAttribute("msg", "Please select an image file");
            return "redirect:/projects";
        }

        try {
            String fileName = imageFile.getOriginalFilename();

            // Specify the directory where the image will be uploaded
            File uploadDir = new ClassPathResource("static/images").getFile();

            // Create the path for the file
            Path path = Paths.get(uploadDir.getAbsolutePath() + File.separator + fileName);

            // Ensure the directory exists
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Copy the file content to the directory
            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            Project project = new Project();
            project.setImage(fileName); // relative path
            project.setName(name);
            project.setWork(work);
            project.setLocation(location);
            project.setStatus(status);

            projectService.saveProject(project);
            session.setAttribute("msg", "Project added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "Error while uploading image.");
        }

        return "redirect:/projects";
    }

    @PostMapping("/updateProject")
    public String updateProject(@RequestParam("id") int id,
                                @RequestParam("image") MultipartFile imageFile,
                                @RequestParam("name") String name,
                                @RequestParam("work") String work,
                                @RequestParam("location") String location,
                                @RequestParam("status") String status,
                                HttpSession session) throws IOException {

        Project project = projectService.getProjectById(id);
        if (project == null) return "redirect:/projects";

        try {
            // Always update these fields
            project.setName(name);
            project.setWork(work);
            project.setLocation(location);
            project.setStatus(status);

            if (!imageFile.isEmpty()) {
                // Delete the old image if it exists
                if (project.getImage() != null && !project.getImage().isEmpty()) {
                    File uploadDir = new ClassPathResource("static/images").getFile();
                    Path oldImagePath = Paths.get(uploadDir.getAbsolutePath(), project.getImage());
                    Files.deleteIfExists(oldImagePath);
                }

                // Save the new image
                String fileName = imageFile.getOriginalFilename();
                File uploadDir = new ClassPathResource("static/images").getFile();
                Path newPath = Paths.get(uploadDir.getAbsolutePath(), fileName);

                if (!uploadDir.exists()) uploadDir.mkdirs();

                Files.copy(imageFile.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);
                project.setImage(fileName);
            }

            // Save the updated project (including changes without image)
            projectService.saveProject(project);
            session.setAttribute("msg", "Project updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "Error while updating project.");
        }

        return "redirect:/projects";
    }


    @GetMapping("/deleteProject")
    public String deleteProject(@RequestParam("id") int id, HttpSession session) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            try {
                // Delete the image if it exists
                if (project.getImage() != null && !project.getImage().isEmpty()) {
                    File uploadDir = new ClassPathResource("static/images").getFile();
                    Path imagePath = Paths.get(uploadDir.getAbsolutePath(), project.getImage());
                    Files.deleteIfExists(imagePath);
                }

                // Delete the project from the database
                projectService.deleteProjectById(id);
                session.setAttribute("msg", "Project deleted successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("msg", "Error occurred while deleting the project.");
            }
        } else {
            session.setAttribute("msg", "Project not found.");
        }
        return "redirect:/projects";
    }

}

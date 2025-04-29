package com.project.ProjectApp.controller;

import com.project.ProjectApp.entities.Project;
import com.project.ProjectApp.service.BannerService;
import com.project.ProjectApp.service.DataService;
import com.project.ProjectApp.service.ProjectService;
import com.project.ProjectApp.service.ServicesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ServicesService servicesService;
    @Autowired
    private DataService dataService;
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/")
    public String home1(Model model){
        model.addAttribute("services", servicesService.getAllServices());
        model.addAttribute("projects",projectService.getAllProjects());
        model.addAttribute("data", dataService.getDataById(1).orElse(null));
        model.addAttribute("banner",bannerService.getBanner());
        return "index";
    }

    @RequestMapping("/home")
    public String home2(Model model){
        model.addAttribute("services", servicesService.getAllServices());
        model.addAttribute("projects",projectService.getAllProjects());
        model.addAttribute("data", dataService.getDataById(1).orElse(null));
        model.addAttribute("banner",bannerService.getBanner());
        return "index";
    }



    @GetMapping("/services")
    public String showServices(Model model) {
        model.addAttribute("services", servicesService.getAllServices());
        return "services";
    }

    @RequestMapping("/project")
    public String project(@RequestParam(defaultValue = "0") int page,
                          HttpSession session,
                          Model model){
        int pageSize = 9;
        Page<Project> projectPage = projectService.getProjectsPaginated(page, pageSize);

        model.addAttribute("projects", projectPage.getContent()); // ✅ fix
        model.addAttribute("projectPage", projectPage);
        model.addAttribute("currentPage", page);

        return "project";
    }


    @RequestMapping("/gallery")
    public String gallery(){
        return "gallery";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("data", dataService.getDataById(1).orElse(null));

        return "about";
    }
}

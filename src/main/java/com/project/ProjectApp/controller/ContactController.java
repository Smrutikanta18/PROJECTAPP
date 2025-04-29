package com.project.ProjectApp.controller;

import com.project.ProjectApp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/addcontact")
    private String addContact(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("subject") String subject,
                              @RequestParam("massage") String massage,
                              Model model){
        contactService.addContact(name,email,subject,massage);
        return "redirect:/contact";
    }

    @PostMapping("/addquery")
    private String addContact(@RequestParam("first") String first,
                              @RequestParam("last") String last,
                              @RequestParam("phone") String phone,
                              @RequestParam("service") String service,
                              @RequestParam("message") String massage,
                              Model model){
        contactService.addQuery(first,last,phone,service,massage);
        return "redirect:/contact";
    }

    @GetMapping("/contacts")
    private String getContacts(Model model){
        model.addAttribute("contacts",contactService.getAllContact());
        model.addAttribute("queries",contactService.getAllQuery());
        return "admin/contact";
    }

}

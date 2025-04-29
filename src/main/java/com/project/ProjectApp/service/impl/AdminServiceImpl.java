package com.project.ProjectApp.service.impl;

import com.project.ProjectApp.entities.Admin;
import com.project.ProjectApp.repository.AdminRepository;
import com.project.ProjectApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public boolean emailExists(String email) {
        return adminRepository.findAll().stream()
                .anyMatch(a -> a.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public Admin validateAdminLogin(String email, String password) {
        return adminRepository.findAll().stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email) && a.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Admin updateAdminDetails(Integer id, String email) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            admin.setEmail(email);
            return adminRepository.save(admin);
        }
        return null;
    }

    @Override
    public Admin changeAdminPassword(Integer id, String password) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if(admin != null){
            admin.setPassword(password);
            return adminRepository.save(admin);
        }
        return null;
    }


}

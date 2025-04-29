package com.project.ProjectApp.service;

import com.project.ProjectApp.entities.Admin;

public interface AdminService {

    Admin saveAdmin(Admin admin);
    boolean emailExists(String email);

    Admin validateAdminLogin(String email, String password);

    Admin updateAdminDetails(Integer id, String email);

    Admin changeAdminPassword(Integer id , String password);

}

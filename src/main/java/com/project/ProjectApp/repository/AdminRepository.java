package com.project.ProjectApp.repository;

import com.project.ProjectApp.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin , Integer>{

}

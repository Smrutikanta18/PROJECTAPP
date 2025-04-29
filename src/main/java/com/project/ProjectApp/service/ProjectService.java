package com.project.ProjectApp.service;

import com.project.ProjectApp.entities.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();

    void saveProject(Project project);

    Project getProjectById(int id);

    void deleteProjectById(int id);

    Page<Project> getProjectsPaginated(int page, int size);


}

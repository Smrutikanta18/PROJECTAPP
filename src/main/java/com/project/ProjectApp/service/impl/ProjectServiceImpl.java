package com.project.ProjectApp.service.impl;

import com.project.ProjectApp.entities.Project;
import com.project.ProjectApp.repository.ProjectRepository;
import com.project.ProjectApp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProjectById(int id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Page<Project> getProjectsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return projectRepository.findAll(pageable);
    }

}

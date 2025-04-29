package com.project.ProjectApp.service.impl;

import com.project.ProjectApp.entities.Data;
import com.project.ProjectApp.repository.DataRepository;
import com.project.ProjectApp.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public Optional<Data> getDataById(int id) {
        return dataRepository.findById(id);
    }

    @Override
    public Data updateData(int id, int projects, int customers) {
        Data existingData = dataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid data ID: " + id));

        existingData.setProjects(projects);
        existingData.setCustomers(customers);

        return dataRepository.save(existingData);
    }
}

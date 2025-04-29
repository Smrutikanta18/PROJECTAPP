package com.project.ProjectApp.service;

import com.project.ProjectApp.entities.Data;

import java.util.Optional;

public interface DataService {
    Optional<Data> getDataById(int id);
    Data updateData(int id, int projects, int customers);
}

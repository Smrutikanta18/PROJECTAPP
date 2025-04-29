package com.project.ProjectApp.repository;

import com.project.ProjectApp.entities.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data,Integer> {
}

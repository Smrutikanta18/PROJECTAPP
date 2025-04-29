package com.project.ProjectApp.repository;

import com.project.ProjectApp.entities.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query,Integer> {
}

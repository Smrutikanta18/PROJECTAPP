package com.project.ProjectApp.repository;

import com.project.ProjectApp.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery,Integer> {
}

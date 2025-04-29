package com.project.ProjectApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "gallery")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String image;

    public Gallery() {
    }

    public Gallery(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

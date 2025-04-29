package com.project.ProjectApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String image;
    private String name;
    private String description;

    public Services() {
    }

    public Services(int id, String image, String name, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

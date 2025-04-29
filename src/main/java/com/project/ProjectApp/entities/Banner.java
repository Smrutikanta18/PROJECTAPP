package com.project.ProjectApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String vedio;

    public Banner() {
    }

    public Banner(int id, String vedio) {
        this.id = id;
        this.vedio = vedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }
}

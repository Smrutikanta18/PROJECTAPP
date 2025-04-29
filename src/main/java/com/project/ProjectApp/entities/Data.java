package com.project.ProjectApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int projects;
    private int customers;

    public Data() {
    }

    public Data(int id, int projects, int customers) {
        this.id = id;
        this.projects = projects;
        this.customers = customers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjects() {
        return projects;
    }

    public void setProjects(int projects) {
        this.projects = projects;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }
}

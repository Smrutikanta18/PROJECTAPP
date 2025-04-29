package com.project.ProjectApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "query")
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String service;
    private String massage;

    public Query() {
    }

    public Query(int id, String firstname,String phone, String lastname, String service, String massage) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.service = service;
        this.massage = massage;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}

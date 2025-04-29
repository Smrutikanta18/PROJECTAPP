package com.project.ProjectApp.service;

import com.project.ProjectApp.entities.Contact;
import com.project.ProjectApp.entities.Query;

public interface ContactService {

    void addContact(String name,String email,String subject,String massage);
    void addQuery(String first,String second,String phone,String service,String message);
    Contact getAllContact();
    Query getAllQuery();
}

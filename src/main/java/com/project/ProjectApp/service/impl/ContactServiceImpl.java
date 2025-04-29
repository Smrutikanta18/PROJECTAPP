package com.project.ProjectApp.service.impl;

import com.project.ProjectApp.entities.Contact;
import com.project.ProjectApp.entities.Query;
import com.project.ProjectApp.repository.ContactRepository;
import com.project.ProjectApp.repository.QueryRepository;
import com.project.ProjectApp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private QueryRepository queryRepository;

    @Override
    public void addContact(String name, String email, String subject, String massage) {
        Contact c = new Contact();
        c.setName(name);
        c.setEmail(email);
        c.setSubject(subject);
        c.setMassage(massage);
        contactRepository.save(c);
    }

    @Override
    public void addQuery(String first, String second, String phone, String service, String message) {
        Query q = new Query();
        q.setFirstname(first);
        q.setLastname(second);
        q.setService(service);
        q.setMassage(message);
        q.setPhone(phone);
        queryRepository.save(q);
    }

    @Override
    public Contact getAllContact() {
        return (Contact) contactRepository.findAll();
    }

    @Override
    public Query getAllQuery() {
        return (Query) queryRepository.findAll();
    }
}

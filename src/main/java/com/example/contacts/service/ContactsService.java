package com.example.contacts.service;

import com.example.contacts.data.Contacts;

import java.util.List;

public interface ContactsService {
    List<Contacts> findAll();

    Contacts findById(Long id);

    Contacts save(Contacts contacts);

    Contacts update(Contacts contacts);

    void delete(Long id);

}

package com.example.contacts.repository;

import com.example.contacts.data.Contacts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactsRepository {
    List<Contacts> findAll();

    Optional<Contacts> findById(Long id);

    Contacts save(Contacts contact);

    Contacts update(Contacts contact);

    void delete(Long id);
}

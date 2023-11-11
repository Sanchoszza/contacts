package com.example.contacts.repository;

import com.example.contacts.data.Contacts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class InMemoryContactsRepository implements ContactsRepository{

    private final List<Contacts> contacts = new ArrayList<>();

    @Override
    public List<Contacts> findAll() {
        return contacts;
    }

    @Override
    public Optional<Contacts> findById(Long id) {
        return contacts.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public Contacts save(Contacts contact) {
        contact.setId(System.currentTimeMillis());
        contacts.add(contact);
        return contact;
    }

    @Override
    public Contacts update(Contacts contact) {
        Contacts existedContact = findById(contact.getId()).orElse(null);
        if (existedContact != null){
            existedContact.setFirstName(contact.getFirstName());
            existedContact.setLastName(contact.getLastName());
            existedContact.setEmail(contact.getEmail());
            existedContact.setPhone(contact.getPhone());
        }
        return existedContact;
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(contacts::remove);
    }
}

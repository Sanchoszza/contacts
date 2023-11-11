package com.example.contacts.service;

import com.example.contacts.data.Contacts;
import com.example.contacts.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactsServiceImpl implements ContactsService{

    private final ContactsRepository contactsRepository;

    @Override
    public List<Contacts> findAll() {
        log.debug("Call findAll in ContactsServiceImpl");
        return contactsRepository.findAll();
    }

    @Override
    public Contacts findById(Long id) {
        log.debug("Call findById in ContactsServiceImpl");
        return contactsRepository.findById(id).orElse(null);
    }

    @Override
    public Contacts save(Contacts contacts) {
        log.debug("Call save in ContactsServiceImpl");
        return contactsRepository.save(contacts);
    }

    @Override
    public Contacts update(Contacts contacts) {
        log.debug("Call update in ContactsServiceImpl");
        return contactsRepository.update(contacts);
    }

    @Override
    public void delete(Long id) {
        log.debug("Call delete in ContactsServiceImpl");
        contactsRepository.delete(id);
    }

}

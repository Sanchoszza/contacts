package com.example.contacts.exception;

public class ContactsNotFoundException extends RuntimeException{
    public ContactsNotFoundException(String message) {
        super(message);
    }
}

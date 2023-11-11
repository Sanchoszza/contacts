package com.example.contacts.data;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
public class Contacts {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

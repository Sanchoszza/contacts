package com.example.contacts.repository.mapper;

import com.example.contacts.data.Contacts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsRowMapper implements RowMapper<Contacts> {
    @Override
    public Contacts mapRow(ResultSet rs, int rowNum) throws SQLException {

        Contacts contact = new Contacts();

        contact.setId(rs.getLong(Contacts.Fields.id));
        contact.setFirstName(rs.getString(Contacts.Fields.firstName));
        contact.setLastName(rs.getString(Contacts.Fields.lastName));
        contact.setEmail(rs.getString(Contacts.Fields.email));
        contact.setPhone(rs.getString(Contacts.Fields.phone));

        return contact;
    }
}

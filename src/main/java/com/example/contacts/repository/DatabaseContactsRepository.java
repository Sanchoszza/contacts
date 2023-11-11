package com.example.contacts.repository;

import com.example.contacts.data.Contacts;
import com.example.contacts.exception.ContactsNotFoundException;
import com.example.contacts.repository.mapper.ContactsRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class DatabaseContactsRepository implements ContactsRepository{


    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contacts> findAll() {

        log.debug("Colling DatabaseContactsRepository -> findAll()");

        String sql = "SELECT * FROM contact";
        return jdbcTemplate.query(sql, new ContactsRowMapper());
    }

    @Override
    public Optional<Contacts> findById(Long id) {
        log.debug("Colling DatabaseContactsRepository -> findById() with ID {}", id);
        String sql = "SELECT * FROM contact WHERE id = ?";
        Contacts contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new ContactsRowMapper(), 1))
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contacts save(Contacts contact) {
        log.debug("Colling DatabaseContactsRepository -> save() with Contact {}", contact);
        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contact (firstName, lastName, email, phone, id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone(), contact.getId());
        return contact;
    }

    @Override
    public Contacts update(Contacts contact) {
        log.debug("Colling DatabaseContactsRepository -> update() with Contact {}", contact);
        Contacts existedContact =findById(contact.getId()).orElse(null);
        if (existedContact != null){
            String sql = "UPDATE contact SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(),
                    contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }
        throw new ContactsNotFoundException("Task for update not found! ID: " + contact.getId());
    }

    @Override
    public void delete(Long id) {
        log.debug("Colling DatabaseContactsRepository -> delete() with ID {}", id);
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

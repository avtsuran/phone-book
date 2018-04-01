package com.lardi.repository;

import com.lardi.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findContactById(Long id);
    void deleteContactById(Long id);
    List<Contact> findContactsByUser_Id(Long id);
}

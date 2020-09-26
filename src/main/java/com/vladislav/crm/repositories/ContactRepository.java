package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByUserId(Long userId);

    boolean existsById(Long id);
}

package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByUserId(Long userId);

    @Query("SELECT user.id from Contact where id = :id")
    Optional<Long> findUserIdById(Long id);
}

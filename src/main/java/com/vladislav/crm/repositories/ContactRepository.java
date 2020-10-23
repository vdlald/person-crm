package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Collection<Contact> findAllByUserId(Long userId);

    @Query("SELECT user.id from Contact where id = :id")
    Optional<Long> findUserIdById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE contacts SET company_id = :companyId WHERE contact_id = :contactId", nativeQuery = true)
    void attachContactToCompany(@Param("contactId") Long contactId, @Param("companyId") Long companyId);
}

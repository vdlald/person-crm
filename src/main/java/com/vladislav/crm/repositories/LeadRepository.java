package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    Collection<Lead> findAllByStatusId(Long statusId);

    Collection<Lead> findAllByUserId(Long userId);

    @Query("select user.id from Lead where id = :id")
    Optional<Long> findUserIdById(Long id);

    @Query("select status.id from Lead where id = :id")
    Optional<Long> findStatusIdById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE leads SET status_id = :statusId WHERE lead_id = :leadId", nativeQuery = true)
    void moveLeadToStatus(@Param("leadId") Long leadId, @Param("statusId") Long statusId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO leads_contacts VALUES (:contactId, :leadId)", nativeQuery = true)
    void attachLeadToContact(Long leadId, Long contactId);
}

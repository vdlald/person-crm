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

    @Query("SELECT user.id from Lead where id = :id")
    Optional<Long> findUserIdById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE leads SET status_id = :statusId WHERE lead_id = :leadId", nativeQuery = true)
    void moveLeadToStatus(@Param("leadId") Long leadId, @Param("statusId") Long statusId);
}

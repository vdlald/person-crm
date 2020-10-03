package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    @Query("SELECT user.id from Lead where id = :id")
    Optional<Long> findUserIdById(Long id);
}

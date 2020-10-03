package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT user.id from Status where id = :id")
    Optional<Long> findUserIdById(Long id);
}

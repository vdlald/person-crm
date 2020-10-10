package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT pipeline.user.id from Status where id = :id")
    Optional<Long> findUserIdById(Long id);

    Collection<Status> findAllByPipelineId(Long pipelineId);
}

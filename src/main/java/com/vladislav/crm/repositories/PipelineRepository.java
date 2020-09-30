package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long> {

    List<Pipeline> findAllByUserId(Long userId);

    @Query("SELECT user.id from Pipeline where id = :id")
    Optional<Long> findUserIdById(Long id);
}

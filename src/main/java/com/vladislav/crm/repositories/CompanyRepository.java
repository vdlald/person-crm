package com.vladislav.crm.repositories;

import com.vladislav.crm.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT user.id from Company where id = :id")
    Optional<Long> findUserIdById(Long id);

    Collection<Company> findAllByUserId(Long userId);
}

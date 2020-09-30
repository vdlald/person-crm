package com.vladislav.crm.services.operations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
public abstract class AbstractReadOperation<T> implements ReadOperation<T> {

    private final JpaRepository<T, Long> repository;

    @Override
    public T execute(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

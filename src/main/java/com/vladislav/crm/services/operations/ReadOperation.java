package com.vladislav.crm.services.operations;

public interface ReadOperation<T> {
    T execute(Long id);
}

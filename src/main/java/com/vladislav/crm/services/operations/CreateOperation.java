package com.vladislav.crm.services.operations;

public interface CreateOperation<T> {
    T execute(T entity);
}

package com.vladislav.crm.services.operations;

public interface UpdateOperation<T> {
    T execute(T entity);
}

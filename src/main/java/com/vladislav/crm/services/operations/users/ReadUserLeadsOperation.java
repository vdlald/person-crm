package com.vladislav.crm.services.operations.users;

import com.vladislav.crm.entities.Lead;

import java.util.Collection;

public interface ReadUserLeadsOperation {
    Collection<Lead> execute(Long userId);
}

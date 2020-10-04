package com.vladislav.crm.services.operations.leads;

import com.vladislav.crm.entities.Lead;

import java.util.Collection;

public interface ReadStatusLeadsOperation {
    Collection<Lead> execute(Long userId);
}

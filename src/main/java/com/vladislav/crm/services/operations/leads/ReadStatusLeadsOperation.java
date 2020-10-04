package com.vladislav.crm.services.operations.leads;

import com.vladislav.crm.entities.Lead;

import java.util.List;

public interface ReadStatusLeadsOperation {
    List<Lead> execute(Long userId);
}

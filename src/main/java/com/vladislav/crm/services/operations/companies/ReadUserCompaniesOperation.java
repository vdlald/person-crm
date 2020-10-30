package com.vladislav.crm.services.operations.companies;

import com.vladislav.crm.entities.Company;

import java.util.Collection;

public interface ReadUserCompaniesOperation {
    Collection<Company> execute(Long userId);
}

package com.vladislav.crm.services.operations.companies;

import com.vladislav.crm.entities.Company;

public interface ReadCompanyOperation {
    Company execute(Long companyId);
}

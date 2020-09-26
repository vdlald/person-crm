package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.Company;

public interface ReadCompanyOperation {
    Company execute(Long companyId);
}

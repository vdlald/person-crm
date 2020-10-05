package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;

public interface UserCompaniesController {
    EntityModel<CompanyResponse> readCompany(Long companyId);
}

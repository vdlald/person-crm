package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.requests.CreateCompanyRequest;
import com.vladislav.crm.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UserCompaniesController {
    EntityModel<CompanyResponse> readCompany(Long companyId);

    EntityModel<CompanyResponse> createCompany(CreateCompanyRequest request);

    ResponseEntity<Void> deleteCompany(Long companyId);
}

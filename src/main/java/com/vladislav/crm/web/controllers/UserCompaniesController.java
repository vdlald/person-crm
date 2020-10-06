package com.vladislav.crm.web.controllers;

import com.vladislav.crm.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface UserCompaniesController {
    EntityModel<CompanyResponse> readCompany(Long companyId);

    ResponseEntity<Void> deleteCompany(Long companyId);
}

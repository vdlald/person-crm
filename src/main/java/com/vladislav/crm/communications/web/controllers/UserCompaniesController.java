package com.vladislav.crm.communications.web.controllers;

import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface UserCompaniesController {
    RepresentationModel<?> readUserCompanies();

    EntityModel<CompanyResponse> readCompany(Long companyId);

    EntityModel<CompanyResponse> createCompany(CreateCompanyRequest request);

    EntityModel<CompanyResponse> updateCompany(Long companyId, UpdateCompanyRequest request);

    ResponseEntity<Void> deleteCompany(Long companyId);
}

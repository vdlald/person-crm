package com.vladislav.crm.communications.web.controllers;

import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.communications.web.responses.ReadCompanyResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

public interface UserCompaniesController {
    RepresentationModel<?> readUserCompanies();

    EntityModel<ReadCompanyResponse> readCompany(Long companyId);

    EntityModel<ReadCompanyResponse> createCompany(CreateCompanyRequest request);

    EntityModel<ReadCompanyResponse> updateCompany(Long companyId, UpdateCompanyRequest request);

    ResponseEntity<Void> deleteCompany(Long companyId);
}

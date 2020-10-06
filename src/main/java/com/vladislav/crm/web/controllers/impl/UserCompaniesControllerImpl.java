package com.vladislav.crm.web.controllers.impl;

import com.vladislav.crm.web.controllers.UserCompaniesController;
import com.vladislav.crm.web.handlers.companies.CreateCompanyRequestHandler;
import com.vladislav.crm.web.handlers.companies.DeleteCompanyRequestHandler;
import com.vladislav.crm.web.handlers.companies.ReadCompanyRequestHandler;
import com.vladislav.crm.web.requests.CreateCompanyRequest;
import com.vladislav.crm.web.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCompaniesControllerImpl implements UserCompaniesController {

    private final ReadCompanyRequestHandler readCompanyRequestHandler;
    private final CreateCompanyRequestHandler createCompanyRequestHandler;
    private final DeleteCompanyRequestHandler deleteCompanyRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#companyId)")
    public EntityModel<CompanyResponse> readCompany(
            @PathVariable("id") Long companyId
    ) {
        return readCompanyRequestHandler.handle(companyId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<CompanyResponse> createCompany(
            @Valid @RequestBody CreateCompanyRequest request
    ) {
        return createCompanyRequestHandler.handle(request);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#companyId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCompany(
            @PathVariable("id") Long companyId
    ) {
        return deleteCompanyRequestHandler.handle(companyId);
    }
}

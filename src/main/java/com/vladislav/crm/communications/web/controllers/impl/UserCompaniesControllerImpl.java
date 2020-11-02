package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.controllers.UserCompaniesController;
import com.vladislav.crm.communications.web.adapters.companies.*;
import com.vladislav.crm.communications.web.requests.CreateCompanyRequest;
import com.vladislav.crm.communications.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.communications.web.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCompaniesControllerImpl implements UserCompaniesController {

    private final ReadUserCompaniesRequestHandlerAdapter readUserCompaniesRequestHandlerAdapter;
    private final ReadCompanyRequestHandlerAdapter readCompanyRequestHandlerAdapter;
    private final CreateCompanyRequestHandlerAdapter createCompanyRequestHandlerAdapter;
    private final UpdateCompanyRequestHandlerAdapter updateCompanyRequestHandlerAdapter;
    private final DeleteCompanyRequestHandlerAdapter deleteCompanyRequestHandlerAdapter;

    @Override
    @GetMapping("/")
    public RepresentationModel<?> readUserCompanies() {
        return readUserCompaniesRequestHandlerAdapter.handle();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#companyId) || " +
            "@userOwnsReadAllAuthorization.hasAuthorization()")
    public EntityModel<CompanyResponse> readCompany(
            @PathVariable("id") Long companyId
    ) {
        return readCompanyRequestHandlerAdapter.handle(companyId);
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<CompanyResponse> createCompany(
            @Valid @RequestBody CreateCompanyRequest request
    ) {
        return createCompanyRequestHandlerAdapter.handle(request);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#companyId)")
    public EntityModel<CompanyResponse> updateCompany(
            @PathVariable("id") Long companyId,
            @Valid @RequestBody UpdateCompanyRequest request
    ) {
        return updateCompanyRequestHandlerAdapter.handle(Pair.of(companyId, request));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#companyId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCompany(
            @PathVariable("id") Long companyId
    ) {
        return deleteCompanyRequestHandlerAdapter.handle(companyId);
    }
}

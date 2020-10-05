package com.vladislav.crm.web.controllers.impl;

import com.vladislav.crm.web.controllers.UserCompaniesController;
import com.vladislav.crm.web.handlers.companies.ReadCompanyRequestHandler;
import com.vladislav.crm.web.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCompaniesControllerImpl implements UserCompaniesController {

    private final ReadCompanyRequestHandler readCompanyRequestHandler;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@userOwnsCompanyAuthorization.hasAuthorization(#companyId)")
    public EntityModel<CompanyResponse> readCompany(
            @PathVariable("id") Long companyId
    ) {
        return readCompanyRequestHandler.handle(companyId);
    }
}

package com.vladislav.crm.web.assemblers;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.web.responses.CompanyResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface CompanyResponseAssembler extends RepresentationModelAssembler<Company, EntityModel<CompanyResponse>> {
}

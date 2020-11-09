package com.vladislav.crm.communications.web.assemblers;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.communications.web.responses.ReadCompanyResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ReadCompanyResponseAssembler extends RepresentationModelAssembler<Company, EntityModel<ReadCompanyResponse>> {
}

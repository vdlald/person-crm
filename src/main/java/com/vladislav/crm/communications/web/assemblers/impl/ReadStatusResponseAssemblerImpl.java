package com.vladislav.crm.communications.web.assemblers.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.entities.Status;
import com.vladislav.crm.communications.web.assemblers.ReadStatusResponseAssembler;
import com.vladislav.crm.communications.web.controllers.impl.UserStatusControllerImpl;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReadStatusResponseAssemblerImpl implements ReadStatusResponseAssembler {
    @Override
    public EntityModel<ReadStatusResponse> toModel(Status entity) {

        final List<Lead> leads = entity.getLeads();
        final BigDecimal saleTotal = leads.stream()
                .map(Lead::getSale)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        final ReadStatusResponse.Statistics statistics = new ReadStatusResponse.Statistics()
                .setLeadTotal(leads.size())
                .setSaleTotal(saleTotal);

        final ReadStatusResponse response = new ReadStatusResponse()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPipelineId(entity.getPipeline().getId())
                .setStatistics(statistics);

        final Link selfRel = linkTo(methodOn(UserStatusControllerImpl.class)
                .readStatus(entity.getId()))
                .withSelfRel();

        return EntityModel.of(response, selfRel);
    }
}

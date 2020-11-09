package com.vladislav.crm.communications.grpc.assemblers.impl;

import com.proto.leads.LeadResponse;
import com.vladislav.crm.communications.grpc.assemblers.LeadResponseAssembler;
import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.services.operations.contacts.GetContactsByLeadOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeadResponseAssemblerImpl implements LeadResponseAssembler {

    private final DateTimeFormatter formatter;
    private final GetContactsByLeadOperation getContactsByLeadOperation;

    @Override
    public LeadResponse toMessage(Lead lead) {
        long statusId = 0;
        if (lead.getStatus() != null) {
            statusId = lead.getStatus().getId();
        }

        final LeadResponse.Builder builder = LeadResponse.newBuilder()
                .setId(lead.getId())
                .setName(lead.getName())
                .setSale(lead.getSale().toString())
                .setUserId(lead.getUser().getId())
                .setStatusId(statusId)
                .setCreatedAt(formatter.format(lead.getCreatedAt()));

        if (lead.getUpdatedAt() != null) {
            builder.setUpdatedAt(formatter.format(lead.getUpdatedAt()));
        }

        final List<Long> contactsId = getContactsByLeadOperation.execute(lead)
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toUnmodifiableList());
        builder.addAllContactsId(contactsId);

        return builder.build();
    }
}

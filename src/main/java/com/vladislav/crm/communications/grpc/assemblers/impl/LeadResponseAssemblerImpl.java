package com.vladislav.crm.communications.grpc.assemblers.impl;

import com.proto.leads.LeadResponse;
import com.vladislav.crm.communications.grpc.assemblers.LeadResponseAssembler;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.Lead;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeadResponseAssemblerImpl implements LeadResponseAssembler {

    private final DateTimeFormatter formatter;

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

        int i = 0;
        for (Contact contact : lead.getContacts()) {
            Long contactId = contact.getId();
            builder.setContactsId(i, contactId);
            i++;
        }

        return builder.build();
    }
}

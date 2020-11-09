package com.vladislav.crm.services.operations.leads.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteLeadOperationImpl implements DeleteOperation<Lead> {

    private final LeadRepository leadRepository;
    private final ReadOperation<Lead> readLeadOperation;

    @Override
    public void execute(@NonNull Long id) {
        final Lead lead = readLeadOperation.execute(id);
        lead.getContacts().forEach(contact -> contact.removeLead(lead));
        leadRepository.delete(lead);
    }
}

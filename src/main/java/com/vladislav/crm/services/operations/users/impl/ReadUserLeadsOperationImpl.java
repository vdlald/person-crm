package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.repositories.LeadRepository;
import com.vladislav.crm.services.operations.users.ReadUserLeadsOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserLeadsOperationImpl implements ReadUserLeadsOperation {

    private final LeadRepository leadRepository;

    @Override
    public Collection<Lead> execute(@NonNull Long userId) {
        return leadRepository.findAllByUserId(userId);
    }
}

package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.entities.Status;
import com.vladislav.crm.repositories.StatusRepository;
import com.vladislav.crm.services.operations.DeleteOperation;
import com.vladislav.crm.services.operations.ReadOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteStatusOperationImpl implements DeleteOperation<Status> {

    private final ReadOperation<Status> readStatusOperation;
    private final StatusRepository statusRepository;

    @Override
    @Transactional
    public void execute(@NonNull Long id) {
        final Status status = readStatusOperation.execute(id);
        status.getLeads().forEach(lead -> lead.setStatusUnsafe(null));
        statusRepository.delete(status);
    }
}

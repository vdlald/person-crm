package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.repositories.StatusRepository;
import com.vladislav.crm.services.operations.statuses.GetUserIdByStatusIdOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserIdByStatusIdOperationImpl implements GetUserIdByStatusIdOperation {

    private final StatusRepository statusRepository;

    @Override
    public long execute(long statusId) {
        return statusRepository.findUserIdById(statusId).orElseThrow(EntityNotFoundException::new);
    }
}

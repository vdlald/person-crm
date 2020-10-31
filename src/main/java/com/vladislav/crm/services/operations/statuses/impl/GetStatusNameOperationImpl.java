package com.vladislav.crm.services.operations.statuses.impl;

import com.vladislav.crm.repositories.StatusRepository;
import com.vladislav.crm.services.operations.statuses.GetStatusNameOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetStatusNameOperationImpl implements GetStatusNameOperation {

    private final StatusRepository statusRepository;

    @Override
    public String execute(Long id) {
        return statusRepository.findStatusNameById(id).orElseThrow(EntityNotFoundException::new);
    }
}

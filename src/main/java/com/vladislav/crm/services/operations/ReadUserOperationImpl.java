package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadUserOperationImpl implements ReadUserOperation {

    private final UserRepository userRepository;

    @Override
    public User execute(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

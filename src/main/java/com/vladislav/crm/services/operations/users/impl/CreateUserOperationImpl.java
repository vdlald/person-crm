package com.vladislav.crm.services.operations.users.impl;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.repositories.UserRepository;
import com.vladislav.crm.services.operations.CreateOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserOperationImpl implements CreateOperation<User> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User execute(@NonNull User newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new EntityExistsException();
        }

        newUser.setInfo(new UserInfo());
        encodePassword(newUser);

        return userRepository.save(newUser);
    }

    private void encodePassword(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    }
}

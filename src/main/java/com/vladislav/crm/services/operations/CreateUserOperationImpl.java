package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.User;
import com.vladislav.crm.entities.UserInfo;
import com.vladislav.crm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserOperationImpl implements CreateUserOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User execute(User newUser) {
        checkExistUser(newUser.getUsername());

        newUser.setInfo(new UserInfo());
        encodePassword(newUser);

        return userRepository.save(newUser);
    }

    private void encodePassword(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    }

    private void checkExistUser(String username) {
        final Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            throw new EntityExistsException();
        }
    }
}

package com.vladislav.crm.controllers;

import com.vladislav.crm.controllers.responses.ReadUserContactsResponse;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.operations.ReadUserContactsOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsController {

    private final ReadUserContactsOperation readUserContactsOperation;

    @GetMapping(value = {"", "/"})
    public List<ReadUserContactsResponse> readUserContacts(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return readUserContactsOperation.execute(user.getId()).stream()
                .map(contact -> new ReadUserContactsResponse()
                        .setContactId(contact.getId())
                        .setName(contact.getName())
                        .setCompany(contact.getCompany())
                )
                .collect(Collectors.toUnmodifiableList());
    }
}

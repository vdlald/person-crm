package com.vladislav.crm.controllers;

import com.vladislav.crm.entities.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/{id}/contacts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserContactsController {

    @GetMapping
    public List<Contact> readUserContacts(@PathVariable("id") Long id) {
        return null;
    }
}

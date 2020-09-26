package com.vladislav.crm.services.operations.contacts;

import com.vladislav.crm.entities.Contact;

import java.util.List;

public interface ReadUserContactsOperation {
    List<Contact> execute(Long userId);
}

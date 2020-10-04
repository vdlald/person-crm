package com.vladislav.crm.services.operations.contacts;

import com.vladislav.crm.entities.Contact;

import java.util.Collection;

public interface ReadUserContactsOperation {
    Collection<Contact> execute(Long userId);
}

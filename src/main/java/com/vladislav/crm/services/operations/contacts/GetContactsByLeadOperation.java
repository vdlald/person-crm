package com.vladislav.crm.services.operations.contacts;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.Lead;

import java.util.List;

public interface GetContactsByLeadOperation {
    List<Contact> execute(Lead lead);
}

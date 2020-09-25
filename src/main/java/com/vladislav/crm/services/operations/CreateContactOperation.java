package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.Contact;

public interface CreateContactOperation {
    Contact execute(Contact contact);
}

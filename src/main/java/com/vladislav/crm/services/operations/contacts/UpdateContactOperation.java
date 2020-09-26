package com.vladislav.crm.services.operations.contacts;

import com.vladislav.crm.entities.Contact;

public interface UpdateContactOperation {
    Contact execute(Contact contact);
}

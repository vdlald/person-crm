package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.Contact;

public interface ReadContactOperation {
    Contact execute(Long contactId);
}

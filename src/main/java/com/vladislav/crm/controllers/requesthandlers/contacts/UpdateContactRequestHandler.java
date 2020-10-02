package com.vladislav.crm.controllers.requesthandlers.contacts;

import com.vladislav.crm.controllers.requesthandlers.RequestHandler;
import com.vladislav.crm.controllers.requests.UpdateContactRequest;
import com.vladislav.crm.controllers.responses.ReadContactResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateContactRequestHandler extends RequestHandler<Pair<Long, UpdateContactRequest>, EntityModel<ReadContactResponse>> {
}

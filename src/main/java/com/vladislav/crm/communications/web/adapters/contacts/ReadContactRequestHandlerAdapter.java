package com.vladislav.crm.communications.web.adapters.contacts;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.ReadContactResponse;
import org.springframework.hateoas.EntityModel;

public interface ReadContactRequestHandlerAdapter extends RequestHandlerAdapter<Long, EntityModel<ReadContactResponse>> {
}

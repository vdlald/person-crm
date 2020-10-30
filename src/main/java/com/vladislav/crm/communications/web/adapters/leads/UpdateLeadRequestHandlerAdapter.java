package com.vladislav.crm.communications.web.adapters.leads;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.UpdateLeadRequest;
import com.vladislav.crm.communications.web.responses.ReadLeadResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateLeadRequestHandlerAdapter extends RequestHandlerAdapter<Pair<Long, UpdateLeadRequest>, EntityModel<ReadLeadResponse>> {
}

package com.vladislav.crm.communications.web.adapters.statuses;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.UpdateStatusRequest;
import com.vladislav.crm.communications.web.responses.ReadStatusResponse;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;

public interface UpdateStatusRequestHandlerAdapter extends RequestHandlerAdapter<Pair<Long, UpdateStatusRequest>, EntityModel<ReadStatusResponse>> {
}

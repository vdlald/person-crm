package com.vladislav.crm.communications.web.adapters.users;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;

public interface UpdateCurrentUserInfoRequestHandlerAdapter extends RequestHandlerAdapter<UpdateCurrentUserInfoRequest, EntityModel<GetCurrentUserResponse>> {
}

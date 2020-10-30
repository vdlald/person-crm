package com.vladislav.crm.communications.web.handlers.users;

import com.vladislav.crm.communications.web.handlers.RequestHandler;
import com.vladislav.crm.communications.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.communications.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;

public interface UpdateCurrentUserInfoRequestHandler extends RequestHandler<UpdateCurrentUserInfoRequest, EntityModel<GetCurrentUserResponse>> {
}

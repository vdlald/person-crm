package com.vladislav.crm.web.handlers.users;

import com.vladislav.crm.web.handlers.RequestHandler;
import com.vladislav.crm.web.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.web.responses.GetCurrentUserResponse;
import org.springframework.hateoas.EntityModel;

public interface UpdateCurrentUserInfoRequestHandler extends RequestHandler<UpdateCurrentUserInfoRequest, EntityModel<GetCurrentUserResponse>> {
}

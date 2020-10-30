package com.vladislav.crm.communications.handlers.users;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.requests.UpdateCurrentUserInfoRequest;
import com.vladislav.crm.entities.User;

public interface UpdateCurrentUserInfoRequestHandler extends RequestHandler<UpdateCurrentUserInfoRequest, User> {
}

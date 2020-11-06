package com.vladislav.crm.communications.handlers.auth;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.communications.responses.AuthResponse;

import java.util.UUID;

public interface RefreshAccessTokenRequestHandler extends RequestHandler<UUID, AuthResponse> {
}

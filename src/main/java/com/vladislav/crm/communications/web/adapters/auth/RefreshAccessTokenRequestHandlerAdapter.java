package com.vladislav.crm.communications.web.adapters.auth;

import com.vladislav.crm.communications.web.adapters.RequestHandlerAdapter;
import com.vladislav.crm.communications.web.responses.AuthResponse;

import java.util.UUID;

public interface RefreshAccessTokenRequestHandlerAdapter extends RequestHandlerAdapter<UUID, AuthResponse> {
}

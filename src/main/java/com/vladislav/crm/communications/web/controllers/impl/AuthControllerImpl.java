package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.requests.AuthRequest;
import com.vladislav.crm.communications.web.adapters.auth.AuthRequestHandlerAdapter;
import com.vladislav.crm.communications.web.adapters.auth.RefreshAccessTokenRequestHandlerAdapter;
import com.vladislav.crm.communications.web.controllers.AuthController;
import com.vladislav.crm.communications.web.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthControllerImpl implements AuthController {

    private final AuthRequestHandlerAdapter authRequestHandlerAdapter;
    private final RefreshAccessTokenRequestHandlerAdapter refreshAccessTokenRequestHandlerAdapter;

    @Override
    @PostMapping("")
    public AuthResponse auth(@RequestBody AuthRequest authRequest) {
        return authRequestHandlerAdapter.handle(authRequest);
    }

    @GetMapping("")
    public AuthResponse refreshSession(@RequestParam String refreshToken) {
        return refreshAccessTokenRequestHandlerAdapter.handle(UUID.fromString(refreshToken));
    }
}

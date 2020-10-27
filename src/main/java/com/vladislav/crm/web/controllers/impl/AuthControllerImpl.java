package com.vladislav.crm.web.controllers.impl;

import com.vladislav.crm.web.controllers.AuthController;
import com.vladislav.crm.web.handlers.auth.AuthRequestHandler;
import com.vladislav.crm.web.requests.AuthRequest;
import com.vladislav.crm.web.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthControllerImpl implements AuthController {

    private final AuthRequestHandler authRequestHandler;

    @Override
    @PostMapping("")
    public AuthResponse auth(@RequestBody AuthRequest authRequest) {
        return authRequestHandler.handle(authRequest);
    }
}

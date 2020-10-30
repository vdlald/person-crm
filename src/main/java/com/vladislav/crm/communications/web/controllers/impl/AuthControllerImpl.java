package com.vladislav.crm.communications.web.controllers.impl;

import com.vladislav.crm.communications.web.controllers.AuthController;
import com.vladislav.crm.communications.web.adapters.auth.AuthRequestHandlerAdapter;
import com.vladislav.crm.communications.requests.AuthRequest;
import com.vladislav.crm.communications.web.responses.AuthResponse;
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

    private final AuthRequestHandlerAdapter authRequestHandlerAdapter;

    @Override
    @PostMapping("")
    public AuthResponse auth(@RequestBody AuthRequest authRequest) {
        return authRequestHandlerAdapter.handle(authRequest);
    }
}

package com.vladislav.crm.communications.handlers.auth;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.auth.impl.AuthRequestHandlerImpl;
import com.vladislav.crm.communications.requests.AuthRequest;
import com.vladislav.crm.communications.responses.AuthResponse;
import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.services.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

@ExtendWith(MockitoExtension.class)
public class TestAuthRequestHandler {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationManager authenticationManager;

    private AuthRequestHandler requestHandler;

    private User user;
    private RefreshToken refreshToken;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L).setUsername("demo");

        Mockito.when(userDetailsService.loadUserByUsername("demo"))
                .thenReturn(user);

        Mockito.when(tokenService.generateAccessToken(user))
                .thenReturn("token");

        refreshToken = new RefreshToken();
        Mockito.when(tokenService.generateRefreshToken(user))
                .thenReturn(refreshToken);

        requestHandler = new AuthRequestHandlerImpl(userDetailsService, tokenService, authenticationManager);
    }

    @Test
    public void testHandle() {
        final AuthResponse handle = requestHandler.handle(new AuthRequest().setUsername("demo"));

        Mockito.verify(authenticationManager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        Mockito.verify(userDetailsService).loadUserByUsername("demo");
        Mockito.verify(tokenService).generateAccessToken(user);
        Mockito.verify(tokenService).generateRefreshToken(user);

        Assertions.assertEquals("token", handle.getAccessToken());
        Assertions.assertEquals(refreshToken, handle.getRefreshToken());
    }
}

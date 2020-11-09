package com.vladislav.crm.communications.handlers.auth;

import com.vladislav.crm.TestUtils;
import com.vladislav.crm.communications.handlers.auth.impl.RefreshAccessTokenRequestHandlerImpl;
import com.vladislav.crm.communications.responses.AuthResponse;
import com.vladislav.crm.entities.RefreshToken;
import com.vladislav.crm.entities.User;
import com.vladislav.crm.functions.GenerateAccessTokenFunction;
import com.vladislav.crm.functions.GenerateRefreshTokenFunction;
import com.vladislav.crm.services.operations.refreshtokens.DeleteRefreshTokenOperation;
import com.vladislav.crm.services.operations.refreshtokens.ReadRefreshTokenOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TestRefreshAccessTokenRequestHandler {

    @Mock
    private ReadRefreshTokenOperation readRefreshTokenOperation;

    @Mock
    private DeleteRefreshTokenOperation deleteRefreshTokenOperation;

    @Mock
    private GenerateRefreshTokenFunction generateRefreshTokenFunction;

    @Mock
    private GenerateAccessTokenFunction generateAccessTokenFunction;

    private RefreshAccessTokenRequestHandler requestHandler;

    private User user;
    private RefreshToken refreshToken;

    @BeforeEach
    public void setUp() {
        user = TestUtils.getUser(1L);

        refreshToken = new RefreshToken().setToken(UUID.randomUUID())
                .setUser(user)
                .setValidUntil(LocalDateTime.now().plus(1, ChronoUnit.DAYS));

        requestHandler = new RefreshAccessTokenRequestHandlerImpl(
                readRefreshTokenOperation,
                deleteRefreshTokenOperation,
                generateRefreshTokenFunction,
                generateAccessTokenFunction
        );
    }

    @Test  // no errors
    public void testHandleCase1() {
        Mockito.when(readRefreshTokenOperation.execute(refreshToken.getToken())).thenReturn(refreshToken);
        Mockito.when(generateAccessTokenFunction.apply(user)).thenReturn("access");

        final RefreshToken newRefreshToken = new RefreshToken()
                .setToken(UUID.randomUUID())
                .setUserSafe(user);
        Mockito.when(generateRefreshTokenFunction.apply(user)).thenReturn(newRefreshToken);

        final AuthResponse handle = requestHandler.handle(refreshToken.getToken());

        Mockito.verify(readRefreshTokenOperation).execute(refreshToken.getToken());
        Mockito.verify(deleteRefreshTokenOperation).execute(refreshToken);
        Mockito.verify(generateAccessTokenFunction).apply(user);
        Mockito.verify(generateRefreshTokenFunction).apply(user);

        Assertions.assertEquals("access", handle.getAccessToken());
        Assertions.assertEquals(newRefreshToken, handle.getRefreshToken());
    }

    @Test  // refresh token not found
    public void testHandleCase2() {
        final UUID raw = refreshToken.getToken();

        Mockito.when(readRefreshTokenOperation.execute(raw)).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(AccessDeniedException.class, () -> requestHandler.handle(raw));

        Mockito.verify(generateAccessTokenFunction, Mockito.times(0)).apply(Mockito.any());
        Mockito.verify(generateRefreshTokenFunction, Mockito.times(0)).apply(Mockito.any());
    }

    @Test  // token expired
    public void testHandleCase3() {
        refreshToken.setValidUntil(LocalDateTime.now().minus(1, ChronoUnit.DAYS));
        Mockito.when(readRefreshTokenOperation.execute(refreshToken.getToken())).thenReturn(refreshToken);

        Assertions.assertThrows(AccessDeniedException.class, () -> requestHandler.handle(refreshToken.getToken()));

        Mockito.verify(deleteRefreshTokenOperation).execute(refreshToken);
        Mockito.verify(generateAccessTokenFunction, Mockito.times(0)).apply(Mockito.any());
        Mockito.verify(generateRefreshTokenFunction, Mockito.times(0)).apply(Mockito.any());
    }
}

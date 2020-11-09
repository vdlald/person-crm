package com.vladislav.crm.communications.grpc.interceptors;

import com.vladislav.crm.AppUtils;
import io.grpc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BasicAuthInterceptor implements ServerInterceptor {

    private final AuthenticationManager authenticationManager;
    private final Metadata.Key<String> authorizationKey;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler
    ) {
        final String authorization = metadata.get(authorizationKey);

        if (authorization == null) {
            return serverCallHandler.startCall(serverCall, metadata);
        }

        try {
            if (authorization.startsWith("Basic ")) {
                authUser(authorization);
            }
            return serverCallHandler.startCall(serverCall, metadata);
        } catch (BadCredentialsException e) {
            serverCall.close(Status.UNAUTHENTICATED.withDescription(AppUtils.getMessage(e)), new Metadata());
            return new ServerCall.Listener<>() {
            };
        }
    }

    private void authUser(String basicAuth) {
        final String base64Credentials = basicAuth.substring("Basic ".length());
        final String credentials;
        try {
            credentials = new String(Base64Utils.decodeFromString(base64Credentials), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("bad credentials");
        }
        final String[] values = credentials.split(":", 2);

        final String username = values[0];
        final String password = values[1];

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        final Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}

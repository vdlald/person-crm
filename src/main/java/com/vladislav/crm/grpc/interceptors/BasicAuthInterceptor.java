package com.vladislav.crm.grpc.interceptors;

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

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler
    ) {
        final Context context = Context.current();

        final String basicAuth = metadata.get(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER));

        if (basicAuth == null) {
            return Contexts.interceptCall(context, serverCall, metadata, serverCallHandler);
        }

        try {
            authUser(basicAuth);
        } catch (BadCredentialsException e) {
            serverCall.close(Status.UNAUTHENTICATED.withDescription(e.getLocalizedMessage()), new Metadata());
            return new ServerCall.Listener<>() {
            };
        }

        return Contexts.interceptCall(context, serverCall, metadata, serverCallHandler);
    }

    private void authUser(String basicAuth) {
        final String base64Credentials = basicAuth.substring("Basic".length()).trim();
        final String credentials = new String(Base64Utils.decodeFromString(base64Credentials), StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);

        final String username = values[0];
        final String password = values[1];

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        final Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}

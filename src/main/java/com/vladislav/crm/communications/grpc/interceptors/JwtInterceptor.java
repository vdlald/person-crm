package com.vladislav.crm.communications.grpc.interceptors;

import com.vladislav.crm.functions.AuthenticateByJwtFunction;
import io.grpc.*;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtInterceptor implements ServerInterceptor {

    private final AuthenticateByJwtFunction authenticateByJwtFunction;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> next
    ) {
        final String requestTokenHeader = metadata.get(Metadata.Key.of("jwt", Metadata.ASCII_STRING_MARSHALLER));
        try {
            authenticateByJwtFunction.apply(requestTokenHeader);
            return next.startCall(serverCall, metadata);
        } catch (ExpiredJwtException e) {
            serverCall.close(Status.UNAUTHENTICATED.withDescription("ExpiredJwtException"), new Metadata());
            return new ServerCall.Listener<>() {
            };
        } catch (Exception e) {
            serverCall.close(Status.UNAUTHENTICATED.withDescription(e.getMessage()), new Metadata());
            return new ServerCall.Listener<>() {
            };
        }
    }
}


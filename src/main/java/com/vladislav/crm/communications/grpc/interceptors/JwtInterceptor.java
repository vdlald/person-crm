package com.vladislav.crm.communications.grpc.interceptors;

import com.vladislav.crm.AppUtils;
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
    private final Metadata.Key<String> authorizationKey;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> next
    ) {
        final String requestTokenHeader = metadata.get(authorizationKey);
        try {
            authenticateByJwtFunction.apply(requestTokenHeader);
            return next.startCall(serverCall, metadata);
        } catch (ExpiredJwtException e) {
            serverCall.close(Status.UNAUTHENTICATED.withDescription("ExpiredJwtException"), new Metadata());
            return new ServerCall.Listener<>() {
            };
        } catch (Exception e) {
            serverCall.close(Status.UNAUTHENTICATED.withDescription(AppUtils.getMessage(e)), new Metadata());
            return new ServerCall.Listener<>() {
            };
        }
    }
}


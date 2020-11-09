package com.vladislav.crm.communications.grpc.interceptors;

import com.vladislav.crm.AppUtils;
import io.grpc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionHandler implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler
    ) {
        final ServerCall.Listener<ReqT> delegate = serverCallHandler.startCall(serverCall, metadata);

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(delegate) {
            @Override
            public void onHalfClose() {
                try {
                    super.onHalfClose();
                } catch (Exception e) {
                    e.printStackTrace();
                    final Status status = Status.INTERNAL.withCause(e).withDescription(AppUtils.getMessage(e));
                    serverCall.close(status, metadata);
                }
            }
        };
    }
}

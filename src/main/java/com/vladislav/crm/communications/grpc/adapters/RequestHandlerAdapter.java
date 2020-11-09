package com.vladislav.crm.communications.grpc.adapters;

import com.google.protobuf.GeneratedMessageV3;

public interface RequestHandlerAdapter<REQ extends GeneratedMessageV3, RES extends GeneratedMessageV3> {

    RES handle(REQ request);
}

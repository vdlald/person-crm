package com.vladislav.crm.grpc.assemblers;

import com.google.protobuf.GeneratedMessageV3;

public interface MessageAssembler<M extends GeneratedMessageV3, FROM> {

    M toMessage(FROM object);
}

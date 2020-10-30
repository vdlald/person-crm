package com.vladislav.crm.communications.grpc.adapters;

import com.google.protobuf.GeneratedMessageV3;

public interface RequestHandlerAdapter<REQ extends GeneratedMessageV3, RES extends GeneratedMessageV3> {

    // refactor candidate: может быть здесь стоит принимать и StreamObserver чтобы кидать в него эксепшены?
    // Или делать много interceptor-ов ?
    // Мне больше нравится вариант с interceptor
    RES handle(REQ request);
}

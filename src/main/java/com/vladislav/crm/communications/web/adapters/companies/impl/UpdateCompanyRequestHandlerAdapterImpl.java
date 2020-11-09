package com.vladislav.crm.communications.web.adapters.companies.impl;

import com.vladislav.crm.communications.handlers.companies.UpdateCompanyRequestHandler;
import com.vladislav.crm.communications.web.adapters.companies.UpdateCompanyRequestHandlerAdapter;
import com.vladislav.crm.communications.web.assemblers.ReadCompanyResponseAssembler;
import com.vladislav.crm.communications.web.requests.UpdateCompanyRequest;
import com.vladislav.crm.communications.web.responses.ReadCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component("webUpdateCompanyRequestHandlerAdapter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateCompanyRequestHandlerAdapterImpl implements UpdateCompanyRequestHandlerAdapter {

    private final ReadCompanyResponseAssembler readCompanyResponseAssembler;
    private final UpdateCompanyRequestHandler requestHandler;

    @Override
    public EntityModel<ReadCompanyResponse> handle(Pair<Long, UpdateCompanyRequest> requestPair) {
        return readCompanyResponseAssembler.toModel(
                requestHandler.handle(
                        Pair.of(requestPair.getFirst(), requestPair.getSecond().toCommunicationRequest())));
    }
}

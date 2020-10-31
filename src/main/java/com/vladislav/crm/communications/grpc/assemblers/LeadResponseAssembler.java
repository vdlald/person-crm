package com.vladislav.crm.communications.grpc.assemblers;

import com.proto.leads.LeadResponse;
import com.vladislav.crm.entities.Lead;

public interface LeadResponseAssembler extends MessageAssembler<LeadResponse, Lead> {
}

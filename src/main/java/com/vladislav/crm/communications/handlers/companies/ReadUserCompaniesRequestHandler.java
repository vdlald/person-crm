package com.vladislav.crm.communications.handlers.companies;

import com.vladislav.crm.communications.handlers.RequestHandler;
import com.vladislav.crm.entities.Company;

import java.util.Collection;

public interface ReadUserCompaniesRequestHandler extends RequestHandler<Void, Collection<Company>> {
    Collection<Company> handle();
}

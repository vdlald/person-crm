package com.vladislav.crm.events;

import com.vladislav.crm.entities.Lead;
import org.springframework.context.ApplicationEvent;

public class CreateLeadEvent extends ApplicationEvent {
    public CreateLeadEvent(Lead lead) {
        super(lead);
    }

    @Override
    public Lead getSource() {
        return (Lead) super.getSource();
    }
}

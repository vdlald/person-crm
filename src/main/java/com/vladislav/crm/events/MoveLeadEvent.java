package com.vladislav.crm.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class MoveLeadEvent extends ApplicationEvent {

    @Getter
    private final Long leadId;

    @Getter
    private final Long statusId;

    public MoveLeadEvent(Object source, Long leadId, Long statusId) {
        super(source);
        this.leadId = leadId;
        this.statusId = statusId;
    }
}

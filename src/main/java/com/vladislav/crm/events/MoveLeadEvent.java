package com.vladislav.crm.events;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Value
@EqualsAndHashCode(callSuper = true)
public class MoveLeadEvent extends ApplicationEvent {

    Long userId;
    Long leadId;
    Long prevStatusId;
    Long nextStatusId;

    public MoveLeadEvent(Object source, Long userId, Long leadId, Long prevStatusId, Long nextStatusId) {
        super(source);
        this.userId = userId;
        this.leadId = leadId;
        this.prevStatusId = prevStatusId;
        this.nextStatusId = nextStatusId;
    }
}

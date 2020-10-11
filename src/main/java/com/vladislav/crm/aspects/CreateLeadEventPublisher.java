package com.vladislav.crm.aspects;

import com.vladislav.crm.entities.Lead;
import com.vladislav.crm.events.CreateLeadEvent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateLeadEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Pointcut("execution(* com.vladislav.crm.services.operations.leads.impl.CreateLeadOperationImpl.execute(..))")
    public static void createLeadPoint() {
    }

    @AfterReturning(value = "createLeadPoint()", returning = "lead")
    public void createLead(Lead lead) {
        publishEvent(lead);
    }

    public void publishEvent(Lead lead) {
        publisher.publishEvent(new CreateLeadEvent(lead));
    }
}

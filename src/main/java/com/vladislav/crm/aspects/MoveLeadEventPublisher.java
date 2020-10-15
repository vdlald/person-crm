package com.vladislav.crm.aspects;

import com.vladislav.crm.events.MoveLeadEvent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveLeadEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Pointcut("execution(* com.vladislav.crm.services.operations.leads.impl.MoveLeadToStatusOperationImpl.execute())")
    public static void leadChangeStatusPoint() {
    }

    @Around(value = "leadChangeStatusPoint() && args(leadId, statusId)", argNames = "joinPoint,leadId,statusId")
    public Object moveLead(ProceedingJoinPoint joinPoint, Long leadId, Long statusId) throws Throwable {
        final Object proceed = joinPoint.proceed();
        publishEvent(leadId, statusId);
        return proceed;
    }

    public void publishEvent(Long leadId, Long statusId) {
        publisher.publishEvent(new MoveLeadEvent(this, leadId, statusId));
    }
}

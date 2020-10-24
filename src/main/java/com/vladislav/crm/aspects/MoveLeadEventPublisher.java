package com.vladislav.crm.aspects;

import com.vladislav.crm.events.MoveLeadEvent;
import com.vladislav.crm.services.operations.leads.GetStatusIdByLeadIdOperation;
import com.vladislav.crm.services.operations.leads.GetUserIdByLeadIdOperation;
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
    private final GetStatusIdByLeadIdOperation getStatusIdByLeadIdOperation;
    private final GetUserIdByLeadIdOperation getUserIdByLeadIdOperation;

    @Pointcut("execution(* com.vladislav.crm.services.operations.leads.impl.MoveLeadToStatusOperationImpl.execute())")
    public static void leadChangeStatusPoint() {
    }

    @Around(value = "leadChangeStatusPoint() && args(leadId, statusId)", argNames = "joinPoint,leadId,statusId")
    public Object moveLead(ProceedingJoinPoint joinPoint, Long leadId, Long statusId) throws Throwable {
        final Long prevStatusId = getStatusIdByLeadIdOperation.execute(leadId);

        final Object proceed = joinPoint.proceed();

        final MoveLeadEvent event = new MoveLeadEvent(
                this, getUserIdByLeadIdOperation.execute(leadId), leadId, prevStatusId, statusId);
        publisher.publishEvent(event);
        return proceed;
    }
}

package com.vladislav.crm.aspects;

import com.vladislav.crm.authorizations.UserOwnsStatusAuthorization;
import com.vladislav.crm.web.requests.CreateLeadRequest;
import com.vladislav.crm.web.requests.UpdateLeadRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeadRequestHandlerAspect {

    private final UserOwnsStatusAuthorization userOwnsStatusAuthorization;

    @Pointcut("execution(* com.vladislav.crm.web.handlers.leads.UpdateLeadRequestHandler.handle(..))")
    public static void webUpdateHandlePoint() {
    }

    @Pointcut("execution(* com.vladislav.crm.web.handlers.leads.CreateLeadRequestHandler.handle(..))")
    public static void webCreateHandlePoint() {
    }

    @Around("webUpdateHandlePoint() && args(requestPair)")
    public Object checkUserOwnsStatusAuthorization(
            ProceedingJoinPoint joinPoint, Pair<Long, UpdateLeadRequest> requestPair
    ) throws Throwable {
        final Long statusId = requestPair.getSecond().getStatusId();
        if (statusId == null || userOwnsStatusAuthorization.hasAuthorization(statusId)) {
            return joinPoint.proceed();
        } else {
            throw getAccessDeniedException();
        }
    }

    @Around("webCreateHandlePoint() && args(request)")
    public Object checkUserOwnsStatusAuthorization(
            ProceedingJoinPoint joinPoint, CreateLeadRequest request
    ) throws Throwable {
        final Long statusId = request.getStatusId();
        if (userOwnsStatusAuthorization.hasAuthorization(statusId)) {
            return joinPoint.proceed();
        } else {
            throw getAccessDeniedException();
        }
    }

    private static AccessDeniedException getAccessDeniedException() {
        return new AccessDeniedException("this status does not belong to the current user");
    }
}

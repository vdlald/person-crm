package com.vladislav.crm.aspects;

import com.vladislav.crm.authorizations.UserOwnsPipelineAuthorization;
import com.vladislav.crm.web.requests.CreateStatusRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatusRequestHandlerAspect {

    private final UserOwnsPipelineAuthorization userOwnsPipelineAuthorization;

    @Pointcut("execution(* com.vladislav.crm.web.handlers.statuses.CreateStatusRequestHandler.handle(..))")
    public static void webCreateHandlePoint() {
    }

    @Around("webCreateHandlePoint() && args(request)")
    public Object checkUserOwnsPipelineAuthorization(
            ProceedingJoinPoint joinPoint, CreateStatusRequest request
    ) throws Throwable {
        final Long pipelineId = request.getPipelineId();
        if (userOwnsPipelineAuthorization.hasAuthorization(pipelineId)) {
            return joinPoint.proceed();
        } else {
            throw getAccessDeniedException();
        }
    }

    private static AccessDeniedException getAccessDeniedException() {
        return new AccessDeniedException("this pipeline does not belong to the current user");
    }
}

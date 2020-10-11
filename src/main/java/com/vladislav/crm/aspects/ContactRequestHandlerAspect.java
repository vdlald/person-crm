package com.vladislav.crm.aspects;

import com.vladislav.crm.authorizations.UserOwnsCompanyAuthorization;
import com.vladislav.crm.web.requests.CreateContactRequest;
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
public class ContactRequestHandlerAspect {

    private final UserOwnsCompanyAuthorization userOwnsCompanyAuthorization;

    @Pointcut("execution(* com.vladislav.crm.web.handlers.contacts.CreateContactRequestHandler.handle(..))")
    public static void webCreateHandlePoint() {
    }

    @Around("webCreateHandlePoint() && args(request)")
    public Object checkUserOwnsCompanyAuthorization(
            ProceedingJoinPoint joinPoint, CreateContactRequest request
    ) throws Throwable {
        final Long companyId = request.getCompanyId();
        if (companyId == null || userOwnsCompanyAuthorization.hasAuthorization(companyId)) {
            return joinPoint.proceed();
        } else {
            throw getAccessDeniedException();
        }
    }

    private static AccessDeniedException getAccessDeniedException() {
        return new AccessDeniedException("this company does not belong to the current user");
    }
}

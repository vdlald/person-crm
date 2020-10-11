package com.vladislav.crm.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserOwnsEntityAuthorizationAspect {

    @Pointcut("execution(* com.vladislav.crm.authorizations.UserOwnsEntityAuthorization.hasAuthorization(..))")
    public static void hasAuthorizationPoint() {
    }

    // вопрос: нормально ли делать таким образом ?
    @Around("hasAuthorizationPoint() && args(id)")
    public Object whenNullReturnTrue(
            ProceedingJoinPoint joinPoint, Long id
    ) throws Throwable {
        if (id == null) {
            return true;
        } else {
            return joinPoint.proceed();
        }
    }
}

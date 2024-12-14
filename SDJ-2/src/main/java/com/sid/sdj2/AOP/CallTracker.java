package com.sid.sdj2.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CallTracker {


    @Around("within(com.sid.sdj2.services.StudentService)||within(com.sid.sdj2.repo.StudentRepository)")
    public Object logBeforeAfterMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String name = proceedingJoinPoint.getSignature().getName();
        System.out.println("MethodStart:" + name);
        Object returnVal = proceedingJoinPoint.proceed();
        System.out.println("MethodEnd:" + name);
        return returnVal;
    }
}

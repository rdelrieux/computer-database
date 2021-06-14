package com.excilys.computerDatabase.logger.time;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.logger.LoggerCdb;

@Aspect
@Component
public class MeasurementAspect {
    @Around("@annotation(Timed)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LoggerCdb.logTime(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}

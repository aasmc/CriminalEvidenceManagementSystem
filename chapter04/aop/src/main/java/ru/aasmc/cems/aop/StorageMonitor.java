package ru.aasmc.cems.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StorageMonitor {
    private static final Logger logger = LoggerFactory.getLogger(StorageMonitor.class);

    @Before("ru.aasmc.cems.aop.PointcutContainer.proxyBubu()")
    public void bubuHappens(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        var className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("[bubuHappens] ---> BUBU when calling: {}.{}", className, methodName);
    }

    @Before("within(@ru.aasmc.cems.services.CemsService *)")
    public void beforeAnyWithClassAnnotation(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        logger.info("[beforeAnyWithClassAnnotation]: ---> Method {} is about to be called", methodName);
    }
}

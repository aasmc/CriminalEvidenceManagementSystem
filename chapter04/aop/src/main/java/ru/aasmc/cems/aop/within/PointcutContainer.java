package ru.aasmc.cems.aop.within;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointcutContainer {

    @Pointcut("within(ru.aasmc.cems.repos.PersonRepo+)")
    public void onAnyRepoMethod() {

    }

    @Pointcut("within(ru.aasmc.cems.services.*)")
    public void onAnyServiceMethod() {
    }
}

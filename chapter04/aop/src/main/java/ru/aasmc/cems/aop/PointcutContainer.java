package ru.aasmc.cems.aop;

import org.aspectj.lang.annotation.Pointcut;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.services.PersonService;

public class PointcutContainer {

    @Pointcut("execution(* ru.aasmc.cems.*.*PersonRepo+.findBy*(..))")
    public void repoFind() {
    }

    @Pointcut("execution(* ru.aasmc.cems.services.*Service+.findBy*(..))")
    public void serviceFind() {
    }

    @Pointcut(value = "execution(* ru.aasmc.cems.services.*Service+.save(..)) " +
            "&& args(person) && target(service)", argNames = "person,service")
    public void beforeSavePointcut(Person person, PersonService service) {
    }

    @Pointcut("execution(* ru.aasmc.cems.services.*Service+.save*(..))")
    public void proxyBubu() {
    }
}

package ru.aasmc.cems.aop.within;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
        "ru.aasmc.cems.services",
        "ru.aasmc.cems.aop.within",
        "ru.aasmc.cems.repos"
})
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class WithinConfig {
}

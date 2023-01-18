package ru.aasmc.cems.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"ru.aasmc.cems.aop", "ru.aasmc.cems.repos"})
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AopConfig {
}

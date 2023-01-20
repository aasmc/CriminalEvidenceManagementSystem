package ru.aasmc.cems.tx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"ru.aasmc.cems.repos", "ru.aasmc.cems.tx.services"})
@EnableTransactionManagement
public class AppConfig {
}

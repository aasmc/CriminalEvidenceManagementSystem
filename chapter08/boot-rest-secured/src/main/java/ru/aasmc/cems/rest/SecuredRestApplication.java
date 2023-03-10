package ru.aasmc.cems.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SecuredRestApplication {
    private static Logger logger = LoggerFactory.getLogger(SecuredRestApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SecuredRestApplication.class, args);
        ctx.registerShutdownHook();
        logger.info("Application started...");
    }
}

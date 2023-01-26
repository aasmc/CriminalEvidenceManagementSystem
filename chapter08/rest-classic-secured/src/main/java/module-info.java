module ru.aasmc.cems.classic.rest.sec {
    requires ru.aasmc.cems.dao;
    requires ru.aasmc.cems.dj;

    requires java.naming;

    requires spring.core;
    requires spring.webmvc;
    requires spring.context;
    requires spring.web;

    requires javax.servlet.api;
    requires org.slf4j;
    requires java.annotation;
    requires com.zaxxer.hikari;
    requires spring.beans;
    requires java.sql;

    requires thymeleaf.spring5;
    requires thymeleaf;
    requires com.fasterxml.jackson.databind;
    requires spring.security.web;
    requires spring.security.core;
    requires spring.security.config;

    exports ru.aasmc.cems.rest.sec.config;
    exports ru.aasmc.cems.rest.sec.controllers;
    exports ru.aasmc.cems.rest.sec.problem;
    opens ru.aasmc.cems.rest.sec.config to spring.core;
}
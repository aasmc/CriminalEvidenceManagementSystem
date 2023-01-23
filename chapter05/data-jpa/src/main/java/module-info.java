module ru.aasmc.cems.dj {
    requires ru.aasmc.cems.dao;
    requires org.hibernate.orm.core;

    requires org.apache.commons.lang3;
    requires java.sql;
    requires org.slf4j;
    requires java.naming;
    requires java.annotation;

    requires spring.data.jpa;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.jdbc;
    requires spring.tx;
    requires spring.orm;
    requires java.persistence;
    requires spring.data.commons;
    requires ojdbc8;

    exports ru.aasmc.cems.dj;
    exports ru.aasmc.cems.dj.repos;
    exports ru.aasmc.cems.dj.problem;
    exports ru.aasmc.cems.dj.services;
    exports ru.aasmc.cems.dj.services.wrappers;
}
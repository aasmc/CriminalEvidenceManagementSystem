/**
 * Created by iuliana.cosmina on 21/01/19.
 */
module com.apress.cems.aop {
    exports ru.aasmc.cems.aop.exception;
    requires ru.aasmc.cems.dao;
    requires ru.aasmc.cems.repos;
    requires ru.aasmc.cems.services;
    requires org.apache.commons.lang3;
    requires java.sql;
    requires spring.jdbc;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires org.aspectj.weaver;
    requires org.slf4j;
}
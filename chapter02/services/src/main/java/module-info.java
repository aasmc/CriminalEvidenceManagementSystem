module ru.aasmc.cems.services {
    requires ru.aasmc.cems.dao;
    requires org.apache.commons.lang3;
    requires java.sql;
    requires ru.aasmc.cems.repos;
    requires org.slf4j;
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    exports ru.aasmc.cems.services;
    exports ru.aasmc.cems.services.simpleimpl;
}
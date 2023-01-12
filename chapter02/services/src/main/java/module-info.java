module com.apress.cems.pojos {
    requires ru.aasmc.cems.dao;
    requires org.apache.commons.lang3;
    requires java.sql;
    requires ru.aasmc.cems.repos;

    exports ru.aasmc.cems.services;
    exports ru.aasmc.cems.services.simpleimpl;
}
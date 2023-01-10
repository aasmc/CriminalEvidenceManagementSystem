module ru.aasmc.cems.repos {
    requires ru.aasmc.cems.dao;
    requires spring.context;
    requires spring.beans;
    requires spring.jdbc;
    requires spring.tx;  // needed only for testing @Transactional on repo
    requires java.sql;
    requires org.apache.commons.lang3;

    exports ru.aasmc.cems.repos;
    exports ru.aasmc.cems.repos.util;
    exports ru.aasmc.cems.repos.jdbcimpl;
}
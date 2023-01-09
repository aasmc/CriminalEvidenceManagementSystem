module ru.aasmc.cems.dao {
    requires java.persistence;
    requires spring.context;
    requires java.validation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    exports ru.aasmc.cems.dao;
    exports ru.aasmc.cems.dto;
    exports ru.aasmc.cems.util;
    exports ru.aasmc.cems.ex;
}
package ru.aasmc.cems.aop.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestDbConfig {

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        var builder = new EmbeddedDatabaseBuilder();
        var db = builder
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("db/schema.sql")
                .addScript("db/test-data.sql")
                .build();
        return db;
    }

}

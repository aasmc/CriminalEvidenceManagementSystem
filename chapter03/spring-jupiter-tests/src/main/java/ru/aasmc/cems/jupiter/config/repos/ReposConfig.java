package ru.aasmc.cems.jupiter.config.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"ru.aasmc.cems.repos", "ru.aasmc.cems.jupiter.config"})
public class ReposConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate userJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}

package ru.aasmc.cems.jupiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.aasmc.cems.ex.ConfigurationException;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Profile("prod")
public class ProdDataSourceConfig {

    @Bean("connectionProperties")
    Properties connectionProperties() {
        try {
            return PropertiesLoaderUtils.loadProperties(
                    new ClassPathResource("db/prod-datasource.properties")
            );
        } catch (IOException e) {
            throw new ConfigurationException("Could not retrieve connection properties!", e);
        }
    }

    @Bean
    public DataSource dataSource() {
        final Properties props = connectionProperties();
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName(props.getProperty("db.driverClassName"));
        ds.setUrl(props.getProperty("db.url"));
        ds.setUsername(props.getProperty("db.username"));
        ds.setPassword(props.getProperty("db.password"));
        return ds;
    }

}

package ru.aasmc.cems.dj;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import ru.aasmc.cems.ex.ConfigurationException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class OracleDataSource {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        Properties connectionProperties = connectionProperties();
        hibernateProperties.put("hibernate.dialect", connectionProperties.getProperty("dialect"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", connectionProperties.getProperty("hbm2ddl"));

        hibernateProperties.put("hibernate.format_sql", true);
        hibernateProperties.put("hibernate.use_sql_comments", true);
        hibernateProperties.put("hibernate.show_sql", true);
        return hibernateProperties;
    }

    @Bean("connectionProperties")
    Properties connectionProperties() {
        try {
            return PropertiesLoaderUtils.loadProperties(
                    new ClassPathResource("prod-database.properties")
            );
        } catch (IOException e) {
            throw new ConfigurationException("Couldn't retrieve connection properties!", e);
        }
    }

    @Bean
    public DataSource dataSource() {
        try {
            final Properties props = connectionProperties();
            OracleConnectionPoolDataSource ods = new OracleConnectionPoolDataSource();
            ods.setNetworkProtocol("tcp");
            ods.setDriverType(props.getProperty("driverType"));
            ods.setServerName(props.getProperty("serverName"));
            ods.setDatabaseName(props.getProperty("serviceName"));
            ods.setPortNumber(Integer.parseInt(props.getProperty("port")));
            ods.setUser(props.getProperty("user"));
            ods.setPassword(props.getProperty("password"));
            return ods;
        } catch (SQLException e) {
            throw new ConfigurationException("Could not configure Oracle database!", e);
        }
    }
}

























